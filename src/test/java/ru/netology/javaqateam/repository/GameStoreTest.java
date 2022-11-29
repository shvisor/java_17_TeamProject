package ru.netology.javaqateam.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.javaqateam.domain.Game;

public class GameStoreTest {

    GameStore store = new GameStore();

    @Test
    public void shouldContainsGameTest() {

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldNotContainsGameTest() {

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game noGame = new Game("No title", "No genre", store);   //"No title", "No genre"

        assertFalse(store.containsGame(noGame));
    }

    @Test
    public void shouldGetSumPlayedTimeTest() {

        store.addPlayTime("Name 1", 3);
        store.addPlayTime("Name 1", 5);
        store.addPlayTime("Name 1", 1);

        int expected = 9;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetMostPlayerTest() {

        store.addPlayTime("Name 1", 3);
        store.addPlayTime("Name 2", 5);
        store.addPlayTime("Name 3", 1);

        String expected = "Name 2";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotGetMostPlayerTest() {

        Assertions.assertNull(store.getMostPlayer());
    }

}
