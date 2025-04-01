package model;

import java.util.List;

public class Club {

    private int idClub;
    private String name;
    private String dateFoundation;
    private List<League> leagues;
    private List<Player> players;
    private Trainer trainer;

    public Club(int idClub, String name, String dateFoundation, List<League> leagues, List<Player> players, Trainer trainer) {
        this.idClub = idClub;
        this.name = name;
        this.dateFoundation = dateFoundation;
        this.leagues = leagues;
        this.players = players;
        this.trainer = trainer;
    }

    public Club(String name, String dateFoundation, List<League> leagues, List<Player> players, Trainer trainer) {
        this.name = name;
        this.dateFoundation = dateFoundation;
        this.leagues = leagues;
        this.players = players;
        this.trainer = trainer;
    }

    public int getIdClub() {
        return idClub;
    }

    public void setIdClub(int idClub) {
        this.idClub = idClub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateFoundation() {
        return dateFoundation;
    }

    public void setDateFoundation(String dateFoundation) {
        this.dateFoundation = dateFoundation;
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public String toString() {
        return "\nID do clube " + getIdClub() +
               "\nNome do clube " + getName() +
               "\nData de fundação " + getDateFoundation() +
               "\nLigas " + getLeagues() +
               "\nJogadores " + getPlayers() +
               "\nTreinador " + getTrainer();
    }
}
