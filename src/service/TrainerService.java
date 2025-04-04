package service;
import connection.BankConnection;
import model.Club;
import model.Trainer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerService {

    public static Trainer saveTrainer (Trainer trainer) throws SQLException {
        String sql = "INSERT INTO trainer (experience, name, club) VALUES (?, ?, ?)";

        try (Connection connection = BankConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, trainer.getExperience());
            ps.setString(2, trainer.getName());
            ps.setInt(3, trainer.getClub().getIdClub());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()) {
                    trainer.setIdTrainer(rs.getInt(1));
                }
            }
        }
        return trainer;
    }

    public static void deleteTrainer (int idTrainer) throws SQLException {
        String sql = "DELETE FROM trainer WHERE idTrainer = ?";

        try (Connection connection = BankConnection.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idTrainer);
            ps.executeUpdate();

        }
    }

    public static Trainer searchTrainerPerId(int idTrainer) throws SQLException {
        String sql = "SELECT * FROM trainer WHERE idTrainer = ?";

        try (Connection connection = BankConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idTrainer);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Club club = ClubService.searchClubPerId(rs.getInt("club"));

                    return new Trainer(
                            rs.getInt("idTrainer"),
                            rs.getInt("experience"),
                            rs.getString("name"),
                            club
                    );
                }
            }
        }
        return null;
    }

    public static List<Trainer> getAllTrainers() throws SQLException {
        String sql = "SELECT * FROM trainer";
        List<Trainer> trainers = new ArrayList<>();

        try (Connection connection = BankConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idTrainer = rs.getInt("idTrainer");
                int experience = rs.getInt("experience");
                String name = rs.getString("name");
                int idClub = rs.getInt("club");

                Club club = null;
                if (idClub != 0) {
                    club = ClubService.searchClubPerId(idClub);
                }

                Trainer trainer = new Trainer(idTrainer, experience, name, club);
                trainers.add(trainer);
            }
        }
        return trainers;
    }

}
