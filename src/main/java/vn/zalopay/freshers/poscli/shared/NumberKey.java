package vn.zalopay.freshers.poscli.shared;

public class NumberKey implements Key {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NumberKey)) return false;

        NumberKey numberKey = (NumberKey) o;

        return key == numberKey.key;
    }

    @Override
    public int hashCode() {
        return key;
    }

    @Override
    public String toString() {
        return Integer.toString(this.key);
    }

    private final int key;

    public int getKey() {
        return key;
    }

    public NumberKey(int key) {
        this.key = key;
    }
}
