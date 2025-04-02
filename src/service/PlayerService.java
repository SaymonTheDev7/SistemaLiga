package service;

import connection.BankConnection;
import model.Player;

import java.sql.*;

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
}
