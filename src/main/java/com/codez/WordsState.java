package com.codez;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

// Record-like object for encapsulating state relating to a board's words.
// Does NOT need size parameter in constructor.

public class WordsState {

    private ArrayList<String> words;
    private Map<String, ArrayList<String>> values;

    public WordsState(Map<String, String> wordsMap){
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
            }
        }
    }

    public ArrayList<String> getWords() {
        return (ArrayList<String>) this.words.clone();
    }

    public Map<String, ArrayList<String>> getValues() {
        // Deep-copy for immutability.

        Map<String, ArrayList<String>> newValues = new HashMap<String, ArrayList<String>>();

        for (Map.Entry<String, ArrayList<String>> entry : this.values.entrySet()){
            ArrayList<String > listCopy = (ArrayList<String>) entry.getValue().clone();

            for (String word : entry.getValue()){
                listCopy.add(word);
            }
            newValues.put(entry.getKey(), listCopy);
        }

        return newValues;

    }
}
