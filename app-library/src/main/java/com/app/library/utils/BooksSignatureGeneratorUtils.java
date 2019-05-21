package com.app.library.utils;

import com.app.library.model.Book;
import com.itextpdf.text.DocumentException;
import io.library.signaturegenerator.generator.PDFBookSignatureGenerator;
import javafx.stage.FileChooser;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BooksSignatureGeneratorUtils {

    public void showWindowToSaveBookSignatures(Book book) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Zapisz wygenerowane przez system sygnatury");
        fileChooser.setInitialFileName(String.format("%s.pdf", book.getName()));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));
        File outputFile = fileChooser.showSaveDialog(null);

        if (outputFile != null) {

            try (final FileOutputStream fileStream = new FileOutputStream(outputFile)) {
                io.library.signaturegenerator.models.Book bookToGenerate = getBookToGenerateSignatures(book);
                final ByteArrayOutputStream outputStream = PDFBookSignatureGenerator.generateBookSignatures(bookToGenerate);
                outputStream.writeTo(fileStream);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }
        }
    }

    private io.library.signaturegenerator.models.Book getBookToGenerateSignatures(Book book) {
        List<String> signatures = book.getBookUnits()
                .stream()
                .map(el -> el.getSignature().toString()).collect(Collectors.toList());

        return new io.library.signaturegenerator.models.Book.Builder()
                .setAuthor(book.getAuthor())
                .setPublishedCompany(book.getPublishingCompany())
                .setPublishedYear(book.getYearOfPublication())
                .setTitle(book.getName())
                .setSignatures(signatures)
                .build();
    }
}
