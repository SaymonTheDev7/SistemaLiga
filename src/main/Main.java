package main;
import model.Club;
import model.League;
import model.Player;
import model.Trainer;
import service.ClubService;
import service.LeagueService;
import service.PlayerService;
import service.TrainerService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        System.out.println(inputSearchClubPerId());
    }

    public static Club inputSaveClub() throws SQLException {

        System.out.println("Qual o nome do clube?");
        String nameClub = sc.next();

        System.out.println("Qual a data de fundação do clube?");
        String dateFoundation = sc.next();

        Club club = new Club(nameClub, dateFoundation);
        club = ClubService.saveClub(club);

        System.out.println("Clube salvo com sucesso!");

        List <Trainer> trainers = TrainerService.getAllTrainers();

        if (!trainers.isEmpty()) {

            System.out.println("Já existem treinadores cadastrados. Deseja associar um deles ao clube?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não, quero cadastrar um novo treinador.");
            int option = sc.nextInt();

            if (option == 1) {

                System.out.println("Escolha o ID do treinador para associar ao clube:");
                for (Trainer trainer : trainers) {
                    System.out.println("ID: " + trainer.getIdTrainer() + " | Nome: " + trainer.getName());
                }

                int idTrainer = sc.nextInt();

                ClubService.updateClubWithTrainer(club.getIdClub(), idTrainer);
                club.setTrainer(TrainerService.searchTrainerPerId(idTrainer));
                System.out.println("Treinador associado com sucesso!");
                return club;
            }
        }

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

    public static Club inputSearchClubPerId () throws SQLException {

        System.out.println("Insira o ID do Clube que deseja buscar:");
        int idClub = sc.nextInt();
        Club club = ClubService.searchClubPerId(idClub);
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

    public static Player inputSavePlayer() throws SQLException {

        System.out.println("Qual o nome do jogador?");
        String namePlayer = sc.next();

        System.out.println("Quantos anos o jogador tem?");
        int yearsOld = sc.nextInt();

        System.out.println("Qual a posição do jogador?");
        String position = sc.next();

        System.out.println("Qual o ID do clube desse jogador?");
        int idClub = sc.nextInt();

        Club club = ClubService.searchClubPerId(idClub);

        Player player = new Player(club, yearsOld, position, namePlayer);
        player = PlayerService.savePlayer(player);

        ClubService.saveClubPlayer(idClub, player.getIdPlayer());

        return player;

    }

    public static Trainer inputSaveTrainer () throws SQLException {

        System.out.println("Qual a experiência do treinador?");
        int experience = sc.nextInt();

        System.out.println("Qual o nome do treinador?");
        String nameTrainer = sc.next();

        System.out.println("Qual o ID do clube do trinador?");
        int idClub = sc.nextInt();

        Club club = ClubService.searchClubPerId(idClub);
        Trainer trainer = new Trainer(experience, nameTrainer, club);
        return trainer;
        
    }
}
