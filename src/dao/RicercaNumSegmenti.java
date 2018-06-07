package dao;

import entity.Filamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RicercaNumSegmenti {
    public List<Filamento> search(int inf, int sup) {
        int id;
        String nome;
        String strumento;
        String satellite;
        String flusso;
        String densita;
        double ellitticita;
        double contrasto;
        double temperatura;
        ResultSet rs;
        List<Filamento> list = new ArrayList<Filamento>();
        Filamento fil;
        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();

        String sql = "SELECT * " +
                     "FROM FILAMENTO " +
                     "WHERE NOME IN ( " +
                     "SELECT F.NOME " +
                     "FROM FILAMENTO AS F JOIN SEGMENTO AS S ON S.FILAMENTO=F.ID AND S.SATELLITE=F.SATELLITE " +
                     "GROUP BY F.NOME, F.ID " +
                     "HAVING COUNT(*)<? AND COUNT(*)>? )";

        try {
            stmt = c.prepareStatement(sql);

            stmt.setInt(1, sup);
            stmt.setInt(2, inf);
            rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                nome = rs.getString("nome");
                strumento = rs.getString("strumento");
                satellite = rs.getString("satellite");
                flusso = rs.getString("flusso");
                densita = rs.getString("densita");
                ellitticita = rs.getInt("ellitticita");
                contrasto = rs.getInt("contrasto");
                temperatura = rs.getInt("temperatura");
                fil = new Filamento(id, nome, strumento, satellite, flusso, densita, ellitticita, contrasto, temperatura);
                list.add(fil);
            }
            stmt.close();
            rs.close();
            c.close();
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Filamento> searchLum(float con) {
        int id;
        String nome;
        String strumento;
        String satellite;
        String flusso;
        String densita;
        double ellitticita;
        double contrasto;
        double temperatura;
        ResultSet rs;
        List<Filamento> list = new ArrayList<Filamento>();
        Filamento fil;
        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();

        String sql = "SELECT * " +
                     "FROM FILAMENTO " +
                     "WHERE CONTRASTO > ? ";

        try {
            stmt = c.prepareStatement(sql);

            stmt.setFloat(1, con);
            rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                nome = rs.getString("nome");
                strumento = rs.getString("strumento");
                satellite = rs.getString("satellite");
                flusso = rs.getString("flusso");
                densita = rs.getString("densita");
                ellitticita = rs.getInt("ellitticita");
                contrasto = rs.getInt("contrasto");
                temperatura = rs.getInt("temperatura");
                fil = new Filamento(id, nome, strumento, satellite, flusso, densita, ellitticita, contrasto, temperatura);
                list.add(fil);
            }
            stmt.close();
            rs.close();
            c.close();
            return list;
        } catch (SQLException e) {
            return null;
        }

    }

    public int getTot() {
        ResultSet rs;
        Statement stmt = null;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        int tot = 0;

        String sql = "SELECT COUNT(*) AS T FROM FILAMENTO ";

        try {
            stmt = c.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tot = rs.getInt("t");
            }
            stmt.close();
            rs.close();
            c.close();
            return tot;
        } catch (SQLException e) {
            return 0;
        }

    }
}
