/*package Test;

import control.ControllerFil;
import dao.DataSource;
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
            String sql = "DELETE FROM PUNTO_SEGMENTO";
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            sql = "DELETE FROM SEGMENTO";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM FILAMENTO";
            stmt.executeUpdate(sql);
            //Inserimento 4 FILAMENTI di prova
            sql = "INSERT INTO FILAMENTO VALUES(1,'A','PACS','Herschel','','',0,1.88679)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO FILAMENTO VALUES(2,'B','PACS','Herschel','','',0,2.32558)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO FILAMENTO VALUES(3,'C','PACS','Herschel','','',0,5.89342)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO FILAMENTO VALUES(4,'D','PACS','Herschel','','',0,8.11234)";
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
                if (id != 1 && id != 2 && id != 4) {
                    System.out.println("ERRORE!!");
                }
            }
            //Prova #2
            inf = 3;
            sup = 6;
            list = source.ricercaNumS(inf, sup);
            System.out.println("Prova #2 con intervallo di ricerca (3,6). Esito previsto FILAMENTI: 1,3");
            System.out.println("FILAMENTI trovati:");
            for (n = 0; n < list.size(); n++) {
                Filamento fil = list.get(n);
                int id = fil.getId();
                System.out.println(id);
                if (id != 1 && id != 3) {
                    System.out.println("ERRORE!!");
                }
            }
            //Prova #3
            inf = 1;
            sup = 4;
            list = source.ricercaNumS(inf, sup);
            System.out.println("Prova #3 con intervallo di ricerca (1,4). Esito previsto FILAMENTI: 1,2");
            System.out.println("FILAMENTI trovati:");
            for (n = 0; n < list.size(); n++) {
                Filamento fil = list.get(n);
                int id = fil.getId();
                System.out.println(id);
                if (id != 1 && id != 2) {
                    System.out.println("ERRORE!!");
                }
            }
            //Prova #4
            inf = 6;
            sup = 9;
            list = source.ricercaNumS(inf, sup);
            System.out.println("Prova #4 con intervallo di ricerca (1,4). Esito previsto FILAMENTI: NESSUNO");
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
            c = ds.getConnection();
            sql = "DELETE FROM PUNTO_SEGMENTO";
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            sql = "DELETE FROM SEGMENTO";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM FILAMENTO";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}*/