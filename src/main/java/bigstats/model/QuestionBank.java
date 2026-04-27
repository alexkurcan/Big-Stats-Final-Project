package bigstats.model;

import java.util.*;

/**
 * Stores and retrieves Questions organized by unit tag.
 * Acts as the Iterable used by the Iterator pattern.
 */
public class QuestionBank {

    // unit tag -> list of questions for that unit
    private final Map<String, List<Question>> bank = new LinkedHashMap<>();

    /** Add a single question to the bank. */
    public void addQuestion(Question q) {
        bank.computeIfAbsent(q.getUnitTag(), k -> new ArrayList<>()).add(q);
    }

    /**
     * Returns an unmodifiable list of questions for the given unit.
     * Throws IllegalArgumentException if the unit is unknown or has no questions.
     */
    public List<Question> getQuestionsForUnit(String unitTag) {
        List<Question> list = bank.get(unitTag);
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("No questions found for unit: " + unitTag);
        }
        return Collections.unmodifiableList(list);
    }

    /**
     * Returns all questions across every unit, in insertion order (unit by unit).
     */
    public List<Question> getAllQuestions() {
        List<Question> all = new ArrayList<>();
        for (List<Question> unit : bank.values()) {
            all.addAll(unit);
        }
        return Collections.unmodifiableList(all);
    }

    /** Returns the set of unit tags currently loaded. */
    public Set<String> getUnitTags() {
        return Collections.unmodifiableSet(bank.keySet());
    }

    /** Returns true if the given unit tag exists and has at least one question. */
    public boolean hasUnit(String unitTag) {
        List<Question> list = bank.get(unitTag);
        return list != null && !list.isEmpty();
    }

    /** Total number of questions across all units. */
    public int totalSize() {
        return bank.values().stream().mapToInt(List::size).sum();
    }
}
