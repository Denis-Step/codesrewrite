package com.codez.game;

public class PlayerState {

    // Record-like object for storing state of a game.
    // @TODO: Convert fields to use Enums.

    private final String teamTurn; //  Which team is up.
    private final boolean spymasterTurn; // Spymaster goes first, then chooser.
    private final String hint;
    private final int remainingGuesses;

    // @TODO: More constructors.

    public PlayerState(String teamTurn, boolean spymasterTurn, String hint, int remainingGuesses) {
        this.teamTurn = teamTurn;
        this.spymasterTurn = spymasterTurn;
        this.hint = hint;
        this.remainingGuesses = remainingGuesses;
    }

    public static PlayerState newGameState() {
        return new PlayerState(
                "red",
                true,
                "",
                0
        );
    }

    public String getTeamTurn() {
        return teamTurn;
    }

    public boolean getSpymasterTurn() {
        return spymasterTurn;
    }

    public String getHint() {
        return hint;
    }

    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    }
