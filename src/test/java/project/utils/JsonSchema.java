package project.utils;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.response.Response;

public class JsonSchema {

    public static void validate(Response response, String schemaJsonFile) {

        response.then().body(matchesJsonSchemaInClasspath("schemas/" + schemaJsonFile));
    }
    
  
}