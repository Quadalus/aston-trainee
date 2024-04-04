package hw5.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {
    Optional<T> findById(K id);

    List<T> findAll();

    void deleteById(K id);

    T add(T object);

    T update(T object);
}
