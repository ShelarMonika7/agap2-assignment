package com.agap2.assignment.web;

import com.agap2.assignment.domain.CreateRecipeRequest;
import com.agap2.assignment.domain.PageResultInfo;
import com.agap2.assignment.domain.Recipe;
import com.agap2.assignment.service.RecipeService;
import com.agap2.assignment.service.RecipeServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/recipe", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PageResultInfo<Integer> createRecipe(@Valid @RequestBody CreateRecipeRequest createRecipeRequest)throws NotFoundException {
        return recipeService.createRecipe(createRecipeRequest);
    }

    @GetMapping("/{id}")
    PageResultInfo<Recipe> getRecipeDetails(@PathVariable("id") final Integer id) throws NotFoundException {
        return recipeService.getRecipeDetails(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    PageResultInfo<Integer> deleteRecipe(@PathVariable("id") final Integer id) {
        return recipeService.deleteRecipe(id);
    }

    @GetMapping
    PageResultInfo<List<Recipe>> getRecipeList(@RequestParam(value = "name", required=false) String name,
                                               @RequestParam(value = "vegetarian", required=false) String vegetarian, @RequestParam (value = "numberOfServing", required=false) Integer numberOfServing,
                                               @RequestParam(value = "ingredients", required=false) List<String> ingredients, @RequestParam (value = "instructions", required=false) String  instructions,
                                               @RequestParam(value = "isInclude", required=false) Boolean isInclude, Pageable pageable) {
        return recipeService.getRecipeList(name, vegetarian, numberOfServing, ingredients, instructions, isInclude, pageable);
    }

    @PutMapping
    PageResultInfo<Recipe> updateRecipe(@Valid @RequestBody Recipe recipe) throws Exception {
        return recipeService.updateRecipe(recipe);
    }
}

