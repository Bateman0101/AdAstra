package control;


import dao.*;
import entity.PuntoPerimetro;
import entity.PuntoScheletro;
import entity.Stella;
import entity.StellaDistanza;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

@SuppressWarnings("ALL")
public class SearchObjectsInFilamento {

    /**
     * Questo metodo controlla se una stella(passate le proprie coordinate) sia all'interno di un filamento
     * controllando le coordinate di essa con quelle del contotrno del filamento
     *
     * @param longitudine
     * @param latitudine
     * @param con
     * @return Boolean
     */
    public Boolean isStellaInFil(int idFil, double longitudine,  double latitudine, Vector<PuntoPerimetro> vec, Connection con) {

        Boolean bool = false;

        double longSte = longitudine;
        double latSte = latitudine;
        int l = vec.size();
        //int l = vec.size();
        int numPer = 0;
        int id;
        String satellite;
        int idSuc;
        String satelliteSuc;
        double result = 0;

        while (numPer < l ) {

            //System.out.println("First while " + numPer + " < " + l);


            //PuntoPerimetro pPer = vec.get(numPer);
            PuntoPerimetro pPer = vec.get(numPer);

            //System.out.println(pPer.getLongitudine() + " " + pPer.getLatitudine());
            //PuntoPerimetro pPerSuc = vec.get(numPer + 1);
            PuntoPerimetro pPerSuc = vec.get(numPer);

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


    /**
     * Questo metodo controlla tramite coordinate che una stella sia interna a un filamento(contorno di esso)
     * oppure no
     *
     * @param longitudine
     * @param latitudine
     * @param vec
     * @param con
     * @return Boolean
     */
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

            numPer = numPer + 1;
        }

        if (result >= 0.01) {

            bool = true;
        }

        return bool;
    }


    /**
     * Da qui parte il controllore del REQ-FN-12, questo metodo trova le stelle all'interno di un filamento
     * e la distanza minima dalla spina dorsale di esso
     *
     * @param idFil
     * @param satellite
     * @return Vector<StellaDistanza>
     */
    public Vector<StellaDistanza> positionStella(int idFil, String satellite) {

        DataSource dS = new DataSource();
        Connection con = dS.getConnection();
        //Vector<Object> stelle;
        Vector<StellaDistanza> steDist = new Vector<>();
        Vector<PuntoPerimetro> vector = new Vector<PuntoPerimetro>();
        PerimetroDao perD = new PerimetroDao();
        //vector = perD.getAllPerimetriFil(idFil, con);
        StellaDao sD = new StellaDao();
        double dist;
        Stella stella;
        //StellaDistanza stellaDistanza;

        /*
        Commentare il DAO ed utilizzare i dati seguenti per fare i test
         */

        PuntoPerimetro pTest1 = new PuntoPerimetro(1.00, 1.00, "Spitzer", 380);
        PuntoPerimetro pTest2 = new PuntoPerimetro(2.00, 1.00, "Spitzer", 380);
        PuntoPerimetro pTest3 = new PuntoPerimetro(2.00, 2.00, "Spitzer", 380);
        PuntoPerimetro pTest4 = new PuntoPerimetro(1.50, 1.50, "Spitzer", 380);
        PuntoPerimetro pTest5 = new PuntoPerimetro(1.75, 1.75, "Spitzer", 380);
        PuntoPerimetro pTest6 = new PuntoPerimetro(1.25, 1.25, "Spitzer", 380);

        vector.add(pTest1);
        vector.add(pTest2);
        vector.add(pTest3);
        vector.add(pTest4);
        vector.add(pTest5);
        vector.add(pTest6);



        Stella stTest1 = new Stella(1, "Test1", "PRESTELLAR", 20.00, "MIPS", "Spitzer",
                -20.37483, 272.0743);
        Stella stTest2 = new Stella(2, "Test2", "PRESTELLAR", 20.00, "MIPS", "Spitzer",
                1.00, 1.00);
        Stella stTest3 = new Stella(3, "Test3", "PRESTELLAR", 20.00, "MIPS", "Spitzer",
                1.00, 1.00);
        Stella stTest4 = new Stella(4, "Test4", "PRESTELLAR", 20.00, "MIPS", "Spitzer",
                1.00, 1.00);
        Stella stTest5 = new Stella(5, "Test5", "PRESTELLAR", 20.00, "MIPS", "Spitzer",
                1.00, 1.00);


        Vector<Stella> vec = new Vector<>();
        vec = sD.getAllStars(con);
        /*vec.add(stTest1);
        vec.add(stTest2);
        vec.add(stTest3);
        vec.add(stTest4);
        vec.add(stTest5);*/

        int size = vec.size();
        int i = 0;
        try {
            while (i < size) {
                stella = vec.get(i);
                double lat = stella.getLatitudine();
                double lon = stella.getLongitudine();
                if (this.isStellaInFil(idFil, lon, lat, vector, con)) {
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
                }
                i = i+1;

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return steDist;
    }


    /**
     * Questo metodo date Latitudine e Longitudine trova la distanza minima rispetto la spina dorsale di un filamento
     *
     * @param latSt
     * @param lonSt
     * @param IdFil
     * @param sat
     * @param con
     * @return double
     */
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


    /**
     * Questo metodo trova la distanza tra due coordinate formate entrambe da Latitudine e Longitudine
     *
     * @param lat1
     * @param lat2
     * @param lon1
     * @param lon2
     * @return Double[]
     */
    public Double[] distance(double lat1, double lat2, double lon1, double lon2) {

        Double[] distance = new Double[2];
        double numLat = Math.pow((Math.pow(lat1, 2) + Math.pow(lat2, 2)), 0.5);
        double numLon = Math.pow((Math.pow(lon1, 2) + Math.pow(lon2, 2)), 0.5);
        distance[0] = numLat;
        distance[1] = numLon;

        return distance;
    }


    /**
     * Questa formula fa parte del REQ-FN-9 e serve a vedere se una stella(o più in generale punto dello spazio)
     * sia interna ad un filamento oppure no. Il metodo riporta solamente il risultato della formula che poi
     * dovrà essere confrontata dal chiamante con un >= 0.01
     *
     * @param lonS
     * @param latS
     * @param lonP
     * @param latP
     * @param lonPS
     * @param latPS
     * @return double
     */
    public double formula(double lonS, double latS, double lonP, double latP,
                          double lonPS, double latPS) {

        double num;
        double den;
        double div;
        double res;

        num = ((lonP - lonS) * (latPS - latS)) - ((latP - latS) * (lonPS - lonS));
        den = ((lonP - lonS) * (lonPS - lonS)) + ((latP - latS) * (latPS - latS));

        if (!(den == 0)){
            div = num / den;
            System.out.println(div);
            res = Math.abs(Math.atan(div));

        }else {
            res = 0.00;
        }



        return res;
    }

    public static void main(String[] args) {
        SearchObjectsInFilamento s = new SearchObjectsInFilamento();
        DataSource ds = new DataSource();
        Connection con = ds.getConnection();
        Vector<StellaDistanza> vector = new Vector<>();
        //vector = s.positionStella(380, "Spitzer");
        //StellaDao sD = new StellaDao();
        //Vector<Stella> vec = new Vector<>();
        //vec = sD.getAllStars(con);
        vector = s.positionStella(380, "Spitzer");
        //Stella stella = new Stella(1,"ciao","luce", 20.20, "A", "ab",20.02,20.2);
        //vector.add(stella);
        //vector.add(20.2);
        //boolean res = s.isStellaInFil(381,1.2,1.2,con);
        //System.out.println(res);
        int size = vector.size();
        //int lung = vec.size();
        int i = 0;

        if(size == 0){
            System.out.println("EMPTY");
        }
        //int j = 0;
        while(i < size){
            System.out.println(vector.get(i));

            i = i+1;
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
