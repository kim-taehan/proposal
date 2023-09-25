package org.developx.proposal.domain.project.data.enums;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTypeTest {

    @Test
    void getValues() {
        DocumentType[] values = DocumentType.values();
        String name = values[0].name();
    }

    @Test
    void pathTest() {
        Paths.get("upload");

        System.out.println(Paths.get("upload").getRoot());
    }
}