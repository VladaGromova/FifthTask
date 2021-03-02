package com.epam.task.fifth.chain;

import com.epam.task.fifth.composite.Component;
import com.epam.task.fifth.composite.Composite;
import org.apache.log4j.Logger;

import java.util.stream.Stream;

abstract class AbstractParser implements Parser {

    protected final static String TEXT_SPLITTER = "\n";
    protected final static String PARAGRAPH_SPLITTER = "[.?!]+";
    protected final static String SENTENCE_SPLITTER = " ";
    protected final static String LOGGER_MESSAGE = "Component parsed successfully";
    private final static Logger LOGGER = Logger.getLogger(AbstractParser.class);

    private Parser successor;

    public AbstractParser(Parser successor){
        this.successor = successor;
    }

    protected AbstractParser() {}

    protected Parser getSuccessor(){
        return successor;
    }

    @Override
    public Component parse(String input) {
        String[] elements = input.split(chooseSplitter());
        Composite composite = new Composite();

        Stream.of(elements).forEach(element->{
            if(element.equals("")) {
                return;
            }
            Component component = getSuccessor().parse(element);
            composite.add(component);
        });
        LOGGER.info(LOGGER_MESSAGE);
        return composite;
    }

    protected abstract String chooseSplitter();
}
