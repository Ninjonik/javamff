package cz.cuni.mff.zatkope.toolkit;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MString implements Iterable<Character> {
    char[] stringArray;

    public MString() {
        stringArray = new char[0];
    }

    public MString(String str) {
        stringArray = new char[str.length()];

        loadStringIntoArray(str, 0, stringArray);
    }

    private static void loadStringIntoArray(String sourceStr, int startIndex, char[] toArray) {
        sourceStr.getChars(0, sourceStr.length(), toArray, startIndex);
    }

    public void append(String str) {
        char[] newStringArray = new char[stringArray.length + str.length()];

        System.arraycopy(stringArray, 0, newStringArray, 0, stringArray.length);
        loadStringIntoArray(str, stringArray.length, newStringArray);

        stringArray = newStringArray;
    }

    public void append(char ch) {
        append(String.valueOf(ch));
    }

    public void insert(int pos, String str) {
        char[] newStringArray = new char[stringArray.length + str.length() - pos];

        System.arraycopy(stringArray, 0, newStringArray, 0, stringArray.length);
        loadStringIntoArray(str, pos, newStringArray);

        stringArray = newStringArray;
    }

    public void insert(int pos, char ch) {
        insert(pos, String.valueOf(ch));
    }

    public void delete(int pos, int length) {
        int toDeleteCount = Math.min(stringArray.length - pos, length);

        char[] newStringArray = new char[stringArray.length - toDeleteCount];

        System.arraycopy(stringArray, 0, newStringArray, 0, pos); // left
        System.arraycopy(stringArray, pos + toDeleteCount, newStringArray, pos, stringArray.length - toDeleteCount - pos); // right

        stringArray = newStringArray;
    }

    @Override
    public String toString() {
        return stringArray.length == 0 ? "" : new String(stringArray);
    }

    @Override
    public Iterator<Character> iterator() {
        return new Iterator<Character>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < stringArray.length;
            }

            @Override
            public Character next() {
                if (i < stringArray.length) {
                    return stringArray[i++];
                }
                throw new NoSuchElementException();
            }
        };
    }
}
