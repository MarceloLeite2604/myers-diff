package com.github.marceloleite2604;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Instruction {

    private final Operation operation;

    private final PositionedCharacter firstOperator;

    private final PositionedCharacter secondOperator;

    public Character retrieveCharacter() {
        return Optional.ofNullable(firstOperator)
            .map(PositionedCharacter::getCharacter)
            .orElseGet(() -> Optional.ofNullable(secondOperator)
                .map(PositionedCharacter::getCharacter)
                .orElseThrow(() -> new IllegalStateException("Instruction does not have either first or second operator.")));
    }

    private String retrieveFirstPosition() {
        return retrieveOperatorPosition(firstOperator);
    }

    private String retrieveSecondPosition() {
        return retrieveOperatorPosition(secondOperator);
    }

    public Character retrieveFirstCharacter() {
        return firstOperator.getCharacter();
    }

    public Character retrieveSecondCharacter() {
        return secondOperator.getCharacter();
    }

    private String retrieveOperatorPosition(PositionedCharacter operator) {
        return Optional.ofNullable(operator)
            .map(PositionedCharacter::retrievePosition)
            .orElse(" ");
    }

    public String print() {
        return operation.getSignal() +
            " " +
            retrieveFirstPosition() +
            " " +
            retrieveSecondPosition() +
            " " +
            retrieveCharacter();
    }
}
