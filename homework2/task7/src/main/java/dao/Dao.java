package dao;

import java.util.List;

public interface Dao<T> {
    void create(T record);
    List<T> readAll();
    void update(T record);
    void delete(T record);
}
