package com.abc.recipemainservice.constants;

public class Util {

    public static final String API_GATEWAY_URL = "http://localhost:8011";

    public static final String NOTES_PATH = "/recipe-notes/notes";

    public static final String SLASH_RECIPE_NAME = "/recipeName";

    public static final String SLASH_ID = "/id";

    public static final String FORWARD_SLASH = "/";
    public static final String RECIPE_PATH = "/recipe";

    private Util() {
        throw new RuntimeException("Can't instantiate this class!");
    }
}
