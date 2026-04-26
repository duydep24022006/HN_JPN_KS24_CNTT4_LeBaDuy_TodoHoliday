package com.example.codeholiday.service;

import com.example.codeholiday.model.Todo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ITodoService {

    List<Todo> findAll();
    Optional<Todo> findById(long id);
    Todo saveTodo(Todo todo);
    void deleteById(long id);
}
