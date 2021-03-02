package com.epam.task.fifth;

import com.epam.task.fifth.composite.Component;
import com.epam.task.fifth.composite.Composite;
import com.epam.task.fifth.composite.Leaf;
import org.junit.Assert;
import org.junit.Test;

public class TextLogicTest {

    private final static String VALID_INPUT = "src/main/resources/input.txt";
    private final static String INVALID_INPUT = "src/main/resources/output.txt";
    private final static String EXPECTED_RESTORED = "Hello - bye  It is Java 8.0  Like this: interesting  ";

    private final static Leaf FIRST_LEAF = new Leaf("Hello");
    private final static Leaf SECOND_LEAF = new Leaf("-");
    private final static Leaf THIRD_LEAF = new Leaf("bye");
    private final static Leaf FOURTH_LEAF = new Leaf("It");
    private final static Leaf FIFTH_LEAF = new Leaf("is");
    private final static Leaf SIXTH_LEAF = new Leaf("Java");
    private final static Leaf SEVENTH_LEAF = new Leaf("8.0");
    private final static Leaf EIGHTS_LEAF = new Leaf("Like");
    private final static Leaf NINTH_LEAF = new Leaf("this:");
    private final static Leaf TENTH_LEAF = new Leaf("interesting");

    private final static TextLogic textLogic = new TextLogic();
    private Composite firstSentence = new Composite();
    private Composite secondSentence = new Composite();
    private final Composite thirdSentence = new Composite();
    private Composite firstParagraph = new Composite();
    private final Component secondParagraph = new Composite();

    @Test
    public void testSplitShouldReturnStructureOfTextIfInputDataIsValid() throws ParsingException {
        Composite expected = buildText();
        Component actual = textLogic.split(VALID_INPUT);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSortByNumberOfSentencesShouldSort() throws ParsingException {
        Component text = textLogic.split(VALID_INPUT);
        Composite actual = textLogic.sortByNumberOfSentences((Composite) text);
        Composite expected = buildSortedByNumberOfSentencesText();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSortByWordLengthShouldSort() throws ParsingException {
        Component text = textLogic.split(VALID_INPUT);
        Composite actual = textLogic.sortByWordLength((Composite) text);
        Composite expected = buildSortedByWordLengthText();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRestoreShouldRestoreText() throws ParsingException {
        Component splitedText = textLogic.split(VALID_INPUT);
        String actualRestored = textLogic.restore(splitedText);
        Assert.assertEquals(EXPECTED_RESTORED, actualRestored);
    }

    @Test(expected = ParsingException.class)
    public void testSplitShouldThrowExceptionWhenInputInvalid() throws ParsingException {
        Component actual =  textLogic.split(INVALID_INPUT);
    }

    private Composite buildText(){
        firstSentence.addComponents(FIRST_LEAF,SECOND_LEAF,THIRD_LEAF);
        secondSentence.addComponents(FOURTH_LEAF,FIFTH_LEAF,SIXTH_LEAF,SEVENTH_LEAF);
        thirdSentence.addComponents(EIGHTS_LEAF,NINTH_LEAF,TENTH_LEAF);
        firstParagraph.addComponents(firstSentence, secondSentence);
        secondParagraph.add(thirdSentence);
        Composite result = new Composite();
        result.addComponents(firstParagraph,secondParagraph);
        return result;
    }

    private Composite buildSortedByWordLengthText(){
        Composite result = new Composite();
        firstSentence = new Composite();
        firstSentence.addComponents(SECOND_LEAF, THIRD_LEAF, FIRST_LEAF);
        secondSentence = new Composite();
        secondSentence.addComponents(FOURTH_LEAF, FIFTH_LEAF, SEVENTH_LEAF, SIXTH_LEAF);
        firstParagraph = new Composite();
        firstParagraph.addComponents(firstSentence, secondSentence);
        thirdSentence.addComponents(EIGHTS_LEAF,NINTH_LEAF,TENTH_LEAF);
        secondParagraph.add(thirdSentence);
        result.addComponents(firstParagraph, secondParagraph);
        return result;
    }

    private Composite buildSortedByNumberOfSentencesText(){
        Composite result = new Composite();
        firstSentence.addComponents(FIRST_LEAF,SECOND_LEAF,THIRD_LEAF);
        secondSentence.addComponents(FOURTH_LEAF,FIFTH_LEAF,SIXTH_LEAF,SEVENTH_LEAF);
        thirdSentence.addComponents(EIGHTS_LEAF,NINTH_LEAF,TENTH_LEAF);
        firstParagraph.addComponents(firstSentence, secondSentence);
        secondParagraph.add(thirdSentence);
        result.addComponents(secondParagraph);
        result.addComponents(firstParagraph);
        return result;
    }
}
