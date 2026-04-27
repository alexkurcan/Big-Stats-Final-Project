package bigstats.model;

import java.util.List;

/**
 * Tracks overall game progression through boss stages.
 */
public class GameState {

    public enum Stage { UNIT_BOSS, AP_EXAM, GAME_OVER, VICTORY }

    private final List<Boss> bosses;        // ordered list of unit bosses
    private int currentBossIndex;           // index into bosses (before AP exam)
    private Stage stage;
    private boolean playerWon;

    public GameState(List<Boss> bosses) {
        if (bosses == null || bosses.isEmpty()) {
            throw new IllegalArgumentException("Boss list cannot be null or empty.");
        }
        this.bosses            = List.copyOf(bosses);
        this.currentBossIndex  = 0;
        this.stage             = Stage.UNIT_BOSS;
        this.playerWon         = false;
    }

    // ── current boss ─────────────────────────────────────────────────────────

    /** Returns the currently active unit boss, or null if we are past all unit bosses. */
    public Boss getCurrentBoss() {
        if (currentBossIndex < bosses.size()) {
            return bosses.get(currentBossIndex);
        }
        return null;
    }

    public int getCurrentBossIndex() { return currentBossIndex; }
    public int getTotalBosses()      { return bosses.size(); }
    public List<Boss> getBosses()    { return bosses; }

    /** How many unit bosses have been defeated so far. */
    public int getDefeatedBossCount() { return currentBossIndex; }

    // ── stage transitions ────────────────────────────────────────────────────

    public Stage getStage() { return stage; }

    /**
     * Advance to the next boss. If all unit bosses are defeated, transitions to AP_EXAM.
     */
    public void advanceToNextBoss() {
        currentBossIndex++;
        if (currentBossIndex >= bosses.size()) {
            stage = Stage.AP_EXAM;
        }
    }

    /** Called when the player loses all HP. */
    public void setGameOver() {
        stage     = Stage.GAME_OVER;
        playerWon = false;
    }

    /** Called when the player passes the AP Exam. */
    public void setVictory() {
        stage     = Stage.VICTORY;
        playerWon = true;
    }

    public boolean isGameOver()  { return stage == Stage.GAME_OVER; }
    public boolean isVictory()   { return stage == Stage.VICTORY; }
    public boolean isFinished()  { return stage == Stage.GAME_OVER || stage == Stage.VICTORY; }
    public boolean isPlayerWon() { return playerWon; }

    @Override
    public String toString() {
        return String.format("GameState{stage=%s, bossIndex=%d/%d}",
                stage, currentBossIndex, bosses.size());
    }
}
