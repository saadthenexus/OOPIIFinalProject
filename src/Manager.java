package src;

import java.util.List;

interface Manager<T> {
    void add();
    void delete();
    List<T> getAll();
    void save();
}