package com.example.todolistbfjodi.category;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    Category findByCategory (String category);

}
