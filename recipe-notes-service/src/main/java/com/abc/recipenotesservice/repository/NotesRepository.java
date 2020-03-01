package com.abc.recipenotesservice.repository;

import com.abc.recipenotesservice.domain.Notes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends CrudRepository<Notes, Long> {

    Notes findByRecipeId(String recipeId);
}