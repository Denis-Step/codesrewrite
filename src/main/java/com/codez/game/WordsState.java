package com.codez.game;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

// Enforce immutability.
// Record-like object for encapsulating state relating to a board's words.
// @TODO: Remove words array, only here for ease of use for now.

public class WordsState {

    private Map<String, String> wordsMap;
    private String[] words;
    private Map<String, ArrayList<String>> values;

    public WordsState(Map<String, String> wordsMap){
        this.wordsMap = wordsMap;
        ArrayList<String> wordList = new ArrayList();
        this.values = new HashMap<>();

        for (Map.Entry<String, String> entry: wordsMap.entrySet()) {
            String word = entry.getKey();
            String value = entry.getValue();

            wordList.add(word);

            // @MUTATION of Map value
            if (this.values.containsKey(value)){
                ArrayList<String> valueWords = this.values.get(value);
                valueWords.add(word);
            }
            else {
                ArrayList<String> valueWords = new ArrayList<String>();
                valueWords.add(word);
                this.values.put(value, valueWords);
            }
        }

        this.words = wordList.toArray(new String[wordList.size()]);
    }

    public String[] getWords() {
        return this.words.clone();
    }


    public Map<String, ArrayList<String>> getValues() {

        Map<String, ArrayList<String>> newValues = new HashMap<>();

        this.values.entrySet()
                .stream().forEach(
                        entry -> newValues.put(
                                entry.getKey(), (ArrayList<String>) entry.getValue().clone()
                        )
        );

        return newValues;
    }

    public Map<String, String> getWordsMap() {
        return wordsMap;
    }
}
