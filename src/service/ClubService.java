package service;
import model.Club;
import connection.BankConnection;

import java.sql.*;

public class ClubService {

    public static Club saveClub(Club club) throws SQLException {
        String sql = "INSERT INTO clube (name, dateFoundation, trainer) VALUES (?,?,?)";

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
            }
        }
        return club;
    }
}
