package cz.cuni.mff.zatkope.toolkit;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MString implements Iterable<Character> {
    private char[] stringArray;

    public MString() {
        stringArray = new char[0];
    }

    public MString(String str) {
        stringArray = str.toCharArray();
    }

    private static void loadStringIntoArray(String sourceStr, int startIndex, char[] destArray) {
        sourceStr.getChars(0, sourceStr.length(), destArray, startIndex);
    }

    private char[] handleArraySplitting(int pos, int removeCount, int insertCount) {
        char[] resultArray = new char[stringArray.length - removeCount + insertCount];

        System.arraycopy(stringArray, 0, resultArray, 0, pos); // left
        System.arraycopy(stringArray, pos + removeCount,
                resultArray, pos + insertCount,
                stringArray.length - pos - removeCount); // right

        return resultArray;
    }

    public void append(String str) {
        insert(stringArray.length, str);
    }

    public void append(char ch) {
        append(String.valueOf(ch));
    }

    public void insert(int pos, String str) {
        if (pos < 0 || pos > stringArray.length) {
            throw new IndexOutOfBoundsException();
        }

        char[] newStringArray = handleArraySplitting(pos, 0, str.length());
        loadStringIntoArray(str, pos, newStringArray);

        stringArray = newStringArray;
    }

    public void insert(int pos, char ch) {
        insert(pos, String.valueOf(ch));
    }

    public void delete(int pos, int length) {
        if (pos < 0 || pos > stringArray.length || length < 0) {
            throw new IndexOutOfBoundsException();
        }

        int toDeleteCount = Math.min(stringArray.length - pos, length);

        stringArray = handleArraySplitting(pos, toDeleteCount, 0);
    }

    @Override
    public String toString() {
        return new String(stringArray);
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
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return stringArray[i++];
            }
        };
    }
}