package generator;

import com.itextpdf.text.DocumentException;
import models.Book;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PDFBookSignatureGeneratorTest {

    @Test
    void shouldThrowNullPointerException() throws DocumentException {
        assertThrows(NullPointerException.class, () ->
                PDFBookSignatureGenerator.generateBookSignatures(null)
        );
    }

    @Test
    void shouldThrowIllegalArgumentException() throws DocumentException {
        Book bookWithEmptyListOfSignatures = new Book.Builder()
                .setTitle("Title")
                .setPublishedYear(1996)
                .setSignatures(new ArrayList<>())
                .build();

        assertThrows(IllegalArgumentException.class, () ->
                PDFBookSignatureGenerator.generateBookSignatures(bookWithEmptyListOfSignatures)
        );
    }
}