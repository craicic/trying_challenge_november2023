package com.persistence.hub.challenge.query;

import java.util.ArrayList;
import java.util.List;

public class TournamentValue {
    
    private Long id;  
    private String tournamentName;
    private List<String> playerNames = new ArrayList<>();

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(List<String> playerNames) {
        this.playerNames = playerNames;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
