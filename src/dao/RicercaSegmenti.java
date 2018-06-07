package dao;

import entity.SegmentoAdam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RicercaSegmenti {
    public List<SegmentoAdam> search(int idFil, String satellite) {
        int id;
        Character tipo;
        ResultSet rs;
        List<SegmentoAdam> list = new ArrayList<SegmentoAdam>();
        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();

        String sql = "SELECT ID, TIPO " +
                     "FROM SEGMENTO " +
                     "WHERE FILAMENTO = ? AND SATELLITE = ? ";

        try {
            stmt = c.prepareStatement(sql);

            stmt.setInt(1, idFil);
            stmt.setString(2, satellite);
            rs = stmt.executeQuery();
            SegmentoAdam segmentoAdam = null;
            while (rs.next()) {
                id = rs.getInt("id");
                tipo = rs.getString("tipo").charAt(0);
                segmentoAdam = new SegmentoAdam(id, tipo);
                list.add(segmentoAdam);
            }
            stmt.close();
            rs.close();
            c.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Double> getPerimetro(int idF, String sat) {
        ResultSet rs;
        List<Double> list = new ArrayList<Double>();
        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();

        String sql = "SELECT LONGITUDINE, LATITUDINE " +
                     "FROM PERIMETRO " +
                     "WHERE FILAMENTO = ? AND SATELLITE = ? ";
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    public double[] getEstremoInf(int idS){
        ResultSet rs;
        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        double infLat = 0.0;
        double infLon = 0.0;
        double[] inf = new double[2];

        String sql = "SELECT LONGITUDINE, LATITUDINE " +
                     "FROM PUNTO_SEGMENTO " +
                     "WHERE SEGMENTO = ? AND NUMERO = 1 ";
        try {
            stmt = c.prepareStatement(sql);

            stmt.setInt(1, idS);
            rs = stmt.executeQuery();
            while (rs.next()) {
                infLon = rs.getDouble("longitudine");
                infLat = rs.getDouble("latitudine");
            }
            inf[0] = infLon;
            inf[1] = infLat;
            stmt.close();
            rs.close();
            c.close();
            return inf;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inf;
    }

    public double[] getEstremoSup(int idS){
        ResultSet rs;
        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        double supLat = 0.0;
        double supLon = 0.0;
        double[] sup = new double[2];

        String sql = "SELECT LONGITUDINE, LATITUDINE " +
                     "FROM PUNTO_SEGMENTO " +
                     "WHERE SEGMENTO = ? AND NUMERO >= all(  SELECT NUMERO " +
                                                            "FROM PUNTO_SEGMENTO " +
                                                            "WHERE SEGMENTO = ? )";
        try {
            stmt = c.prepareStatement(sql);

            stmt.setInt(1, idS);
            stmt.setInt(2, idS);
            rs = stmt.executeQuery();
            while (rs.next()) {
                supLon = rs.getDouble("longitudine");
                supLat = rs.getDouble("latitudine");
            }
            sup[0] = supLon;
            sup[1] = supLat;
            stmt.close();
            rs.close();
            c.close();
            return sup;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sup;
    }

    public double[] getDistance(int idS, int idF, String sat) {
        List<Double> perim = getPerimetro(idF, sat);
        double[] inf = getEstremoInf(idS);
        double[] sup = getEstremoSup(idS);
        double infLon = inf[0];
        double infLat = inf[1];
        double supLon = sup[0];
        double supLat = sup[1];
        double pLon = 0.0;
        double pLat = 0.0;
        int n;
        double dist[] = new double[2];
        double distInf = 0.0;
        double distSup = 0.0;
        double compareInf = 0.0;
        double compareSup = 0.0;
        for(n=0; n<perim.size(); n++) {
            pLon = perim.get(n);
            n++;
            pLat = perim.get(n);
            compareInf = Math.sqrt((infLon - pLon)*(infLon - pLon) + (infLat - pLat)*(infLat - pLat));
            compareSup = Math.sqrt((supLon - pLon)*(supLon - pLon) + (supLat - pLat)*(supLat - pLat));
            if(distInf == 0.0) {
                distInf = compareInf;
            }
            else if(compareInf < distInf) {
                distInf = compareInf;
            }
            if(distSup == 0.0) {
                distSup = compareSup;
            }
            else if(compareSup < distSup) {
                distSup = compareSup;
            }
        }
        dist[0] = distInf;
        dist[1] = distSup;
        return dist;
    }
}
