package dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PuntoDao extends AbstractDao {

    private static final String TABLE_NAME = "punto";
    private static final String COLUMN_LATITUDINE = "latitudine";
    private static final String COLUMN_LONGITUDINE = "longitudine";

    public void insertPunto(double latitudine, double longitudine, Connection c) {

        //DataSource ds = new DataSource();
        //Connection c = ds.getConnection();

        try {
            PreparedStatement stmt;
            String sql = "insert into " + TABLE_NAME + "(" +
                    COLUMN_LATITUDINE + ", " +
                    COLUMN_LONGITUDINE + ")" +
                    " values(" +
                    "'" + latitudine + "', " +
                    "'" + longitudine + "')";

            stmt = c.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate();
            //c.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public Boolean isPresentPunto(double latitudine, double longitudine, Connection c){
        String sql = "SELECT from " + TABLE_NAME + " WHERE " +
                COLUMN_LATITUDINE + " = '" + latitudine + "'" + " AND " +
                COLUMN_LONGITUDINE + " = '" + longitudine + "'";
        return this.isPresent(sql, c);
    }

    public static void main(String[] args) {
        PuntoDao pD = new PuntoDao();
        /*for(int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }*/
        //boolean b = pD.isPresentPunto(0.084881,5.000437);
        //System.out.println(b);
    }
}
