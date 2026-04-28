package bigstats.model;

import java.util.List;

public class Question {
    private final String prompt, unitTag;
    private final List<String> choices;
    private final int correctIndex;

    public Question(String prompt, List<String> choices, int correctIndex, String unitTag) {
        if (choices == null || choices.size() < 2)
            throw new IllegalArgumentException("A question must have at least 2 choices.");
        if (correctIndex < 0 || correctIndex >= choices.size())
            throw new IllegalArgumentException("correctIndex out of range.");
        this.prompt       = prompt;
        this.choices      = List.copyOf(choices);
        this.correctIndex = correctIndex;
        this.unitTag      = unitTag;
    }

    public String       getPrompt()      { return prompt; }
    public List<String> getChoices()     { return choices; }
    public int          getCorrectIndex(){ return correctIndex; }
    public String       getUnitTag()     { return unitTag; }
    public boolean      isCorrect(int i) { return i == correctIndex; }
}
