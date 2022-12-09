package ru.netology.javaqateam.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.javaqateam.domain.Game;

public class PlayerTest {

    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Red Dead Redemption", "Action");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfSeveralGame() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Red Dead Redemption", "Action");
        Game game2 = store.publishGame("Assassins Creed", "Adventure");
        Game game3 = store.publishGame("GTA V", "Action");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);
        player.installGame(game2);
        player.play(game2, 5);
        player.installGame(game3);
        player.play(game3, 4);

        int expected = 7;
        int actual = player.sumGenre("Action");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSumTimePerGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Red Dead Redemption", "Action");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);
        player.play(game, 5);

        int expected = 12;
        int actual = player.play(game, 4);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldException() {
        GameStore store = new GameStore();
        Game game = store.publishGame("GTA V", "Adventure");

        Player player = new Player("Petya");

        Assertions.assertThrows(RuntimeException.class, () -> {
            player.play(game, 5);
        });
    }

    @Test
    public void shouldInstallIfGameExist() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Red Dead Redemption", "Action");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);
        player.installGame(game1);

        int expected = 3;
        int actual = player.play(game1, 0);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldGameMostPlayedByGenre() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Red Dead Redemption", "Action");
        Game game2 = store.publishGame("Assassins Creed", "Adventure");
        Game game3 = store.publishGame("GTA V", "Action");
        Game game4 = store.publishGame("Far Cry V", "Simulator");
        Game game5 = store.publishGame("Call of Duty", "Action");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);
        player.installGame(game2);
        player.play(game2, 5);
        player.installGame(game3);
        player.play(game3, 6);
        player.installGame(game4);
        player.play(game4, 4);
        player.installGame(game5);
        player.play(game5, 1);

        Game expected = game3;
        Game actual = player.mostPlayerByGenre("Action");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldGameMostPlayedWhenNotGenre() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Red Dead Redemption", "Action");
        Game game2 = store.publishGame("Assassins Creed", "Adventure");
        Game game3 = store.publishGame("GTA V", "Action");
        Game game4 = store.publishGame("Far Cry V", "Simulator");
        Game game5 = store.publishGame("Detroit: Become Human", "Adventure");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);
        player.installGame(game2);
        player.play(game2, 5);
        player.installGame(game3);
        player.play(game3, 6);
        player.installGame(game4);
        player.play(game4, 4);
        player.installGame(game5);
        player.play(game5, 1);

        Game expected = null;
        Game actual = player.mostPlayerByGenre("MMORPG");

        Assertions.assertEquals(expected, actual);
    }
}

