package com.codez.seed;

import com.codez.WordsState;

import java.util.Map;

public interface Seeder {

    public String[] getWords();

    public WordsState createBoard();
}
