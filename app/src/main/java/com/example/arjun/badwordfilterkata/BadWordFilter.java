package com.example.arjun.badwordfilterkata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by arjun on 15/07/2016.
 */
public class BadWordFilter {

    public ArrayList<String> readInFile(InputStream inputStream) {

        String currentWord = "";
        ArrayList<String> listOfWords = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            if (inputStream != null) {

                while ((currentWord = reader.readLine()) != null) {

                    listOfWords.add(currentWord);
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        } finally {

            try {
                inputStream.close();

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        return listOfWords;
    }

    public Boolean isTextInList(String text, ArrayList<String> words) {

        for (String word : words) {

            if (word.contains(text.toLowerCase())) {

                return true;
            }
        }
        return false;
    }

    public Boolean isWordInList(String word, ArrayList<String> words) {

        for (String currentWord : words) {

            if (currentWord.equalsIgnoreCase(word)) {

                return true;
            }
        }
        return false;
    }

    public String censorSentence(String sentence, ArrayList words) {

        char[] characters = sentence.toCharArray();

        String censoredSentence = "";
        boolean needsToAddCharacter;

        for (int i = 0; i < characters.length; i++) {

            needsToAddCharacter = false;

            for (int j = i+1; j <= characters.length; j++) {

                needsToAddCharacter = true;
                String word = sentence.substring(i, j);
                if (isTextInList(word, words)) {

                    if (isWordInList(word, words)) {

                        String censoredWord = censorWord(word);
                        censoredSentence += censoredWord;
                        i=j-1;
                        needsToAddCharacter = false;
                        break;
                    }
                }
                else {

                    censoredSentence += characters[i];
                    needsToAddCharacter = false;
                    break;
                }
            }

            if (needsToAddCharacter) {

                censoredSentence += characters[i];
            }

        }
        return censoredSentence;
    }

    public String censorWord(String word) {

        char[] wordToCensor = word.toCharArray();
        for (int i = 1; i < wordToCensor.length; i++) {

            wordToCensor[i] = '*';
        }

        return new String(wordToCensor);
    }

}
