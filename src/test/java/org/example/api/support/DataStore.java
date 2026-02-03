package org.example.api.support;

import java.util.EnumMap;
import java.util.Map;


public class DataStore {

    private final Map<DataStoreKeyEnum, Object> store = new EnumMap<>(DataStoreKeyEnum.class);

    public DataStore(){}

    public void put(DataStoreKeyEnum key, Object value) {
        store.put(key, value);
    }

    public <T> T get(DataStoreKeyEnum key, Class<T> type) {
        Object value = store.get(key);
        if (value == null) return null;
        return type.cast(value);
    }

    public boolean contains(DataStoreKeyEnum key) {
        return store.containsKey(key);
    }

    public void clear() {
        store.clear();
    }

    public void remove(DataStoreKeyEnum key) {
        store.remove(key);
    }
}
