package io.library.signaturegenerator.models.paragraph;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import io.library.signaturegenerator.utils.StringUtils;

class AuthorParagraph extends BookParagraph {

    AuthorParagraph(Integer alignment, Font font, String content) {
        super(alignment, font, content);
    }

    @Override
    public Paragraph getParagraph() {
        if (StringUtils.isNotBlank(content)) {
            Paragraph p = new Paragraph(content, font);
            p.setAlignment(Element.ALIGN_LEFT);

            return p;
        }
        return null;
    }
}
