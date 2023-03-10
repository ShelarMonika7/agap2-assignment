package com.agap2.assignment.service;

import com.agap2.assignment.Utils.MsgConstants;
import com.agap2.assignment.Utils.SearchSpecification;
import com.agap2.assignment.domain.CreateRecipeRequest;
import com.agap2.assignment.domain.PageResultInfo;
import com.agap2.assignment.domain.Recipe;
import com.agap2.assignment.repository.RecipeRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class RecipeServiceImpl implements RecipeService {

    private static final Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);

    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public PageResultInfo<Integer> createRecipe(@Valid CreateRecipeRequest createRecipeRequest) throws NotFoundException {
        if (createRecipeRequest == null) {
            throw new NotFoundException("Recipe or Id must not be null");
        }
        try {
            Recipe recipeInfo = new Recipe();
            BeanUtils.copyProperties(createRecipeRequest, recipeInfo);
            recipeInfo = recipeRepository.save(recipeInfo);
            logger.info(MsgConstants.RECIPE_REGISTRATION_MESSEGE, recipeInfo.getId());
            return PageResultInfo.getSucceedInstanceForRegistration(recipeInfo.getId(), MsgConstants.RECIPE_REGISTRATION_MESSEGE);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return PageResultInfo.getFailInstance();
    }

    @Override
    public PageResultInfo<Recipe> getRecipeDetails(Integer id) throws NotFoundException {
        Optional<Recipe> recruiterOptional = recipeRepository.findById(id);
        if (recruiterOptional.isPresent()) {
            Recipe recipe = recruiterOptional.get();
            return PageResultInfo.getSucceedInstance(recipe);
        } else {
            throw new NotFoundException("Recipe or Id must not be null");
        }
    }

    @Override
    public PageResultInfo<Integer> deleteRecipe(Integer id) {
        try {
            recipeRepository.deleteById(id);
            logger.info(MsgConstants.RECIPE_DELETE_MESSEGE, id);
            return PageResultInfo.getSucceedInstanceForRegistration(id, MsgConstants.RECIPE_DELETE_MESSEGE);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return PageResultInfo.getFailInstance();
        }
    }

    @Override
    public PageResultInfo<List<Recipe>> getRecipeList(String name, String vegetarian, Integer numberOfServing, List<String> ingredients, String instructions, Boolean isInclude, Pageable pageable) {
        Page<Recipe> recipeList = null;
        Specification<Recipe> specTotal = null;

        ArrayList<Specification> recipeSpecs = getRecipeSpecification(name, vegetarian, numberOfServing, ingredients, instructions, isInclude);
        if(recipeSpecs != null){
            specTotal = getTotalSpecification(recipeSpecs);}

        recipeList = recipeRepository.findAll(specTotal, pageable);
        List<Recipe> recipes = recipeList.getContent();
        return PageResultInfo.getSucceedInstanceForList(recipes, recipeList.getTotalElements(),
                recipeList.getTotalPages());

    }

    @Override
    public PageResultInfo<Recipe> updateRecipe(@Valid Recipe recipe) throws NotFoundException {

        if(recipe == null || recipe.getId() == null){
            throw new NotFoundException("Recipe ot Id must not be null");
        }

        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipe.getId());
        if(!optionalRecipe.isPresent()){
            throw new NotFoundException("Recipe with id "+recipe.getId()+" does not exist");
        }

        Recipe existingRecord = optionalRecipe.get();
        existingRecord.setName(recipe.getName());
        existingRecord.setVegetarian(recipe.getVegetarian());
        existingRecord.setNumberOfServing(recipe.getNumberOfServing());
        existingRecord.setIngredients(recipe.getIngredients());
        existingRecord.setInstructions(recipe.getInstructions());

        existingRecord = recipeRepository.save(existingRecord);
        logger.info(MsgConstants.RECIPE_UPDATE_MESSEGE, existingRecord.getId() );
        return PageResultInfo.getSucceedInstanceForUpdate(existingRecord,MsgConstants.RECIPE_UPDATE_MESSEGE);
    }


    private ArrayList<Specification> getRecipeSpecification(String name, String vegetarian, Integer numberOfServing, List<String> ingredients, String instructions, Boolean isInclude){
        ArrayList<Specification> recipeSpecs = new ArrayList<Specification>();
        if (name != null) {
            Specification spec1 = SearchSpecification.recipeNameEquals(name);
            recipeSpecs.add(spec1);
        }

        if (vegetarian != null) {
            Specification spec2 = SearchSpecification.vegOrNonVegEquals(vegetarian);
            recipeSpecs.add(spec2);
        }

        if (numberOfServing != null) {
            Specification spec3 = SearchSpecification.noOfServingsEquals(numberOfServing);
            recipeSpecs.add(spec3);
        }

        Specification spec4 = null;
        if (ingredients != null && isInclude != null) {
            if (isInclude == true) {
                spec4 = SearchSpecification.ingredientsInclude(ingredients);
            } else if (isInclude == false) {
                spec4 = SearchSpecification.ingredientsExclude(ingredients);
            }
            recipeSpecs.add(spec4);
        }

        if (instructions != null) {
            Specification spec5 = SearchSpecification.instructionLike(instructions);
            recipeSpecs.add(spec5);
        }

        return recipeSpecs;

    }

    private Specification<Recipe> getTotalSpecification(ArrayList<Specification> recipeSpecs){

        int totalToAppend = 0;
        totalToAppend = recipeSpecs.size();
        Specification<Recipe> specTotal = null;

        switch (totalToAppend) {
            case 1: {
                specTotal = Specification.where(recipeSpecs.get(0));
                break;
            }
            case 2: {
                specTotal = Specification.where(recipeSpecs.get(0).and(recipeSpecs.get(1)));
                break;
            }
            case 3: {
                specTotal = Specification.where(recipeSpecs.get(0).and(recipeSpecs.get(1)).and(recipeSpecs.get(2)));
                break;
            }
            case 4: {
                specTotal = Specification.where(recipeSpecs.get(0).and(recipeSpecs.get(1)).and(recipeSpecs.get(2)).and(recipeSpecs.get(3)));
                break;
            }
            case 5: {
                specTotal = Specification.where(recipeSpecs.get(0).and(recipeSpecs.get(1)).and(recipeSpecs.get(2)).and(recipeSpecs.get(3)).and(recipeSpecs.get(4)));
                break;
            }
        }
        return specTotal;
    }


}
