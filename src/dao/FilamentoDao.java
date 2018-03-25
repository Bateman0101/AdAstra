package dao;

import entity.Filamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilamentoDao{

    private static final String TABLE_NAME = "filamento";

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
              stmt.setString(6, f.get(i).getDensità());
              stmt.setDouble(7, f.get(i).getEllitticità());
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

