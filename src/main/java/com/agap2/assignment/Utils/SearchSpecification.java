package com.agap2.assignment.Utils;


import com.agap2.assignment.domain.Recipe;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SearchSpecification {


    public static Specification<Recipe> recipeNameEquals(String name) {
        return (root, query, builder) ->
                builder.like(builder.lower(root.get("name")),name.toLowerCase());
    }

    public static Specification<Recipe> vegOrNonVegEquals(String vegetarian) {
        return (root, query, builder) ->
                builder.like(builder.lower(root.get("vegetarian")), vegetarian.toLowerCase());
    }

    public static Specification<Recipe> noOfServingsEquals(Integer serving) {
        return (root, query, builder) ->
                        builder.equal(root.get("numberOfServing"), serving);
    }

    public static Specification<Recipe> ingredientsInclude(List<String> ingredients) {
        return (root, query, builder) ->
                        builder.isMember(ingredients,root.get("ingredients"));
    }

    public static Specification<Recipe> ingredientsExclude(List<String> ingredients) {
        return (root, query, builder) ->
                        builder.isNotMember(ingredients, root.get("ingredients"));
    }

    public static Specification<Recipe> instructionLike(String instructions) {
        return (root, query, builder) ->
                        builder.like(root.get("instructions"), "%" + instructions + "%");
    }



    }

