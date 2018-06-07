package dao;

import java.sql.*;

    /*String str="2015-03-31";
    Date date=Date.valueOf(str);//converting string into sql date
    System.out.println(date);  */

public class SatelliteDao extends AbstractDao {

    public static final String TABLE_NAME = "satellite";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_DATA_INIZIO = "data_inizio";
    public static final String COLUMN_DATA_FINE = "data_fine";
    public static final String COLUMN_AGENZIA = "agenzia";

    public void insertSatellite(String nome, String dataInizio,
                                String dataFine, String agenzia){

        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        Date dataIn = Date.valueOf(dataInizio);
        Date datafi = Date.valueOf(dataFine);

        try {
            PreparedStatement stmt;
            String sql = "insert into " + TABLE_NAME + "(" +
                    COLUMN_NOME + ", " +
                    COLUMN_DATA_INIZIO + ", " +
                    COLUMN_DATA_FINE + ", " +
                    COLUMN_AGENZIA + ")" +
                    " values(" +
                    "'" + nome + "', " +
                    "'" + dataIn + "', " +
                    "'" + datafi + "', " +
                    "'" + agenzia + "')";

            stmt = c.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*
    public Boolean isPresentSatellite(String nome){
        String sql = "SELECT from " + TABLE_NAME + " WHERE " +
                COLUMN_NOME + " = '" + nome + "'";
        return this.isPresent(sql);
    }
*/
}
