package org.developx.proposal.domain.proposal.pdfbox;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xslf.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PdfboxService {
    public static void main(String[] args) throws IOException {

        List<ContextDto> contexts = new ArrayList<>();

        String path = "222.pptx";
        ZipSecureFile.setMinInflateRatio(0);
        XMLSlideShow ppt  = new XMLSlideShow(new FileInputStream(path));
        for (XSLFSlide slide : ppt.getSlides()) {

            for (XSLFShape sh : slide.getShapes()) {
                extracted(contexts, sh);
            }
            if (1 == 1) {
                break;
            }
        }
    }

    private static void extracted(List<ContextDto> contexts, XSLFShape sh) {
        if (sh instanceof XSLFTextShape) {
            String text = ((XSLFTextShape) sh).getText();
//            System.out.println("이궁 : " + text);
            contexts.add(new ContextDto(text, ShapeType.TEXT));
        }
        else if (sh instanceof XSLFGroupShape) {
            XSLFGroupShape gShapes = (XSLFGroupShape) sh;
            for (XSLFShape inSh : gShapes.getShapes()) {
                extracted(contexts, inSh);
            }
        } else if (sh instanceof XSLFTable) {
            XSLFTable table = (XSLFTable) sh;
            table.getRows().forEach(xslfTableCells -> {
                xslfTableCells.getCells()
                        .forEach(
                                xslfTextParagraphs -> contexts.add(new ContextDto(xslfTextParagraphs.getText(), ShapeType.TABLE))  );
            });

            System.out.println(sh.getClass());
        } else {
            System.out.println(sh.getClass());
        }

    }
}
