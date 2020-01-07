package by.epam.esm.dao;

import java.util.List;

public interface Dao<T> {
    void save(T t);
    void delete(T t);
    List<T> getAll();

}
