package control;

import dao.DataSource;
import dao.FilamentoDao;
import dao.StellaDao;
import entity.Filamento;
import entity.Punto;
import entity.Stella;
import exceptions.NoFilamentoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.Executor;

public class FilamentoHandler {

    public double findMin(ArrayList<Double> l) {

        double minValue = l.get(0);
        for(int i=1;i<l.size();i++){
            if(l.get(i) < minValue){
                minValue = l.get(i);
            }
        }
        return minValue;
    }

    public double findMax(ArrayList<Double> l) {

        double maxValue = l.get(0);
        for(int i=1;i<l.size();i++){
            if(l.get(i) > maxValue){
                maxValue = l.get(i);
            }
        }
        return maxValue;
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

    public Punto centroid(ArrayList<Punto> p) {

        ArrayList<Double> listLat = new ArrayList<>();
        ArrayList<Double> listLon = new ArrayList<>();


        for (int i = 0; i < p.size(); i++) {
            double lat = p.get(i).getLatitudine();
            double lon = p.get(i).getLongitudine();
            listLat.add(lat);
            listLon.add(lon);
        }

        double meanLat = 0;
        for (int i = 0; i < listLat.size(); i++) {
            meanLat += listLat.get(i);
            System.out.println(Double.toString(listLat.get(i)));

        }
        meanLat = meanLat/listLat.size();

        double meanLon = 0;
        for (int i = 0; i < listLon.size(); i++) {
            meanLon += listLon.get(i);
        }
        meanLon = meanLon/listLon.size();

        System.out.println(Double.toString(meanLat));


        return new Punto(meanLat, meanLon);

    }

    public double extension (ArrayList<Punto> p) {

        ArrayList<Double> listLat = new ArrayList<>();
        ArrayList<Double> listLon = new ArrayList<>();

        for (int i = 0; i < p.size(); i++) {
            double lat = p.get(i).getLatitudine();
            double lon = p.get(i).getLongitudine();

            listLat.add(lat);
            listLon.add(lon);
        }

        double minLat = findMin(listLat);
        double minLon = findMin(listLon);
        double maxLat = findMax(listLat);
        double maxLon = findMax(listLon);

        double distance = Math.hypot(maxLat-minLat, maxLon-minLon);

        System.out.println(Double.toString(distance));

        return distance;


    }

    public boolean checkSquare(double clat, double clon, double l, ArrayList<Punto> p) {

        double edgeLat = clat - l / 2;
        double edgeLon = clon - l / 2;

        for (int i = 0; i < p.size(); i++) {

            if (!(p.get(i).getLatitudine() >= edgeLat && p.get(i).getLatitudine() <= edgeLat + l
                    && p.get(i).getLongitudine() >= edgeLon && p.get(i).getLongitudine() <= edgeLon + l))
                return false;
        }

        return true;
    }

    public boolean checkCircle(double clat, double clon, double r, ArrayList<Punto> p) {

        for (int i = 0; i < p.size(); i++) {

            System.out.println(Math.hypot(p.get(i).getLatitudine()-clat, p.get(i).getLongitudine()-clon));
            System.out.println(r);

            if (Math.hypot(p.get(i).getLatitudine()-clat, p.get(i).getLongitudine()-clon) > r) {
                return false;
            }
        }

        return true;
    }


    public Punto computeCentroid(int id, String satellite) throws NoFilamentoException{

        FilamentoDao dao = new FilamentoDao();
            ArrayList<Punto> list = dao.findPerimeter(id, satellite);

        Punto c = centroid(list);

        return c;
    }

    public double computeExtension(int id, String satellite) throws NoFilamentoException{

        FilamentoDao dao = new FilamentoDao();
        ArrayList<Punto> list = dao.findPerimeter(id, satellite);

        double e = extension(list);

        return e;
    }

    public ArrayList<Integer> findSegments(int id, String satellite) {

        FilamentoDao dao = new FilamentoDao();
        ArrayList<Integer> list = dao.findSegments(id, satellite);

        return list;
    }

    public ArrayList<Filamento> isInsideSquare(double centerLat, double centerLon, double lato) throws NoFilamentoException{

        FilamentoDao dao = new FilamentoDao();

        ArrayList<Filamento> l = dao.findAll();
        ArrayList<Filamento> result = new ArrayList<>();

        for (int i = 0; i < l.size(); i++) {

         ArrayList<Punto> list = dao.findPerimeter(l.get(i).getId(), l.get(i).getSatellite());

         if (checkSquare(centerLat,centerLon, lato, list)){
             result.add(l.get(i));
         }

    }

    return result;
}

    public ArrayList<Filamento> isInsideCircle(double centerLat, double centerLon, double radius) throws NoFilamentoException{

        FilamentoDao dao = new FilamentoDao();

        ArrayList<Filamento> l = dao.findAll();
        ArrayList<Filamento> result = new ArrayList<>();

        System.out.println(l.size());

        for (int i = 0; i < l.size(); i++) {

            ArrayList<Punto> list = dao.findPerimeter(l.get(i).getId(), l.get(i).getSatellite());

            if (checkCircle(centerLat,centerLon, radius, list)){
                result.add(l.get(i));
            }

        }
        return result;
    }

    public ArrayList<Stella> stelleInFilamento(int id, String satellite) throws NoFilamentoException{


        FilamentoDao dao = new FilamentoDao();
        ArrayList<Punto> listPerimeter = dao.findPerimeter(id, satellite);

        StellaDao daoS = new StellaDao();

        DataSource ds = new DataSource();
        Connection con = ds.getConnection();

        Vector<Stella> listStar = daoS.getAllStars(con);
        ArrayList<Stella> resultList = new ArrayList<>();

        System.out.println(listPerimeter.size());
        int numPer = 0;
        double result = 0;
        for (int i = 0; i < listStar.size(); i++) {

            while (numPer < listPerimeter.size() - 1) {

                Punto p = listPerimeter.get(numPer);
                Punto pNext = listPerimeter.get(numPer + 1);
                    double lonP = p.getLongitudine();
                    double latP = p.getLatitudine();
                    double lonPNext = pNext.getLongitudine();
                    double latPNext = pNext.getLatitudine();

                    result += formula(listStar.get(i).getLongitudine(), listStar.get(i).getLatitudine(),
                            lonP, latP,
                            lonPNext, latPNext);

                    numPer++;

                }

            if (result >= 0.01)
                resultList.add(listStar.get(i));

            numPer = 0;
            result = 0;
        }

        return resultList;

    }

}
