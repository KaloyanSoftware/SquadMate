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

    private List<Player> players;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<TeamMatch> matchParticipations;
}
