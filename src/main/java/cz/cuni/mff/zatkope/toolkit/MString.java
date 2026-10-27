package cz.cuni.mff.zatkope.toolkit;

public class MString {
    char[] string;

    MString() {
        string = new char[0];
    }

    MString(String str) {
        string = new char[str.length()];

        loadStringIntoArray(str, 0, string);
    }

    private static void loadStringIntoArray(String str, int start, char[] to) {
        str.getChars(0, str.length(), to, start);
    }

    void append(String str) {
        char[] newString = new char[string.length + str.length()];

        System.arraycopy(string, 0, newString, 0, string.length);
        loadStringIntoArray(str, string.length, newString);

        string = newString;
    }

    void append(char ch) {
        append(String.valueOf(ch));
    }

    void insert(int pos, String str) {
        char[] newString = new char[string.length + str.length() - pos];

        System.arraycopy(string, 0, newString, 0, string.length);
        loadStringIntoArray(str, pos, newString);

        string = newString;
    }

    void insert(int pos, char ch) {
        insert(pos, String.valueOf(ch));
    }

    void delete(int pos, int length) {
        char[] newString = new char[string.length - (Math.min(string.length - pos, length))];

        System.arraycopy(string, 0, newString, 0, pos);
        System.arraycopy(string, pos + length, newString, pos, string.length - length - pos);

        string = newString;
    }

    @Override
    public String toString() {
        return string.length == 0 ? "" : new String(string);
    }
}
