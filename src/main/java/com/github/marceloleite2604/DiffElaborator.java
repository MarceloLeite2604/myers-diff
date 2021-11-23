package com.github.marceloleite2604;

import java.util.Collections;
import java.util.List;

public class DiffElaborator {

    public List<Instruction> elaborate(final String first, final String second) {

        var context = Context.builder()
                .first(first)
                .second(second)
                .build();

        elaborateTrace(context);
        elaborateSteps(context);
        elaborateInstructions(context);

        return context.getInstructions();
    }

    private Context elaborateInstructions(Context context) {
        char firstCharacter = 0;
        char secondCharacter = 0;

        for (Step step : context.getSteps()) {
            if (step.getToX() > 0) {
                firstCharacter = context.getCharAtOnFirst(step.getToX() - 1);
            }
            if (step.getToY() > 0) {
                secondCharacter = context.getCharAtOnSecond(step.getToY() - 1);
            }

            var firstOperator = PositionedCharacter.builder()
                    .position(step.getToX())
                    .character(firstCharacter)
                    .build();

            var secondOperator = PositionedCharacter.builder()
                    .position(step.getToY())
                    .character(secondCharacter)
                    .build();

            Instruction instruction;
            if (step.getToX() == step.getFromX()) {
                instruction = Instruction.builder()
                        .operation(Operation.INSERT)
                        .secondOperator(secondOperator)
                        .build();
            } else if (step.getToY() == step.getFromY()) {
                instruction = Instruction.builder()
                        .operation(Operation.DELETE)
                        .firstOperator(firstOperator)
                        .build();
            } else {
                instruction = Instruction.builder()
                        .operation(Operation.EQUAL)
                        .firstOperator(firstOperator)
                        .secondOperator(secondOperator)
                        .build();
            }

            context.addInstruction(instruction);
        }

        return context;
    }

    private Context elaborateTrace(final Context context) {
        int n = context.getFirstLength();
        int m = context.getSecondLength();
        int maxSize = n + m;
        final var array = new CentralizedArray(2 * maxSize + 1);
        int x;
        int y;

        for (int d = 0; d <= maxSize; d++) {
            for (int k = -d; k <= d; k += 2) {
                if (k == -d || (k != d && array.get(k - 1) < array.get(k + 1))) {
                    x = array.get(k + 1); // Move downward
                } else {
                    x = array.get(k - 1) + 1; // Move rightward
                }
                y = x - k;

                while (x < n && y < m && context.getCharAtOnFirst(x) == context.getCharAtOnSecond(y)) { // If characters are the same
                    x++; // Then moves diagonally
                    y++;
                }

                array.set(k, x);

                if (x >= n && y >= m) {
                    context.putOnTrace(d, array);
                    return context;
                }
            }
            context.putOnTrace(d, array);
        }
        return context;
    }

    private Context elaborateSteps(Context context) {
        var current = new CartesianCoordinates(context.getFirstLength(), context.getSecondLength());
        var previous = new CartesianCoordinates();
        int k;
        int previousK;

        for (Integer d : context.getTraceKeysInDescendingOrder()) {
            final var array = context.getArrayOnTrace(d);
            k = current.getX() - current.getY();

            if (k == -d || (k != d && array.get(k - 1) < array.get(k + 1))) {
                previousK = k + 1;
            } else {
                previousK = k - 1;
            }

            previous.setX(array.get(previousK));
            previous.setY(previous.getX() - previousK);

            while (current.getX() > previous.getX() && current.getY() > previous.getY()) {
                final var from = new CartesianCoordinates(current.getX() - 1, current.getY() - 1);
                final var to = new CartesianCoordinates(current);
                context.addStep(new Step(from, to));

                current.decrementX();
                current.decrementY();
            }

            if (d > 0) {
                final var from = new CartesianCoordinates(previous);
                final var to = new CartesianCoordinates(current);
                context.addStep(new Step(from, to));
            }
            current = new CartesianCoordinates(previous);
        }

        return context;
    }
}
