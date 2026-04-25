package com.example.codeholiday.service.impl;

import com.example.codeholiday.model.Todo;
import com.example.codeholiday.repository.ITodoRepository;
import com.example.codeholiday.service.ITodoService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class TodoServiceImpl implements ITodoService {
    private final ITodoRepository todoRepository;

    public TodoServiceImpl(ITodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Optional<Todo> findById(long id) {
        return todoRepository.findById(id);
    }

    @Override
    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void deleteTodo(long id) {
        todoRepository.deleteById(id);
    }
}
