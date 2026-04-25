package com.example.codeholiday.controller;

import com.example.codeholiday.dto.TodoDto;
import com.example.codeholiday.model.Todo;
import com.example.codeholiday.service.impl.TodoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
public class TodoController {
    private final TodoServiceImpl todoService;

    public TodoController(TodoServiceImpl todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String listTodos(Model model) {
        model.addAttribute("todos", todoService.findAll());
        return "home";
    }

    @GetMapping("/new")
    public String newTodo(Model model) {
        model.addAttribute("todoDto", new TodoDto());
        return "form";
    }

    @PostMapping("/add")
    public String addTodo(@Valid @ModelAttribute("todoDto") TodoDto todoDto,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("todos", todoService.findAll());
            return "form";
        }

        Todo todo = new Todo();
        todo.setContent(todoDto.getContent());
        todo.setDueDate(todoDto.getDueDate());
        todo.setStatus(todoDto.getStatus());
        todo.setPriority(todoDto.getPriority());

        todoService.saveTodo(todo);
        return "redirect:/todo";
    }




}
