package com.epam.task.fifth.chain;

public class ParagraphParser extends AbstractParser {

    public ParagraphParser(Parser successor) {
        super(successor);
    }

    @Override
    protected String chooseSplitter() {
        return PARAGRAPH_SPLITTER;
    }

}
