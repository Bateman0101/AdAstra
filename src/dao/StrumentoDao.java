package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StrumentoDao extends AbstractDao {

    private static final String TABLE_NAME = "strumento";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_SATELLITE = "satellite";
    private static final String COLUMN_BANDE = "bande";

    public void insertStrumento(String nome, String satellite, String bande){

        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        SatelliteDao satD = new SatelliteDao();
        try {
            if(!satD.isPresentSatellite(satellite)){
                satD.insertSatellite(satellite);
            }
            if (!isPresentStrumento(nome)) {
                PreparedStatement stmt;
                String sql = "insert into " + TABLE_NAME + "(" +
                        COLUMN_NOME + ", " +
                        COLUMN_SATELLITE + ", " +
                        COLUMN_BANDE + ")" +
                        " values(" +
                        "'" + nome + "', " +
                        "'" + satellite + "', " +
                        "'" + bande + "')";

                stmt = c.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
                stmt.executeUpdate();
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean isPresentStrumento(String nome){
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        String sql = "SELECT from " + TABLE_NAME + " WHERE " +
                COLUMN_NOME + " = '" + nome + "'";
        return this.isPresent(sql, c);
    }
}

