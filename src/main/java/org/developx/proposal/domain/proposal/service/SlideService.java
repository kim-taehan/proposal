package org.developx.proposal.domain.proposal.service;

import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xslf.usermodel.XSLFGroupShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.developx.proposal.domain.customer.entity.Customer;
import org.developx.proposal.domain.proposal.entity.Context;
import org.developx.proposal.domain.proposal.entity.Document;
import org.developx.proposal.domain.proposal.entity.Slide;
import org.developx.proposal.domain.proposal.pdfbox.ContextDto;
import org.developx.proposal.domain.proposal.pdfbox.ShapeType;
import org.developx.proposal.domain.proposal.repository.ContextRepository;
import org.developx.proposal.domain.proposal.repository.SlideRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SlideService {

    private final SlideRepository slideRepository;
    private final ContextRepository contextRepository;

    public Slide findById(long id) {
        return slideRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }

    public void saveSlides(Document document) {

        try(PDDocument doc = Loader.loadPDF(document.getPath().toFile())){
            document.setTotalSlide(doc.getNumberOfPages());
            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            for (int i=0; i < doc.getNumberOfPages(); i++) {
                pdfTextStripper.setStartPage(i);
                pdfTextStripper.setEndPage(i);

                Slide slideEntity = saveSlide(document, i+1);

                String text = pdfTextStripper.getText(doc);
                Arrays.stream(text.split("\r\n")).map(s -> s.trim()).filter(s -> StringUtils.hasText(s) && s.length() > 1)
                                .forEach(s -> savaContext(s, ShapeType.TEXT, slideEntity));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//        ZipSecureFile.setMinInflateRatio(0);
//        try(XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(document.getPath().toFile()))){
//            document.setTotalSlide(ppt.getSlides().size());
//            int slidePage = 1;
//
//            for (XSLFSlide slide : ppt.getSlides()) {
//                List<ContextDto> contexts = new ArrayList<>();
//                Slide slideEntity = saveSlide(document, slidePage++);
//                for (XSLFShape sh : slide.getShapes()) {
//                    extracted(contexts, sh, slideEntity);
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }

    private Slide saveSlide(Document document, int slideNumber) {

        Slide slide = Slide.builder()
                .document(document)
                .slideNumber(slideNumber)
                .build();

        slideRepository.save(slide);
        return slide;
    }

    private void extracted(List<ContextDto> contexts, XSLFShape sh, Slide slideEntity) {
        if (sh instanceof XSLFTextShape) {
            String text = ((XSLFTextShape) sh).getText();
            savaContext(text, ShapeType.TEXT, slideEntity);
        }
        else if (sh instanceof XSLFGroupShape) {
            XSLFGroupShape gShapes = (XSLFGroupShape) sh;
            for (XSLFShape inSh : gShapes.getShapes()) {
                extracted(contexts, inSh, slideEntity);
            }
        } else if (sh instanceof XSLFTable) {
            XSLFTable table = (XSLFTable) sh;
            table.getRows().forEach(xslfTableCells -> {
                xslfTableCells.getCells()
                        .forEach(
                                xslfTextParagraphs -> savaContext(xslfTextParagraphs.getText(), ShapeType.TABLE, slideEntity));
            });
        } else {
            System.out.println(sh.getClass());
        }
    }

    private void savaContext(String text, ShapeType shapeType, Slide slide) {

        String[] splits = text.split("\\n");

        Arrays.stream(splits).filter(s -> StringUtils.hasText(s) && s.length() > 1).forEach(split -> {
            Context context = Context.builder()
                    .text(split)
                    .shapeType(shapeType)
                    .slide(slide)
                    .build();
            contextRepository.save(context);
        });
    }
}
