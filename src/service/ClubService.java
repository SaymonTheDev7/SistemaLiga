package service;
import model.Club;
import connection.BankConnection;
import model.League;
import model.Player;
import model.Trainer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClubService {

    public static Club saveClub(Club club) throws SQLException {
        String sql = "INSERT INTO club (name, dateFoundation, trainer) VALUES (?, ?, NULL)";

        try (Connection connection = BankConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, club.getName());
            ps.setString(2, club.getDateFoundation());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    club.setIdClub(rs.getInt(1));
                }
            }
        }


        if (club.getLeagues() != null) {
            for (League league : club.getLeagues()) {
                saveClubLeague(club.getIdClub(), league.getIdLeague());
            }
        }

        if (club.getPlayers() != null) {
            for (Player player : club.getPlayers()) {
                saveClubPlayer(club.getIdClub(), player.getIdPlayer());
            }
        }

        return club;
    }

    public static Club searchClubPerId(int idClub) throws SQLException {
        String sql = "SELECT * FROM club WHERE idClub = ?"; // Correção do nome da coluna

        try (Connection connection = BankConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idClub);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Trainer trainer = new Trainer();
                    trainer.setIdTrainer(rs.getInt("trainer"));

                    return new Club(
                            rs.getInt("idClub"),
                            rs.getString("name"),
                            rs.getString("dateFoundation"),
                            trainer
                    );
                }
            }
        }
        return null;
    }

    public static void saveClubLeague (int idClub, int idLeague) throws SQLException {
        String sql = "INSERT INTO club_league (idClub, idLeague) VALUES (?, ?)";

        try (Connection connection = BankConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idClub);
            ps.setInt(2, idLeague);
            ps.executeUpdate();
        }
    }

    public static void saveClubPlayer (int idClub, int idPlayer) throws SQLException {
        String sql = "INSERT INTO club_player (idClub, idPlayer) VALUES (?,?)";

        try (Connection connection = BankConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idClub);
            ps.setInt(2, idPlayer);
            ps.executeUpdate();
        }
    }

    public static void updateClubWithTrainer(int idClub, int idTrainer) throws SQLException {
        String sql = "UPDATE club SET trainer = ? WHERE idClub = ?";

        try (Connection connection = BankConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idTrainer);
            ps.setInt(2, idClub);
            ps.executeUpdate();
        }
    }

    public static void deleteClub(int idClub) throws SQLException {
        String sql = "DELETE FROM club WHERE idClub = ?";

        try (Connection connection = BankConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idClub);
            ps.executeUpdate();
        }
    }

    public static Club updateClub(Club club) throws SQLException {
        String sql = "UPDATE club SET name = ?, dateFoundation = ? WHERE idClub = ?";

        try (Connection connection = BankConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, club.getName());
            ps.setString(2, club.getDateFoundation());
            ps.setInt(3, club.getIdClub());
            ps.executeUpdate();
        }
        return club;
    }

    public static List <Club> getAllClubs() throws SQLException {
        String sql = "SELECT * FROM club";
        List<Club> clubs = new ArrayList<>();

        try (Connection connection = BankConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                clubs.add(new Club(
                        rs.getInt("idClub"),
                        rs.getString("name"),
                        rs.getString("dateFoundation"),
                        null
                ));
            }
        }
        return clubs;
    }
}
