package models.paragraph;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

class TitleParagraph extends BookParagraph {

    TitleParagraph(Integer alignment, Font font, String content) {
        super(alignment, font, content);
    }

    @Override
    public Paragraph getParagraph() {
        Paragraph p = new Paragraph(content, font);
        p.setAlignment(alignment);

        return p;
    }
}
