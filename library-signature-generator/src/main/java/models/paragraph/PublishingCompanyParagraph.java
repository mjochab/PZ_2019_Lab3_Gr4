package models.paragraph;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import utils.StringUtils;

class PublishingCompanyParagraph extends BookParagraph {

    PublishingCompanyParagraph(Integer alignment, Font font, String content) {
        super(alignment, font, content);
    }

    @Override
    public Paragraph getParagraph() {
        if (StringUtils.isNotBlank(content)) {
            Paragraph p = new Paragraph(String.format("Wydawnictwo: %s", content), font);
            p.setAlignment(alignment);

            return p;
        }
        return null;
    }
}
