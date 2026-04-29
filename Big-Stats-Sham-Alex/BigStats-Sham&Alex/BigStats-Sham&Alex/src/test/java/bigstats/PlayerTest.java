package bigstats;

import bigstats.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Alex", 100);
    }

    // ── construction ──────────────────────────────────────────────────────────

    @Test
    void constructor_setsNameAndMaxHealth() {
        assertEquals("Alex", player.getName());
        assertEquals(100, player.getMaxHealth());
        assertEquals(100, player.getHealth());
    }

    @Test
    void constructor_defaultMaxHealth() {
        Player p = new Player("Sham");
        assertEquals(Player.DEFAULT_MAX_HEALTH, p.getMaxHealth());
    }

    @Test
    void constructor_invalidMaxHealth_throws() {
        assertThrows(IllegalArgumentException.class, () -> new Player("X", 0));
        assertThrows(IllegalArgumentException.class, () -> new Player("X", -5));
    }

    // ── damage ─────────────────────────────────────────────────────────────────

    @Test
    void takeDamage_reducesHealth() {
        player.takeDamage(30);
        assertEquals(70, player.getHealth());
    }

    @Test
    void takeDamage_exactlyZero_triggersGameOver() {
        player.takeDamage(100);
        assertEquals(0, player.getHealth());
        assertFalse(player.isAlive());
    }

    @Test
    void takeDamage_moreThanHealth_clipsToZero() {
        player.takeDamage(999);
        assertEquals(0, player.getHealth());
        assertFalse(player.isAlive());
    }

    @Test
    void takeDamage_negative_throws() {
        assertThrows(IllegalArgumentException.class, () -> player.takeDamage(-1));
    }

    // ── heal ──────────────────────────────────────────────────────────────────

    @Test
    void heal_restoresHealth() {
        player.takeDamage(40);
        player.heal(20);
        assertEquals(80, player.getHealth());
    }

    @Test
    void heal_cappedAtMaxHealth() {
        player.heal(50);
        assertEquals(100, player.getHealth());
    }

    @Test
    void heal_negative_throws() {
        assertThrows(IllegalArgumentException.class, () -> player.heal(-1));
    }

    // ── scoring ───────────────────────────────────────────────────────────────

    @Test
    void recordCorrect_incrementsScoreAndTotal() {
        player.recordCorrect();
        assertEquals(1, player.getScore());
        assertEquals(1, player.getTotalAnswered());
    }

    @Test
    void recordIncorrect_incrementsTotalOnly() {
        player.recordIncorrect();
        assertEquals(0, player.getScore());
        assertEquals(1, player.getTotalAnswered());
    }

    @Test
    void accuracy_noAnswers_returnsZero() {
        assertEquals(0.0, player.getAccuracy());
    }

    @Test
    void accuracy_allCorrect_returnsOne() {
        player.recordCorrect();
        player.recordCorrect();
        assertEquals(1.0, player.getAccuracy(), 0.001);
    }

    @Test
    void accuracy_mixed() {
        player.recordCorrect();
        player.recordIncorrect();
        assertEquals(0.5, player.getAccuracy(), 0.001);
    }
}
