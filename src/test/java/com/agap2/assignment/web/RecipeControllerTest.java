package com.agap2.assignment.web;

import com.agap2.assignment.domain.Recipe;
import com.agap2.assignment.repository.RecipeRepository;
import com.agap2.assignment.service.RecipeService;
import com.agap2.assignment.service.RecipeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RecipeControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    private Pageable pageable;

    @InjectMocks
    RecipeController recipeController;

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    RecipeServiceImpl recipeServiceImpl;

    Recipe recipe_1 = new Recipe(1, "Paneer", "V",1,Arrays.asList("potato","paneer"),"must be in oven");
    Recipe recipe_2 = new Recipe(1, "Paneer Chilli", "V",4,Arrays.asList("potato","paneer"),"must be in oven");
    Recipe recipe_3 = new Recipe(1, "Poha", "V",2,Arrays.asList("poha","tomato"),"must be in oven");
    Recipe recipeInfo = new Recipe(1, "Upma", "V",2,Arrays.asList("rava","onion"),"must be in oven");
    Recipe updatedRecord = new Recipe(1, "Paneer", "V",1,Arrays.asList("paneer","peas"),"must be in oven");

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(recipeController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void getAllRecipeSuccess() throws Exception{

        List<Recipe> list = new ArrayList<>(Arrays.asList(recipe_1,recipe_2,recipe_3));
        Page<Recipe> page = new PageImpl<Recipe>(list);

        Mockito.when(recipeRepository.findAll(pageable)).thenReturn((page));

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getRecipeByIdSuccess() throws Exception{

        Mockito.when(recipeRepository.findById(recipe_1.getId())).thenReturn((Optional.of(recipe_1)));

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createRecipeSuccess() throws Exception{
        Mockito.when(recipeRepository.save(recipeInfo)).thenReturn(recipeInfo);

        String content = objectWriter.writeValueAsString(recipeInfo);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/recipe")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    public void updateCarSuccess() throws Exception{
        Mockito.when(recipeRepository.findById(recipe_1.getId())).thenReturn(Optional.of(recipe_1));
        Mockito.when(recipeRepository.save(updatedRecord)).thenReturn(recipeInfo);

        String updatedContent = objectWriter.writeValueAsString(updatedRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/recipe")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updatedContent);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());

    }


    @Test
    public void deleteCarByIdSuccess() throws Exception{

        Mockito.when(recipeRepository.findById(recipe_1.getId())).thenReturn(Optional.of(recipe_1));

        mockMvc.perform(MockMvcRequestBuilders.delete("/recipe/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
