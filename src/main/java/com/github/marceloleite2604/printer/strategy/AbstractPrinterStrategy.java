package com.github.marceloleite2604.printer.strategy;

import com.github.marceloleite2604.Instruction;
import org.fusesource.jansi.Ansi;

import java.util.List;
import java.util.function.Consumer;

public abstract class AbstractPrinterStrategy implements PrinterStrategy {

    protected final Consumer<Ansi> ansiConsumer;
    protected Ansi ansi;

    public AbstractPrinterStrategy(Consumer<Ansi> ansiConsumer) {
        this.ansiConsumer = ansiConsumer;
    }

    public void apply(List<Instruction> instructions) {
        instructions.forEach(this::append);
        flush();
    }

    protected abstract void flush();

    protected abstract void append(Instruction instruction);
}
