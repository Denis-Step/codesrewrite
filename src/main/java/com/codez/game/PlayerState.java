package com.codez.game;


import java.util.Map;

public class PlayerState {
    public static String[] DEFAULT_TEAMS = {"red", "blue"};

    // Record-like object for storing state of a game.

    private final String[] teams;
    private final int teamTurn; //  Which team is up.
    private final boolean spymasterTurn; // Spymaster goes first, then chooser.
    private final String hint;
    private final int remainingGuesses;

    public PlayerState(int teamTurn, boolean spymasterTurn, String hint, int remainingGuesses, String[] teams) {
        this.teamTurn = teamTurn;
        this.spymasterTurn = spymasterTurn;
        this.hint = hint;
        this.remainingGuesses = remainingGuesses;
        this.teams = teams;
    }


    public static PlayerState newGameState() {
        return PlayerState.newGameState(DEFAULT_TEAMS);
    }

    public static PlayerState newGameState(String[] teams) {
        return new PlayerState(
                0,
                true,
                "",
                0,
                teams
        );
    }


    public int getTeamTurn() {
        return this.teamTurn;
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

    /* Define transitions here: */

    public PlayerState changeTeamTurn() {

        return new PlayerState(this.getNextTeam(),
                true,
                "",
                0,
                this.teams);
    }

    public PlayerState makeSpymasterTurn(String hint, int guesses){
        return new PlayerState(this.teamTurn, false, hint, guesses, this.teams);
    }

    public PlayerState makeChooserMove(){

        if (this.remainingGuesses > 1) {
            return new PlayerState(this.teamTurn,
                    false,
                    this.hint,
                    this.remainingGuesses - 1,
                    this.teams);
        } else {
            return this.changeTeamTurn();
        }

    }

    private int getNextTeam() {
        int nxtTeam;

        if (this.teamTurn == this.teams.length - 1) {
            nxtTeam = 0;
        } else {nxtTeam = this.teamTurn + 1;}

        return nxtTeam;
    }

    }
