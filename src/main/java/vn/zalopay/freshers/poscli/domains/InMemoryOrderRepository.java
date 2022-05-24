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
        store.put(new StringKey(item.getId()), item);
        return true;
    }

    @Override
    public Order get(Key id) {
        return store.getOrDefault(id, null);
    }

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean update(Key id, Order item) {
        if (!store.containsKey(id)) {
            return false;
        }
        store.put(id, item);
        return true;
    }

    @Override
    public boolean delete(Key id) {
        if (!store.containsKey(id)) {
            return false;
        }
        store.remove(id);
        return true;
    }
}
