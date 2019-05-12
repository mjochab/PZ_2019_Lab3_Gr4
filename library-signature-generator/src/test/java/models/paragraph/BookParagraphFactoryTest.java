package models.paragraph;

import com.itextpdf.text.Paragraph;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookParagraphFactoryTest {

    private static final String TEST_CONTENT = "TEST_CONTENT";

    @Nested
    class AuthorParagraph {

        @Test
        void shouldReturnNullParagraph() {
            Paragraph paragraph = BookParagraphFactory.createParagraph(ParagraphType.AUTHOR, null).getParagraph();
            assertNull(paragraph);
        }

        @Test
        void shouldReturnParagraphWithTestContent() {
            Paragraph paragraph = BookParagraphFactory.createParagraph(ParagraphType.AUTHOR, TEST_CONTENT).getParagraph();

            String expectedContent = "TEST_CONTENT";
            assertEquals(expectedContent, paragraph.getContent());
        }
    }

    @Nested
    class TitleParagraph {

        @Test
        void shouldThrowNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    BookParagraphFactory.createParagraph(ParagraphType.TITLE, null).getParagraph()
            );
        }

        @Test
        void shouldReturnParagraphWithTestContent() {
            Paragraph paragraph = BookParagraphFactory.createParagraph(ParagraphType.TITLE, TEST_CONTENT).getParagraph();

            String expectedContent = "TEST_CONTENT";
            assertEquals(expectedContent, paragraph.getContent());
        }
    }

    @Nested
    class PublishingCompanyParagraph {

        @Test
        void shouldReturnNullParagraph() {
            Paragraph paragraph = BookParagraphFactory.createParagraph(ParagraphType.PUBISHING_COMPANY, null).getParagraph();
            assertNull(paragraph);
        }

        @Test
        void shouldReturnParagraphWithTestContent() {
            Paragraph paragraph = BookParagraphFactory.createParagraph(ParagraphType.PUBISHING_COMPANY, TEST_CONTENT).getParagraph();

            String expectedContent = "Wydawnictwo: TEST_CONTENT";
            assertEquals(expectedContent, paragraph.getContent());
        }
    }

    @Nested
    class YearOfPublicationParagraph {

        @Test
        void shouldThrowNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    BookParagraphFactory.createParagraph(ParagraphType.YEAR_OF_PUBLICATION, null).getParagraph()
            );
        }

        @Test
        void shouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () ->
                    BookParagraphFactory.createParagraph(ParagraphType.YEAR_OF_PUBLICATION, TEST_CONTENT).getParagraph()
            );
        }

        @Test
        void shouldReturnParagraphWithTestContent() {
            Paragraph paragraph = BookParagraphFactory.createParagraph(ParagraphType.YEAR_OF_PUBLICATION, "1996").getParagraph();

            String expectedContent = "Rok publikacji: 1996";
            assertEquals(expectedContent, paragraph.getContent());
        }
    }

    @Nested
    class SignatureParagraph {

        @Test
        void shouldThrowNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    BookParagraphFactory.createParagraph(ParagraphType.SIGNATURE, null).getParagraph()
            );
        }

        @Test
        void shouldReturnParagraphWithTestContent() {
            Paragraph paragraph = BookParagraphFactory.createParagraph(ParagraphType.SIGNATURE, TEST_CONTENT).getParagraph();

            String expectedContent = "TEST_CONTENT";
            assertEquals(expectedContent, paragraph.getContent());
        }
    }

    @Test
    void shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                BookParagraphFactory.createParagraph(null, null)
        );
    }
}