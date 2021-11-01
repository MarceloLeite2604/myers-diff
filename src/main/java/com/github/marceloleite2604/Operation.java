package com.github.marceloleite2604;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Operation {
    INSERT('+', "\\e[31m"),
    DELETE('-', "\\e[32m"),
    EQUAL(' ', "\\e[39m");

    private final char signal;

    private final String ttyColor;
}
