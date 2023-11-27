package com.persistence.hub.challenge.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class ChessTournament {

    @Id
    private Long id;

    @ManyToMany
    private Set<ChessPlayer> players;

    private String name;

    public Long getId() {
        return id;
    }

    public Set<ChessPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(Set<ChessPlayer> players) {
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
