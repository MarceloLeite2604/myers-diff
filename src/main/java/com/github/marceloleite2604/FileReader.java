package com.github.marceloleite2604;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {

    public String read(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException exception) {
            final var message = String.format("Error while reading content from \"%s\" file.", path);
            throw new IllegalStateException(message);
        }
    }
}
