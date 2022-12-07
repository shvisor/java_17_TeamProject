package ru.netology.javaqateam.repository;

import ru.netology.javaqateam.domain.Game;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private String name;

    /**
     * информация о том, в какую игру сколько часов было сыграно
     * ключ - игра
     * значение - суммарное количество часов игры в эту игру
     */
    private Map<Game, Integer> playedTime = new HashMap<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * добавление игры игроку
     * если игра уже была, никаких изменений происходить не должно
     */
    public void installGame(Game game) { // проверил, все ок shouldInstallGame
        playedTime.put(game, 0);
    }

    /**
     * игрок играет в игру game на протяжении hours часов
     * об этом нужно сообщить объекту-каталогу игр, откуда была установлена игра
     * также надо обновить значения в мапе игрока, добавив проигранное количество часов
     * возвращает суммарное количество часов, проигранное в эту игру.
     * если игра не была установлена, то надо выкидывать RuntimeException
     */
    public int play(Game game, int hours) {
        if (playedTime.containsKey(game)) {
            game.getStore().addPlayTime(name, hours); // перенесли в разветвление что б не сообщать каталогу если игра не инстолированна
            int value = playedTime.get(game) + hours; // Суммируем и возвращем в мапу
            playedTime.put(game, value);
        } else {
            throw new NotFoundException( // отрабатываем если не была инстолированна
                    "Game with name: " + "<" + game.getTitle() + ">" + " not installed.");
        }
        return playedTime.get(game);
    }

    /**
     * Метод принимает жанр игры (одно из полей объекта игры) и
     * суммирует время, проигранное во все игры этого жанра этим игроком
     */
    public int sumGenre(String genre) {
        int sum = 0;
        for (Game game : playedTime.keySet()) {
            if (game.getGenre().equals(genre)) {
                sum += playedTime.get(game);
            }
        }
        return sum;
    }

    /**
     * Метод принимает жанр и возвращает игру этого жанра, в которую играли больше всего
     * Если в игры этого жанра не играли, возвращается null
     */
    public Game mostPlayerByGenre(String genre) {
        int sum = 0;
        Game gameOfGenrePlayedMost = null;            // если жанра нет вернем null
        for (Game game : playedTime.keySet()) {       // перебираем по ключу все игры
            if (game.getGenre().equals(genre)) {      // проверяем на жанр
                if (playedTime.get(game) > sum) {     // ищем максимальное значение
                    sum = playedTime.get(game);
                    gameOfGenrePlayedMost = game;
                }
            }
        }
        return gameOfGenrePlayedMost;
    }
}
