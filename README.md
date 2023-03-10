# Favourite Recipe API

    Technology used : Java 8,Gradle-5.6.1,Springboot,JPA, Spring security, JWT token, Swagger UI
    Database : H2 In memory DB
    version Control : github
    Tools : InteijIDEA,Postman

## How to Run :
    Step 1 : Configure the gradle
    step 2 : Configure springboot application and click the run  button
    Step 3 : execute below link for Swagger ui and Database

    Database link : http://localhost:8080/h2-console
            username : sa   
            Password : password
    Swagger Ui link : http://localhost:8080/swagger-ui.html

## Implementation of REST API 

### Favourite Recipe API

	1)To Add Recipe 
          POST : http://localhost:8080/recipe
				{
					"name": "pizza",
					"vegetarian": "NV",
					"numberOfServing": 3,
					"instructions": "oven",
					"ingredients" : ["chicken","onion","panner"]
				}
				{
					"name": "panner",
					"vegetarian": "V",
					"numberOfServing": 4,
					"instructions": "gravy",
					"ingredients" : ["paneer","onion","pease"]
				}

				{
					"name": "panner chilli",
					"vegetarian": "NV",
					"numberOfServing": 2,
					"instructions": "dry",
					"ingredients" : ["paneer","onion","chilli"]
				}

				{
					"name": "poha",
					"vegetarian": "V",
					"numberOfServing": 1,
					"instructions": "",
					"ingredients" : ["poha","onion","potato"]
				}

				{
					"name": "upma",
					"vegetarian": "V",
					"numberOfServing": 1,
					"instructions": "abc",
					"ingredients" : ["rava","onion"]
				}
			Add above entries.

          2)To Update Recipe
          PUT : http://localhost:8080/recipe
				{
					"id": 1,
					"name": "upma",
					"vegetarian": "V",
					"numberOfServing": 1,
					"instructions": "abc",
					"ingredients" : ["rava","onion"]
				}

          3)To Get Recipe by Id
          GET : http://localhost:8080/recipe/1
		  
		  4)To Delete Recipe by Id
          DELETE : http://localhost:8080/recipe/2

          5)To Get All Recipe
          GET : http://localhost:8080/recipe
		  
		  6)To Get All Veg Recipe
		  GET : http://localhost:8080/recipe?vegetarian=V
		  
		  7)Get Recipe excluding or Including ingredients
		  GET : http://localhost:8080/recipe?ingredients=paneer&isInclude=false&instructions=oven
		  
		  Note : If you want to search any excluding or including ingredients then you must have to give below request params
				1)You want to fetch including some ingredients then put ingredients=paneer&isInclude=true
				2)You want to fetch excluding some ingredients then put ingredients=paneer&isInclude=false
