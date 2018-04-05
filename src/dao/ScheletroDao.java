package dao;

import java.sql.*;

public class ScheletroDao {
    public void insertPunto(float lon, float lat) throws SQLException {

        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();

        String sql = "INSERT INTO PUNTO(LONGITUDINE, LATITUDINE)" +
                     "VALUES(?,?)";

        stmt = c.prepareStatement(sql);
        stmt.setFloat(1, lon);
        stmt.setFloat(2, lat);

        stmt.executeUpdate();

        stmt.close();
    }
    public void insertSegmento(int idS, int idF, String satellite, String tipo) throws SQLException {
        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();

        String sql = "INSERT INTO SEGMENTO(ID, TIPO, SATELLITE, FILAMENTO)" +
                     "VALUES(?,?,?,?)";

        stmt = c.prepareStatement(sql);
        stmt.setInt(1, idS);
        stmt.setString(2, tipo);
        stmt.setString(3,satellite);
        stmt.setInt(4,idF);
        stmt.executeUpdate();

        stmt.close();
    }
    public void insertPuntoSegmento(float lon, float lat, int idS, int num, String flusso) throws SQLException {
        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();

        String sql = "INSERT INTO PUNTO_SEGMENTO(LATITUDINE, LONGITUDINE, SEGMENTO, NUMERO, FLUSSO)" +
                     "VALUES(?,?,?,?,?)";

        stmt = c.prepareStatement(sql);
        stmt.setFloat(1, lat);
        stmt.setFloat(2, lon);
        stmt.setInt(3,idS);
        stmt.setInt(4,num);
        stmt.setString(5,flusso);
        stmt.executeUpdate();

        stmt.close();
    }
    public boolean verificaPerimetro(float lat, float lon, int idF, String satellite) throws SQLException {
        ResultSet rs;
        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();

        String sql = "SELECT *" +
                     "FROM PERIMETRO" +
                     "WHERE LATITUDINE = ? AND LONGITUDINE = ? AND FILAMENTO = ? AND SATELLITE = ?";

        stmt = c.prepareStatement(sql);
        stmt.setFloat(1, lat);
        stmt.setFloat(2, lon);
        stmt.setInt(3,idF);
        stmt.setString(4,satellite);
        rs = stmt.executeQuery();
        if(rs.next()) {
            stmt.close();
            return true;
        }
        stmt.close();
        return false;
    }
}
