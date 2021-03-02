package com.epam.task.fifth.interpreter;

import java.util.ArrayList;
import java.util.Scanner;

public class ExpressionCalculator {

    private final static String DELIMITER = ",";
    private final static char PLUS = '+';
    private final static char MINUS = '-';
    private final static char MULTIPLICATER = '*';
    private final static char DIVIDER = '/';

    public double calculate(String expressionInString){
        ArrayList<AbstractExpression> list = parse(expressionInString);
        Context context = new Context();
        for(AbstractExpression expression : list){
            expression.interpret(context);
        }
        return context.popValue();
    }

    private ArrayList<AbstractExpression> parse(String expression) {
        ArrayList<AbstractExpression> list = new ArrayList<>();
        for(String lexeme : expression.split(DELIMITER)){
            if(lexeme.isEmpty()){
                continue;
            }
            char symbol = lexeme.charAt(0);
            switch (symbol){
                case PLUS:
                    list.add(new TerminalExpressionPlus());
                    break;
                case MINUS:
                    list.add(new TerminalExpressionMinus());
                    break;
                case MULTIPLICATER:
                    list.add(new TerminalExpressionMultiply());
                    break;
                case DIVIDER:
                    list.add(new TerminalExpressionDivide());
                    break;
                default:
                    Scanner scanner = new Scanner(lexeme);
                    if(scanner.hasNextInt()){
                        list.add(new NonterminalExpression(scanner.nextInt()));
                    }
            }
        }
        return list;
    }
}
