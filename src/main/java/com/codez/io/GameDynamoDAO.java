package com.codez.io;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.codez.game.Game;
import com.codez.game.PlayerState;
import com.codez.game.WordsState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@DynamoDBTable(tableName="Games")
public class GameDynamoDAO {


    private String ID;

    // PS variables
    private List<String> teams;
    private int teamTurn;
    private boolean spymasterTurn;
    private String hint;
    private int remainingGuesses;

    // WS variables
    private Map<String, String> words;

    public GameDynamoDAO() {}

    public GameDynamoDAO(Game g) {
        this.ID = g.ID;
        this.teams = Arrays.asList(g.getTeams());
        this.teamTurn = g.getTeamTurn();
        this.spymasterTurn = g.getSpymasterTurn();
        this.hint = g.getHint();
        this.remainingGuesses = g.getRemainingGuesses();

        this.words = g.getBoard();
    }

    @DynamoDBHashKey(attributeName = "ID")
    public String getID() {return this.ID;}

    public void setID(String ID) {this.ID = ID;}

    @DynamoDBAttribute(attributeName = "teams")
    public List<String> getTeams() {
        return teams;
    }

    public void setTeams(List<String> teams) {
        this.teams = teams;
    }


    @DynamoDBAttribute(attributeName = "spymasterTurn")
    public boolean getSpymasterTurn() {
        return spymasterTurn;
    }

    public void setSpymasterTurn(boolean spymasterTurn) {
        this.spymasterTurn = spymasterTurn;
    }

    @DynamoDBAttribute(attributeName = "teamTurn")
    public int getTeamTurn() {
        return teamTurn;
    }

    public void setTeamTurn(int teamTurn) {
        this.teamTurn = teamTurn;
    }

    @DynamoDBAttribute(attributeName = "hint")
    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    @DynamoDBAttribute(attributeName = "remainingGuesses")
    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    public void setRemainingGuesses(int remainingGuesses) {
        this.remainingGuesses = remainingGuesses;
    }

    @DynamoDBAttribute(attributeName = "words")
    public Map<String, String> getWords() {
        return words;
    }

    public void setWords(Map<String, String> words) {
        this.words = words;
    }

    @DynamoDBIgnore
    public Game getGame() {
        WordsState ws = new WordsState(getWords());
        PlayerState ps = new PlayerState(getTeamTurn(),
                getSpymasterTurn(),
                getHint(),
                getRemainingGuesses(),
                getTeams().toArray(new String[0]));

        Game g = new Game(ws, ps, getID());
        return g;
    }
}
