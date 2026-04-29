package bigstats.model;

import java.util.List;

public class GameState {
    public enum Stage { UNIT_BOSS, AP_EXAM, GAME_OVER, VICTORY }

    private final List<Boss> bosses;
    private int bossIndex = 0;
    private Stage stage   = Stage.UNIT_BOSS;
    private boolean won   = false;

    public GameState(List<Boss> bosses) {
        if (bosses == null || bosses.isEmpty())
            throw new IllegalArgumentException("Boss list cannot be null or empty.");
        this.bosses = List.copyOf(bosses);
    }

    public Boss    getCurrentBoss()      { return bossIndex < bosses.size() ? bosses.get(bossIndex) : null; }
    public int     getCurrentBossIndex() { return bossIndex; }
    public int     getTotalBosses()      { return bosses.size(); }
    public List<Boss> getBosses()        { return bosses; }
    public int     getDefeatedBossCount(){ return bossIndex; }
    public Stage   getStage()            { return stage; }
    public boolean isGameOver()          { return stage == Stage.GAME_OVER; }
    public boolean isVictory()           { return stage == Stage.VICTORY; }
    public boolean isFinished()          { return isGameOver() || isVictory(); }
    public boolean isPlayerWon()         { return won; }

    public void advanceToNextBoss() {
        if (++bossIndex >= bosses.size()) stage = Stage.AP_EXAM;
    }
    public void setGameOver() { stage = Stage.GAME_OVER; won = false; }
    public void setVictory()  { stage = Stage.VICTORY;   won = true;  }
}
