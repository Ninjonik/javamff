package cz.cuni.mff.zatkope.toolkit;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class SimpleDynamicArray<T> implements SimpleCollection<T>, Iterable<T> {
    private int lastItemIndex = -1;
    private Object[] data;

    public SimpleDynamicArray(int size) {
        data = new Object[Math.max(size, 1)];
    }

    public static <K> SimpleDynamicArray<K> of(K[] args) {
        SimpleDynamicArray<K> arr = new SimpleDynamicArray<>(args.length);
        for (K arg : args) {
            arr.add(arg);
        }
        return arr;
    }

    public int size() {
        return lastItemIndex + 1;
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

    public void sort(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator, "comparator");
        new Sorter().sort(comparator, 0, lastItemIndex);
    }

    private class Sorter {
        private void sort(Comparator<? super T> comparator, int lo, int hi) {
            while (lo < hi) {
                T pivot = (T) data[lo + (hi - lo) / 2];
                int lt = lo;
                int eq = lo;
                int gt = hi;

                while (eq <= gt) {
                    int comparison = comparator.compare((T) data[eq], pivot);
                    if (comparison < 0) {
                        swap(lt++, eq++);
                    } else if (comparison > 0) {
                        swap(eq, gt--);
                    } else {
                        eq++;
                    }
                }

                if (lt - lo < hi - gt) {
                    sort(comparator, lo, lt - 1);
                    lo = gt + 1;
                } else {
                    sort(comparator, gt + 1, hi);
                    hi = lt - 1;
                }
            }
        }

        private void swap(int first, int second) {
            Object temp = data[first];
            data[first] = data[second];
            data[second] = temp;
        }
    }
}

