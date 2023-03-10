package com.agap2.assignment.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="RECIPE")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Integer id;

    @Column(name="NAME")
    private String name;

    @Column(name="VEGETARIAN")
    private String vegetarian;

    @Column(name="NUMBER_OF_SERVING")
    private Integer numberOfServing;

    @Column(name="INGREDIENTS")
    @ElementCollection(targetClass=String.class)
    private List<String> ingredients;

    @Column(name="INSTRUCTIONS")
    private String instructions;

    public Recipe(Integer id,String name, String vegetarian, Integer numberOfServing, List<String> ingredients, String instructions) {
        this.id = id;
        this.name = name;
        this.vegetarian = vegetarian;
        this.numberOfServing = numberOfServing;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public Recipe() {
    }

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

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vegetarian=" + vegetarian +
                ", numberOfServing='" + numberOfServing + '\'' +
                ", ingredients=" + ingredients +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
