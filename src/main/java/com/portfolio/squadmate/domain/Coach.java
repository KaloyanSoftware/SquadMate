package com.portfolio.squadmate.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Coach extends User {
    @OneToOne(fetch = FetchType.LAZY)

    private Team team;
}
