package com.github.marceloleite2604.printer.strategy;

import com.github.marceloleite2604.Instruction;
import com.github.marceloleite2604.Operation;
import com.github.marceloleite2604.printer.Color;
import lombok.Builder;
import org.fusesource.jansi.Ansi;

import java.util.Optional;
import java.util.function.Consumer;

public class CharacterPrinterStrategy extends AbstractPrinterStrategy {

    private Operation currentOperation;

    @Builder
    private CharacterPrinterStrategy(Consumer<Ansi> ansiConsumer) {
        super(ansiConsumer);
    }

    protected void append(Instruction instruction) {

        final var operation = instruction.getOperation();

        if (operationChanged(operation)) {
            flush();
            this.ansi = Color.findByOperation(operation)
                    .apply(Ansi.ansi());
            this.currentOperation = operation;
        }

        ansi.a(instruction.retrieveCharacter());
    }

    private boolean operationChanged(Operation otherOperation) {
        return Optional.ofNullable(currentOperation)
                .map(operation -> !operation.equals(otherOperation))
                .orElse(true);
    }

    protected void flush() {

        if (ansi != null && ansiConsumer != null) {
            ansiConsumer.accept(ansi);
        }

        ansi = Ansi.ansi();
    }
}
