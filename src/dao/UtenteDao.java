package dao;

import entity.Utente;

import java.sql.*;

public class UtenteDao extends AbstractDao {

    private final String TABLE_NAME = "utente";
    private final String COLUMN_ID = "id";
    private final String COLUMN_PASSWORD = "password";
    private final String COLUMN_COGNOME = "cognome";
    private final String COLUMN_EMAIL = "email";
    private final String COLUMN_NOME = "nome";
    private final String COLUMN_TIPO = "tipo";


    public Boolean isUserPresent(String id, String password, Connection c) {
        String sql = "SELECT from " + TABLE_NAME + " WHERE " +
                COLUMN_ID + " = '" + id + "'" + " AND " +
                COLUMN_PASSWORD + " = '" + password + "'";
        return this.isPresent(sql, c);
    }

    public void insertNewUser(String id, String nome, String cognome,
                              String email, String password, String tipo, Connection c) {

        try {

            PreparedStatement stmt;
            String sql = "insert into " + TABLE_NAME + "(" +
                    COLUMN_ID + ", " +
                    COLUMN_NOME + ", " +
                    COLUMN_COGNOME + ", " +
                    COLUMN_EMAIL + ", " +
                    COLUMN_PASSWORD + ", " +
                    COLUMN_TIPO + ")" +
                    " values(" +
                    "'" + id + "', " +
                    "'" + nome + "', " +
                    "'" + cognome + "', " +
                    "'" + email + "', " +
                    "'" + password + "', " +
                    "'" + tipo + "' )";

            stmt = c.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Utente getUser(String id, String password, Connection c) {

        String sql = "SELECT * from " + TABLE_NAME + " WHERE " +
                COLUMN_ID + " = '" + id + "'" + " AND " +
                COLUMN_PASSWORD + " = '" + password + "'";
        Utente u = new Utente();
        try{
        Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = s.executeQuery(sql);
        if (rs.next()) {
            u = new Utente(rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6));
        }
    } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;

        }

}
