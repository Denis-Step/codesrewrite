package com.codez.game;

import com.codez.seed.Seeder;
import com.codez.seed.TextFileSource;
import org.apache.commons.lang3.ArrayUtils;


import java.util.*;

/*
    Keep business logic in here. PlayerState/WordsState are meant to be immutable Record-like objects.
    Score calculations, game logic, and anything else that requires knowledge of the domain
    should be encapsulated here or in a higher-level class.
 */

public class Game {
    public final String ID;

    private final String[] teams;
    private WordsState wordsState;
    private PlayerState playerState;
    private final Map<String, Integer> score;


    // @TODO: Add ID param.
    public Game (WordsState wordsState, PlayerState playerState) {
        this.teams = new String[] {"red", "blue"};
        this.wordsState = wordsState;
        this.playerState = playerState;
        this.score = calculateScore();
        this.ID = UUID.randomUUID().toString();
    }

    public Game (WordsState wordsState) {
        this(wordsState, PlayerState.newGameState());
    }

    // Business logic related to keys in here.
    private Map<String, Integer> calculateScore () {
        Map<String, ArrayList<String>> values = this.wordsState.getValues();
        System.out.println(values);

        // Init scoreMap for all teams.
        Map<String, Integer> scoreMap = new HashMap<>();

        for (String team: this.teams) {
            scoreMap.put(team, 0);
        }

        for (Map.Entry<String, ArrayList<String>> entry: values.entrySet()) {
            String[] key = entry.getKey().split("-");

            // Avoid looking at unchosen words.
            if (key.length < 2) {
                continue;
            }

            // Avoid grabbing score for neutral and black.
            // This checks score based on the second part of the key being chosen.
            if (scoreMap.containsKey(key[0])){

                Integer score = scoreMap.get(key[1]);
                scoreMap.put(key[1], score++);
            }
        }

        return scoreMap;
    }

    public String[] getBoardWords (){
        return this.wordsState.getWords();
    }

    public Map<String, String> getBoard(){
        return this.wordsState.getWordsMap();
    }

    public Map<String, Integer> getScore() {
        return score;
    }

    public Map<String, String> getTurn() {
        Map<String, String> turnMap = new HashMap<>();

        turnMap.put("teamTurn", playerState.getTeamTurn());
        turnMap.put("spymasterTurn", Boolean.toString(playerState.getSpymasterTurn()));
        turnMap.put("hint", playerState.getHint());
        turnMap.put("remainingGuesses", Integer.toString(playerState.getRemainingGuesses()));

        return turnMap;
    }
}
