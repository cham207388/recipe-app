package com.abc.recipenotesservice.repository;

import com.abc.recipenotesservice.model.entity.Notes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotesRepository extends CrudRepository<Notes, Long> {

    Optional<Notes> findByRecipeName(String recipeName);

    //@Query("delete from Notes n where n.recipeName = ?1")
    void deleteByRecipeName(String recipeName);

    boolean existsByRecipeName(String recipeName);
}
