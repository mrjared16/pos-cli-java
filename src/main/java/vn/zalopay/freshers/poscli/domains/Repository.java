package vn.zalopay.freshers.poscli.domains;

import vn.zalopay.freshers.poscli.shared.Key;

import java.util.List;

public interface Repository<T> {
    boolean add(T item);
    T get(Key id);
    List<T> getAll();
    boolean update(Key id, T item);
    boolean delete(Key id);
}
