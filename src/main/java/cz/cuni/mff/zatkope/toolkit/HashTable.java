package cz.cuni.mff.zatkope.toolkit;

import java.util.Iterator;

public class HashTable implements Iterable<String> {

    private Entry[] buckets;

    public HashTable() {
        buckets = new Entry[10];
    }

    public HashTable(int bucketSize) {
        if (bucketSize < 1) throw new IllegalArgumentException("The bucketSize has to be at least 1.");

        buckets = new Entry[bucketSize];
    }

    private int getBucketForKey(String value) {
        return Math.floorMod(value.hashCode(), buckets.length);
    }

    private MatchedEntry getEntry(String key) {
        int bucket = getBucketForKey(key);

        Entry current = buckets[bucket];
        Entry last = null;
        while (current != null) {
            if (key.equals(current.key)) {
                return new MatchedEntry(true, current);
            }
            if (current.next == null) {
                last = current;
            }
            current = current.next;
        }

        return new MatchedEntry(false, last);
    }

    public Object get(String key) {
        MatchedEntry matchedEntry = getEntry(key);

        return matchedEntry.matched ? matchedEntry.entry.value : null;
    }

    public void set(String key, Object value) {
        MatchedEntry matchedEntry = getEntry(key);

        if (matchedEntry.matched) {
            matchedEntry.entry.value = value;
        } else {
            Entry lastEntryForBucket = matchedEntry.entry;
            Entry newEntry = new Entry(key, value, null);

            if (lastEntryForBucket != null) {
                lastEntryForBucket.next = newEntry;
            } else {
                int index = getBucketForKey(key);
                buckets[index] = newEntry;
            }
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {
            int bucketIndex = 0;
            Entry nextEntry = null;

            @Override
            public boolean hasNext() {
                if (nextEntry != null) {
                    return true;
                }

                while (bucketIndex < buckets.length) {
                    if (buckets[bucketIndex] != null) {
                        nextEntry = buckets[bucketIndex];
                        bucketIndex++;
                        return true;
                    }
                    bucketIndex++;
                }

                return false;
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }

                String key = nextEntry.key;
                nextEntry = nextEntry.next;
                return key;
            }
        };
    }

    private static class Entry {
        String key;
        Object value;
        Entry next;

        public Entry(String key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private static class MatchedEntry {
        boolean matched;
        Entry entry;

        public MatchedEntry(boolean matched, Entry entry) {
            this.matched = matched;
            this.entry = entry;
        }
    }
}
