package com.epam.task.fifth.composite;

public interface Component {
    void add(Component component);
    void remove(Component component);
    int countSentences();
    Object getChild(int index);
}
