package Test;

import control.ControllerFil;
import dao.DataSource;
import dao.DefaultDao;
import exceptions.NoEstremoException;
import exceptions.NoPerimetroException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDistanzaEstremiDaContorno {
    public static void main(String[] args) {
        try {
            Statement stmt;
            DataSource ds = new DataSource();
            Connection c = ds.getConnection();
            DefaultDao db = new DefaultDao();
            db.insertSampleData();
            stmt = c.createStatement();
            //Inserimento PUNTI di prova
            String sql = "INSERT INTO PUNTO VALUES(-1.26132,13.4513)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO PUNTO  VALUES(-0.93522,7.88213)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO PUNTO VALUES(2.37794,12.9872)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO PUNTO VALUES(3.41000,14.3217)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO PUNTO VALUES(-1.87233,11.5592)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO PUNTO  VALUES(-1.00763,17.3100)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO PUNTO VALUES(-0.00999,6.71134)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO PUNTO VALUES(2.78123,15.1298)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO PUNTO VALUES(2.99000,9.13278)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO PUNTO VALUES(3.57990,13.9971)";
            stmt.executeUpdate(sql);
            //Inserimento FILAMENTO di prova
            sql = "INSERT INTO FILAMENTO VALUES(1,'A','','',0.0,0.0,0.0,'PACS','Herschel')";
            stmt.executeUpdate(sql);
            //Inserimento SEGMENTO di prova
            sql = "INSERT INTO SEGMENTO VALUES(1,'S','Herschel',1)";
            stmt.executeUpdate(sql);
            //Inserimento 4 PUNTI_SEGMENTO di prova
            sql = "INSERT INTO PUNTO_SEGMENTO VALUES(-1.26132,13.4513,1,1,'','S','Herschel')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO PUNTO_SEGMENTO VALUES(-0.93522,7.88213,1,2,'','S','Herschel')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO PUNTO_SEGMENTO VALUES(2.37794,12.9872,1,3,'','S','Herschel')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO PUNTO_SEGMENTO VALUES(3.41000,14.3217,1,4,'','S','Herschel')";
            stmt.executeUpdate(sql);
            //Inserimento PERIMETRO di prova
            sql = "INSERT INTO PERIMETRO VALUES(-1.87233,11.5592,'Herschel',1)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO PERIMETRO VALUES(-1.00763,17.3100,'Herschel',1)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO PERIMETRO VALUES(-0.00999,6.71134,'Herschel',1)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO PERIMETRO VALUES(2.78123,15.1298,'Herschel',1)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO PERIMETRO VALUES(2.99000,9.13278,'Herschel',1)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO PERIMETRO VALUES(3.57990,13.9971,'Herschel',1)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            int n;
            //Prova #1
            double[] dist;
            ControllerFil source = new ControllerFil();
            dist = source.getDistance(1,1,"Herschel");
            System.out.println("Prova #1. Esito previsto FILAMENTI: dist inf = 1.98831 | dist sup = 0.36638");
            System.out.println("Distanze trovate:");
            System.out.println("dist inf = " + dist[0] + " | " + "dist sup = " + dist[1]);
            //Pulizia DataBase dopo il test
            db.insertSampleData();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoEstremoException e) {
            e.printStackTrace();
        } catch (NoPerimetroException e) {
            e.printStackTrace();
        }
    }
}