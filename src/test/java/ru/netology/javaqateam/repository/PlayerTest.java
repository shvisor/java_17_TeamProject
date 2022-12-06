package ru.netology.javaqateam.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.javaqateam.domain.Game;

public class PlayerTest {

    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
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
}

// если несколько игр
// если нет игр
// тесты на mostPlayerByGenre
