package com.codez;
import java.util.Map;

public class GameState {

    // Record-like object for storing state of a game.
    // @TODO: Convert fields to use Enums.
    // @TODO: Implement java.serializable.

    private String winner;
    private int redScore;
    private int blueScore;

    private String teamTurn; //  Which team is up.
    private boolean spymasterTurn; // Spymaster goes first, then chooser.
    private int remainingGuesses;

    private int numWords;
    private int numToWin;

    private Map<String, String> wordsState;

    // @TODO: More constructors.

    public GameState (Map<String, String> wordsState, String teamTurn, boolean spymasterTurn, int remainingGuesses, int numToWin) {
        this.wordsState = wordsState;
        this.teamTurn = teamTurn;
        this.spymasterTurn = spymasterTurn;
        this.remainingGuesses = remainingGuesses;
        this.numToWin = numToWin;

        // @TODO: Add in methods for figuring out derived fields.
    }



}
