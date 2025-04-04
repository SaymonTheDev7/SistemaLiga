package service;

import connection.BankConnection;
import model.Club;
import model.League;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeagueService {

    public static League saveLeague(League league) throws SQLException {
        String sql = "INSERT INTO league (foundationYear, name) VALUES (?, ?)";

        try (Connection connection = BankConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, league.getFoundationYear());
            ps.setString(2, league.getName());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    league.setIdLeague(rs.getInt(1));
                }
            }
        }

        if (league.getClubs() != null) {
            for (Club club : league.getClubs()) {
                ClubService.saveClubLeague(club.getIdClub(), league.getIdLeague());
            }
        }

        return league;
    }

    public static League searchLeaguePerId(int idLeague) throws SQLException {
        String sql = "SELECT * FROM league WHERE idLeague = ?";

        try (Connection connection = BankConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idLeague);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new League(
                            rs.getInt("idLeague"),
                            rs.getString("foundationYear"),
                            rs.getString("name")
                    );
                }
            }
        }
        return null;
    }

    public static void deleteLeague(int idLeague) throws SQLException {
        String sql = "DELETE FROM league WHERE idLeague = ?";
        try (Connection connection = BankConnection.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, idLeague);
            ps.executeUpdate();
        }
    }

    public static League updateLeague (League league) throws SQLException {
        String sql = "UPDATE league SET foundationYear = ?, name = ? WHERE idLeague = ?";

        try (Connection connection = BankConnection.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, league.getFoundationYear());
            ps.setString(2, league.getName());
            ps.setInt(3, league.getIdLeague());
            ps.executeUpdate();

        }
        return league;
    }

    public static List<League> getAllLeagues() throws SQLException {
        String sql = "SELECT * FROM league";
        List<League> leagues = new ArrayList<>();

        try (Connection connection = BankConnection.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                leagues.add(new League(
                        rs.getInt("idLeague"),
                        rs.getString("foundationYear"),
                        rs.getString("name")
                ));
            }
        }
        return leagues;
    }
}
