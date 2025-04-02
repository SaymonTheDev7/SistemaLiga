package model;

import java.util.List;

public class League {

    private int idLeague;
    private String foundationYear;
    private String name;
    private List<Club> clubs;

    public League(int idLeague, String foundationYear, String name, List<Club> clubs) {
        this.idLeague = idLeague;
        this.foundationYear = foundationYear;
        this.name = name;
        this.clubs = clubs;
    }

    public League(String foundationYear, String name) {
        this.foundationYear = foundationYear;
        this.name = name;
    }

    public League() {
    }

    public int getIdLeague() {
        return idLeague;
    }

    public void setIdLeague(int idLeague) {
        this.idLeague = idLeague;
    }

    public String getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(String foundationYear) {
        this.foundationYear = foundationYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }

    public String toString() {
        return "\nID da liga " + getIdLeague() +
               "\nAno de fundação " + getFoundationYear() +
               "\nNome da liga " + getName() +
               "\nClubes " + getClubs();
    }
}
