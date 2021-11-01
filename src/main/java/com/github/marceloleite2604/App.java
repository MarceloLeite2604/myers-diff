package com.github.marceloleite2604;

public class App {

    public static final String FIRST = "Marcelo Leite";
    public static final String SECOND = "Marcelo de Moraes Leite";

    public static void main(String[] args) {
        final var diffs = new DiffElaborator().elaborate(FIRST, SECOND);
        diffs.forEach(instruction -> System.out.println(instruction.print()));
    }
}
