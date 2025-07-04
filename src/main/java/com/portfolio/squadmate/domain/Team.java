package com.portfolio.squadmate.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

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
}
