package bigstats;

import bigstats.controller.GameController;
import bigstats.model.*;
import bigstats.view.ConsoleView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        QuestionBank bank = QuestionFactory.buildBank();

        List<Boss> bosses = List.of(
            new Boss(QuestionFactory.UNIT_1, 100, Boss.Difficulty.EASY),
            new Boss(QuestionFactory.UNIT_2, 120, Boss.Difficulty.EASY),
            new Boss(QuestionFactory.UNIT_3, 140, Boss.Difficulty.MEDIUM),
            new Boss(QuestionFactory.UNIT_4, 160, Boss.Difficulty.MEDIUM),
            new Boss(QuestionFactory.UNIT_5, 180, Boss.Difficulty.HARD),
            new Boss(QuestionFactory.UNIT_6, 200, Boss.Difficulty.HARD)
        );

        new GameController(new ConsoleView(), bank).startGame(bosses);
    }
}
