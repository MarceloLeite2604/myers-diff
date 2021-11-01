package com.github.marceloleite2604;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Context {

    private final String first;

    private final String second;

    private final Map<Integer, CentralizedArray> trace;

    @Getter
    private final List<Step> steps;

    @Getter
    private final List<Instruction> instructions;

    @Builder
    public Context(String first, String second) {
        this.first = first;
        this.second = second;
        this.trace = new HashMap<>();
        this.steps = new ArrayList<>();
        this.instructions = new ArrayList<>();
    }

    public char getCharAtOnFirst(int position) {
        return first.charAt(position);
    }

    public char getCharAtOnSecond(int position) {
        return second.charAt(position);
    }

    public int getFirstLength() {
        return first.length();
    }

    public int getSecondLength() {
        return second.length();
    }

    public void putOnTrace(int key, CentralizedArray centralizedArray) {
        trace.put(key, new CentralizedArray(centralizedArray));
    }

    public CentralizedArray getArrayOnTrace(int index) {
        return trace.get(index);
    }

    public List<Integer> getTraceKeysInDescendingOrder() {
        return trace.keySet()
            .stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
    }

    public void addStep(Step step) {
        steps.add(0, step);
    }

}
