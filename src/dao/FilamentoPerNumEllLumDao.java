package dao;

import entity.Filamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilamentoPerNumEllLumDao {

    /**
     * Il metodo restituisce una lista di filamenti con un numero di segmenti compreso in un dato intevallo
     * @return un oggetto List<Filamento>
     * @throws SQLException se si verifica qualcosa di inaspettato nel database
     * @see #buildList(ResultSet)
     */
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
                                     "HAVING COUNT(*)<=? AND COUNT(*)>=? )";
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

    /**
     * Il metodo restituisce una lista di filamenti con un numero di segmenti compreso nell'intevallo che va da 0 a un numero dato.
     * @return un oggetto List<Filamento>
     * @throws SQLException se si verifica qualcosa di inaspettato nel database
     * @see #buildList(ResultSet)
     */
    public List<Filamento> searchAlt(int sup) throws SQLException{
        ResultSet rs;
        List<Filamento> list;
        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        String sql = "SELECT * " +
                     "FROM FILAMENTO " +
                     "WHERE NOME NOT IN ( " +
                                     "SELECT F.NOME " +
                                     "FROM FILAMENTO AS F JOIN SEGMENTO AS S ON S.FILAMENTO=F.ID AND S.SATELLITE=F.SATELLITE " +
                                     "GROUP BY F.NOME " +
                                     "HAVING COUNT(*) > ? )";
        stmt = c.prepareStatement(sql);
        stmt.setInt(1, sup);
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
            ellitticita = rs.getDouble("ellitticita");
            contrasto = rs.getDouble("contrasto");
            temperatura = rs.getDouble("temperatura");
            fil = new Filamento(id, nome, strumento, satellite, flusso, densita, ellitticita, contrasto, temperatura);
            list.add(fil);
        }
        return list;
    }

    /**
     * Il metodo restituisce una lista di filamenti aventi un contrasto maggiore di un dato valore
     * @return un oggetto List<Filamento>
     * @throws SQLException se si verifica qualcosa di inaspettato nel database
     * @see #buildList(ResultSet)
     */
    public List<Filamento> searchLum(double con) throws SQLException{
        ResultSet rs;
        List<Filamento> list;
        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        String sql = "SELECT * " +
                     "FROM FILAMENTO " +
                     "WHERE CONTRASTO > ? ";
        stmt = c.prepareStatement(sql);
        stmt.setDouble(1, con);
        rs = stmt.executeQuery();
        list = buildList(rs);
        stmt.close();
        rs.close();
        c.close();
        return list;
    }

    /**
     * Il metodo restituisce una lista di filamenti con l'ellitticità compresa in un dato intervallo
     * @return un oggetto List<Filamento>
     * @throws SQLException se si verifica qualcosa di inaspettato nel database
     * @see #buildList(ResultSet)
     */
    public List<Filamento> searchEll(Double inf, Double sup) throws SQLException{
        ResultSet rs;
        List<Filamento> list;
        PreparedStatement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        String sql = "SELECT * " +
                     "FROM FILAMENTO " +
                     "WHERE ellitticita >= ? AND ellitticita <= ? ";
        stmt = c.prepareStatement(sql);
        stmt.setDouble(1, inf);
        stmt.setDouble(2, sup);
        rs = stmt.executeQuery();
        list = buildList(rs);
        stmt.close();
        rs.close();
        c.close();
        return list;
    }

    /**
     * Il metodo restituisce il numero di filamenti presenti nel database
     * @return un int
     * @throws SQLException se si verifica qualcosa di inaspettato nel database
     */
    public int getTot() throws SQLException{
        ResultSet rs;
        Statement stmt;
        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        int tot = 0;
        String sql = "SELECT COUNT(*) AS T FROM FILAMENTO ";
        stmt = c.createStatement();
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            tot = rs.getInt("t");
        }
        stmt.close();
        rs.close();
        c.close();
        return tot;
    }
}
