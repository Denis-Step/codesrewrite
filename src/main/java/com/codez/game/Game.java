package com.codez.game;

import com.codez.seed.Seeder;
import com.codez.seed.TextFileSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
    Keep business logic in here. PlayerState/WordsState are meant to be immutable Record-like objects.
    Score calculations, game logic, and anything else that requires knowledge of the domain
    should be encapsulated here or in a higher-level class.
 */

public class Game {
    private static Seeder seeder = new TextFileSource("/Users/denisstepanenko/Documents/codezrewrite/src/main/java/com/codez/seed/5lenwords.txt");

    private String[] teams;
    private WordsState wordsState;
    private PlayerState playerState;

    private Map<String, Integer> score;


    public Game (WordsState wordsState, PlayerState playerState) {
        this.teams = new String[] {"red", "blue"};
        this.wordsState = wordsState;
        this.playerState = playerState;
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

    public Map<String, ArrayList<String>> getBoardState (){
        return this.wordsState.getValues();
    }

}
