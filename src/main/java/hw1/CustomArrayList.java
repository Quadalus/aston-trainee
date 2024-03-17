package hw1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class CustomArrayList<E> {
    private int size = 0;

    public static final double DEFAULT_GROW_RATIO = 2;

    public static final int DEFAULT_CAPACITY = 10;

    public static final int DEFAULT_MIN_CAPACITY = 2;

    private Object[] array;

    public CustomArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity size = %d".formatted(capacity));
        } else {
            array = new Object[capacity];
        }
    }

    public CustomArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    public void add(E element) {
        if (size == array.length) {
            grow();
        }
        array[size] = element;
        size++;
    }

    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }

        if (size == array.length) {
            grow();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    private void grow() {
        grow(DEFAULT_MIN_CAPACITY);
    }

    private void grow(int minCapacity) {
        int newLength =  (int) ((array.length + minCapacity) * DEFAULT_GROW_RATIO);
        array = Arrays.copyOf(array, newLength);
    }

    public void addAll(Collection<? extends E> collection) {
        var arrayFromCollection = collection.toArray();
        if (array.length - size < arrayFromCollection.length) {
            grow(arrayFromCollection.length);
        }
        System.arraycopy(arrayFromCollection, 0, array, size, arrayFromCollection.length);
        size += collection.size();
    }

    public void clear() {
        array = new Object[array.length];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }
        return (E) array[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void remove(int index) {
        var lastElementIndex = size - 1;
        if (index == lastElementIndex) {
            removeLast();
        } else if (index < 0 || index > lastElementIndex) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        } else {
            System.arraycopy(array, index + 1, array, index, size - index);
            size--;
        }
    }

    public void remove(Object o) {
        for (int i = 0; i < size; i++) {
            if ((i == size - 1) && o.equals(array[i])) {
                removeLast();
                break;
            } else if (o.equals(array[i])) {
                System.arraycopy(array, i + 1, array, i, size - i);
                size--;
                break;
            }
        }
    }

    public void removeLast() {
        if (size != 0) {
            array[size - 1] = null;
            size--;
        }
    }

    public int size() {
        return size;
    }

    public void sort(Comparator<? super E> comparator) {
        var sortedList = ArrayUtil.mergeSort(Arrays.copyOfRange(array, 0, size), comparator);
        System.arraycopy(sortedList, 0, array, 0, sortedList.length);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CustomArrayList<?> that = (CustomArrayList<?>) object;
        return size == that.size && Arrays.equals(Arrays.copyOf(array, size), Arrays.copyOf(that.array, size));
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(Arrays.copyOf(array, size));
    }

    @Override
    public String toString() {
        return "CustomArrayList{" + Arrays.toString(array) +
                '}';
    }
}
