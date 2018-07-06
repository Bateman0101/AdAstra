package dao;

import entity.Stella;

import java.sql.*;
import java.util.Vector;

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
                             String strumento, String satellite, double latitudine, double longitudine, Connection c) {

        PuntoDao pD = new PuntoDao();
        try {
            if (!pD.isPresentPunto(latitudine, longitudine, c)) {
                pD.insertPunto(latitudine, longitudine, c);
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
                    "'" + nome + "', " +
                    "'" + tipo + "', " +
                    "'" + flusso + "', " +
                    "'" + strumento + "', " +
                    "'" + satellite + "', " +
                    "'" + latitudine + "', " +
                    "'" + longitudine + "')";

            stmt = c.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean isPresentStella(int id, Connection c) {
        String sql = "SELECT from " + TABLE_NAME + " WHERE " +
                COLUMN_ID + " = '" + id + "'";
        return this.isPresent(sql, c);
    }

    public int stellaId(double lat, double lon, Connection c) {
        Statement s;
        int id = 0;
        try {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT id FROM " + TABLE_NAME + " WHERE " + COLUMN_LATITUDINE +
                    " = '" + lat + "' AND " + COLUMN_LONGITUDINE + " = '" + lon + "'";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                try {
                    id = rs.getInt(COLUMN_ID);
                } catch (NullPointerException n) {
                    n.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public Vector<Stella> getAllStars(Connection con) {
        Statement s = null;
        Vector<Stella> vec = new Vector<>();

        try {
            s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM " + TABLE_NAME;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                try {
                    Stella stella = new Stella(rs.getInt(COLUMN_ID),
                            rs.getString(COLUMN_NOME),
                            rs.getString(COLUMN_TIPO),
                            rs.getDouble(COLUMN_FLUSSO),
                            rs.getString(COLUMN_STRUMENTO),
                            rs.getString(COLUMN_SATELLITE),
                            rs.getDouble(COLUMN_LATITUDINE),
                            rs.getDouble(COLUMN_LONGITUDINE));
                    vec.add(stella);
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

    public Stella getStella(double lat, double lon, Connection con) {
        Statement s;
        Stella st = null;
        try {
            s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM " + TABLE_NAME +
                    " WHERE " + COLUMN_LATITUDINE + "='" + lat + " AND " + COLUMN_LONGITUDINE +
                    "='" + lon + "'");
            ResultSet rs = s.executeQuery(sql.toString());
            while (rs.next()) {
                try {
                    st = new Stella(rs.getInt(COLUMN_ID),
                            rs.getString(COLUMN_NOME),
                            rs.getString(COLUMN_TIPO),
                            rs.getDouble(COLUMN_FLUSSO),
                            rs.getString(COLUMN_STRUMENTO),
                            rs.getString(COLUMN_SATELLITE),
                            rs.getDouble(COLUMN_LATITUDINE),
                            rs.getDouble(COLUMN_LONGITUDINE));
                } catch (NullPointerException n){
                    n.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;
    }

    public Boolean coordinateIntPresent(Integer lat, Integer lon, Connection c) {
        Statement s;
        Integer[] coordinate = new Integer[2];
        Boolean bool = false;
        String sql = "SELECT " + "FLOOR(" + COLUMN_LATITUDINE + ") AS latInt" + " , " + "FLOOR(" + COLUMN_LATITUDINE +
                ") AS lonInt" + " FROM " + TABLE_NAME + " WHERE latInt = " + lat + " AND lonInt = " + lon;
        return this.isPresent(sql, c);
    }

    public Double[] trovaCoordinate(int id, Connection c) {
        Statement s;
        Double[] coordinate = new Double[2];
        try {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT " + COLUMN_LATITUDINE + " , " + COLUMN_LONGITUDINE + " FROM " + TABLE_NAME
                    + " WHERE " + COLUMN_ID + " = '" + id + "'";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                try {
                    coordinate[0] = rs.getDouble(COLUMN_LATITUDINE);
                    coordinate[1] = rs.getDouble(COLUMN_LONGITUDINE);
                } catch (NullPointerException n) {
                    n.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coordinate;
    }

    public String trovaTipo(double lat, double lon, Connection c) {
        Statement s;
        String type = "";
        try {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT " + COLUMN_TIPO + " FROM " + TABLE_NAME + " WHERE " + COLUMN_LATITUDINE +
                    " = '" + lat + "' AND " + COLUMN_LONGITUDINE + " = '" + lon + "'";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                try {
                    type = rs.getString(COLUMN_TIPO);
                } catch (NullPointerException n) {
                    n.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }

    public boolean isPresentStellaCoor(double lat, double lon, Connection c) {
        String sql = "SELECT from " + TABLE_NAME + " WHERE " +
                COLUMN_LATITUDINE + " = '" + lat + "'" + " AND " + COLUMN_LONGITUDINE
                + " = '" + lon + "'";
        return this.isPresent(sql, c);
    }

    public String searchType(int id, Connection c) {
        Statement s;
        String type = "";
        try {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT " + COLUMN_TIPO + " FROM " + TABLE_NAME + " WHERE " +
                    COLUMN_ID + "= '" + id + "'";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                try {
                    type = rs.getString(COLUMN_TIPO);
                } catch (NullPointerException n) {
                    n.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }

    public static void main(String[] args) {
        StellaDao sD = new StellaDao();
        //sD.insertStella(10, "splendente", "brilla", 2.588,
        //     "MIPS", "Spitzer", 1, 1);

        //boolean b = sD.isPresentStella(25646);
        //System.out.println(b);
        String str = "2015-03-31";
        Date date = Date.valueOf(str);//converting string into sql date
        //System.out.println(date);
        DataSource dS = new DataSource();
        Connection c = dS.getConnection();
        int id = sD.stellaId(-0.159528, 5.476947, c);
        System.out.println(id);
        String type = sD.searchType(id, c);
        String tipo = sD.trovaTipo(-0.159528, 5.476947, c);
        System.out.println(tipo);
    }
}
