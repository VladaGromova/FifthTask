package com.epam.task.fifth.chain;

import com.epam.task.fifth.composite.Component;
import com.epam.task.fifth.composite.Leaf;
import com.epam.task.fifth.interpreter.ExpressionCalculator;
import org.apache.log4j.Logger;

public class LexemeParser extends AbstractParser {

    private final static char BRACKET = '[';
    private final static String LOGGER_MESSAGE = "Lexeme parsed successfully";
    private final static Logger LOGGER = Logger.getLogger(LexemeParser.class);

    public LexemeParser(Parser successor) {
        super(successor);
    }

    protected LexemeParser() {
    }

    @Override
    public Component parse(String input) {
        char[] symbols = input.toCharArray();
        if(symbols[0]==BRACKET){
            String expression = new String(symbols, 1, symbols.length - 2);
            ExpressionCalculator calculator = new ExpressionCalculator();
            double expressionResult = calculator.calculate(expression);
            LOGGER.info(LOGGER_MESSAGE);
            return new Leaf(Double.toString(expressionResult));
        } else{

            LOGGER.info(LOGGER_MESSAGE);
            return new Leaf(new String(symbols));
        }
    }

    @Override
    protected String chooseSplitter() {
        return null;
    }
}
