package dev.pierce.repository;

public interface GenericDAO <T>{

    T getByUsername(String username);

    T getById(Integer id);

}
