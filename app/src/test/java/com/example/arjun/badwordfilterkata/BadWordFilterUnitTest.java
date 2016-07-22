package com.example.arjun.badwordfilterkata;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class BadWordFilterUnitTest {

    ArrayList words;

    @Before
    public void setUp() throws Exception {

        BadWordFilter filter = new BadWordFilter();
        String file = "badwords.txt";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(file);
        words = filter.readInFile(inputStream);
    }

    @Test
    public void readInFileFromParameter() throws Exception {

        BadWordFilter filter = new BadWordFilter();
        String file = "badwords.txt";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(file);
        ArrayList words = filter.readInFile(inputStream);
        assertEquals(words.size(), 77);
    }

    @Test
    public void testIsSubstringInList() throws Exception {

        String text = "fu";
        BadWordFilter filter = new BadWordFilter();
        Boolean isInTheList = filter.isTextInList(text, words);

        assertTrue(isInTheList);

        String erroneousText = "%/";
        Boolean shouldNotBeInList = filter.isTextInList(erroneousText, words);
        assertFalse(shouldNotBeInList);
    }

    @Test
    public void ensureWordIsInTheList() throws Exception {

        String word = "damn";
        BadWordFilter filter = new BadWordFilter();
        Boolean isInTheList = filter.isWordInList(word, words);

        assertTrue(isInTheList);

        String erroneousWord = "%/^g";
        Boolean shouldNotBeInList = filter.isWordInList(erroneousWord, words);

        assertFalse(shouldNotBeInList);
    }

    @Test
    public void ensureTextIsSensored() throws Exception {

        String sentence = "damn Arjun";
        BadWordFilter filter = new BadWordFilter();
        String censoredSentence = filter.censorSentence(sentence, words);
        assertEquals("d*** Arjun", censoredSentence);

        String sentenceWithSpaces = "f u c k damn clitoris";
        String censoredSentenceWithSpaces = filter.censorSentence(sentenceWithSpaces, words);
        assertEquals("f****** d*** c*******", censoredSentenceWithSpaces);
    }

    @Test
    public void ensureWordIsCensored() throws Exception{

        String word = "bugger";
        BadWordFilter filter =new BadWordFilter();
        String censoredWord = filter.censorWord(word);
        assertEquals("b*****", censoredWord);
    }
}