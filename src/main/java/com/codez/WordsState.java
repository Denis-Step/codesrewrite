package com.codez;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

// Record-like object for encapsulating state relating to a board's words.
// Does NOT need size parameter in constructor.

public class WordsState {

    private ArrayList<String> words;
    private Map<String, ArrayList<String>> values;

    public WordsState(Map<String, String> wordsMap){
        this.words = new ArrayList();
        this.values = new HashMap<>();

        for (Map.Entry<String, String> entry: wordsMap.entrySet()) {
            String word = entry.getKey();
            String value = entry.getValue();

            this.words.add(word);

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
    }

    public ArrayList<String> getWords() {
        return (ArrayList<String>) this.words.clone();
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
}
