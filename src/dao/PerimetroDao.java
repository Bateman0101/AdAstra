package dao;

import entity.PuntoPerimetro;

import java.sql.*;
import java.util.Vector;

@SuppressWarnings("ALL")
public class PerimetroDao extends AbstractDao {

    private static final String TABLE_NAME = "perimetro";
    private static final String COLUMN_LATITUDINE = "latitudine";
    private static final String COLUMN_LONGITUDINE = "longitudine";
    private static final String COLUMN_FILAMENTO_SATELLITE = "filamento_satellite";
    private static final String COLUMN_FILAMENTO_ID = "filamento_id";

    @SuppressWarnings("Duplicates")
    public void insertPuntoPerimetro(double latitudine, double longitudine,
                                     String filamento_satellite, int filamento_id, Connection c) {

        //DataSource ds = new DataSource();
        //Connection c = ds.getConnection();
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
            //c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<PuntoPerimetro> getAllPerimetri(Connection con) {
        Statement s = null;
        Vector<PuntoPerimetro> vec = new Vector<>();

        try {
            s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM " + TABLE_NAME;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                try {
                    PuntoPerimetro puntoPerimetro = new PuntoPerimetro(rs.getDouble(COLUMN_LATITUDINE),
                            rs.getDouble(COLUMN_LONGITUDINE),
                            rs.getString(COLUMN_FILAMENTO_SATELLITE),
                            rs.getInt(COLUMN_FILAMENTO_ID));
                    vec.add(puntoPerimetro);
                } catch (NullPointerException n) {
                    n.printStackTrace();
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(s);
        }
        return vec;
    }

    public Vector<PuntoPerimetro> getAllPerimetriFil(int idFil, Connection con) {
        Statement s = null;
        Vector<PuntoPerimetro> vec = new Vector<>();

        try {
            s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_FILAMENTO_ID + " = '" +  idFil + "'";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                try {
                    PuntoPerimetro puntoPerimetro = new PuntoPerimetro(rs.getDouble(COLUMN_LATITUDINE),
                            rs.getDouble(COLUMN_LONGITUDINE),
                            rs.getString(COLUMN_FILAMENTO_SATELLITE),
                            rs.getInt(COLUMN_FILAMENTO_ID));
                    vec.add(puntoPerimetro);
                } catch (NullPointerException n) {
                    n.printStackTrace();
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(s);
        }
        return vec;
    }

    public Boolean isPresentPerimetroCoor(double latitudine, double longitudine, Connection c) {
        String sql = "SELECT from " + TABLE_NAME + " WHERE " +
                COLUMN_LATITUDINE + " = '" + latitudine + "'" + " AND " +
                COLUMN_LONGITUDINE + " = '" + longitudine + "'";
        return this.isPresent(sql, c);
    }

    public Boolean isPresentPerimetro(double latitudine, double longitudine,
                                      String filamento_satellite, int filamento_id, Connection c) {
        String sql = "SELECT from " + TABLE_NAME + " WHERE " +
                COLUMN_LATITUDINE + " = '" + latitudine + "'" + " AND " +
                COLUMN_LONGITUDINE + " = '" + longitudine + "'" + " AND " +
                COLUMN_FILAMENTO_ID + " = '" + filamento_id + "'" + " AND " +
                COLUMN_FILAMENTO_SATELLITE + " = '" + filamento_satellite + "'";
        return this.isPresent(sql, c);
    }

    public static void main(String[] args) {
        PerimetroDao perD = new PerimetroDao();
        Boolean bool;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        //bool = perD.isPresentPerimetroCoor(-0.06612, 352.259, c);
        //System.out.println(bool);
        Vector<PuntoPerimetro> vec;
        vec = perD.getAllPerimetri(c);
        int l = vec.size();
        int numPer = 0;
        while (numPer < l) {
            PuntoPerimetro pPer = vec.get(numPer);
            System.out.println(pPer.getLongitudine() + " " +
                    pPer.getLatitudine() + " " +
                    pPer.getFilamento() + " " +
                    pPer.getSatellite());

            numPer++;
        }


    }
}
