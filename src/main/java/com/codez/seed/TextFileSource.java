package com.codez.seed;
import com.codez.game.WordsState;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Stream;

public class TextFileSource implements Seeder {

    private String[] words;
    private Random random = new Random();

    public TextFileSource (String path) {
        Charset charset = Charset.forName("US-ASCII");
        ArrayList<String> wordsList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            line = reader.readLine();

            while (line != null){
                if (line != ""){
                    wordsList.add(line.trim());
                }
                line = reader.readLine();
            }

        }
        catch (IOException exception) {
            System.err.println(exception);
        }
        words = wordsList.toArray(new String[1000]);

    }

    public Stream<String> getAllWords(){
        return Arrays.stream(words);
    }

    // Default is for 9 red, 8 blue, 1 black, 7 neutral
    public WordsState createBoard() {

        Map<String, String> boardMap = new HashMap<>();
        int sizeWords = words.length;
        System.out.println(sizeWords);

        // Red
        for (int i = 0; i < 9; i++) {

            int randInt = random.nextInt(sizeWords);
            String randWord = words[randInt];

            if (!boardMap.containsKey(randWord)){
                boardMap.put(randWord, "red");
            } else {
                // Repeat loop if word already in map.
                i--;
            }

        }

        // Blue
        for (int i = 0; i < 8; i++) {
            int randInt = random.nextInt(sizeWords);
            String randWord = words[randInt];

            if (!boardMap.containsKey(randWord)){
                boardMap.put(randWord, "blue");
            } else {
                // Repeat loop if word already in map.
                i--;
            }
        }

        for (int i = 0; i < 1; i++) {
            int randInt = random.nextInt(sizeWords);
            String randWord = words[randInt];

            if (!boardMap.containsKey(randWord)){
                boardMap.put(randWord, "black");
            } else {
                // Repeat loop if word already in map.
                i--;
            }
        }

        // Neutral
        for (int i = 0; i < 7; i++) {
            int randInt = random.nextInt(sizeWords);
            String randWord = words[randInt];

            if (!boardMap.containsKey(randWord)){
                boardMap.put(randWord, "neutral");
            } else {
                // Repeat loop if word already in map.
                i--;
            }
        }

        System.out.println(boardMap);
        return new WordsState(boardMap);

    }
}
