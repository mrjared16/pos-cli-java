package vn.zalopay.freshers.poscli.shared;

public class StringKey implements Key {
    private String key;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringKey)) return false;

        StringKey stringKey = (StringKey) o;

        return key != null ? key.equals(stringKey.key) : stringKey.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

    public StringKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
