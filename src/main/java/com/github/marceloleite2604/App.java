package com.github.marceloleite2604;

import com.github.marceloleite2604.printer.DiffPrinter;

import java.nio.file.Path;

public class App {

    private static final Path INPUT_DIRECTORY = Path.of("input");
    private static final Path FIRST = INPUT_DIRECTORY.resolve(Path.of("first.txt"));
    private static final Path SECOND = INPUT_DIRECTORY.resolve(Path.of("second.txt"));

    public static void main(String[] args) {

        final var fileReader = new FileReader();

        final var firstContent = fileReader.read(FIRST);
        final var secondContent = fileReader.read(SECOND);
        final var diffs = new DiffElaborator().elaborate(firstContent, secondContent);

        new DiffPrinter().print(diffs);
    }
}
