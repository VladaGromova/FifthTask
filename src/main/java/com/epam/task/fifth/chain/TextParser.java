package com.epam.task.fifth.chain;

public class TextParser extends AbstractParser {

    public TextParser(Parser successor) {
    super(successor);
    }

    @Override
    protected String chooseSplitter() {
        return TEXT_SPLITTER;
    }
}
