package com.portfolio.squadmate.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "player")
public class Player extends User{
    @Setter
    @Getter
    private int jerseyNumber;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private Position position;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private List<MatchAvailability> matchAvailabilities = new ArrayList<>();

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @Getter
    @Setter
    private boolean isStarter;

    public void setTeam(final Team team){
        if (this.team != null) {
            this.team.getPlayers().remove(this);
        }
        this.team = team;
        if (team != null) {
            team.getPlayers().add(this);
        }
    }
}
