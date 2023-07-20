package com.samet.yazlab;

import java.util.*;

public class TextAlgorithm {
    private Text text;

    public TextAlgorithm(Text text) {
        this.text = text;
    }

    ArrayList<String> textList = new ArrayList<String>();

    public void extractSentences() {
        String textStr = text.getText();
        String[] sentences = textStr.split("-");

        for (String sentence : sentences) {
            textList.add(sentence.trim());
        }
    }

    private String compareWords(String word1, String word2) {
        if (word1.contains(word2))
            return word1;
        else if (word2.contains(word1))
            return word2;
        else return "";
    }

    public String finalControl() {
        String sentence = createMergedSentence();
        ArrayList<String> wordsList = new ArrayList<String>();

        String[] wordsArray = sentence.split(" ");

        for (int i = 0; i < wordsArray.length; i++) {
            wordsList.add(wordsArray[i]);
        }
        for (int i = 0; i < wordsList.size() - 1; i++) {
            String extension = compareWords(wordsList.get(i),wordsList.get(i+1));
            if (extension.equals(wordsList.get(i))) {
                wordsList.remove(i+1);
            } else if (extension.equals(wordsList.get(i+1))) {
                wordsList.remove(i);
            }
        }
        return wordsList.toString().replaceAll("\\[|\\]", "").replaceAll(",\\s", " ");
    }

    public String createMergedSentence() {

        StringBuilder mergedSentence = new StringBuilder();

        for (int i = 0; i < textList.size(); i++) {
            String sentence = textList.get(i);

            if (i == 0) {
                // First sentence is included entirely
                mergedSentence.append(sentence);
            } else {
                // Get the common words from the previous sentence and the current sentence
                String[] prevWords = textList.get(i - 1).split(" ");
                String[] currWords = sentence.split(" ");

                List<String> commonWords = new ArrayList<>();
                for (int j = 0; j < prevWords.length; j++) {
                    for (int k = 0; k < currWords.length; k++) {
                        if (prevWords[j].equalsIgnoreCase(currWords[k])) {
                            commonWords.add(prevWords[j].replaceAll("-", ""));
                            break;
                        }
                    }
                }

                // Append the current sentence, skipping common words that have already been included
                String[] words = sentence.split(" ");
                for (int j = 0; j < words.length; j++) {
                    if (!commonWords.contains(words[j].replaceAll("-", ""))) {
                        mergedSentence.append(" ").append(words[j]);
                    }
                }
            }
        }

        return mergedSentence.toString().trim();
    }
}

