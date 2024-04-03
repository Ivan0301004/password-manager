package com.ivan.passwordmanager.repository;

import com.ivan.passwordmanager.model.Category;
import com.ivan.passwordmanager.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.name= :category")
    Optional<Category> findCategoryByName(@Param("category") String category);
}
