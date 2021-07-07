package com.codez.game;

import com.codez.seed.Seeder;
import com.codez.seed.TextFileSource;

import java.util.*;

/*
    Keep business logic in here. PlayerState/WordsState are meant to be immutable Record-like objects.
    Score calculations, game logic, and anything else that requires knowledge of the domain
    should be encapsulated here or in a higher-level class.
 */

public class Game {
    public final String ID;

    public static Seeder seeder = new TextFileSource("/Users/denisstepanenko/Documents/codezrewrite/src/main/java/com/codez/seed/5lenwords.txt");

    private final String[] teams;
    private WordsState wordsState;
    private PlayerState playerState;
    private final Map<String, Integer> score;


    public Game (WordsState wordsState, PlayerState playerState) {
        this.teams = new String[] {"red", "blue"};
        this.wordsState = wordsState;
        this.playerState = playerState;
        this.score = calculateScore();

        // @TODO: Add ID param.

        this.ID = UUID.randomUUID().toString();
    }

    // Business logic related to keys in here.
    private Map<String, Integer> calculateScore () {
        Map<String, ArrayList<String>> values = this.wordsState.getValues();
        Map<String, Integer> scoreMap = new HashMap<>();

        for (String team: this.teams) {
            String chosenKey = team + "-" + team;
             if (values.containsKey(chosenKey)) {
                 scoreMap.put(team, values.get(team).size());
             } else {
                 scoreMap.put(team, 0);
             }
        }

        return scoreMap;
    }

    // default
    public static Game createGame () {
        return Game.createGame(seeder);
    }

    public static Game createGame (Seeder seed) {
        WordsState ws = seed.createBoard();
        PlayerState ps = PlayerState.newGameState();
        return new Game(ws, ps);
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
