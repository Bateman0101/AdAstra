package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PerimetroDao extends AbstractDao {

    private static final String TABLE_NAME = "perimetro";
    private static final String COLUMN_LATITUDINE = "latitudine";
    private static final String COLUMN_LONGITUDINE = "longitudine";
    private static final String COLUMN_FILAMENTO_SATELLITE = "filamento_satellite";
    private static final String COLUMN_FILAMENTO_ID = "filamento_id";

    public void insertPerimetro(double latitudine, double longitudine,
                                String filamento_satellite, int filamento_id){

        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        try {
            PreparedStatement stmt;
            String sql = "insert into " + TABLE_NAME + "(" +
                    COLUMN_LATITUDINE + ", " +
                    COLUMN_LONGITUDINE + ", " +
                    COLUMN_FILAMENTO_SATELLITE + ", " +
                    COLUMN_FILAMENTO_ID + ")" +
                    " values(" +
                    "'" + latitudine + "', " +
                    "'" + longitudine + "', " +
                    "'" + filamento_satellite + "', " +
                    "'" + filamento_id + "')";

            stmt = c.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean isPresentPerimetro(double latitudine, double longitudine,
                                      String filamento_satellite, int filamento_id){
        String sql = "SELECT from " + TABLE_NAME + " WHERE " +
                COLUMN_LATITUDINE + " = '" + latitudine + "'" + " AND " +
                COLUMN_LONGITUDINE + " = '" + longitudine + "'" + " AND " +
                COLUMN_FILAMENTO_ID + " = '" + filamento_id + "'" + " AND " +
                COLUMN_FILAMENTO_SATELLITE + " = '" + filamento_satellite + "'";
        return this.isPresent(sql);
    }
}
