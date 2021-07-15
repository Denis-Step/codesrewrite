package com.codez.game;

public class PlayerState {

    // Record-like object for storing state of a game.
    // @TODO: Convert fields to use Enums.

    private final String[] teams;
    private final String teamTurn; //  Which team is up.
    private final boolean spymasterTurn; // Spymaster goes first, then chooser.
    private final String hint;
    private final int remainingGuesses;

    // @TODO: More constructors.

    public PlayerState(String teamTurn, boolean spymasterTurn, String hint, int remainingGuesses, String[] teams) {
        this.teamTurn = teamTurn;
        this.spymasterTurn = spymasterTurn;
        this.hint = hint;
        this.remainingGuesses = remainingGuesses;
        this.teams = teams;
    }

    public static PlayerState newGameState() {

        String[] teams = {"red", "blue"};
        return new PlayerState(
                "red",
                true,
                "",
                0,
                teams
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
