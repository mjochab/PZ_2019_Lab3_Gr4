package io.library.signaturegenerator;

import com.itextpdf.text.DocumentException;
import io.library.signaturegenerator.generator.PDFBookSignatureGenerator;
import io.library.signaturegenerator.models.Book;

import java.io.*;
import java.util.Arrays;

public class MainTestClass {

    public static void main(String[] args) throws DocumentException {
        try (FileOutputStream stream = new FileOutputStream(new File("myFile.pdf"))) {
            ByteArrayOutputStream outputStream = PDFBookSignatureGenerator.generateBookSignatures(getSimpleBook());
            outputStream.writeTo(stream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Book getSimpleBook() {
        return new Book.Builder()
                .setAuthor("Adam Krupa")
                .setPublishedCompany("Febrit.com")
                .setPublishedYear(2019)
                .setSignatures(Arrays.asList("Sign123", "Sign456", "Sign6587", "Sign11245234", "Sign17564", "Sign4562", "Sign15463"))
                .setTitle("Nowy rozdział")
                .build();
    }
}
