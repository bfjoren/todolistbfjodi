package com.example.todolistbfjodi.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;


@Controller
public class ToDoController {

    public ToDoController(ToDoRepository repository) {
        this.repository = repository;
    }

    private final ToDoRepository repository;

    @GetMapping("/todos")
    @ResponseBody
    public Iterable<ToDo> fetchToDo(){
        return this.repository.findAll();
    }

    @GetMapping("/remainingTodos")
    @ResponseBody
    public Iterable<ToDo> fetchRemainingToDo(){
        return this.repository.findByCompletedDateIsNull();
    }

    @GetMapping("/todo/{title}")
    @ResponseBody
    public ToDo fetchToDo(@PathVariable String title){
        return this.repository.findByTitle(title);
    }

    @PostMapping("/todo")
    @ResponseBody
    public void createTodo(@Valid @RequestBody ToDo todo) {
         this.repository.save(todo);
    }

    @PostMapping("/todo/complete/{todoId}")
    @ResponseBody
    public void createQuestion(@Valid @PathVariable Long todoId) {
        Optional<ToDo> todo = this.repository.findById(todoId);

        todo.ifPresent((ToDo todoOb) -> {
                    todoOb.setCompletedDate(new Date());
                    this.repository.save(todoOb);
                }
        );
    }

    @GetMapping("/")
    public String dashboard(Model model){
        return "dashboard";
    }

}
