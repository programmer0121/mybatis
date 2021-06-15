package org.example.mybatis.city;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CityService service;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cities"))
                .andExpect(status().isOk());
        verify(service, times(1)).findAll();
        verify(service, times(0)).findById(anyLong());
    }

    @Test
    void getOne() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cities/2"))
                .andExpect(status().isOk());
        verify(service, times(0)).findAll();
        verify(service, times(1)).findById(anyLong());
    }

    @Test
    void addCity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/cities/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"name\": \"Lublin\", \"country\": \"Poland\" }"))
                .andExpect(status().isOk());
        verify(service, times(1)).insert(any(City.class));
    }
}