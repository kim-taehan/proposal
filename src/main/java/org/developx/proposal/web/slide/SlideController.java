package org.developx.proposal.web.slide;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.developx.proposal.domain.bookmark.service.BookmarkService;
import org.developx.proposal.domain.proposal.data.ContextDto;
import org.developx.proposal.domain.proposal.entity.Slide;
import org.developx.proposal.domain.proposal.service.SlideService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;

@Controller
@Slf4j
@RequestMapping("/slides")
@RequiredArgsConstructor
public class SlideController {

    private final SlideService slideService;
    private final BookmarkService bookmarkService;

    @GetMapping("{slideId}")
    public String detail(@PathVariable("slideId") Long slideId, Model model) {

        Slide slide = slideService.findById(slideId);
        long bookmarkId = bookmarkService.findBookmarkId(slide);

        model.addAttribute("pdfUrl", "/slides/download/"+slide.getId()+"#page="+(slide.getSlideNumber()-1) + "#toolbar=0&navpanes=0&scrollbar=0");
        model.addAttribute("slideId", slide.getId());
        model.addAttribute("bookmarkId", bookmarkId);
        return "slides/preview";
    }

    @GetMapping("download/{slideId}")
    public StreamingResponseBody downloadReport(@PathVariable("slideId") Long slideId, HttpServletResponse response) throws FileNotFoundException {

        Slide slide = slideService.findById(slideId);
        response.setContentType("application/pdf");
        File targetFile =  slide.getDocument().getPath().toFile();
        InputStream targetStream =  new DataInputStream(new FileInputStream(targetFile));

        return outputStream -> {
            int nRead;
            byte[] data = new byte[4069];
            while ((nRead = targetStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
        };
    }

}
