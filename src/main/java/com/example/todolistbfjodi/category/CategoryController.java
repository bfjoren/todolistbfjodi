package com.example.todolistbfjodi.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CategoryController {

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    private final CategoryRepository repository;

    @GetMapping("/category")
    @ResponseBody
    public Iterable<Category> fetchToDo(){
        return this.repository.findAll();
    }


}
