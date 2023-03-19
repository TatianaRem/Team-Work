package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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

    //переустановка игры и подсчет суммы часов
    @Test
    public void shouldSumAddDoubleGame() {
        GameStore store = new GameStore();
        Player player = new Player("Petya");

        Game game1 = store.publishGame("Hitman", "стелс-утер");
        Game game2 = store.publishGame("Need For Speed", "аркада");
        Game game3 = store.publishGame("Mortal Kombat", "файтинг");
        Game game4 = store.publishGame("Stronghold Crusader", "стратегия");
        Game game5 = store.publishGame("Call of Duty", "шутер");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);

        player.play(game1, 5);
        player.installGame(game1);

        int expected = 5;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    //подсчет суммарного количества сыгранного времени в игру одного жанра
    @Test
    public void shouldCheckSumGenreTwoGames() {
        GameStore store = new GameStore();
        Player player = new Player("Petya");

        Game game1 = store.publishGame("Hitman", "шутер");
        Game game2 = store.publishGame("Need For Speed", "аркада");
        Game game3 = store.publishGame("Mortal Kombat", "файтинг");
        Game game4 = store.publishGame("Stronghold Crusader", "стратегия");
        Game game5 = store.publishGame("Call of Duty", "шутер");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);

        player.play(game1, 3);
        player.play(game5, 3);

        int expected = 6;
        int actual = player.sumGenre("шутер");
        assertEquals(expected, actual);
    }

    //подсчет суммарного количества времени, если наиграно 0 минут
    // тест проходит
    @Test
    public void shouldCheckSumGenreZeroTime() {
        GameStore store = new GameStore();
        Player player = new Player("Petya");

        Game game1 = store.publishGame("Hitman", "шутер");
        Game game2 = store.publishGame("Need For Speed", "аркада");
        Game game3 = store.publishGame("Mortal Kombat", "файтинг");
        Game game4 = store.publishGame("Stronghold Crusader", "стратегия");
        Game game5 = store.publishGame("Call of Duty", "шутер");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);

        player.play(game1, 0);
        player.play(game2, 0);
        player.play(game3, 0);
        player.play(game4, 0);
        player.play(game5, 0);

        int expected = 0;
        int actual = player.sumGenre(game1.getGenre());

        assertEquals(expected, actual);
    }

    //Выпадение RuntimeException, если игра не установлена
    // не установленно ни одной игры
    @Test
    public void shouldCheckPlayWithoutInstall() {
        GameStore store = new GameStore();
        Player player = new Player("Petya");

        Game game1 = store.publishGame("Hitman", "шутер");
        Game game2 = store.publishGame("Need For Speed", "аркада");
        Game game3 = store.publishGame("Mortal Kombat", "файтинг");
        Game game4 = store.publishGame("Stronghold Crusader", "стратегия");
        Game game5 = store.publishGame("Call of Duty", "шутер");

        assertThrows(RuntimeException.class, () ->
                player.play(game2, 1)
        );
    }

    //Проверка выпадения RunTimeException, если хотя бы одна игра установлена
    @Test
    public void shouldCheckRunTimeException() {
        GameStore store = new GameStore();
        Player player = new Player("Petya");

        Game game1 = store.publishGame("Hitman", "шутер");
        Game game2 = store.publishGame("Need For Speed", "аркада");
        Game game3 = store.publishGame("Mortal Kombat", "файтинг");
        Game game4 = store.publishGame("Stronghold Crusader", "стратегия");
        Game game5 = store.publishGame("Call of Duty", "шутер");

        player.installGame(game1);
        player.installGame(game2);

        assertThrows(RuntimeException.class, () -> {
            player.play(game3, 1);

        });
    }

    // вывод жанра игры, в который было сыграно больше всего часов
    @Test
    public void shouldCheckMostPlayerByGenre() {
        GameStore store = new GameStore();
        Player player = new Player("Petya");

        Game game1 = store.publishGame("Hitman", "шутер");
        Game game2 = store.publishGame("Need For Speed", "аркада");
        Game game3 = store.publishGame("Mortal Kombat", "файтинг");
        Game game4 = store.publishGame("Stronghold Crusader", "стратегия");
        Game game5 = store.publishGame("Call of Duty", "шутер");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);

        player.play(game1, 1);
        player.play(game2, 3);
        player.play(game3, 2);
        player.play(game4, 6);
        player.play(game5, 4);

        Game expected = game4;
        Game actual = player.mostPlayerByGenre("стратегия");

        assertEquals(expected, actual);
    }

    // если в игру данного жанра не играли, возвращается null
    @Test
    public void shouldCheckMostNoPlayerGenre() {
        GameStore store = new GameStore();
        Player player = new Player("Petya");

        Game game1 = store.publishGame("Hitman", "шутер");
        Game game2 = store.publishGame("Need For Speed", "аркада");
        Game game3 = store.publishGame("Mortal Kombat", "файтинг");
        Game game4 = store.publishGame("Stronghold Crusader", "стратегия");
        Game game5 = store.publishGame("Call of Duty", "шутер");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game5);

        player.play(game1, 1);
        player.play(game2, 3);
        player.play(game3, 2);
        player.play(game5, 6);

        String expected = null;
        Game actual = player.mostPlayerByGenre("стратегия");

        assertEquals(expected, actual);
    }
}