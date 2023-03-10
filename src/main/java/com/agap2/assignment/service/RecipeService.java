package com.agap2.assignment.service;

import com.agap2.assignment.domain.CreateRecipeRequest;
import com.agap2.assignment.domain.PageResultInfo;
import com.agap2.assignment.domain.Recipe;
import javassist.NotFoundException;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface RecipeService {
   PageResultInfo<Integer> createRecipe(@Valid CreateRecipeRequest createRecipeRequest)throws NotFoundException;

   PageResultInfo<Recipe> getRecipeDetails(Integer id) throws NotFoundException;

    PageResultInfo<Integer> deleteRecipe(Integer id);

    PageResultInfo<List<Recipe>> getRecipeList(String name,
                                               String vegetarian, Integer numberOfServing,
                                               List<String> ingredients, String instructions,
                                               Boolean isInclude, Pageable pageable);
    PageResultInfo<Recipe> updateRecipe(@Valid Recipe recipe) throws NotFoundException;


}
