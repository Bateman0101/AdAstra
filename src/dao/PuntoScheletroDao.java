package dao;

import entity.PuntoScheletro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class PuntoScheletroDao {

    private static final String TABLE_NAME = "punto_segmento";
    private static final String COLUMN_LATITUDINE = "latitudine";
    private static final String COLUMN_LONGITUDINE = "longitudine";
    private static final String COLUMN_SEGMENTO = "segmento";
    private static final String COLUMN_NUMERO = "numero";
    private static final String COLUMN_FLUSSO = "flusso";
    private static final String COLUMN_TIPO = "tipo";
    private static final String COLUMN_SATELLITE = "satellite";
    private static final String COLUMN_FILAMENTO_ID = "filamento";

    public Vector<PuntoScheletro> vectorPuntiSegFil(int IDFil, String sat, Connection con) {
        Statement s = null;
        Vector<PuntoScheletro> vector = new Vector<>();
        try {
            s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT p.latitudine, p.longitudine, p.segmento, p.numero, p.flusso, p.tipo, p.satellite, s.filamento " +
                    "FROM punto_segmento p JOIN segmento s on s.id = p.segmento AND p.satellite = s.satellite " +
                    "WHERE s.filamento = '" + IDFil + "' AND s.satellite = '" + sat + "'");
            ResultSet rs = s.executeQuery(sql.toString());
            while (rs.next()) {
                try {
                    PuntoScheletro pS = new PuntoScheletro(rs.getDouble(COLUMN_LATITUDINE),
                            rs.getDouble(COLUMN_LONGITUDINE),
                            rs.getInt(COLUMN_SEGMENTO),
                            rs.getInt(COLUMN_NUMERO),
                            rs.getString(COLUMN_FLUSSO),
                            rs.getString(COLUMN_TIPO),
                            rs.getString(COLUMN_SATELLITE),
                            rs.getInt(COLUMN_FILAMENTO_ID));
                    /*System.out.println(pS.getLatitudine());
                    System.out.println(pS.getLongitudine());
                    System.out.println(pS.getSegmento());
                    System.out.println(pS.getNumero());
                    System.out.println(pS.getFlusso());
                    System.out.println(pS.getTipo());
                    System.out.println(pS.getSatellite());
                    System.out.println(pS.getIdFil());*/
                    vector.add(pS);

                } catch (NullPointerException n) {
                    n.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vector;

    }

    public static void main(String[] args){
        PuntoScheletroDao pSD = new PuntoScheletroDao();
        DataSource dS = new DataSource();
        Connection con = dS.getConnection();
        Vector<PuntoScheletro> vector;
        vector = pSD.vectorPuntiSegFil(380, "Spitzer", con);
        int size = vector.size();
        int i = 0;
        while (i < size){
            System.out.println(vector.get(i));
            i++;
        }
    }
}
