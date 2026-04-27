package bigstats.model;

import java.util.List;

public class GameState {

    public enum Stage { UNIT_BOSS, AP_EXAM, GAME_OVER, VICTORY }

    private final List<Boss> bosses;
    private int currentBossIndex = 0;
    private Stage stage = Stage.UNIT_BOSS;
    private boolean playerWon = false;

    public GameState(List<Boss> bosses) {
        if (bosses == null || bosses.isEmpty())
            throw new IllegalArgumentException("Boss list cannot be null or empty.");
        this.bosses = List.copyOf(bosses);
    }

    public Boss getCurrentBoss() {
        return currentBossIndex < bosses.size() ? bosses.get(currentBossIndex) : null;
    }

    public int getCurrentBossIndex() { return currentBossIndex; }
    public int getTotalBosses()      { return bosses.size(); }
    public List<Boss> getBosses()    { return bosses; }
    public int getDefeatedBossCount(){ return currentBossIndex; }
    public Stage getStage()          { return stage; }
    public boolean isGameOver()      { return stage == Stage.GAME_OVER; }
    public boolean isVictory()       { return stage == Stage.VICTORY; }
    public boolean isFinished()      { return isGameOver() || isVictory(); }
    public boolean isPlayerWon()     { return playerWon; }

    /** Move to the next unit boss, or transition to AP_EXAM when all are done. */
    public void advanceToNextBoss() {
        currentBossIndex++;
        if (currentBossIndex >= bosses.size()) stage = Stage.AP_EXAM;
    }

    public void setGameOver() { stage = Stage.GAME_OVER; playerWon = false; }
    public void setVictory()  { stage = Stage.VICTORY;   playerWon = true;  }

    @Override
    public String toString() {
        return String.format("GameState{stage=%s, boss=%d/%d}", stage, currentBossIndex, bosses.size());
    }
}
