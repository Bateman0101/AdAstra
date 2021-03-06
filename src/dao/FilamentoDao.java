package dao;

import entity.Filamento;
import entity.Punto;
import entity.Strumento;
import exceptions.NoFilamentoException;

import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;

public class FilamentoDao extends AbstractDao {

    private static final String TABLE_NAME = "filamento";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_FLUSSO = "flusso";
    private static final String COLUMN_DENSITA = "densita";
    private static final String COLUMN_ELLITTICITA = "ellitticita";
    private static final String COLUMN_CONTRASTO = "contrasto";
    private static final String COLUMN_TEMPERATURA = "temperatura";
    private static final String COLUMN_STRUMENTO = "strumento";
    private static final String COLUMN_SATELLITE = "satellite";



    public void insertFil(int id, String nome, String flusso, String densita, double ellitticita,
                          double contrasto, double temperatura, String strumento, String satellite, Connection c){

        try {
            PreparedStatement stmt;
            String sql = "insert into " + TABLE_NAME + "(" +
                    COLUMN_ID + ", " +
                    COLUMN_NOME + ", " +
                    COLUMN_FLUSSO + ", " +
                    COLUMN_DENSITA + ", " +
                    COLUMN_ELLITTICITA + ", " +
                    COLUMN_CONTRASTO + ", " +
                    COLUMN_TEMPERATURA + ", " +
                    COLUMN_STRUMENTO + ", " +
                    COLUMN_SATELLITE + ")" +
                    " values(" +
                    "'" + id + "', " +
                    "'" + nome + "', " +
                    "'" + flusso + "', " +
                    "'" + densita + "', " +
                    "'" + ellitticita + "', " +
                    "'" + contrasto + "', " +
                    "'" + temperatura + "', " +
                    "'" + strumento + "', " +
                    "'" + satellite + "')";

            //stmt.executeUpdate(sql);
            //stmt.close();
            stmt = c.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate();



        }catch (PSQLException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public Filamento getFilamento(int id, String satellite, Connection con){
        Statement stmt;
        Filamento f = null;
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "='" + id
                    + "' AND " + COLUMN_SATELLITE + " = '" + satellite + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) try {
                f = new Filamento(rs.getInt(COLUMN_ID),
                        rs.getString(COLUMN_NOME),
                        rs.getString(COLUMN_STRUMENTO),
                        rs.getString(COLUMN_SATELLITE),
                        rs.getString(COLUMN_FLUSSO),
                        rs.getString(COLUMN_DENSITA),
                        rs.getDouble(COLUMN_ELLITTICITA),
                        rs.getDouble(COLUMN_CONTRASTO),
                        rs.getDouble(COLUMN_TEMPERATURA));
            }catch (NullPointerException n){
                n.printStackTrace();
                System.exit(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public void updateFilamento(int id, String nome, String flusso, String densita, double ellitticita,
                                double contrasto, double temperatura, String strumento, String satellite, Connection c){
        try{
            PreparedStatement stmt;
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE " + TABLE_NAME)
                    .append(" SET ")
                    .append(COLUMN_NOME).append(" = '").append(nome).append(", ")
                    .append(COLUMN_FLUSSO).append(" = '").append(flusso).append(", ")
                    .append(COLUMN_DENSITA).append(" = '").append(densita).append(", ")
                    .append(COLUMN_ELLITTICITA).append(" = '").append(ellitticita).append(", ")
                    .append(COLUMN_CONTRASTO).append(" = '").append(contrasto).append(", ")
                    .append(COLUMN_TEMPERATURA).append(" = '").append(temperatura)
                    .append(COLUMN_STRUMENTO).append(" = '").append(strumento)
                    .append(" WHERE ").append(COLUMN_ID).append(" = '").append(id)
                    .append(" AND ").append(COLUMN_SATELLITE).append(" = '").append(satellite)
                    .append("'");
            stmt = c.prepareStatement(String.valueOf(sql), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public ArrayList<Filamento> findAll() {

        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        ArrayList<Filamento> list = new ArrayList<>();

        try {

            Statement stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT * FROM " + TABLE_NAME;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String strumento = rs.getString("strumento");
                String satellite = rs.getString("satellite");
                String flusso = rs.getString("flusso");
                String densita = rs.getString("densita");
                Double ellitticita = rs.getDouble("ellitticita");
                Double contrasto = rs.getDouble("contrasto");
                Double temperatura= rs.getDouble("temperatura");


                list.add(new Filamento(id, nome, strumento,
                        satellite, flusso, densita, ellitticita,
                        contrasto, temperatura));

            }
            c.close();
        }
        catch (SQLException e) {
        e.printStackTrace();
        }

        return list;
    }

    public Boolean isPresentFilamento(int id, String satellite, Connection c){
        String sql = "SELECT from " + TABLE_NAME + " WHERE " +
                COLUMN_ID + " = '" + id + "'" + " AND " + COLUMN_SATELLITE +
                " = '" + satellite + "'";
        return this.isPresent(sql, c);
    }

    public ArrayList<Punto> findPerimeter(int id, String satellite) throws NoFilamentoException{

        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        ArrayList<Punto> p = new ArrayList<>();

        if (isPresentFilamento(id, satellite, c)) {

            try {

                PreparedStatement stmt = null;
                ResultSet rs;
                String sql = "SELECT *" +
                        "FROM PERIMETRO " +
                        "WHERE filamento_id = ? AND filamento_satellite = ? ";
                stmt = c.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.setString(2, satellite);

                rs = stmt.executeQuery();

                while (rs.next()) {
                    double lat = rs.getDouble("latitudine");
                    double lon = rs.getDouble("longitudine");
                    p.add(new Punto(lat, lon));
                }

                rs.close();
                stmt.close();
                c.close();


            } catch (SQLException e) {
                e.printStackTrace();
            }



        }

        else {
            throw new NoFilamentoException("The occurrence is not present in the database");
        }

        return p;


    }

    public ArrayList<Integer> findSegments(int id, String satellite) {

        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        ArrayList<Integer> list= new ArrayList<>();

        if (isPresentFilamento(id, satellite, c)) {

            try {

                PreparedStatement stmt = null;
                ResultSet rs;
                String sql = "SELECT *" +
                        "FROM SEGMENTO " +
                        "WHERE FILAMENTO = ? AND SATELLITE = ? ";
                stmt = c.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.setString(2, satellite);

                rs = stmt.executeQuery();

                while (rs.next()) {
                    int num = rs.getInt("id");
                    list.add(num);
                }

                rs.close();
                stmt.close();
                c.close();


            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return list;


    }
    public void insert(ArrayList<Filamento> f) {

        DataSource ds = new DataSource();
        Connection c = ds.getConnection();
        try{
            PreparedStatement stmt = null;
          for (int i = 0; i < f.size(); i++) {


              String sql = "insert into " + TABLE_NAME + " values(?,?,?,?,?,?,?,?,?)";
              stmt = c.prepareStatement(sql);
              stmt.setInt(1, f.get(i).getId());
              stmt.setString(2, f.get(i).getNome());
              stmt.setString(3, f.get(i).getStrumento());
              stmt.setString(4, f.get(i).getSatellite());
              stmt.setString(5, f.get(i).getFlusso());
              stmt.setString(6, f.get(i).getDensita());
              stmt.setDouble(7, f.get(i).getEllitticita());
              stmt.setDouble(8, f.get(i).getContrasto());
              stmt.setDouble(9, f.get(i).getTemperatura());

              stmt.executeUpdate();

          }
          stmt.close();
          c.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }



}

