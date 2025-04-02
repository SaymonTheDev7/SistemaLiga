package main;

import connection.BankConnection;
import model.Club;
import model.League;
import model.Player;
import model.Trainer;
import service.ClubService;
import service.LeagueService;
import service.TrainerService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        inputSaveLeague();
    }

    public static Club inputSaveClub() throws SQLException {

        System.out.println("Qual o nome do clube?");
        String nameClub = sc.next();

        System.out.println("Qual a data de fundação do clube?");
        String dateFoundation = sc.next();

        Club club = new Club(nameClub, dateFoundation);
        club = ClubService.saveClub(club);

        System.out.println("Clube salvo com sucesso!");

        System.out.println("Qual a experiência do treinador?");
        int experience = sc.nextInt();

        System.out.println("Qual o nome do treinador?");
        String nameTrainer = sc.next();

        Trainer trainer = new Trainer(experience, nameTrainer, club);
        trainer = TrainerService.saveTrainer(trainer);
        System.out.println("Treinador cadastrado com sucesso!");

        ClubService.updateClubWithTrainer(club.getIdClub(), trainer.getIdTrainer());
        club.setTrainer(trainer);

        return club;

    }

    public static League inputSaveLeague() throws SQLException {

        System.out.println("Qual a data de fundação da liga?");
        String dateFoundation = sc.next();

        System.out.println("Qual o nome da liga?");
        String nameLeague = sc.next();

        League league = new League(dateFoundation, nameLeague);
        league = LeagueService.saveLeague(league);

        System.out.println("Qual o ID do clube que deseja associar a essa liga?");
        int idClub = sc.nextInt();

        ClubService.saveClubLeague(idClub, league.getIdLeague());
        System.out.println("Liga foi associada ao clube!");

        return league;
    }
}
