package com.abc.recipenotesservice.bootstrap;

import com.abc.recipenotesservice.domain.Notes;
import com.abc.recipenotesservice.service.NotesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final NotesService notesService;

    @Override
    public void run(String... args) {
        loadNotes();
    }

    private void loadNotes() {
        log.info("Loading notes in db started.");
        Notes recipe1 = new Notes();
        recipe1.setRecipeId("recipe1");
        recipe1.setRecipeNotes("Steps to cook mbahal: 1. Bake the makarel and can beef, " +
                "2. Cook the shrimps with the rice, 3. Microwave the ground peanut");
        notesService.save(recipe1);

        Notes recipe2 = new Notes();
        recipe2.setRecipeId("recipe2");
        recipe2.setRecipeNotes("Steps to cook shrimps: 1. Fry the potatoes and take them out, " +
                "2. Fry the shrimps for about 10 minutes, 3. Put the marinated onions");
        notesService.save(recipe2);
        log.info("There are {} notes in db", notesService.count());
        log.info("Loading notes in db completed!");
    }
}
