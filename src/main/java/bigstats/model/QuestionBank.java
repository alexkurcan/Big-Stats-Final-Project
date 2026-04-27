package bigstats.model;

import java.util.*;

public class QuestionBank {
    private final Map<String, List<Question>> bank = new LinkedHashMap<>();

    public void addQuestion(Question q) {
        bank.computeIfAbsent(q.getUnitTag(), k -> new ArrayList<>()).add(q);
    }

    public List<Question> getQuestionsForUnit(String unit) {
        List<Question> list = bank.get(unit);
        if (list == null || list.isEmpty())
            throw new IllegalArgumentException("No questions found for unit: " + unit);
        return Collections.unmodifiableList(list);
    }

    public List<Question> getAllQuestions() {
        List<Question> all = new ArrayList<>();
        bank.values().forEach(all::addAll);
        return Collections.unmodifiableList(all);
    }

    public Set<String> getUnitTags()       { return Collections.unmodifiableSet(bank.keySet()); }
    public boolean     hasUnit(String tag) { List<Question> l = bank.get(tag); return l != null && !l.isEmpty(); }
    public int         totalSize()         { return bank.values().stream().mapToInt(List::size).sum(); }
}
