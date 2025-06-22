package com.portfolio.squadmate.domain;

import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private Position position;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private List<MatchAvailability> matchAvailabilities;
}
