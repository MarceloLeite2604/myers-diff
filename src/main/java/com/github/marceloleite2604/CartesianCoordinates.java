package com.github.marceloleite2604;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CartesianCoordinates {

    private int x;

    private int y;

    public CartesianCoordinates(CartesianCoordinates cartesianCoordinates) {
        this.x = cartesianCoordinates.x;
        this.y = cartesianCoordinates.y;
    }

    public void decrementX() {
        x--;
    }

    public void decrementY() {
        y--;
    }
}
