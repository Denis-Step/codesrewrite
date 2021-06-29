package com.codez;
import java.util.Map;

public class GameState {

    // Record-like object for storing state of a game.
    // @TODO: Convert fields to use Enums.
    // @TODO: Implement java.serializable.

    private final String teamTurn; //  Which team is up.
    private final boolean spymasterTurn; // Spymaster goes first, then chooser.
    private final String hint;
    private final int remainingGuesses;

    // @TODO: More constructors.

    public GameState (String teamTurn, boolean spymasterTurn, String hint, int remainingGuesses) {
        this.teamTurn = teamTurn;
        this.spymasterTurn = spymasterTurn;
        this.hint = hint;
        this.remainingGuesses = remainingGuesses;
    }

    public String getTeamTurn() {
        return teamTurn;
    }

    public boolean isSpymasterTurn() {
        return spymasterTurn;
    }

    public String getHint() {
        return hint;
    }

    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    }
