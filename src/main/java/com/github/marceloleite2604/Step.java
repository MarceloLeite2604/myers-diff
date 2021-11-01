package com.github.marceloleite2604;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Step {

    private final CartesianCoordinates from;

    private final CartesianCoordinates to;

    public int getFromX() {
        return from.getX();
    }

    public int getFromY() {
        return from.getY();
    }

    public int getToX() {
        return to.getX();
    }

    public int getToY() {
        return to.getY();
    }
}
