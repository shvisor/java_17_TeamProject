package ru.netology.javaqateam.domain;

import ru.netology.javaqateam.repository.GameStore;

public class GameTwo {
    private String title;
    private String genre;
    private GameStore store;

    public GameTwo(String title, String genre, GameStore store) {
        this.title = title;
        this.genre = genre;
        this.store = store;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public GameStore getStore() {
        return store;
    }
}
