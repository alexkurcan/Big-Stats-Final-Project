package bigstats.model;

import java.util.List;

/**
 * Represents a single AP Statistics multiple-choice question.
 */
public class Question {

    private final String prompt;
    private final List<String> choices;   // e.g. ["A) ...", "B) ...", "C) ...", "D) ..."]
    private final int correctIndex;        // 0-based index into choices
    private final String unitTag;          // e.g. "Exploring Data"

    public Question(String prompt, List<String> choices, int correctIndex, String unitTag) {
        if (choices == null || choices.size() < 2) {
            throw new IllegalArgumentException("A question must have at least 2 answer choices.");
        }
        if (correctIndex < 0 || correctIndex >= choices.size()) {
            throw new IllegalArgumentException("correctIndex out of range.");
        }
        this.prompt       = prompt;
        this.choices      = List.copyOf(choices);
        this.correctIndex = correctIndex;
        this.unitTag      = unitTag;
    }

    public String getPrompt()       { return prompt; }
    public List<String> getChoices(){ return choices; }
    public int getCorrectIndex()    { return correctIndex; }
    public String getUnitTag()      { return unitTag; }

    /** Returns true if the supplied 0-based index matches the correct answer. */
    public boolean isCorrect(int answerIndex) {
        return answerIndex == correctIndex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(prompt).append("\n");
        for (int i = 0; i < choices.size(); i++) {
            sb.append("  ").append((char)('A' + i)).append(") ").append(choices.get(i)).append("\n");
        }
        return sb.toString();
    }
}
