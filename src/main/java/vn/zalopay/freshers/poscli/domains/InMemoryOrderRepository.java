package vn.zalopay.freshers.poscli.domains;

import vn.zalopay.freshers.poscli.models.Order;
import vn.zalopay.freshers.poscli.shared.Key;
import vn.zalopay.freshers.poscli.shared.StringKey;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class InMemoryOrderRepository implements OrderRepository {
    LinkedHashMap<Key, Order> store;

    public InMemoryOrderRepository() {
        store = new LinkedHashMap<>();
    }

    @Override
    public boolean add(Order item) {
        store.put(new StringKey(item.getID()), item);
        return true;
    }

    @Override
    public Order get(Key id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean update(Key id, Order item) {
        store.put(id, item);
        return true;
    }

    @Override
    public boolean delete(Key id) {
        store.remove(id);
        return true;
    }
}
