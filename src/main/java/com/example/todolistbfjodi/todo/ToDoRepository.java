package com.example.todolistbfjodi.todo;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ToDoRepository extends PagingAndSortingRepository<ToDo, Long> {

    ToDo findByTitle (String title);

    Iterable<ToDo> findByCompletedDateIsNull();

}
