package com.codez.game;


import com.codez.io.GameDynamoIOController;
import com.codez.io.IOController;
import com.codez.io.RedisController;
import com.codez.seed.Seeder;
import com.codez.seed.TextFileSource;

import java.util.*;

/*
    Keep business logic in here. PlayerState/WordsState are meant to be immutable Record-like objects.
    Score calculations, game logic, and anything else that requires knowledge of the domain
    should be encapsulated here or in a higher-level class.
 */

public class Game {
    public static Seeder seed = new TextFileSource("/Users/denisstepanenko/Documents/codezrewrite/src/main/java/com/codez/seed/5lenwords.txt");
    public static GameBuilder gb = new GameBuilder(seed);
    public static IOController io = new GameDynamoIOController();

    public final String ID;

    private WordsState ws;
    private PlayerState ps;
    private final Map<String, Integer> score;

    public Game (WordsState wordsState, PlayerState ps, String ID) {
        this.ws = wordsState;
        this.ps = ps;
        this.score = calculateScore();
        this.ID = ID;
    }

    public Game (WordsState wordsState, PlayerState ps) {
        this(wordsState, ps, UUID.randomUUID().toString());
    }

    public Game (WordsState wordsState) {
        this(wordsState, PlayerState.newGameState());
    }

    public static Game createGame(){
        return Game.gb.build();
    }

    public static Game getGame(String ID) {
        return io.getGame(ID);
    }

    public String[] getBoardWords (){
        return this.ws.getWords();
    }

    public Map<String, String> getBoard(){
        return this.ws.getWordsMap();
    }

    public Map<String, Integer> getScore() {
        return this.score;
    }

    public Map<String, String> getTurn() {
        Map<String, String> turnMap = new HashMap<>();

        turnMap.put("teams", String.join(",", ps.getTeams()));
        turnMap.put("teamTurn", String.valueOf(ps.getTeamTurn()));
        turnMap.put("spymasterTurn", Boolean.toString(ps.getSpymasterTurn()));
        turnMap.put("hint", ps.getHint());
        turnMap.put("remainingGuesses", Integer.toString(ps.getRemainingGuesses()));

        return turnMap;
    }

    public Map<String, Map<String, String>> toMap () {
        Map<String, Map<String, String>> gameMap = new HashMap<>();
        gameMap.put("playerState", this.getTurn());
        gameMap.put("wordsState", this.getBoard());

        return gameMap;
    }

    // Business logic related to keys in here.
    private Map<String, Integer> calculateScore () {
        Map<String, ArrayList<String>> values = this.ws.getValues();

        // Init scoreMap for all teams.
        Map<String, Integer> scoreMap = new HashMap<>();

        for (String team: this.ps.getTeams()) {
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

    public String[] getTeams() {
        return this.ps.getTeams();
    }

    public int getTeamTurn() {
        return this.ps.getTeamTurn();
    }

    public boolean getSpymasterTurn() {
        return this.ps.getSpymasterTurn();
    }

    public String getHint() {
        return this.ps.getHint();
    }

    public int getRemainingGuesses() {
        return this.ps.getRemainingGuesses();
    }

    /* Assume moves are valid farther up the stack. */

    public Game makeSpymasterTurn(String hint, int guesses){
        PlayerState newPS = this.ps.makeSpymasterTurn(hint, guesses);
        return new Game(this.ws, newPS, this.ID);
    };

    public Game makeChooserMove(String word){
        String team = this.ps.getTeams()[this.ps.getTeamTurn()];
        WordsState newWS;
        PlayerState newPS;

        if (this.ws.getWordValue(word) == team) {
            newWS = this.ws.chooseWord(word, team);
            newPS = this.ps.makeChooserMove();
        }
        else {
            newWS = this.ws.chooseWord(word, team);
            newPS = this.ps.changeTeamTurn();
        }

       return new Game(newWS, newPS, this.ID);
    }

    /*public static boolean exists(String ID) {
        return io.exists(ID);
    }*/

    public static Game getGameById(String ID) {
        return io.getGame(ID);
    }

    public void save() {
        io.save(this);
    }

}
