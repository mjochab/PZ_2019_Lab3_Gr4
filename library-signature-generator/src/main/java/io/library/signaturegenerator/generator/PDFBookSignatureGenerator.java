package io.library.signaturegenerator.generator;

import com.google.common.collect.ImmutableList;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;
import io.library.signaturegenerator.models.Book;
import io.library.signaturegenerator.models.paragraph.BookParagraphFactory;
import io.library.signaturegenerator.models.paragraph.ParagraphType;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PDFBookSignatureGenerator {
    private PDFBookSignatureGenerator() {
        throw new AssertionError();
    }

    /**
     * This method is used to generate pdf document.
     * Method receives book with list of signatures and
     * returns document with two columns on every page
     * with the same book with single signature (from list)
     * @param book
     * @return ByteArrayOutputStream This returns result
     * which we can use to save as a file or to return in http response etc.
     * @throws DocumentException
     */
    public static ByteArrayOutputStream generateBookSignatures(Book book) throws DocumentException {
        if (book == null) {
            throw new NullPointerException("Book can not be null");
        }
        if (book.getSignatures() == null || book.getSignatures().size() == 0) {
            throw new IllegalArgumentException("Book signatures can not be empty or null");
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);

        document.open();
        ColumnText columnText = new ColumnText(writer.getDirectContent());

        getBookParagraphs(book).stream()
                .filter(p -> p != null)
                .forEach(columnText::addElement);

        Rectangle[] columns = getDocumentColumns();
        int columnIndex = 0;
        int status = ColumnText.START_COLUMN;
        while (ColumnText.hasMoreText(status)) {
            columnText.setSimpleColumn(columns[columnIndex]);
            status = columnText.go();
            if (++columnIndex == 2) {
                document.newPage();
                columnIndex = 0;
            }
        }
        document.close();

        return outputStream;
    }

    private static ImmutableList<Paragraph> getBookParagraphs(Book book) {
        return book.getSignatures().stream()
                .collect(Collectors.toMap(String::toString, item -> book))
                .entrySet()
                .stream()
                .map(PDFBookSignatureGenerator::getSingleSignatureParagraphs)
                .flatMap(List::stream)
                .collect(ImmutableList.toImmutableList());
    }

    private static ImmutableList<Paragraph> getSingleSignatureParagraphs(Map.Entry<String, Book> entry) {
        String bookSignature = entry.getKey();
        Book book = entry.getValue();

        Paragraph title = BookParagraphFactory
                .createParagraph(ParagraphType.TITLE, book.getTitle()).getParagraph();

        Paragraph author = BookParagraphFactory
                .createParagraph(ParagraphType.AUTHOR, book.getAuthor()).getParagraph();

        Paragraph publishingCompany = BookParagraphFactory
                .createParagraph(ParagraphType.PUBISHING_COMPANY, book.getPublishedCompany()).getParagraph();

        Paragraph yearOfPublication = BookParagraphFactory
                .createParagraph(ParagraphType.YEAR_OF_PUBLICATION, book.getPublishedYear().toString()).getParagraph();

        Paragraph signature = BookParagraphFactory
                .createParagraph(ParagraphType.SIGNATURE, bookSignature).getParagraph();

        return ImmutableList.of(title, author, publishingCompany, yearOfPublication, signature);
    }

    private static Rectangle[] getDocumentColumns() {
        return new Rectangle[]{
                new Rectangle(36, 36, 290, 806), new Rectangle(305, 36, 559, 806)
        };
    }
}
