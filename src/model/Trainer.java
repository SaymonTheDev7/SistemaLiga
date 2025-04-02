package model;

import java.util.List;

public class Trainer {

    private int idTrainer;
    private int experience;
    private String name;
    private Club club;

    public Trainer(int idTrainer, int experience, String name, Club club) {
        this.idTrainer = idTrainer;
        this.experience = experience;
        this.name = name;
        this.club = club;
    }

    public Trainer(int experience, String name, Club club) {
        this.experience = experience;
        this.name = name;
        this.club = club;
    }

    public Trainer() {

    }

    public int getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(int idTrainer) {
        this.idTrainer = idTrainer;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String toString () {
        return "\nID do treinador: " +
               "\nExperiência do treinador: " +
               "\nNome do treinador: " +
               "\nClube do treinador: ";
    }
}
