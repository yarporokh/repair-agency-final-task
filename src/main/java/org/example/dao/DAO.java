package org.example.dao;

import java.util.List;

public interface DAO <T, K> {
    void insert(T t);
    void update(T t);
    List<T> getAll();
}
