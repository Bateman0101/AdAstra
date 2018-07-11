package Test;

import control.ControllerFil;
import dao.DataSource;
import dao.DefaultDao;
import entity.Filamento;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestRicercaPerEllitticità {
    public static void main(String[] args) {
        try {
            Statement stmt;
            DataSource ds = new DataSource();
            Connection c = ds.getConnection();
            String sql;
            stmt = c.createStatement();
            DefaultDao db = new DefaultDao();
            db.insertSampleData();
            //Inserimento 4 FILAMENTI di prova
            sql = "INSERT INTO FILAMENTO VALUES(1,'A','','',1.88679,0.0,0.0,'PACS','Herschel')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO FILAMENTO VALUES(2,'B','','',2.32558,0.0,0.0,'PACS','Herschel')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO FILAMENTO VALUES(3,'C','','',5.89342,0.0,0.0,'PACS','Herschel')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO FILAMENTO VALUES(4,'D','','',8.11234,0.0,0.0,'PACS','Herschel')";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            int n;
            //Prova #1
            double inf = 1.88678;
            double sup = 3;
            List<Filamento> list = new ArrayList<>();
            ControllerFil source = new ControllerFil();
            list = source.ricercaEll(inf,sup);
            System.out.println("Prova #1 con intervallo di ricerca (1.88678,3). Esito previsto FILAMENTI: 1,2");
            System.out.println("FILAMENTI trovati:");
            for (n = 0; n < list.size(); n++) {
                Filamento fil = list.get(n);
                int id = fil.getId();
                System.out.println(id);
                if (id != 1 && id != 2) {
                    System.out.println("ERRORE!!");
                }
            }
            //Prova #2
            inf = 2.33;
            sup = 9.213;
            list = source.ricercaEll(inf, sup);
            System.out.println("Prova #2 con intervallo di ricerca (2.33,9.213). Esito previsto FILAMENTI: 3,4");
            System.out.println("FILAMENTI trovati:");
            for (n = 0; n < list.size(); n++) {
                Filamento fil = list.get(n);
                int id = fil.getId();
                System.out.println(id);
                if (id != 3 && id != 4) {
                    System.out.println("ERRORE!!");
                }
            }
            //Prova #3
            inf = 2.32557654;
            sup = 5.9;
            list = source.ricercaEll(inf, sup);
            System.out.println("Prova #3 con intervallo di ricerca (2.32557654,5.9). Esito previsto FILAMENTI: 2,3");
            System.out.println("FILAMENTI trovati:");
            for (n = 0; n < list.size(); n++) {
                Filamento fil = list.get(n);
                int id = fil.getId();
                System.out.println(id);
                if (id != 2 && id != 3) {
                    System.out.println("ERRORE!!");
                }
            }
            //Prova #4
            inf = 2.33;
            sup = 3.993435454;
            list = source.ricercaEll(inf, sup);
            System.out.println("Prova #4 con intervallo di ricerca (2.33,3.993435454). Esito previsto FILAMENTI: NESSUNO");
            System.out.println("FILAMENTI trovati:");
            for (n = 0; n < list.size(); n++) {
                Filamento fil = list.get(n);
                int id = fil.getId();
                System.out.println(id);
            }
            if (list.size() > 0) {
                System.out.println("ERRORE!!");
            }
            //Pulizia DataBase dopo il test
            db.insertSampleData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}