package dao;

import entity.Filamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilamentoPerNumEllLumDao {
    public List<Filamento> search(int inf, int sup) throws  SQLException{
        ResultSet rs;
        List<Filamento> list;
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
        stmt = c.prepareStatement(sql);
        stmt.setInt(1, sup);
        stmt.setInt(2, inf);
        rs = stmt.executeQuery();
        list = buildList(rs);
        stmt.close();
        rs.close();
        c.close();
        return list;
    }

    private List<Filamento> buildList(ResultSet rs) throws SQLException{
        int id;
        String nome;
        String strumento;
        String satellite;
        String flusso;
        String densita;
        double ellitticita;
        double contrasto;
        double temperatura;
        List<Filamento> list = new ArrayList<>();
        Filamento fil;
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
        return list;
    }

    public List<Filamento> searchLum(float con) throws SQLException{
        ResultSet rs;
        List<Filamento> list;
        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        String sql = "SELECT * " +
                     "FROM FILAMENTO " +
                     "WHERE CONTRASTO > ? ";
        stmt = c.prepareStatement(sql);
        stmt.setFloat(1, con);
        rs = stmt.executeQuery();
        list = buildList(rs);
        stmt.close();
        rs.close();
        c.close();
        return list;
    }

    public List<Filamento> searchEll(Float inf, Float sup) throws SQLException{
        ResultSet rs;
        List<Filamento> list;
        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        String sql = "SELECT * " +
                     "FROM FILAMENTO " +
                     "WHERE ELLITTICITà >= ? AND ELLITTICITà <= ? ";
        stmt = c.prepareStatement(sql);
        stmt.setFloat(1, inf);
        stmt.setFloat(2, sup);
        rs = stmt.executeQuery();
        list = buildList(rs);
        stmt.close();
        rs.close();
        c.close();
        return list;
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
