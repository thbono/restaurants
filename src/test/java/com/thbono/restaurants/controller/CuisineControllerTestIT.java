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
class CuisineControllerTestIT {

  private MockMvc mvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @BeforeEach
  public void setUp() {
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void should_find_all() throws Exception {
    mvc.perform(get("/cuisines").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", is(19)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].name", is("American")));
  }
}
