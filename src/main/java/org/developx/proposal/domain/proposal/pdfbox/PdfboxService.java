package org.developx.proposal.domain.proposal.pdfbox;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class PdfboxService {
    public static void main(String[] args) throws IOException {

        try(PDDocument doc = Loader.loadPDF(new File("1111.pdf"))){

            System.out.println("사이즈=" +  doc.getNumberOfPages());
            PDDocumentCatalog documentCatalog = doc.getDocumentCatalog();
            System.out.println("파싱완료");
            PDPage page = doc.getPage(1);

            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            System.out.println(pdfTextStripper.getText(doc));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
