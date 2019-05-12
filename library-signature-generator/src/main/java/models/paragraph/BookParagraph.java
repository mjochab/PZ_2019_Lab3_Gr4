package models.paragraph;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

public abstract class BookParagraph {

    protected final Integer alignment;
    protected final Font font;
    protected final String content;

    public BookParagraph(Integer alignment, Font font, String content) {
        this.alignment = alignment;
        this.font = font;
        this.content = content;
    }

    public abstract Paragraph getParagraph();
}
