package com.portfolio.squadmate.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TeamMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    private Match match;

    // For opponents not in DB
    @Column(name = "opponent_name")
    private String opponentName;

    private int redCards;

    private int yellowCards;

    private int goals;

    private int corners;

    private double ballPossession;

    private int totalShots;

    private int shotsOnTarget;

    private int fouls;

    private int lineUpNotes;

    private int totalPasses;

    public void setTeam(final Team team){
        this.team = team;
        team.getMatchParticipations().add(this);
    }
}
