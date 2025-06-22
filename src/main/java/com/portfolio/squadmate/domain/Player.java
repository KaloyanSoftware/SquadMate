package com.portfolio.squadmate.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
public class Player extends User{
    @Setter
    @Getter
    private int jerseyNumber;

    @Setter
    @Getter
    private Position position;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private List<MatchAvailability> matchAvailabilities;
}
