package service;

import connection.BankConnection;
import model.Club;
import model.Trainer;

import java.sql.*;

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
}
