package models.paragraph;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

class SignatureParagraph extends BookParagraph {

    SignatureParagraph(Integer alignment, Font font, String content) {
        super(alignment, font, content);
    }

    @Override
    public Paragraph getParagraph() {
        Paragraph p = new Paragraph(content, font);
        p.setAlignment(alignment);
        p.setSpacingAfter(50.0f);

        return p;
    }
}