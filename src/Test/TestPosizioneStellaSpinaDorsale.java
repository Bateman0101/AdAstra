package Test;

import control.SearchObjectsInFilamento;
import dao.DataSource;
import dao.DefaultDao;
import entity.StellaDistanza;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class TestPosizioneStellaSpinaDorsale {

    public static void main(String[] args) {

        try {
            Statement st;

            DataSource ds = new DataSource();
            Connection con = ds.getConnection();
            st = con.createStatement();
            String sql;

            DefaultDao defaultDao = new DefaultDao();
            defaultDao.deleteAll();
            defaultDao.insertSampleData();

            //Inserimento 1 FILAMENTO di prova
            sql = "INSERT INTO filamento VALUES(1,'A','','',0.0,0.0,0.0,'PACS','Herschel')";
            st.executeUpdate(sql);

            //Inserimento PUNTI di prova
            sql = "INSERT INTO punto VALUES(-1.26132,13.4513)";
            st.executeUpdate(sql);
            sql = "INSERT INTO punto  VALUES(-0.93522,7.88213)";
            st.executeUpdate(sql);
            sql = "INSERT INTO punto VALUES(2.37794,12.9872)";
            st.executeUpdate(sql);
            sql = "INSERT INTO punto VALUES(3.41000,14.3217)";
            st.executeUpdate(sql);

            // inserimento PUNTI (perimetro)
            sql = "INSERT INTO punto VALUES(-1.87233,11.5592)";
            st.executeUpdate(sql);
            sql = "INSERT INTO punto  VALUES(-1.00763,17.3100)";
            st.executeUpdate(sql);
            sql = "INSERT INTO punto VALUES(-0.00999,6.71134)";
            st.executeUpdate(sql);
            sql = "INSERT INTO punto VALUES(2.78123,15.1298)";
            st.executeUpdate(sql);
            sql = "INSERT INTO punto VALUES(2.99000,9.13278)";
            st.executeUpdate(sql);
            sql = "INSERT INTO punto VALUES(3.57990,13.9971)";


            st.executeUpdate(sql);
            sql = "INSERT INTO punto VALUES(-2.00,10.00)";
            st.executeUpdate(sql);
            sql = "INSERT INTO punto VALUES(-3.00,11.00)";
            st.executeUpdate(sql);
            sql = "INSERT INTO punto VALUES(-4.00,12.00)";
            st.executeUpdate(sql);


            //Inserimento SEGMENTO di prova
            sql = "INSERT INTO segmento VALUES(1,'S','Herschel',1)";
            st.executeUpdate(sql);
            //Inserimento 4 PUNTI_SEGMENTO di prova
            sql = "INSERT INTO punto_segmento VALUES(-1.26132,13.4513,1,1,'','S','Herschel')";
            st.executeUpdate(sql);
            sql = "INSERT INTO punto_segmento VALUES(-0.93522,7.88213,1,2,'','S','Herschel')";
            st.executeUpdate(sql);
            sql = "INSERT INTO punto_segmento VALUES(2.37794,12.9872,1,3,'','S','Herschel')";
            st.executeUpdate(sql);
            sql = "INSERT INTO punto_segmento VALUES(3.41000,14.3217,1,4,'','S','Herschel')";
            st.executeUpdate(sql);

            //Inserimento STELLE di prova
            sql = "INSERT INTO stella VALUES(1,'Stella', 'FORMATA', 12.25, 'SPIRE'," +
                    " 'Herschel', -2, 10.00)";
            st.executeUpdate(sql);
            sql = "INSERT INTO stella VALUES(2,'Stella2', 'PROTOSTELLAR', 12.25, 'SPIRE'," +
                    " 'Herschel', -3, 11.00)";
            st.executeUpdate(sql);
            sql = "INSERT INTO stella VALUES(3,'Stella3', 'PRESTELLAR', 12.25, 'SPIRE'," +
                    " 'Herschel', -4, 12.00)";
            st.executeUpdate(sql);
            sql = "INSERT INTO stella VALUES(4,'Stella4', 'FORMATA', 12.25, 'SPIRE'," +
                    " 'Herschel', 3.41000, 14.3217)";
            st.executeUpdate(sql);

            //Inserimento PERIMETRO di prova
            sql = "INSERT INTO perimetro VALUES(-1.87233, 11.5592, 'Herschel',1)";
            st.executeUpdate(sql);
            sql = "INSERT INTO perimetro VALUES(-1.00763,17.3100, 'Herschel', 1)";
            st.executeUpdate(sql);
            sql = "INSERT INTO perimetro VALUES(-0.00999,6.71134, 'Herschel', 1)";
            st.executeUpdate(sql);
            sql = "INSERT INTO perimetro VALUES(2.78123,15.1298, 'Herschel', 1)";
            st.executeUpdate(sql);
            sql = "INSERT INTO perimetro VALUES(2.99000,9.13278, 'Herschel', 1)";
            st.executeUpdate(sql);
            sql = "INSERT INTO perimetro VALUES(3.57990,13.9971, 'Herschel', 1)";
            st.executeUpdate(sql);

            Vector<StellaDistanza> vector;
            SearchObjectsInFilamento searchObjectsInFilamento = new SearchObjectsInFilamento();
            vector = searchObjectsInFilamento.positionStella(1, "Herschel");
            int size = vector.size();
            int i = 0;
            while (i < size){

                StellaDistanza stellaDistanza = vector.get(i);
                System.out.println(stellaDistanza.getDistanza() + "  " +
                stellaDistanza.getNome());
                i++;
            }


            defaultDao.deleteAll();

            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

