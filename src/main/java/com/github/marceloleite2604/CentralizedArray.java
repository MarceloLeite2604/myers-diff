package com.github.marceloleite2604;

import java.util.Arrays;

public class CentralizedArray {

    private final int[] array;

    private final int midPoint;

    public CentralizedArray(CentralizedArray centralizedArray) {
        this.array = Arrays.copyOf(centralizedArray.array, centralizedArray.array.length);
        this.midPoint = centralizedArray.midPoint;
    }

    public CentralizedArray(int size) {
        if (size % 2 != 1) {
            throw new IllegalArgumentException("Size must be an odd number. Informed value is  (" + size + ").");
        }
        array = new int[size];
        midPoint = size / 2;
    }

    public int get(int index) {
        return array[midPoint + index];
    }

    public void set(int index, int value) {
        array[midPoint + index] = value;
    }
}
