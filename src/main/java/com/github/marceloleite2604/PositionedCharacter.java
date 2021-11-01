package com.github.marceloleite2604;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class PositionedCharacter {

    private Integer position;

    private Character character;

    public String retrievePosition() {
        return Optional.ofNullable(position)
            .map(Object::toString)
            .orElse(" ");
    }
}
