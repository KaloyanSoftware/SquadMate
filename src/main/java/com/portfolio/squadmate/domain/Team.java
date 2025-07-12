package com.portfolio.squadmate.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Setter
@Getter
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Player> players;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<TeamMatch> matchParticipations;

    @OneToOne(fetch = FetchType.LAZY)
    private Coach coach;

    public List<Integer> getAvailableJerseyNumbers(){
        List<Integer> allJerseyNumbers = IntStream.rangeClosed(1, 30)
                .boxed()
                .toList();

        Set<Integer> takenJerseyNumbers = players.stream()
                .map(Player::getJerseyNumber)
                .collect(Collectors.toSet());

        // Filter and return only the jersey numbers that are NOT taken
        return allJerseyNumbers.stream()
                .filter(number -> !takenJerseyNumbers.contains(number))
                .collect(Collectors.toList());
    }

    public void setCoach(final Coach coach){
        this.coach = coach;
        coach.setTeam(this);
    }
}
