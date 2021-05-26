package com.efortshub.demo.timermovie;

import java.io.Serializable;

public class MovieHintPattern implements Serializable {
    private int word;
    private int character;

    public MovieHintPattern(int word, int character) {
        this.word = word;
        this.character = character;
    }

    public int getWord() {
        return word;
    }

    public int getCharacter() {
        return character;
    }
}
