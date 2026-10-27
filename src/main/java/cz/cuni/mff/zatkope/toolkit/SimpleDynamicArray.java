package cz.cuni.mff.zatkope.toolkit;

interface SimpleCollection<T> {
    void add(T o);
    T get(int i);
    void remove(T o);
    void remove(int i);
}

class SimpleDynamicArray<T> implements SimpleCollection<T> {
    int size;
    int lastItemIndex = 0;
    Object[] data;

    public SimpleDynamicArray(int size){
        this.size = size;

        data = new Object[size];
    }

    public void add(T o) {
        lastItemIndex++;

        if (lastItemIndex < size - 1) {
            data[lastItemIndex] = o;
        } else {
            Object[] newData = new Object[size * 2];
            if (size >= 0) System.arraycopy(data, 0, newData, 0, size);
            size = size * 2;

            data = newData;
        }
    }

    public T get(int i) {
        return (T) data[i];
    }

    public void remove(T o) {
        int breakpoint = 0;
        for (int i = 0; i < lastItemIndex; i++) {
            if(data[i] == o){
                data[i] = null;
                breakpoint = i;
                break;
            }
        }

        for (int i = breakpoint; i < lastItemIndex - 1; i++) {
            data[i] = data[i + 1];
        }
    }

    public void remove(int i) {
        remove((T) data[i]);
    }
}

