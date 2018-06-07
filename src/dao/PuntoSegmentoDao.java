package dao;

import entity.PuntoSegmento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


    public void insertPuntoSegmento(int idSeg, double latitudine, double longitudine,
                                    int num, String flusso, String tipo, Connection con){

        try {
            PreparedStatement stmt;
            String sql = "insert into " + TABLE_NAME + "(" +
                    COLUMN_NUMERO + ", " +
                    COLUMN_FLUSSO + ", " +
                    COLUMN_SEGMENTO + ", " +
                    COLUMN_LATITUDINE + ", " +
                    COLUMN_TIPO + ", " +
                    COLUMN_LONGITUDINE + ")" +
                    " values(" +
                    "'" + num + "', " +
                    "'" + flusso + "', " +
                    "'" + idSeg + "', " +
                    "'" + latitudine + "', " +
                    "'" + tipo + "', " +
                    "'" + longitudine + "')";

            stmt = con.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<PuntoSegmento> listPuntiSeg(){

        List<PuntoSegmento> list = null;

        return list;

    }

    public Boolean isPresentPuntoSegmento(double lat, double lon, Connection c){
        String sql = "SELECT from " + TABLE_NAME + " WHERE " +
                COLUMN_LATITUDINE + " = '" + lat + "'" + " AND " + COLUMN_LONGITUDINE +
                " = '" + lon + "'";
        return this.isPresent(sql, c);
    }




}
