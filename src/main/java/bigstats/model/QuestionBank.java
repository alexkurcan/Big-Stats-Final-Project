package bigstats.model;

import java.util.*;

public class QuestionBank {

    private final Map<String, List<Question>> bank = new LinkedHashMap<>();

    public void addQuestion(Question q) {
        bank.computeIfAbsent(q.getUnitTag(), k -> new ArrayList<>()).add(q);
    }

    /** Returns questions for a unit; throws if none found. */
    public List<Question> getQuestionsForUnit(String unitTag) {
        List<Question> list = bank.get(unitTag);
        if (list == null || list.isEmpty())
            throw new IllegalArgumentException("No questions found for unit: " + unitTag);
        return Collections.unmodifiableList(list);
    }

    /** Returns all questions across every unit. */
    public List<Question> getAllQuestions() {
        List<Question> all = new ArrayList<>();
        bank.values().forEach(all::addAll);
        return Collections.unmodifiableList(all);
    }

    public Set<String> getUnitTags() { return Collections.unmodifiableSet(bank.keySet()); }

    public boolean hasUnit(String unitTag) {
        List<Question> list = bank.get(unitTag);
        return list != null && !list.isEmpty();
    }

    public int totalSize() {
        return bank.values().stream().mapToInt(List::size).sum();
    }
}
