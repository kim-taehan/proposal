package org.developx.proposal.domain.proposal.entity.embed;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.developx.proposal.global.utils.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;

import static lombok.AccessLevel.*;
import static lombok.AccessLevel.PRIVATE;
import static org.developx.proposal.global.utils.FileUtils.getSaveFileName;
import static org.developx.proposal.global.utils.FileUtils.mkdir;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Getter
public class FileInfo {

    @Column(length = 255)
    private String originalName;

    @Column(length = 100)
    private String saveFilePath;


    @Column(length = 100)
    private String saveFileName;

    private String extension;

    public static FileInfo from(MultipartFile file) {
        try {
            final String extension = findExtension(file);
            FileInfo fileInfo = new FileInfo(
                    file.getOriginalFilename(),
                    String.format("%s/%s", "upload", LocalDate.now()),
                    FileUtils.getSaveFileName(extension),
                    extension
            );
            mkdir(fileInfo.saveFilePath);
            file.transferTo(Path.of(fileInfo.saveFilePath, fileInfo.saveFileName));
            return fileInfo;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String findExtension(MultipartFile file) {
        String[] split = file.getOriginalFilename().split("\\.");
        return split.length > 1 ? split[split.length - 1] : "";
    }
}
