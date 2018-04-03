package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.PuntoDao;
import entity.Punto;

public class StellaDao extends AbstractDao {

    private static final String TABLE_NAME = "stella";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_TIPO = "tipo";
    private static final String COLUMN_FLUSSO = "flusso";
    private static final String COLUMN_STRUMENTO = "strumento";
    private static final String COLUMN_SATELLITE = "satellite";
    private static final String COLUMN_LATITUDINE = "latitudine";
    private static final String COLUMN_LONGITUDINE = "longitudine";

    public void insertStella(int id, String nome, String tipo, double flusso,
                             String strumento, String satellite, double latitudine, double longitudine){

        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        PuntoDao pD = new PuntoDao();
        try {
            if (!pD.isPresentPunto(latitudine, longitudine)){
                pD.insertPunto(latitudine,longitudine);
            }
            PreparedStatement stmt;
            String sql = "insert into " + TABLE_NAME + "(" +
                    COLUMN_ID + ", " +
                    COLUMN_NOME + ", " +
                    COLUMN_TIPO + ", " +
                    COLUMN_FLUSSO + ", " +
                    COLUMN_STRUMENTO + ", " +
                    COLUMN_SATELLITE + ", " +
                    COLUMN_LATITUDINE + ", " +
                    COLUMN_LONGITUDINE + ")" +
                    " values(" +
                    "'" + id + "', " +
                    "'" + nome  + "', " +
                    "'" + tipo  + "', " +
                    "'" + flusso  + "', " +
                    "'" + strumento  + "', " +
                    "'" + satellite  + "', " +
                    "'" + latitudine  + "', " +
                    "'" + longitudine  + "')";

            stmt = c.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean isPresentStella(int id){
        String sql = "SELECT from " + TABLE_NAME + " WHERE " +
                COLUMN_ID + " = '" + id + "'";
        return this.isPresent(sql);
    }

    public static void main(String[] args) {
        StellaDao sD = new StellaDao();
        //sD.insertStella(10, "splendente", "brilla", 2.588,
           //     "MIPS", "Spitzer", 1, 1);

        boolean b = sD.isPresentStella(25646);
        System.out.println(b);
    }
}
