package com.github.marceloleite2604.printer;

import com.github.marceloleite2604.Instruction;
import com.github.marceloleite2604.printer.strategy.CharacterPrinterStrategy;
import com.github.marceloleite2604.printer.strategy.PrinterStrategy;
import com.github.marceloleite2604.printer.strategy.WordPrinterStrategy;
import org.fusesource.jansi.AnsiConsole;

import java.util.List;

public class DiffPrinter {

    public void print(List<Instruction> instructions) {

        AnsiConsole.systemInstall();

        PrinterStrategy printerStrategy = CharacterPrinterStrategy.builder()
                .ansiConsumer(System.out::print)
                .build();

        printerStrategy.apply(instructions);


        AnsiConsole.systemUninstall();
    }
}
