package service;

import connection.BankConnection;
import model.Club;
import model.League;
import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerService {

    public static Player savePlayer (Player player) throws SQLException {
        String sql = "INSERT INTO player (club, yearsOld, name, position) VALUES (?,?,?,?)";
        try (Connection connection = BankConnection.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, player.getClub().getIdClub());
            ps.setInt(2, player.getYearsOld());
            ps.setString(3, player.getName());
            ps.setString(4, player.getPosition());
            ps.executeUpdate();

            try (ResultSet rs =  ps.getGeneratedKeys()){
                if (rs.next()) {
                    player.setIdPlayer(rs.getInt(1));
                }
            }
        }
        return player;
    }

    public static void deletePlayer (int idPlayer) throws SQLException {
        String sql = "DELETE FROM player WHERE idPlayer = ?";

        try (Connection connection = BankConnection.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, idPlayer);
            ps.executeUpdate();
        }
    }

    public static Player updatePlayer(Player player) throws SQLException {
        String sql = "UPDATE player SET club = ?, yearsOld = ?, name = ?, position = ? WHERE idPlayer = ?";

        try (Connection connection = BankConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, player.getClub().getIdClub());
            ps.setInt(2, player.getYearsOld());
            ps.setString(3, player.getName());
            ps.setString(4, player.getPosition());
            ps.setInt(5, player.getIdPlayer());
            ps.executeUpdate();
        }
        return player;
    }

    public static Player searchPlayerPerId (int idPlayer) throws SQLException {
        String sql = "SELECT * FROM player WHERE idPlayer = ?";
        try (Connection connection = BankConnection.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, idPlayer);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Club club = new Club();
                    club.setIdClub(rs.getInt("club"));
                    return new Player(
                            club,
                            rs.getInt("idPlayer"),
                            rs.getInt("yearsOld"),
                            rs.getString("name"),
                            rs.getString("position")
                    );
                }
            }
        }
        return null;
    }

    public static List<Player> getAllPlayers() throws SQLException {
        String sql = "SELECT * FROM player";
        List<Player> players = new ArrayList<>();

        try (Connection connection = BankConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idPlayer = rs.getInt("idPlayer");
                int idClub = rs.getInt("club");

                Club club = ClubService.searchClubPerId(idClub);

                Player player = new Player(
                        club,
                        rs.getInt("yearsOld"),
                        rs.getString("position"),
                        rs.getString("name")
                );

                player.setIdPlayer(idPlayer);

                players.add(player);
            }
        }

        return players;
    }

}
