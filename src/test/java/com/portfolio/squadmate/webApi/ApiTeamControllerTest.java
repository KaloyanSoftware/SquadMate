package com.portfolio.squadmate.webApi;

import com.portfolio.squadmate.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiTeamControllerTest {

    @Autowired
    private TestHelper testHelper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void cleanUp() {
        testHelper.cleanup();
        testHelper.createCoachUser("testCoach1@gmail.com");
    }

    @Test
    @WithUserDetails(value="testCoach1@gmail.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void shouldCreateTeam() throws Exception {
        //Arrange
        final var request = post("/api/team")
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content("""
                    {
                      "teamName": "TestTeam"
                    }
                    """);

        //Act
        final var response = mockMvc.perform(request);

        //Assert
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.name").value("TestTeam"));

    }



}
