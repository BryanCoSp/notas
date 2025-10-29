package com.notas.notas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class WebConfig {
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}

//@Test
//void debeCrearUnaNuevaNotePorApi() throws Exception {
//    //Arrange
//    String newNoteJson = "{\"title\": \"Note title\",\"description\": \"Noteeeeeee2 test\", \"completed\":false}";
//
//    //Act and Assert
//
//    mockMvc.perform(post("/api/notas")
//                    .with(user("testUser").roles("USER"))
//                    .with(csrf())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(newNoteJson))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.description").value("Noteeeeeee2 test"))
//            .andExpect(jsonPath("$.id").exists());
//
//
//}

