package dao;

import exceptions.NoSatelliteException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

public class DefaultDao {

    public void insertSampleData(){

        deleteAll();

        DataSource dS = new DataSource();
        Connection c = dS.getConnection();
        StrumentoDao strumDao = new StrumentoDao();
        SatelliteDao satDao = new SatelliteDao();
        UtenteDao userDao = new UtenteDao();

        Date date = Date.valueOf("2016-07-09");
        satDao.insertSatellite("Spitzer", date);
        satDao.insertSatellite("Herschel", date);

        userDao.insertNewUser("1234567", "aaaa", "bbbb", "uniroma2@gmail.com", "uniroma2", "utente", c);
        userDao.insertNewUser("12345678", "aaaa", "bbbb", "uniroma2@gmail.com", "uniroma3", "admin", c);

        try {
            strumDao.insertStrumento("OTHER", "Herschel", "3/7/6");
            strumDao.insertStrumento("IRAC", "Spitzer", "3/7/6");
            strumDao.insertStrumento("MIPS", "Spitzer", "3/7/6");
            strumDao.insertStrumento("SPIRE", "Herschel", "3/7/6");
            strumDao.insertStrumento("PACS", "Herschel", "3/7/6");
        }catch(NoSatelliteException e){
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        try {
            Statement stmt;
            DataSource ds = new DataSource();
            Connection c = ds.getConnection();
            String sql = "DELETE FROM PUNTO_SEGMENTO";
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            sql = "DELETE FROM SEGMENTO";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM PERIMETRO";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM FILAMENTO";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM STELLA";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM PUNTO";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM STRUMENTO";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM SATELLITE";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM UTENTE";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        DefaultDao defaultDao = new DefaultDao();
        defaultDao.insertSampleData();
    }

}
