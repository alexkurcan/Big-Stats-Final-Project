package bigstats;

import bigstats.model.Boss;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BossTest {

    @Test
    void constructor_setsFields() {
        Boss boss = new Boss("Unit 1", 100, Boss.Difficulty.EASY);
        assertEquals("Unit 1", boss.getUnitName());
        assertEquals(100, boss.getMaxHealth());
        assertEquals(100, boss.getHealth());
        assertEquals(Boss.Difficulty.EASY, boss.getDifficulty());
    }

    @Test
    void constructor_invalidHealth_throws() {
        assertThrows(IllegalArgumentException.class, () -> new Boss("X", 0, Boss.Difficulty.EASY));
    }

    @Test
    void takeDamage_reducesHealth() {
        Boss boss = new Boss("Unit 2", 80, Boss.Difficulty.MEDIUM);
        boss.takeDamage(30);
        assertEquals(50, boss.getHealth());
    }

    @Test
    void takeDamage_exactlyZero_isDefeated() {
        Boss boss = new Boss("Unit 3", 50, Boss.Difficulty.HARD);
        boss.takeDamage(50);
        assertEquals(0, boss.getHealth());
        assertTrue(boss.isDefeated());
    }

    @Test
    void takeDamage_moreThanHealth_clipsToZero() {
        Boss boss = new Boss("Unit 4", 30, Boss.Difficulty.EASY);
        boss.takeDamage(999);
        assertEquals(0, boss.getHealth());
        assertTrue(boss.isDefeated());
    }

    @Test
    void takeDamage_negative_throws() {
        Boss boss = new Boss("Unit 5", 100, Boss.Difficulty.EASY);
        assertThrows(IllegalArgumentException.class, () -> boss.takeDamage(-5));
    }

    @Test
    void damageValues_easy() {
        Boss boss = new Boss("U", 100, Boss.Difficulty.EASY);
        assertEquals(10, boss.getPlayerDamagePerWrong());
        assertEquals(25, boss.getBossDamagePerCorrect());
    }

    @Test
    void damageValues_medium() {
        Boss boss = new Boss("U", 100, Boss.Difficulty.MEDIUM);
        assertEquals(15, boss.getPlayerDamagePerWrong());
        assertEquals(20, boss.getBossDamagePerCorrect());
    }

    @Test
    void damageValues_hard() {
        Boss boss = new Boss("U", 100, Boss.Difficulty.HARD);
        assertEquals(20, boss.getPlayerDamagePerWrong());
        assertEquals(15, boss.getBossDamagePerCorrect());
    }

    @Test
    void isDefeated_falseWhenHealthPositive() {
        Boss boss = new Boss("U", 100, Boss.Difficulty.EASY);
        assertFalse(boss.isDefeated());
    }
}
