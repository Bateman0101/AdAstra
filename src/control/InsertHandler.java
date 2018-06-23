package control;

import dao.SatelliteDao;
import entity.Satellite;

import java.time.LocalDate;
import java.util.Date;

public class InsertHandler {

    public void insertSatellite(String nome, LocalDate start, LocalDate end, String agenzia) {

        SatelliteDao d = new SatelliteDao();
       // d.insert(nome, start, end, agenzia);

    }

    public void insertStrumento(String nome, String satellite, Double [] bande) {

    }
}
