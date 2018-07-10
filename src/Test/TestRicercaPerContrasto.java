package Test;

import control.ControllerFil;
import dao.DataSource;
import entity.Filamento;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestRicercaPerContrasto {
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
            sql = "INSERT INTO FILAMENTO VALUES(1,'A','','',0.0,1.14789,0.0,'PACS','Herschel')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO FILAMENTO VALUES(2,'B','','',0.0,2.83405,0.0,'PACS','Herschel')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO FILAMENTO VALUES(3,'C','','',0.0,2.83406,0.0,'PACS','Herschel')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO FILAMENTO VALUES(4,'D','','',0.0,4.11123,0.0,'PACS','Herschel')";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            int n;
            //Prova #1
            double p = 14.6;
            List<Filamento> list = new ArrayList<>();
            ControllerFil source = new ControllerFil();
            list = source.ricercaLum(p);
            System.out.println("Prova #1 con percentuale di ricerca 14.6%. Esito previsto FILAMENTI: 1,2,3,4");
            System.out.println("FILAMENTI trovati:");
            for (n = 0; n < list.size(); n++) {
                Filamento fil = list.get(n);
                int id = fil.getId();
                System.out.println(id);
                if (list.size() < 4) {
                    System.out.println("ERRORE!!");
                }
            }
            //Prova #2
            p = 183.405;
            list = source.ricercaLum(p);
            System.out.println("Prova #2 con percentuale di ricerca 183.405%. Esito previsto FILAMENTI: 3,4");
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
            p = 184;
            list = source.ricercaLum(p);
            System.out.println("Prova #3 con percentuale di ricerca 184. Esito previsto FILAMENTI: 4");
            System.out.println("FILAMENTI trovati:");
            for (n = 0; n < list.size(); n++) {
                Filamento fil = list.get(n);
                int id = fil.getId();
                System.out.println(id);
                if (id != 4) {
                    System.out.println("ERRORE!!");
                }
            }
            //Prova #4
            p = 500;
            list = source.ricercaLum(p);
            System.out.println("Prova #4 con percentuale di ricerca 500%. Esito previsto FILAMENTI: NESSUNO");
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
}