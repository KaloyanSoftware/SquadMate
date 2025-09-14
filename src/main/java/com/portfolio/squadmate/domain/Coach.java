package com.portfolio.squadmate.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coach")
public class Coach extends User {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Team team;
}
