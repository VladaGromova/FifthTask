package com.epam.task.fifth.composite;

import java.util.Objects;

public class Leaf implements Component {

    private final String value;

    public Leaf(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void add(Component component) {
    }

    @Override
    public void remove(Component component) {
    }

    @Override
    public int countSentences() {
        return 1;
    }

    @Override
    public Object getChild(int index) {
        return null;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Leaf leaf = (Leaf) object;
        return value.equals(leaf.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
