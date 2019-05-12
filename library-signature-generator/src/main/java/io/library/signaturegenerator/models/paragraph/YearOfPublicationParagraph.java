package io.library.signaturegenerator.models.paragraph;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

class YearOfPublicationParagraph extends BookParagraph {

    YearOfPublicationParagraph(Integer alignment, Font font, String content) {
        super(alignment, font, content);
        try {
            Integer.parseInt(content);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Year of publication must be number");
        }
    }

    @Override
    public Paragraph getParagraph() {
        Paragraph p = new Paragraph(String.format("Rok publikacji: %s", content), font);
        p.setAlignment(alignment);

        return p;
    }
}
