package com.epam.task.fifth;

import com.epam.task.fifth.chain.ChainBuilder;
import com.epam.task.fifth.chain.Parser;
import com.epam.task.fifth.composite.Component;
import com.epam.task.fifth.composite.Composite;
import com.epam.task.fifth.composite.Leaf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TextLogic {

    private final static String EXCEPTION_MESSAGE = "Parsing exception";
    private final static String EMPTY_STRING = "";
    private final static String DELIMITER = "\n";

    public Composite sortByNumberOfSentences(Composite text){
        List<Component> uneditableParagraphs = text.getComponents();
        List<Component> paragraphs = new ArrayList<>(uneditableParagraphs);
        paragraphs.sort((first, second) -> {
            List<Component> firstParagraph = ((Composite)first).getComponents();
            List<Component> secondParagraph = ((Composite)second).getComponents();
            return firstParagraph.size() - secondParagraph.size();
        });
        return new Composite(paragraphs);
    }

    public Composite sortByWordLength(Composite text){
        List<Component> uneditableParagraphs = text.getComponents();
        List<Component> paragraphs = new ArrayList<>(uneditableParagraphs);
        for(Component paragraph : paragraphs) {
            List<Component> sentences = ((Composite)paragraph).getComponents();
            for (Component sentence : sentences) {
                List<Component> words = ((Composite) sentence).getComponents();
                words.sort((first, second) -> {
                    String firstWord = ((Leaf) first).getValue();
                    String secondWord = ((Leaf) second).getValue();
                    return firstWord.length() - secondWord.length();
                });
            }
        }
        return new Composite(paragraphs);
    }

    public Component split(String filename) throws ParsingException {
        ChainBuilder builder = new ChainBuilder();
        Parser parser = builder.build();
        String data;
        try {
            data = readData(filename);
        } catch (IOException e) {
            throw new ParsingException(EXCEPTION_MESSAGE, e);
        }
        return parser.parse(data);
    }

    public String restore(Component text){
        String result = EMPTY_STRING;
        List<Component> components = ((Composite)text).getComponents();
        for(Component component : components){
            result+=component.toString();
        }
        return result;
    }

    private String readData(String filename) throws IOException {
        StringJoiner joiner = new StringJoiner(DELIMITER);
        List<String> lines = Files.readAllLines(Path.of(filename));
        for(String line : lines){
            joiner.add(line);
        }
        return joiner.toString();
    }
}
