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

    public static SimpleDynamicArray<String> of(String[] args) {
        SimpleDynamicArray<String> arr = new SimpleDynamicArray<>(args.length);
        for (String arg : args) {
            arr.add(arg);
        }
        return arr;
    }

    /*
    // Sorts (a portion of) an array, divides it into partitions, then sorts those
algorithm quicksort(A, lo, hi) is
  if lo >= 0 && lo < hi then
    lt, gt := partition(A, lo, hi) // Multiple return values
    quicksort(A, lo, lt - 1)
    quicksort(A, gt + 1, hi)

// Divides array into three partitions
algorithm partition(A, lo, hi) is
  // Pivot value
  pivot := A[(lo + hi) / 2] // Choose the middle element as the pivot (integer division)

  // Lesser, equal and greater index
  lt := lo
  eq := lo
  gt := hi

  // Iterate and compare all elements with the pivot
  while eq <= gt do
    if A[eq] < pivot then
      // Swap the elements at the equal and lesser indices
      swap A[eq] with A[lt]
      // Increase lesser index
      lt := lt + 1
      // Increase equal index
      eq := eq + 1
    else if A[eq] > pivot then
      // Swap the elements at the equal and greater indices
      swap A[eq] with A[gt]
      // Decrease greater index
      gt := gt - 1
    else // if A[eq] = pivot then
      // Increase equal index
      eq := eq + 1

  // Return lesser and greater indices
  return lt, gt
     */

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

    class Sorter {
        public void partition(data, int lo, int hi) {
            int pivot = (int) data[Math.floorDiv(lo + hi, 2)];

            int lt = lo;
            int eq = lo;
            int gt = hi;

            while (eq <= gt) {
                if (data[eq] < pivot) {
                    int temp = data[lt];
                    data[lt] = eq;
                    data[eq] = temp;
                }
            }
        }

        public void sort() {

        }
    }
}

