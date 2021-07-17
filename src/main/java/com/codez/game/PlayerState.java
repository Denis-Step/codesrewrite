package com.codez.game;

public class PlayerState {

    // Record-like object for storing state of a game.
    // @TODO: Convert fields to use Enums.

    private final String[] teams;
    private final int teamTurn; //  Which team is up.
    private final boolean spymasterTurn; // Spymaster goes first, then chooser.
    private final String hint;
    private final int remainingGuesses;

    // @TODO: More constructors.

    public PlayerState(int teamTurn, boolean spymasterTurn, String hint, int remainingGuesses, String[] teams) {
        this.teamTurn = teamTurn;
        this.spymasterTurn = spymasterTurn;
        this.hint = hint;
        this.remainingGuesses = remainingGuesses;
        this.teams = teams;
    }

    public static PlayerState newGameState() {

        String[] teams = {"red", "blue"};
        return new PlayerState(
                0,
                true,
                "",
                0,
                teams
        );
    }

    public String getTeamTurn() {
        return this.teams[this.teamTurn];
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

    public String[] getTeams() {return teams;}

    public PlayerState nextTeamTurn() {
        int nxtTeam;

        if (this.teamTurn == this.teams.length - 1) {
            nxtTeam = 0;
        } else {nxtTeam = this.teamTurn + 1;}

        return new PlayerState(nxtTeam, true, "", 0, this.teams);
    }

    }
