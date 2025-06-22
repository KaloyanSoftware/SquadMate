package com.portfolio.squadmate.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime matchDate;

    private String location;

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY)
    private List<MatchAvailability> playerAvailabilities;

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY)
    private List<TeamMatch> matchEvents;
}
