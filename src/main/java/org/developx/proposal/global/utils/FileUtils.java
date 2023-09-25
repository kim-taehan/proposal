package org.developx.proposal.global.utils;

import java.io.File;
import java.nio.file.Path;
import java.util.UUID;

public final class FileUtils {

    public static void mkdir(String path) {
        File file = new File((path));
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static String getSaveFileName(String extension) {
        return String.format("%s.%s", UUID.randomUUID(), extension);
    }
}
