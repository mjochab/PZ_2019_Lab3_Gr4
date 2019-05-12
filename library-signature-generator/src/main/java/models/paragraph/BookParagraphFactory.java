package models.paragraph;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

import java.util.Objects;

public class BookParagraphFactory {

    private static final Font normalFont = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
    private static final Font titleFont = new Font(Font.FontFamily.COURIER, 16, Font.BOLD, BaseColor.BLACK);

    public static BookParagraph createParagraph(ParagraphType type, String content) {

        switch (type) {
            case TITLE: return new TitleParagraph(Element.ALIGN_LEFT, titleFont, Objects.requireNonNull(content));
            case AUTHOR: return new AuthorParagraph(Element.ALIGN_LEFT, normalFont, content);
            case PUBISHING_COMPANY: return new PublishingCompanyParagraph(Element.ALIGN_LEFT, normalFont, content);
            case YEAR_OF_PUBLICATION: return new YearOfPublicationParagraph(Element.ALIGN_LEFT, normalFont, Objects.requireNonNull(content));
            case SIGNATURE: return new SignatureParagraph(Element.ALIGN_JUSTIFIED, normalFont, Objects.requireNonNull(content));

            default: throw new IllegalArgumentException("Paragraph type is unknown");
        }
    }
}
