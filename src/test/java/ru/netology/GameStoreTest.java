package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GameStoreTest {

    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    // Проверка отсутствия игры в каталоге
    @Test
    public void shouldNotContainGame() {
        GameStore store = new GameStore();
        Game game = new Game("Нетология Баттл Онлайн", "Аркады", store);
        assertFalse(store.containsGame(game));
    }

    // Проверка наличия игры в каталоге
    @Test
    public void shouldContainGame() {
        GameStore store = new GameStore();
        Game game1 = new Game("Нетология Баттл Онлайн", "Аркады", store);
        Game game2 = new Game("Call of Duty", "Шутеры", store);
        Game game3 = new Game("Mortal Combat", "Файтинги", store);

        Game game = store.publishGame("Call of Duty", "Шутеры");

        assertTrue(store.containsGame(game));
    }

    // Ищет игрока, который играл в игры этого каталога больше всего времени
    // (должно суммироваться время, если игрок указан несколько раз) 
    @Test
    public void shouldGetPlayerWithMostPlayedHoursTwise() {
        GameStore store = new GameStore();
        store.publishGame("Нетология Баттл Онлайн", "Аркады");
        store.publishGame("Call of Duty", "Шутеры");
        store.publishGame("Mortal Combat", "Файтинги");
        store.publishGame("Fortnite", "Выживание");

        store.addPlayTime("Игрок 1", 12);
        store.addPlayTime("Игрок 2", 2);
        store.addPlayTime("Игрок 3", 15);
        store.addPlayTime("Игрок 1", 7);

        assertEquals("Игрок 1", store.getMostPlayer());
    }

    // Ищет одного игрока, который играл в игры этого каталога больше всего времени
    @Test
    public void shouldGetPlayerWithMostPlayedHours() {
        GameStore store = new GameStore();
        store.publishGame("Нетология Баттл Онлайн", "Аркады");
        store.publishGame("Call of Duty", "Шутеры");
        store.publishGame("Mortal Combat", "Файтинги");
        store.publishGame("Fortnite", "Выживание");

        store.addPlayTime("Игрок 1", 12);
        store.addPlayTime("Игрок 2", 2);
        store.addPlayTime("Игрок 3", 15);

        assertEquals("Игрок 3", store.getMostPlayer());
    }

    // Если игроков нет, то результатом должен быть null
    @Test
    public void shouldGetNoPlayerWithMostPlayedHours() {
        GameStore store = new GameStore();
        store.publishGame("Нетология Баттл Онлайн", "Аркады");
        store.publishGame("Call of Duty", "Шутеры");
        store.publishGame("Mortal Combat", "Файтинги");
        store.publishGame("Fortnite", "Выживание");

        assertEquals(null, store.getMostPlayer());
    }

    // Расчет общего количества времени всех игроков, проведенное за играми этого каталога
    @Test
    public void shouldSumAllPlayTime() {
        GameStore store = new GameStore();
        store.publishGame("Нетология Баттл Онлайн", "Аркады");
        store.publishGame("Call of Duty", "Шутеры");
        store.publishGame("Mortal Combat", "Файтинги");
        store.publishGame("Fortnite", "Выживание");

        store.addPlayTime("Игрок 1", 12);
        store.addPlayTime("Игрок 2", 28);
        store.addPlayTime("Игрок 3", 15);
        store.addPlayTime("Игрок 1", 7);

        assertEquals(62, store.getSumPlayedTime());
    }
}