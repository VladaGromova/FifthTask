package com.epam.task.fifth.chain;

public class SentenceParser extends AbstractParser {

    public SentenceParser(Parser successor) {
        super(successor);
    }

    @Override
    protected String chooseSplitter() {
        return SENTENCE_SPLITTER;
    }

}
