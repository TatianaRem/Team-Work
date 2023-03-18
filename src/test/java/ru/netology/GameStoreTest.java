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

    @Test
    public void shouldNotContainGame() {
        GameStore store = new GameStore();
        Game game = new Game("Нетология Баттл Онлайн", "Аркады", store);
        assertFalse(store.containsGame(game));
    }


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
        store.addPlayTime("Игрок 1", 7);

        assertEquals("Игрок 1", store.getMostPlayer());
    }


}