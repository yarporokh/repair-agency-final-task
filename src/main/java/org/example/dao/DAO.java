package org.example.dao;

import java.util.List;

public interface DAO <T, K> {
    T findEntity(K k);
    void insert(T t);
    void update(T t);
    void delete(K k);
    List<T> getAll();
}
