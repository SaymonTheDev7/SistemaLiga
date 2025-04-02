package service;
import model.Club;
import connection.BankConnection;
import model.League;
import model.Player;

import java.sql.*;

public class ClubService {

    public static Club saveClub(Club club) throws SQLException {
        String sql = "INSERT INTO club (name, dateFoundation, trainer) VALUES (?,?,?)";

        try (Connection connection = BankConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, club.getName());
            ps.setString(2, club.getDateFoundation());
            ps.setInt(3, club.getTrainer().getIdTrainer());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()) {
                    club.setIdClub(rs.getInt(1));
                }

                for (League league : club.getLeagues()) {
                    saveClubLeague(club.getIdClub(), league.getIdLeague());
                }

                for (Player player : club.getPlayers()) {
                    PlayerService.savePlayer(player);
                }
            }
        }
        return club;
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
}
