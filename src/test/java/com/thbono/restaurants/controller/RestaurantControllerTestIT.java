package com.thbono.restaurants.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class RestaurantControllerTestIT {

  private MockMvc mvc;

  @Autowired private WebApplicationContext webApplicationContext;

  @BeforeEach
  public void setUp() {
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void should_find_all() throws Exception {
    mvc.perform(get("/restaurants").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", is(200)))
        .andExpect(jsonPath("$[0].name", is("Deliciousgenix")));
  }

  @Test
  void should_find_best() throws Exception {
    mvc.perform(get("/restaurants/best").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", is(5)))
        .andExpect(jsonPath("$[4].name", is("Sizzle Yummy")));
  }

  @Test
  void should_not_find_best_with_invalid_param() throws Exception {
    mvc.perform(
            get("/restaurants/best").queryParam("rating", "A").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void should_not_find_best_with_out_of_bounds_param() throws Exception {
    mvc.perform(
            get("/restaurants/best").queryParam("rating", "6").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void should_find_best_by_cuisine() throws Exception {
    mvc.perform(
            get("/restaurants/best")
                .queryParam("cuisine", "Chinese")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", is(5)))
        .andExpect(jsonPath("$[0].name", is("Deliciouszilla")));
  }

  @Test
  void should_find_empty_best() throws Exception {
    mvc.perform(
            get("/restaurants/best")
                .queryParam("cuisine", "Invalid")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", is(0)));
  }
}
