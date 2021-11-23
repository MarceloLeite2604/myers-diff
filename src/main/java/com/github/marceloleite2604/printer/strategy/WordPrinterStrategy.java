package com.github.marceloleite2604.printer.strategy;

import com.github.marceloleite2604.Instruction;
import com.github.marceloleite2604.PositionedCharacter;
import com.github.marceloleite2604.printer.Color;
import lombok.Builder;
import org.apache.commons.collections4.CollectionUtils;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordPrinterStrategy extends AbstractPrinterStrategy {

    private static final Pattern WHITESPACE_REGEX = Pattern.compile("\\s");

    private boolean printingWhitespaceCharacters;
    private List<Instruction> instructions;

    @Builder
    private WordPrinterStrategy(Consumer<Ansi> ansiConsumer) {
        super(ansiConsumer);
        this.printingWhitespaceCharacters = false;
        this.instructions = new ArrayList<>();
    }

    public void append(Instruction instruction) {

        boolean whitespaceCharacter = isWhitespaceCharacter(instruction.retrieveCharacter());

        if (printingWhitespaceCharacters && !whitespaceCharacter) {
            buildAnsi();
            flush();
        }

        printingWhitespaceCharacters = whitespaceCharacter;
        instructions.add(instruction);
    }

    private void buildAnsi() {

        if (CollectionUtils.isNotEmpty(instructions)) {
            final var distinctOperations = instructions.stream()
                    .map(Instruction::getOperation)
                    .distinct()
                    .collect(Collectors.toList());

            ansi = new Ansi();
            if (distinctOperations.size() == 1) {

                ansi = Color.findByOperation(distinctOperations.get(0))
                        .apply(ansi);
                final var text = instructions.stream()
                        .map(Instruction::retrieveCharacter)
                        .map(Object::toString)
                        .collect(Collectors.joining());
                ansi.a(text);
            } else {

                final var firstText = instructions.stream()
                        .map(Instruction::getFirstOperator)
                        .filter(Objects::nonNull)
                        .map(PositionedCharacter::getCharacter)
                        .filter(Objects::nonNull)
                        .map(Object::toString)
                        .collect(Collectors.joining());

                ansi = Color.INSERT.apply(ansi);
                final var secondText = instructions.stream()
                        .map(Instruction::getSecondOperator)
                        .filter(Objects::nonNull)
                        .map(PositionedCharacter::getCharacter)
                        .filter(Objects::nonNull)
                        .map(Object::toString)
                        .collect(Collectors.joining());

                ansi = Color.DELETE.apply(ansi)
                        .a(firstText);

                ansi = Color.INSERT.apply(ansi)
                        .a(secondText);
            }
        }

        instructions = new ArrayList<>();
    }

    private boolean isWhitespaceCharacter(Character character) {
        return WHITESPACE_REGEX.matcher("" + character)
                .matches();
    }

    protected void flush() {

        buildAnsi();

        if (ansi != null && ansiConsumer != null) {
            ansiConsumer.accept(ansi);
        }
    }
}
