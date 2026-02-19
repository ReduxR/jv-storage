package core.basesyntax.impl;

import core.basesyntax.Storage;

public class StorageImpl<K, V> implements Storage<K, V> {
    private static final int MAX_ARRAY_SIZE = 10;

    private final Object[] keys = new Object[MAX_ARRAY_SIZE];
    private final Object[] values = new Object[MAX_ARRAY_SIZE];

    private int size = 0;

    @Override
    public void put(K key, V value) {
        if (size >= MAX_ARRAY_SIZE) {
            return;
        }

        for (int i = 0; i < size; i++) {
            // безопасное сравнение ключей через тернарник
            if (keys[i] == null ? key == null : keys[i].equals(key)) {
                values[i] = value; // обновляем существующее значение
                return;
            }
        }

        keys[size] = key;
        values[size] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i] == null ? key == null : keys[i].equals(key)) {
                return (V) values[i];
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
