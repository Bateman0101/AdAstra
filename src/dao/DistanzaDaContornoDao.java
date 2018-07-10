package dao;

import entity.Segmento;
import exceptions.NoEstremoException;
import exceptions.NoPerimetroException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DistanzaDaContornoDao {

    /**
     * Il metodo restituisce la lista di segmenti appartenenti a un dato filamento
     * @return un oggetto List<Segmento>
     * @throws SQLException se si verifica qualcosa di inaspettato nel database
     */
    public List<Segmento> search(int idFil, String satellite) throws SQLException {
        int id;
        String tipo;
        ResultSet rs;
        List<Segmento> list = new ArrayList<>();
        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        String sql = "SELECT ID, TIPO " +
                     "FROM SEGMENTO " +
                     "WHERE FILAMENTO = ? AND SATELLITE = ? ";
        stmt = c.prepareStatement(sql);
        stmt.setInt(1, idFil);
        stmt.setString(2, satellite);
        rs = stmt.executeQuery();
        Segmento Segmento;
        while (rs.next()) {
            id = rs.getInt("id");
            tipo = rs.getString("tipo");
            Segmento = new Segmento(id, tipo);
            list.add(Segmento);
        }
        stmt.close();
        rs.close();
        c.close();
        return list;
    }

    private List<Double> getPerimetro(int idF, String sat) throws SQLException {
        ResultSet rs;
        List<Double> list = new ArrayList<>();
        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        String sql = "SELECT LONGITUDINE, LATITUDINE " +
                     "FROM PERIMETRO " +
                     "WHERE FILAMENTO = ? AND SATELLITE = ? ";
        stmt = c.prepareStatement(sql);
        stmt.setInt(1, idF);
        stmt.setString(2, sat);
        rs = stmt.executeQuery();
        while (rs.next()) {
            list.add(rs.getDouble("longitudine"));
            list.add(rs.getDouble("latitudine"));
        }
        stmt.close();
        rs.close();
        c.close();
        return list;
    }

    private double[] getEstremoInf(int idS, String sat) throws SQLException, NoEstremoException{
        ResultSet rs;
        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        double infLat = 0.0;
        double infLon = 0.0;
        double[] inf = new double[2];
        String sql = "SELECT LONGITUDINE, LATITUDINE " +
                     "FROM PUNTO_SEGMENTO " +
                     "WHERE SEGMENTO = ? AND SATELLITE = ? AND NUMERO = 1 ";
        stmt = c.prepareStatement(sql);
        stmt.setInt(1, idS);
        stmt.setString(2,sat);
        rs = stmt.executeQuery();
        if (rs.next()) {
            infLon = rs.getDouble("longitudine");
            infLat = rs.getDouble("latitudine");
            inf[0] = infLon;
            inf[1] = infLat;
            return inf;
        } else {
            stmt.close();
            rs.close();
            c.close();
            throw new NoEstremoException();
        }
    }

    private double[] getEstremoSup(int idS, String sat) throws SQLException, NoEstremoException{
        ResultSet rs;
        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        double supLat = 0.0;
        double supLon = 0.0;
        double[] sup = new double[2];
        String sql = "SELECT LONGITUDINE, LATITUDINE " +
                     "FROM PUNTO_SEGMENTO " +
                     "WHERE SEGMENTO = ? AND SATELLITE = ? AND NUMERO >= all(  SELECT NUMERO " +
                                                                              "FROM PUNTO_SEGMENTO " +
                                                                              "WHERE SEGMENTO = ? AND SATELLITE = ?)";
        stmt = c.prepareStatement(sql);
        stmt.setInt(1, idS);
        stmt.setString(2, sat);
        stmt.setInt(3, idS);
        stmt.setString(4, sat);
        rs = stmt.executeQuery();
        if (rs.next()) {
            supLon = rs.getDouble("longitudine");
            supLat = rs.getDouble("latitudine");
            sup[0] = supLon;
            sup[1] = supLat;
            stmt.close();
            rs.close();
            c.close();
            return sup;
        } else {
            stmt.close();
            rs.close();
            c.close();
            throw new NoEstremoException();
        }
    }

    /**
     * Il metodo restituisce le distanze degli estremi di un dato segmento dal contorno del filamento a cui appartiene
     * @return double[]
     * @throws SQLException se si verifica qualcosa di inaspettato nel database
     * @throws NoPerimetroException se il filamento è privo di punti di contorno
     * @throws NoEstremoException se il segmento è privo di punti
     * @see #getEstremoInf(int, String)
     * @see #getEstremoSup(int, String)
     * @see #getPerimetro(int, String)
     */
    public double[] getDistance(int idS, int idF, String sat) throws SQLException, NoPerimetroException, NoEstremoException {
        List<Double> perim = getPerimetro(idF, sat);
        double[] inf = getEstremoInf(idS,sat);
        double[] sup = getEstremoSup(idS,sat);
        if (perim.size() != 0) {
            double infLon = inf[0];
            double infLat = inf[1];
            double supLon = sup[0];
            double supLat = sup[1];
            double pLon;
            double pLat;
            int n;
            double dist[] = new double[2];
            double distInf = 0.0;
            double distSup = 0.0;
            double compareInf = 0.0;
            double compareSup = 0.0;
            for (n = 0; n < perim.size(); n++) {
                pLon = perim.get(n);
                n++;
                pLat = perim.get(n);
                compareInf = Math.sqrt((infLon - pLon) * (infLon - pLon) + (infLat - pLat) * (infLat - pLat));
                compareSup = Math.sqrt((supLon - pLon) * (supLon - pLon) + (supLat - pLat) * (supLat - pLat));
                if (distInf == 0.0) {
                    distInf = compareInf;
                } else if (compareInf < distInf) {
                    distInf = compareInf;
                }
                if (distSup == 0.0) {
                    distSup = compareSup;
                } else if (compareSup < distSup) {
                    distSup = compareSup;
                }
            }
            dist[0] = distInf;
            dist[1] = distSup;
            return dist;
        } else {
            throw new NoPerimetroException();
        }
    }
}
