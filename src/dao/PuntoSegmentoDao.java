package dao;

import entity.PuntoSegmento;

import java.sql.*;
import java.util.List;

@SuppressWarnings("ALL")
public class PuntoSegmentoDao extends AbstractDao {

    private static final String TABLE_NAME = "punto_segmento";
    private static final String COLUMN_LATITUDINE = "latitudine";
    private static final String COLUMN_LONGITUDINE = "longitudine";
    private static final String COLUMN_SEGMENTO = "segmento";
    private static final String COLUMN_NUMERO = "numero";
    private static final String COLUMN_FLUSSO = "flusso";
    private static final String COLUMN_TIPO = "tipo";
    private static final String COLUMN_SATELLITE = "satellite";


    public void insertPuntoSegmento(int idSeg, double latitudine, double longitudine,
                                    int num, String flusso, String satellite, String tipo, Connection con){

        try {
            PreparedStatement stmt;
            String sql = "insert into " + TABLE_NAME + "(" +
                    COLUMN_NUMERO + ", " +
                    COLUMN_FLUSSO + ", " +
                    COLUMN_SEGMENTO + ", " +
                    COLUMN_LATITUDINE + ", " +
                    COLUMN_TIPO + ", " +
                    COLUMN_SATELLITE + ", " +
                    COLUMN_LONGITUDINE + ")" +
                    " values(" +
                    "'" + num + "', " +
                    "'" + flusso + "', " +
                    "'" + idSeg + "', " +
                    "'" + latitudine + "', " +
                    "'" + tipo + "', " +
                    "'" + satellite + "', " +
                    "'" + longitudine + "')";

            stmt = con.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<PuntoSegmento> listPuntiSeg(Connection con){
        Statement s = null;
        List<PuntoSegmento> list = null;
        try {
            s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM " + TABLE_NAME);
            ResultSet rs = s.executeQuery(sql.toString());
            while (rs.next()) {
                try {
                    PuntoSegmento pS = new PuntoSegmento(rs.getDouble(COLUMN_LATITUDINE),
                            rs.getDouble(COLUMN_LONGITUDINE),
                            rs.getInt(COLUMN_SEGMENTO),
                            rs.getInt(COLUMN_NUMERO),
                            rs.getString(COLUMN_FLUSSO),
                            rs.getString(COLUMN_TIPO),
                            rs.getString(COLUMN_SATELLITE));
                    list.add(pS);

                }catch (NullPointerException n){
                    n.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    public Boolean isPresentPuntoSegmento(double lat, double lon, Connection c){
        String sql = "SELECT from " + TABLE_NAME + " WHERE " +
                COLUMN_LATITUDINE + " = '" + lat + "'" + " AND " + COLUMN_LONGITUDINE +
                " = '" + lon + "'";
        return this.isPresent(sql, c);
    }

}
