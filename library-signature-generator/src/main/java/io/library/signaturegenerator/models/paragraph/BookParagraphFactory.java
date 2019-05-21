package io.library.signaturegenerator.models.paragraph;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;

import java.util.Objects;

public class BookParagraphFactory {

    private static final Font HELVETICA_14 = FontFactory.getFont(FontFactory.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED, 14, Font.UNDEFINED, BaseColor.BLACK);
    private static final Font HELVETICA_16_BOLD = FontFactory.getFont(FontFactory.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED, 16, Font.BOLD, BaseColor.BLACK);
    private static final Font HELVETICA_10 = FontFactory.getFont(FontFactory.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED, 10, Font.UNDEFINED, BaseColor.BLACK);

    public static BookParagraph createParagraph(ParagraphType type, String content) {

        switch (type) {
            case TITLE: return new TitleParagraph(Element.ALIGN_LEFT, HELVETICA_16_BOLD, Objects.requireNonNull(content));
            case AUTHOR: return new AuthorParagraph(Element.ALIGN_LEFT, HELVETICA_14, content);
            case PUBISHING_COMPANY: return new PublishingCompanyParagraph(Element.ALIGN_LEFT, HELVETICA_14, content);
            case YEAR_OF_PUBLICATION: return new YearOfPublicationParagraph(Element.ALIGN_LEFT, HELVETICA_14, Objects.requireNonNull(content));
            case SIGNATURE: return new SignatureParagraph(Element.ALIGN_LEFT, HELVETICA_10, Objects.requireNonNull(content));

            default: throw new IllegalArgumentException("Paragraph type is unknown");
        }
    }
}
