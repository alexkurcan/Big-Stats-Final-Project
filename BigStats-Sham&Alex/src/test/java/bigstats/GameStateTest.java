package bigstats;

import bigstats.model.Boss;
import bigstats.model.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    private List<Boss> bosses;
    private GameState  state;

    @BeforeEach
    void setUp() {
        bosses = List.of(
            new Boss("Unit1", 100, Boss.Difficulty.EASY),
            new Boss("Unit2", 100, Boss.Difficulty.MEDIUM),
            new Boss("Unit3", 100, Boss.Difficulty.HARD)
        );
        state = new GameState(bosses);
    }

    @Test
    void initialState_unitBossStage() {
        assertEquals(GameState.Stage.UNIT_BOSS, state.getStage());
        assertEquals(0, state.getCurrentBossIndex());
        assertFalse(state.isFinished());
    }

    @Test
    void initialState_freshStart_zeroDefeated() {
        assertEquals(0, state.getDefeatedBossCount());
    }

    @Test
    void advanceToNextBoss_incrementsIndex() {
        state.advanceToNextBoss();
        assertEquals(1, state.getCurrentBossIndex());
        assertEquals(GameState.Stage.UNIT_BOSS, state.getStage());
    }

    @Test
    void advanceToNextBoss_allDefeated_transitionsToAPExam() {
        state.advanceToNextBoss(); // 1
        state.advanceToNextBoss(); // 2
        state.advanceToNextBoss(); // 3 → AP_EXAM
        assertEquals(GameState.Stage.AP_EXAM, state.getStage());
        assertNull(state.getCurrentBoss());
    }

    @Test
    void setGameOver_setsCorrectState() {
        state.setGameOver();
        assertTrue(state.isGameOver());
        assertTrue(state.isFinished());
        assertFalse(state.isPlayerWon());
    }

    @Test
    void setVictory_setsCorrectState() {
        state.setVictory();
        assertTrue(state.isVictory());
        assertTrue(state.isFinished());
        assertTrue(state.isPlayerWon());
    }

    @Test
    void constructor_nullBosses_throws() {
        assertThrows(IllegalArgumentException.class, () -> new GameState(null));
    }

    @Test
    void constructor_emptyBosses_throws() {
        assertThrows(IllegalArgumentException.class, () -> new GameState(List.of()));
    }

    @Test
    void getTotalBosses_returnsCorrectCount() {
        assertEquals(3, state.getTotalBosses());
    }

    @Test
    void getCurrentBoss_returnsFirstBossInitially() {
        assertEquals("Unit1", state.getCurrentBoss().getUnitName());
    }

    @Test
    void getCurrentBoss_afterAllAdvanced_returnsNull() {
        for (int i = 0; i < bosses.size(); i++) state.advanceToNextBoss();
        assertNull(state.getCurrentBoss());
    }
}
