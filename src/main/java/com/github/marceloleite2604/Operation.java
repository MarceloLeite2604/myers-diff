package com.github.marceloleite2604;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Operation {
    INSERT('+'),
    DELETE('-'),
    EQUAL(' ');

    private final char signal;
}
