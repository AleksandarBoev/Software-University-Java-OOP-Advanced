package hell.factories;

public interface Factory<T> {
    T create(String[] data);
}
