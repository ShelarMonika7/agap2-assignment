package com.agap2.assignment.domain;

import java.util.List;

public class CreateRecipeRequest {

    private Integer id;

    private String name;

    private String vegetarian;

    private Integer numberOfServing;

    private List<String> ingredients;

    private String instructions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(String vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Integer getNumberOfServing() {
        return numberOfServing;
    }

    public void setNumberOfServing(Integer numberOfServing) {
        this.numberOfServing = numberOfServing;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

}
