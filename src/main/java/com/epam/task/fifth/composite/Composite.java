package com.epam.task.fifth.composite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Composite implements Component {

    private final static String EMPTY_STRING = "";

    private List<Component> components = new ArrayList<>();

    public Composite(List<Component> components) {
        this.components = components;
    }

    public Composite() {

    }

    public List<Component> getComponents() {
        return components;
    }

    public void addComponents(Component... components) {
        this.components.addAll(Arrays.asList(components));
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public int countSentences() {
        int numberOfSentences = 0;
        for (Component component : components) {
            numberOfSentences += component.countSentences();
        }
        return numberOfSentences;
    }

    @Override
    public Object getChild(int index) {
        return components.get(index);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Composite composite = (Composite) object;
        return components.equals(composite.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(components);
    }

    @Override
    public String toString() {
        String result = EMPTY_STRING;
        for (Component component : components) {
            result += component.toString();
            result += ' ';
        }
        return result;
    }
}
