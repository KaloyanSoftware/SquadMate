package com.portfolio.squadmate.webApi;

import com.portfolio.squadmate.TestHelper;
import com.portfolio.squadmate.domain.Coach;
import com.portfolio.squadmate.domain.Player;
import com.portfolio.squadmate.domain.Position;
import com.portfolio.squadmate.domain.Team;
import com.portfolio.squadmate.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiPlayerControllerUnitTest {

    @MockitoBean
    private PlayerService playerService;

    @Autowired
    private TestHelper testHelper;

    private Coach coach;



    @BeforeEach
    public void cleanup(){
        testHelper.cleanup();
        coach = testHelper.createCoach("testCoach@gmail.com");
    }

    @Test

    public void shouldPatchPlayer(){
        //Arrange
        final Team team = testHelper.createTeam(coach);

        final Player player = testHelper.createPlayerWithTeam(13, Position.ATTACKING_MIDFIELDER, true,team);

        //Act
        //build the mock mvc patch request

        //Assert
    }
}
