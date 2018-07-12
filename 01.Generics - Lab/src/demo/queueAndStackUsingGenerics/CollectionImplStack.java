package demo.queueAndStackUsingGenerics;

public class CollectionImplStack<T> extends CollectionImpl<T> {
    @Override
    public T remove() {
        return this.collection.remove(this.collection.size() - 1);
    }
}
