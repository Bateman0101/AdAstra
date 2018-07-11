package Test;

import control.SearchStars;
import dao.DataSource;
import dao.DefaultDao;
import entity.Stella;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("ALL")
public class TestSearchStars {

    public static void main(String[] args){
        try {
            Statement st;
            DataSource ds = new DataSource();
            Connection con = ds.getConnection();
            st = con.createStatement();
            String sql;
/*
            String sql = "DELETE FROM STELLA";
            st.executeUpdate(sql);
            sql = "DELETE FROM perimetro";
            st = con.createStatement();
            st.executeUpdate(sql);
            sql = "DELETE FROM punto_segmento";
            st = con.createStatement();
            st.executeUpdate(sql);
            sql = "DELETE FROM segmento";
            st = con.createStatement();
            st.executeUpdate(sql);
            sql = "DELETE FROM filamento";
            st = con.createStatement();
            st.executeUpdate(sql);
            sql = "DELETE FROM punto";
            st = con.createStatement();
            st.executeUpdate(sql);

*/

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


            SearchStars searchStars = new SearchStars();
            Double[] stelle = new Double[8];
            int i = 0;
            Double[] cen = new Double[2];
            cen[0] = Double.valueOf(1);
            cen[1] = Double.valueOf(1);
            stelle = searchStars.searchStarsRect(150.00,150.00, cen);
            int size = stelle.length;
            while (i < size){
                System.out.println(stelle[i]);
                i++;
            }

            try {
                sql = "DELETE FROM PUNTO_SEGMENTO";
                st.executeUpdate(sql);
                sql = "DELETE FROM SEGMENTO";
                st.executeUpdate(sql);
                sql = "DELETE FROM PERIMETRO";
                st.executeUpdate(sql);
                sql = "DELETE FROM FILAMENTO";
                st.executeUpdate(sql);
                sql = "DELETE FROM STELLA";
                st.executeUpdate(sql);
                sql = "DELETE FROM PUNTO";
                st.executeUpdate(sql);
                sql = "DELETE FROM STRUMENTO";
                st.executeUpdate(sql);
                sql = "DELETE FROM SATELLITE";
                st.executeUpdate(sql);
                sql = "DELETE FROM UTENTE";
                st.executeUpdate(sql);
                defaultDao.deleteAll();

                st.close();
                con.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
