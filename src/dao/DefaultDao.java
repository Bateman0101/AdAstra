package dao;

import exceptions.NoSatelliteException;

import java.sql.Connection;
import java.sql.Date;

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

    }

    public static void main(String args[]){
        DefaultDao defaultDao = new DefaultDao();
        defaultDao.insertSampleData();
    }

}
