package model;

public class Player {

    private Club club;
    private int idPlayer;
    private int yearsOld;
    private String name;
    private String position;

    public Player(Club club, int idPlayer, int yearsOld, String name, String position) {
        this.club = club;
        this.idPlayer = idPlayer;
        this.yearsOld = yearsOld;
        this.name = name;
        this.position = position;
    }

    public Player (Club club, int yearsOld, String name, String position) {
        this.club = club;
        this.yearsOld = yearsOld;
        this.name = name;
        this.position = position;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public int getYearsOld() {
        return yearsOld;
    }

    public void setYearsOld(int yearsOld) {
        this.yearsOld = yearsOld;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String toString() {
        return "\nID do jogador " + getIdPlayer() +
               "\nIdade do jogador " + getYearsOld() +
               "\nClube do jogador " + getClub() +
               "\nNome do jogador " + getName() +
               "\nPosição do jogador " + getPosition();
    }
}
