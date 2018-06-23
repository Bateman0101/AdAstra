package control;


import dao.DataSource;
import dao.PerimetroDao;
import entity.PuntoPerimetro;

import java.sql.Connection;
import java.util.Vector;

@SuppressWarnings("ALL")
public class SearchObjectsInFilamento {

    public Boolean stellaInFil(double longitudine, double latitudine, Connection con) {

        PerimetroDao perD = new PerimetroDao();
        Boolean bool = false;
        Vector<PuntoPerimetro> vec;
        //DataSource dS = new DataSource();
        //Connection con = dS.getConnection();
        vec = perD.getAllPerimetri(con);
        double longSte = longitudine;
        double latSte = latitudine;
        int l = vec.size();
        int numPer = 0;
        int id;
        String satellite;
        int idSuc;
        String satelliteSuc;
        double result = 0;

        while (numPer < l - 1) {

            System.out.println("First while " + numPer + " < " + l);


            PuntoPerimetro pPer = vec.get(numPer);
            //System.out.println(pPer.getLongitudine() + " " + pPer.getLatitudine());
            PuntoPerimetro pPerSuc = vec.get(numPer + 1);
            //System.out.println(pPerSuc.getLongitudine() + " " + pPerSuc.getLatitudine());

            id = pPer.getFilamento();
            satellite = pPer.getSatellite();

            idSuc = pPerSuc.getFilamento();
            satelliteSuc = pPerSuc.getSatellite();

            //System.out.println("id = " + id + " , " + idSuc +
            //        " satellite = " + satellite + " , " + satelliteSuc);

            if (id == idSuc && satellite.equals(satelliteSuc)) {

                //System.out.println("if " + id + "  " + idSuc);

                double longPer = pPer.getLongitudine();
                double latPer = pPer.getLatitudine();
                double longPerSuc = pPerSuc.getLongitudine();
                double latPerSuc = pPerSuc.getLatitudine();

                result += this.formula(longSte, latSte, longPer, latPer,
                        longPerSuc, latPerSuc);

                System.out.println("result is " + result);
                //System.out.println("numPer " + numPer);


            }

            numPer++;
        }

        if (result >= 0.01) {

            bool = true;
        }

        return bool;
    }

    public Boolean stellaInFilVecPer(double longitudine, double latitudine, Vector<PuntoPerimetro> vec, Connection con) {

        //PerimetroDao perD = new PerimetroDao();
        Boolean bool = false;
        //Vector<PuntoPerimetro> vec;
        //DataSource dS = new DataSource();
        //Connection con = dS.getConnection();
        //vec = perD.getAllPerimetri(con);
        double longSte = longitudine;
        double latSte = latitudine;
        int l = vec.size();
        int numPer = 0;
        int id;
        String satellite;
        int idSuc;
        String satelliteSuc;
        double result = 0;

        while (numPer < l - 1) {

            System.out.println("First while " + numPer + " < " + l);


            PuntoPerimetro pPer = vec.get(numPer);
            //System.out.println(pPer.getLongitudine() + " " + pPer.getLatitudine());
            PuntoPerimetro pPerSuc = vec.get(numPer + 1);
            //System.out.println(pPerSuc.getLongitudine() + " " + pPerSuc.getLatitudine());

            id = pPer.getFilamento();
            satellite = pPer.getSatellite();

            idSuc = pPerSuc.getFilamento();
            satelliteSuc = pPerSuc.getSatellite();

            //System.out.println("id = " + id + " , " + idSuc +
            //        " satellite = " + satellite + " , " + satelliteSuc);

            if (id == idSuc && satellite.equals(satelliteSuc)) {

                //System.out.println("if " + id + "  " + idSuc);

                double longPer = pPer.getLongitudine();
                double latPer = pPer.getLatitudine();
                double longPerSuc = pPerSuc.getLongitudine();
                double latPerSuc = pPerSuc.getLatitudine();

                result = this.formula(longSte, latSte, longPer, latPer,
                        longPerSuc, latPerSuc);
                System.out.println("result is " + result);
                //System.out.println("numPer " + numPer);


            }

            numPer++;
        }

        if (result >= 0.01) {

            bool = true;
        }

        return bool;
    }

    public double minDistance(int IdFil, String sat, Connection con){

        double dist = 0;



        return dist;

    }


    public double formula(double lonS, double latS, double lonP, double latP,
                          double lonPS, double latPS) {

        double num;
        double den;
        double div;
        double res;

        num = (lonP - lonS) * (latPS - latS) - (latP - latS) * (lonPS - lonS);
        den = (lonP - lonS) * (lonPS - lonS) + (latP - latS) * (latPS - latS);

        div = num / den;

        res = Math.abs(Math.atan(div));

        return res;
    }

    public Double[] distance(double lat1, double lat2, double lon1, double lon2) {

        Double[] distance = new Double[2];
        double numLat = Math.pow((Math.pow(lat1, 2) + Math.pow(lat2, 2)), 0.5);
        double numLon = Math.pow((Math.pow(lon1, 2) + Math.pow(lon2, 2)), 0.5);
        distance[0] = numLat;
        distance[1] = numLon;

        return distance;
    }

    public static void main(String[] args) {
        SearchObjectsInFilamento s = new SearchObjectsInFilamento();
        DataSource ds = new DataSource();
        PuntoPerimetro pP1 = new PuntoPerimetro(0.0, 0.0, "S", 1);
        PuntoPerimetro pP2 = new PuntoPerimetro(5.0, 0.0, "S", 1);
        PuntoPerimetro pP3 = new PuntoPerimetro(5.0, 1.0, "S", 1);
        PuntoPerimetro pP4 = new PuntoPerimetro(0.0, 1.0, "S", 1);
        Vector<PuntoPerimetro> vec = new Vector<>();
        vec.add(pP1);
        vec.add(pP2);
        vec.add(pP3);
        vec.add(pP4);

        Connection con = ds.getConnection();
        Boolean bool1;
        Boolean bool2;
        //bool1 = s.stellaInFil(18.8839, -0.29865, con);
        bool2 = s.stellaInFilVecPer(2.5, 10.0, vec, con);

        System.out.println(bool2);
        //System.out.println(s.formula(2.5555, 3.4548, -0.2566, 0.2515, 3.15418, -1.2185));


    }

}
