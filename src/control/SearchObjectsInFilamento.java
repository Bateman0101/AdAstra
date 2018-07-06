package control;


import dao.*;
import entity.PuntoPerimetro;
import entity.PuntoScheletro;
import entity.Stella;
import entity.StellaDistanza;

import java.sql.Connection;
import java.util.List;
import java.util.Vector;

@SuppressWarnings("ALL")
public class SearchObjectsInFilamento {

    public Boolean isStellaInFil(double longitudine, double latitudine, Connection con) {

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


    public Stella stellaInFil(double longitudine, double latitudine, Connection con) {
        StellaDao sD = new StellaDao();
        Stella stella = null;

        if (this.isStellaInFil(longitudine, latitudine, con)) {
            stella = sD.getStella(latitudine, longitudine, con);
        }
        return stella;
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

    public Vector<StellaDistanza> positionStella(int idFil, String satellite) {

        DataSource dS = new DataSource();
        Connection con = dS.getConnection();
        //Vector<Object> stelle;
        Vector<StellaDistanza> steDist = new Vector<>();
        StellaDao sD = new StellaDao();
        double dist;
        Stella stella;
        //StellaDistanza stellaDistanza;
        Vector<Stella> vec = new Vector<>();
        vec = sD.getAllStars(con);
        int size = vec.size();
        int i = 0;
        try {
            while (i < size) {
                stella = vec.get(i);
                double lat = stella.getLatitudine();
                double lon = stella.getLongitudine();
                if (this.isStellaInFil(lon, lat, con)) {
                    dist = this.minDistance(lat, lon, idFil, satellite, con);
                    System.out.println(stella);
                    StellaDistanza stellaDistanza = new StellaDistanza(
                            stella.getId(),
                            stella.getNome(),
                            stella.getTipo(),
                            stella.getFlusso(),
                            stella.getStrumento(),
                            stella.getSatellite(),
                            stella.getLatitudine(),
                            stella.getLongitudine(),
                            dist);

                    steDist.add(stellaDistanza);
                    //steDist.add(dist);
                    i++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return steDist;
    }

    public double minDistance(double latSt, double lonSt, int IdFil, String sat, Connection con) {

        Double[] distPre = new Double[2];
        Double[] distPost = new Double[2];
        distPre[0] = 10.00;
        distPre[1] = 500.00;
        double disIn = 5000;
        double disFin = 0;
        double x;
        double y;
        PuntoScheletro pS;
        PuntoScheletroDao pSD = new PuntoScheletroDao();
        Vector<PuntoScheletro> vector = new Vector<>();
        vector = pSD.vectorPuntiSegFil(IdFil, sat, con);
        int size = vector.size();
        int i = 0;
        while (i < size) {

            pS = vector.get(i);
            double lat = pS.getLatitudine();
            double lon = pS.getLongitudine();
            distPost = this.distance(latSt, lat, lonSt, lon);
            y = distPost[0];
            x = distPost[1];
            disFin = Math.pow((Math.pow((x - lonSt), 2)) + Math.pow((y - latSt), 2), 0.5);
            if (disFin < disIn) {
                distPost = distPre;
            }
            i++;
        }

        return disFin;

    }


    public Double[] distance(double lat1, double lat2, double lon1, double lon2) {

        Double[] distance = new Double[2];
        double numLat = Math.pow((Math.pow(lat1, 2) + Math.pow(lat2, 2)), 0.5);
        double numLon = Math.pow((Math.pow(lon1, 2) + Math.pow(lon2, 2)), 0.5);
        distance[0] = numLat;
        distance[1] = numLon;

        return distance;
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

    public static void main(String[] args) {
        SearchObjectsInFilamento s = new SearchObjectsInFilamento();
        DataSource ds = new DataSource();
        Connection con = ds.getConnection();
        Vector<StellaDistanza> vector = new Vector<>();
        StellaDao sD = new StellaDao();
        Vector<Stella> vec = new Vector<>();
        vec = sD.getAllStars(con);
        vector = s.positionStella(380, "Spitzer");
        //Stella stella = new Stella(1,"ciao","luce", 20.20, "A", "ab",20.02,20.2);
        //vector.add(stella);
        //vector.add(20.2);
        int size = vector.size();
        int lung = vec.size();
        int i = 0;
        int j = 0;
        while(i < size){
            System.out.println(vector.get(i));
            i++;
        }
        /*while(j < lung){
            System.out.println(vec.get(j));
            j++;
        }*/


        /*PuntoPerimetro pP1 = new PuntoPerimetro(0.0, 0.0, "S", 1);
        PuntoPerimetro pP2 = new PuntoPerimetro(5.0, 0.0, "S", 1);
        PuntoPerimetro pP3 = new PuntoPerimetro(5.0, 1.0, "S", 1);
        PuntoPerimetro pP4 = new PuntoPerimetro(0.0, 1.0, "S", 1);
        Vector<PuntoPerimetro> vec = new Vector<>();
        vec.add(pP1);
        vec.add(pP2);
        vec.add(pP3);
        vec.add(pP4);
        */
        //Boolean bool1;
        //Boolean bool2;
        //bool1 = s.stellaInFil(18.8839, -0.29865, con);
        //bool2 = s.stellaInFilVecPer(2.5, 10.0, vec, con);

        //System.out.println(bool2);
        //System.out.println(s.formula(2.5555, 3.4548, -0.2566, 0.2515, 3.15418, -1.2185));


    }

}
