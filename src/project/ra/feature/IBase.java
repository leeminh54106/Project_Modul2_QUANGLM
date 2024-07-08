package project.ra.feature;

import java.util.List;

public interface IBase<T, E> {
    List<T> findAll();

    void addOrUpdate(T t);

    void delete(E id);

    int findIndexById(E id);
}
