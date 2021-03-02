package com.epam.task.fifth.interpreter;

public class NonterminalExpression implements AbstractExpression {

    private final double number;

    public NonterminalExpression(double number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(number);
    }
}
