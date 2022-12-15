package ru.netology.javaqateam.repository;

import static org.junit.jupiter.api.Assertions.*;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.javaqateam.domain.Game;

public class GameStoreTest {

    GameStore store = new GameStore();
    Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

    @Test
    public void shouldContainsGameTest() {               // сравниваем по ссылке

        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldContainsFieldGameTest() {          // сравниваем по всем полям

        Game noGame = new Game("Нетология Баттл Онлайн", "Аркады", store);

        assertTrue(store.containsGame(noGame));
    }

    @Test
    public void shouldNotContainsTitleGameTest() {       // сравниваем по полям, не соответствует поле title

        Game noGame = new Game("No title", "Аркады", store);

        assertFalse(store.containsGame(noGame));
    }

    @Test
    public void shouldNotContainsGenreGameTest() {       // сравниваем по полям, не соответствует поле genre

        Game noGame = new Game("Нетология Баттл Онлайн", "No genre", store);

        assertFalse(store.containsGame(noGame));
    }

    @Test
    public void shouldNotContainsStoreGameTest() {        // сравниваем по полям, не соответствует поле store

        Game noGame = new Game("Нетология Баттл Онлайн", "Аркады", null);

        assertFalse(store.containsGame(noGame));
    }

    @Test
    public void shouldNotContainsGameTest() {              // сравниваем по всем полям на несоответствие

        Game noGame = new Game("No title", "No genre", store);

        assertFalse(store.containsGame(noGame));
    }

    @Test
    public void shouldNotContainsNullGameTest() {               // сравниваем на null

        assertFalse(store.containsGame(null));
    }


    @Test
    public void shouldNotEqualsAnotherClassTest() {            // работа метода equals класса Game

        EqualsVerifier.simple().forClass(Game.class)
                .withPrefabValues(Game.class, new Game("Нетология Баттл Онлайн", "Аркады", null), new Game("Нетология ", "Баттл", null))
                .verify();

    }

    @Test
    public void shouldGetSumPlayedTimeTest() {

        store.addPlayTime("Name 1", 3);
        store.addPlayTime("Name 1", 5);
        store.addPlayTime("Name 2", 2);
        store.addPlayTime("Name 3", 1);
        store.addPlayTime("Name 4", 2);
        store.addPlayTime("Name 5", 1);

        int expected = 14;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetSumOnePlayedTimeTest() {

        store.addPlayTime("Name 1", 3);

        int expected = 3;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetSumNoPlayedTimeTest() {

        int expected = 0;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetMostPlayersTest() {                 // не проверяем есть ли два игрока с наибольшем временем (нет в ТЗ)

        store.addPlayTime("Name 1", 3);
        store.addPlayTime("Name 2", 5);
        store.addPlayTime("Name 3", 1);
        store.addPlayTime("Name 4", 4);
        store.addPlayTime("Name 5", 2);

        String expected = "Name 2";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetMostOnePlayerTest() {

        store.addPlayTime("Name 1", 3);

        String expected = "Name 1";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotGetMostPlayerTest() {

        Assertions.assertNull(store.getMostPlayer());
    }
}
