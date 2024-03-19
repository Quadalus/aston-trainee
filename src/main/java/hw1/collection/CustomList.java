package hw1.collection;

import java.util.Collection;
import java.util.Comparator;

public interface CustomList<E> {

    void add(E element);

    void add(int index, E element);

    void addAll(Collection<? extends E> collection);

    void clear();

    E get(int index);

    boolean isEmpty();

    void remove(int index);

    void remove(Object o);

    void removeLast();

    int size();

    void sort(Comparator<? super E> comparator);
}
