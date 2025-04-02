package service;

import connection.BankConnection;
import model.Club;
import model.League;

import java.sql.*;

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
}
