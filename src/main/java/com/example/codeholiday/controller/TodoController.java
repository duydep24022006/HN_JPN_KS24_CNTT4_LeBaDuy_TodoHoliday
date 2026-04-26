package com.example.codeholiday.controller;

import com.example.codeholiday.dto.TodoDto;
import com.example.codeholiday.model.Todo;
import com.example.codeholiday.service.impl.TodoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

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

    @GetMapping("/edit/{id}")
    public String editTodo(@PathVariable Long id, Model model) {
        Optional<Todo> optionalTodo = todoService.findById(id);
        if (optionalTodo.isEmpty()) {
            model.addAttribute("message", "Không tìm thấy công việc!");
            return "redirect:/todo";
        }
        Todo todo = optionalTodo.get();
        TodoDto todoDto = new TodoDto(todo.getId(), todo.getContent(), todo.getDueDate(), todo.getStatus(), todo.getPriority());
        model.addAttribute("todoDto", todoDto);
        model.addAttribute("id", todo.getId());
        return "form";
    }

    @PostMapping("/update/{id}")
    public String updateTodo(@PathVariable Long id,
                             @Valid @ModelAttribute("todoDto") TodoDto todoDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("id", id);
            return "form";
        }
        Todo todo = new Todo();
        todo.setId(id);
        todo.setContent(todoDto.getContent());
        todo.setDueDate(todoDto.getDueDate());
        todo.setStatus(todoDto.getStatus());
        todo.setPriority(todoDto.getPriority());

        todoService.saveTodo(todo);
        redirectAttributes.addFlashAttribute("message", "Cập nhật thành công!");
        return "redirect:/todo";
    }
    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            todoService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Xóa thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Không tìm thấy công việc để xóa!");
        }
        return "redirect:/todo";
    }

}
