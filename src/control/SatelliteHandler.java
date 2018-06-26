package control;

import dao.SatelliteDao;
import dao.StrumentoDao;
import entity.Satellite;

import java.time.LocalDate;
import java.util.Date;

public class SatelliteHandler {

    public void insertSatellite(String nome, LocalDate start, LocalDate end, String agenzia) {

        SatelliteDao d = new SatelliteDao();
        String startDate = start.toString();
        String endDate = start.toString();
        d.insertSatellite(nome, startDate, endDate, agenzia);

    }

    public void insertStrumento(String nome, String satellite, String bande) {

        StrumentoDao dao = new StrumentoDao();

        dao.insertStrumento(nome, satellite, bande);



    }
}
