package com.epam.task.fifth.chain;

public class ChainBuilder {
    public Parser build(){
        return new TextParser(new ParagraphParser(new SentenceParser(new LexemeParser())));
    }
}
