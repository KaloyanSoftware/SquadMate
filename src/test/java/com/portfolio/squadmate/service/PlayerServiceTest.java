package com.portfolio.squadmate.service;

import com.portfolio.squadmate.TestHelper;
import com.portfolio.squadmate.domain.*;
import com.portfolio.squadmate.repository.PlayerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PlayerServiceTest {

    @Autowired
    private PlayerService sut;

    @Autowired
    private PlayerRepository playerRepository;


    @Autowired
    private TestHelper testHelper;

    @AfterEach
    public void cleanup(){
        testHelper.cleanup();
    }

    @Test
    public void shouldPatchAllPlayerFields(){
        //Arrange
        final Player player = testHelper.createPlayer(10, Position.GOALKEEPER,true);

        //Act
        final Player patchedPlayer = sut.patchPlayer(player.getId(),25,Position.CENTRAL_MIDFIELDER,false);

        //Assert
        assertEquals(25,patchedPlayer.getJerseyNumber());
        assertEquals(Position.CENTRAL_MIDFIELDER,patchedPlayer.getPosition());
        assertFalse(patchedPlayer.isStarter());
    }


    @Test
    public void shouldPatchNoFields(){
        //Arrange
        final Player player = testHelper.createPlayer(10, Position.GOALKEEPER,true);

        //Act
        final Player patchedPlayer = sut.patchPlayer(player.getId(),0,null,true);

        //Assert
        assertEquals(10, patchedPlayer.getJerseyNumber());
        assertEquals(Position.GOALKEEPER,patchedPlayer.getPosition());
        assertTrue(patchedPlayer.isStarter());
    }


    @Test
    public void shouldPatchOnlyJerseyNumber(){
        //Arrange
        final Player player = testHelper.createPlayer(10, Position.GOALKEEPER,true);

        //Act
        final Player patchedPlayer = sut.patchPlayer(player.getId(),13,null,true);

        //Assert
        assertEquals(13,patchedPlayer.getJerseyNumber());
        assertEquals(Position.GOALKEEPER,patchedPlayer.getPosition());
        assertTrue(patchedPlayer.isStarter());
    }

    @Test
    public void shouldPatchOnlyPosition(){
        //Arrange
        final Player player = testHelper.createPlayer(10, Position.GOALKEEPER,true);

        //Act
        final Player patchedPlayer = sut.patchPlayer(player.getId(),0,Position.CENTER_BACK,true);

        //Assert
        assertEquals(10,patchedPlayer.getJerseyNumber());
        assertEquals(Position.CENTER_BACK,patchedPlayer.getPosition());
        assertTrue(patchedPlayer.isStarter());
    }

    @Test
    public void shouldPatchOnlyisStarter(){
        //Arrange
        final Player player = testHelper.createPlayer(10, Position.GOALKEEPER,true);

        //Act
        final Player patchedPlayer = sut.patchPlayer(player.getId(),0,null,false);

        //Assert
        assertEquals(10,patchedPlayer.getJerseyNumber());
        assertEquals(Position.GOALKEEPER,patchedPlayer.getPosition());
        assertFalse(patchedPlayer.isStarter());
    }

    @Test
    public void shouldAddPlayerToTeamWithNewJeseyNumberAndPosition(){
        //Arrange
        final Player player = testHelper.createPlayer(10, Position.GOALKEEPER,true);

        final Coach coach = testHelper.createCoach("testCoach@gmail.com");

        final Team team = testHelper.createTeam(coach);

        //Act
        final Player addedPlayer = sut.addPlayerToTeam(player.getId(), 7, Position.LEFT_WINGER,coach.getId());

        //Assert
        assertEquals(team.getId(), addedPlayer.getTeam().getId());
        assertTrue(team.getPlayers().contains(addedPlayer));
        assertEquals(7,addedPlayer.getJerseyNumber());
        assertEquals(Position.LEFT_WINGER,addedPlayer.getPosition());
        assertTrue(addedPlayer.isStarter());
    }

}
