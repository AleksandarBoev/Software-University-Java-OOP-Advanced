package demo.queueAndStackUsingGenerics;

public class CollectionImplQueue<T> extends CollectionImpl<T> {
    @Override
    public T remove() {
        return this.collection.remove(0);
    }
}
