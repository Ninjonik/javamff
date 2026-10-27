package cz.cuni.mff.zatkope.toolkit;

import java.util.Iterator;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class SimpleDynamicArray<T> implements SimpleCollection<T>, Iterable<T> {
    private int lastItemIndex = -1;
    private Object[] data;

    public SimpleDynamicArray(int size) {
        data = new Object[Math.max(size, 1)];
    }

    private void growIfNeeded() {
        if (lastItemIndex >= capacity()) {
            Object[] newData = new Object[capacity() * 2];
            System.arraycopy(data, 0, newData, 0, capacity());

            data = newData;
        }
    }

    private void shrinkIfNeeded() {
        if (capacity() > 1 && size() < capacity() / 2) {
            Object[] newData = new Object[capacity() / 2];
            System.arraycopy(data, 0, newData, 0, size());

            data = newData;
        }
    }

    private int capacity() {
        return data.length;
    }

    public int size() {
        return lastItemIndex + 1;
    }

    public void add(T o) {
        lastItemIndex++;
        growIfNeeded();
        data[lastItemIndex] = o;
    }

    public T get(int i) {
        try {
            return (T) data[i];
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(T o) {
        int objectIndex = -1;
        for (int i = 0; i <= lastItemIndex; i++) {
            if (Objects.equals(data[i], o)) {
                objectIndex = i;
                break;
            }
        }

        if (objectIndex != -1) {
            remove(objectIndex);
        }
    }

    public void remove(int i) {
        for (int j = i; j < lastItemIndex; j++) {
            data[j] = data[j + 1];
        }

        data[lastItemIndex] = null;
        lastItemIndex--;

        shrinkIfNeeded();
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            public boolean hasNext() {
                return index < lastItemIndex + 1;
            }

            public T next() {
                return (T) data[index++];
            }
        };
    }
}

