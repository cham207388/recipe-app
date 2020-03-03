package com.abc.recipemainservice.constants;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import static com.abc.recipemainservice.constants.ServiceName.NOTE_SERVICE;
import static com.abc.recipemainservice.constants.Util.FORWARD_SLASH;
import static com.abc.recipemainservice.constants.Util.NOTES_PATH;

public class ServiceUrl {

    public static final String API_GATE_WAY = "http://localhost:8011";

    public static final String API_GATEWAY_NOTES = API_GATE_WAY + "/recipe-notes/notes";

    public static final String NOTES_URL = "http://localhost:8082/notes";

    private final static String NOTES_BASE_URL = API_GATE_WAY + FORWARD_SLASH + NOTE_SERVICE + NOTES_PATH;

    public final static String NOTES_RECIPE_NAME_PATH = NOTES_BASE_URL + FORWARD_SLASH + "recipeName" + FORWARD_SLASH;

    public final static String NOTES_ID_PATH = NOTES_BASE_URL + FORWARD_SLASH + "id" + FORWARD_SLASH;

    public static final WebClient WEB_CLIENT = WebClient.builder()
            .baseUrl(API_GATEWAY_NOTES)
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    private ServiceUrl() {
        throw new RuntimeException("Can't instantiate this class!");
    }
}
