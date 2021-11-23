package com.github.marceloleite2604.printer;

import com.github.marceloleite2604.Operation;
import org.fusesource.jansi.Ansi;

import java.util.function.Consumer;
import java.util.function.Function;

public enum Color {

    INSERT(Operation.INSERT, ansi -> ansi.fg(Ansi.Color.GREEN)),
    DELETE(Operation.DELETE, ansi -> ansi.fg(Ansi.Color.RED)),
    EQUAL(Operation.EQUAL, Ansi::reset);

    private final Operation operation;

    private final Function<Ansi, Ansi> ansiColorApplier;

    Color(Operation operation, Function<Ansi, Ansi> ansiColorApplier) {
        this.operation = operation;
        this.ansiColorApplier = ansiColorApplier;
    }

    public Ansi apply(Ansi ansi) {
        return ansiColorApplier.apply(ansi);
    }

    public static Color findByOperation(Operation operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Operation cannot be null.");
        }

        for (Color color : Color.values()) {
            if (color.operation.equals(operation)) {
                return color;
            }
        }

        final var message = String.format("Could not find a color for \"%s\" operation.", operation.name());
        throw new IllegalArgumentException(message);
    }
}
