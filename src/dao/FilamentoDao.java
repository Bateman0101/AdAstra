package dao;

import entity.Filamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilamentoDao {

    private static final String TABLE_NAME = "filamento";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_FLUSSO = "flusso";
    private static final String COLUMN_DENSITA = "densita";
    private static final String COLUMN_ELLITTICITA = "ellitticita";
    private static final String COLUMN_CONTRASTO = "contrasto";
    private static final String COLUMN_TEMPERATURA = "temperatura";
    private static final String COLUMN_STRUMENTO = "strumento";
    private static final String COLUMN_SATELLITE = "satellite";

    public void insertFil(int id, String nome, String flusso, String densita, double ellitticita,
                          double contrasto, double temperatura, String strumento, String satellite){
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        try {
            PreparedStatement stmt;
            String sql = "insert into " + TABLE_NAME + "(" +
                    COLUMN_ID + ", " +
                    COLUMN_NOME + ", " +
                    COLUMN_FLUSSO + ", " +
                    COLUMN_DENSITA + ", " +
                    COLUMN_ELLITTICITA + ", " +
                    COLUMN_CONTRASTO + ", " +
                    COLUMN_TEMPERATURA + ", " +
                    COLUMN_STRUMENTO + ", " +
                    COLUMN_SATELLITE + ")" +
                    " values(" +
                    "'" + id + "', " +
                    "'" + nome + "', " +
                    "'" + flusso + "', " +
                    "'" + densita + "', " +
                    "'" + ellitticita + "', " +
                    "'" + contrasto + "', " +
                    "'" + temperatura + "', " +
                    "'" + strumento + "', " +
                    "'" + satellite + "')";

            //stmt.executeUpdate(sql);
            //stmt.close();
            stmt = c.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate();
            c.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void insert(ArrayList<Filamento> f) {

        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        try{
            PreparedStatement stmt = null;
          for (int i = 0; i < f.size(); i++) {

/*
              String sql = "insert into " + TABLE_NAME + " values(?,?,?,?,?,?,?,?,?)";
              stmt = c.prepareStatement(sql);
              stmt.setInt(1, f.get(i).getId());
              stmt.setString(2, f.get(i).getNome());
              stmt.setString(3, f.get(i).getStrumento());
              stmt.setString(4, f.get(i).getSatellite());
              stmt.setString(5, f.get(i).getFlusso());
              stmt.setString(6, f.get(i).getDensità());
              stmt.setDouble(7, f.get(i).getEllitticità());
              stmt.setDouble(8, f.get(i).getContrasto());
              stmt.setDouble(9, f.get(i).getTemperatura());

              stmt.executeUpdate();
*/
          }
          stmt.close();
          c.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    public static void main(String[] args) {
        FilamentoDao fD = new FilamentoDao();
        fD.insertFil(1,"Test","00", "5.256", 123.58, 126.23,
                15555500, "Laser", "Spitzer");
    }
}

