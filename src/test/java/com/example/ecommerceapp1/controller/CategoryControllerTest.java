package com.example.ecommerceapp1.controller;

import com.example.ecommerceapp1.EcommerceApp1Application;
import com.example.ecommerceapp1.Entity.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)   // for running this with junit4
@ContextConfiguration(classes = EcommerceApp1Application.class)  // context setting for the real data (added in main)
@SpringBootTest // spring test
@FixMethodOrder(MethodSorters.NAME_ASCENDING)  // to execute the test methods in order (based on name)
public class CategoryControllerTest {
    // For controller based mocks (for web layer)
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext movieContext; // autowired the configuration


    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(movieContext).build();

    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            System.out.println(jsonContent);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





    // THESE test cases check the payload details after the controller URI is called;

    @Test
    public void verifyAllCategory() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/category/getAllCategory")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andDo(print());
    }


    @Test
    public void verifyGetCategoryById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/category/getCatById/657c29914327112f3d854e90")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("657c29914327112f3d854e90"))
                .andDo(print());
    }

    @Test
    public void verifyGetCategoryById_EXCEPTION() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/category/getCatById/657c29914327112f0d854e90")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Movie not exists!!"))
                .andDo(print());
    }





    @Test
    public void verfiySaveCategory() throws Exception{
        Category category = new Category(new ObjectId("636dcf444b7e8832baeb2607"), "abc", "2002");

        mockMvc.perform(MockMvcRequestBuilders.post("/category/add")
                        .content(asJsonString(category))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value("636dcf444b7e8832baeb2607"))
                .andExpect(jsonPath("$.name").value("abc"))
                .andDo(print());

    }

    @Test
    public void verfiySaveCategory_EXCEPTION() throws Exception{
        Category movie = new Category(new ObjectId("636dcf444b7e8832baeb2607"), "abc", "2002");

        mockMvc.perform(MockMvcRequestBuilders.post("/category/add")
                        .content(asJsonString(movie))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("PAYLOAD MALFORMED. OBJECT ID MUST NOT BE DEFINED"))
                .andDo(print());
    }



    @Test
    public void verifyUpdateCategory() throws Exception{
        Category category = new Category(new ObjectId("636dce61bfff8e1d3db94ef1"), "Mission Impossible 1", "2002-10-10");

        mockMvc.perform(MockMvcRequestBuilders.patch("/category/update/636dce61bfff8e1d3db94ef1")
                        .content(asJsonString(category))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value("636dce61bfff8e1d3db94ef1"))
                .andExpect(jsonPath("$.name").value("Mission Impossible 1"))
                .andDo(print());
    }

    @Test
    public void verifyUpdateCategory_EXCEPTION() throws Exception{
        Category movie = new Category(new ObjectId("636dce61bfff8e1d3db94ef1"), "Mission Impossible 1", "2002-10-10");

        mockMvc.perform(MockMvcRequestBuilders.patch("/category/update/636dce61bfff8e1d3db94ef1")
                        .content(asJsonString(movie))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Category DOESN'T EXISTS"))
                .andDo(print());
    }



    @Test
    public void verifyDeleteById() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/category/del/636dcf444b7e8832baeb2607")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Successfully Deleted !!"))
                .andDo(print());
    }

    @Test
    public void verifyDeleteById_EXCEPTION() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/category/del/636dcf444b7e8832baeb2607")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Movie DOESN'T Exists"))
                .andDo(print());
    }





}
