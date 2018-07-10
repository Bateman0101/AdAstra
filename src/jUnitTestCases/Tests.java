package jUnitTestCases;


import control.FilamentoHandler;
import dao.*;
import entity.Filamento;
import entity.Punto;
import entity.Stella;
import exceptions.NoFilamentoException;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {


    public void insertSampleData() {


            DataSource dS = new DataSource();
            Connection c = dS.getConnection();
            StrumentoDao strumDao = new StrumentoDao();
            SatelliteDao satDao = new SatelliteDao();
            FilamentoDao filDao = new FilamentoDao();
            PerimetroDao perDao = new PerimetroDao();
            SegmentoDao segDao = new SegmentoDao();
            PuntoDao pDao = new PuntoDao();
            StellaDao stellaDao = new StellaDao();

            if (filDao.isPresentFilamento(1, "Spitzer", c))
                return;

            satDao.insertSatellite("Spitzer");
            satDao.insertSatellite("Herschel");
            strumDao.insertStrumento("OTHER", "Herschel", "3/7/6");
            strumDao.insertStrumento("IRAC", "Spitzer", "3/7/6");
            filDao.insertFil(1,"Fil","332.3","4.344",7.43,3.5,3,"IRAC", "Spitzer", c);
            pDao.insertPunto(-0.43, 10.8, c);
            pDao.insertPunto(-0.42, 10.83, c);
            pDao.insertPunto(-0.40, 10.82, c);
            pDao.insertPunto(0.788273, 7.524343, c);
            perDao.insertPuntoPerimetro(-0.43, 10.8, "Spitzer", 1, c);
            perDao.insertPuntoPerimetro(-0.42, 10.83, "Spitzer", 1, c);
            perDao.insertPuntoPerimetro(-0.40, 10.82, "Spitzer", 1, c);
            segDao.insertSegmento(5,1,"E","Spitzer", c);
            stellaDao.insertStella(35593, "HIGALPS007.5238+0.7890", "UNBOUND", 0.788273, "OTHER", "Herschel", 0.788273, 7.524343, c);
    }

    @Test
    public void findInfoFilamento() {

        insertSampleData();
        FilamentoHandler control = new FilamentoHandler();

        try {


            Punto p = control.computeCentroid(1, "Spitzer");
            ArrayList<Integer> listSegments = control.findSegments(1, "Spitzer");
            double extension = control.computeExtension(1, "Spitzer");
            int lat = listSegments.get(0);

            assertEquals(-0.4166666666666667, p.getLatitudine(), "Latitudine del centroide");
            assertEquals(5, lat, "Num Segmento");
            assertEquals(0.042426406871192375, extension, "estensione contorno");
        }
        catch (NoFilamentoException e){
            e.printStackTrace();
        }

    }

    @Test
    public void findInRegion() {

        insertSampleData();

        FilamentoHandler control = new FilamentoHandler();

        try {
            ArrayList<Filamento> list = control.isInsideCircle(5, 5, 10);

            assertEquals(1, list.get(0).getId());


        }catch(NoFilamentoException e){
            e.printStackTrace();
        }

    }

    @Test
    public void findStelleInFilamento(){

        insertSampleData();

        FilamentoHandler control = new FilamentoHandler();
        try {
            ArrayList<Stella> list = control.stelleInFilamento(1, "Spitzer");
            assertEquals(35593, list.get(0).getId(),"id della stella inserita");
        }catch(NoFilamentoException e){
            e.printStackTrace();
        }
    }

}