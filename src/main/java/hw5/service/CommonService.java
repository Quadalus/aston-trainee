package hw5.service;

import java.util.List;

public interface CommonService<T> {
    T findById(Long id);

    List<T> findAll();

    void deleteById(Long id);

    T add(T object);

    T update(T object, Long id);
}
