package demo.simpleGenericExample;

import java.util.ArrayList;
import java.util.List;

public class AnimalList <T extends Animal> {
    private List<T> collection;

    public AnimalList() {
        this.collection = new ArrayList<>();
    }

    public void add(T animal) {
        this.collection.add(animal);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T animal : this.collection) {
            sb.append(animal.toString()).append("\n");
        }

        sb.delete(sb.length() - 1, sb.length());

        return sb.toString();
    }
}
