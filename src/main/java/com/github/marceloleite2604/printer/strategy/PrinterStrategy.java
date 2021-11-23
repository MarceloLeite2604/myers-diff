package com.github.marceloleite2604.printer.strategy;

import com.github.marceloleite2604.Instruction;

import java.util.List;

public interface PrinterStrategy {

    void apply(List<Instruction> instructions);
}
