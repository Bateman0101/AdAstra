package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("ALL")
public class SegmentoDao extends AbstractDao {

    private static final String TABLE_NAME = "segmento";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TIPO = "tipo";
    private static final String COLUMN_FILAMENTO_ID = "filamento";
    private static final String COLUMN_SATELLITE = "satellite";

    public void insertSegmento (int id, int id_fil, String tipo, String satellite, Connection con){

        try{
            PreparedStatement stmt;
            String sql = "insert into " + TABLE_NAME + "(" +
                    COLUMN_ID + ", " +
                    COLUMN_TIPO + ", " +
                    COLUMN_SATELLITE + ", " +
                    COLUMN_FILAMENTO_ID + ")" +
                    " values(" +
                    "'" + id + "', " +
                    "'" + tipo + "', " +
                    "'" + satellite + "', " +
                    "'" + id_fil + "')";

            stmt = con.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean isPresentSegmento(int id, int id_fil, String tipo, String satellite, Connection con){
        String sql = "SELECT from " + TABLE_NAME + " WHERE " +
                COLUMN_ID + " = '" + id + "'" + " AND " +
                COLUMN_FILAMENTO_ID + " = '" + id_fil + "'" + " AND " +
                COLUMN_TIPO + " = '" + tipo + "'" + " AND " +
                COLUMN_SATELLITE + " = '" + satellite + "'";
        return this.isPresent(sql, con);
    }

    public static void main(String[] args){
        SegmentoDao sD = new SegmentoDao();
        DataSource dS = new DataSource();
        Connection con = dS.getConnection();
        sD.insertSegmento(1,380, "spettacolo", "Spitzer", con);
    }

}
