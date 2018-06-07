package control;

import dao.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

public class CsvScheletro {


    public void insert(String satellite) {
        String line;
        String cvsSplitBy = ",";
        FilamentoDao fD = new FilamentoDao();
        PuntoDao pD = new PuntoDao();
        PuntoSegmentoDao pSegD = new PuntoSegmentoDao();
        SegmentoDao sD = new SegmentoDao();
        DataSource dS = new DataSource();
        Connection con = dS.getConnection();
        String csvFileSpitzer = "C:\\Projects\\AdAstra\\CSV Files\\scheletro_filamenti_Spitzer(CORRETTO).csv";
        String csvFileHerschel = "C:\\Projects\\AdAstra\\CSV Files\\scheletro_filamenti_Herschel.csv";
        String filepath = null;

        switch (satellite) {
            case "Spitzer":
                filepath = csvFileSpitzer;
                break;

            case "Herschel":
                filepath = csvFileHerschel;
                break;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {

            while ((line = br.readLine()) != null) {

                String[] row = line.split(cvsSplitBy);
                int idFil = Integer.parseInt(row[0]);
                int idSeg = Integer.parseInt(row[1]);
                String tipo = row[2];
                double lon = Double.parseDouble(row[3]);
                double lat = Double.parseDouble(row[4]);
                int num = Integer.parseInt(row[5]);
                String flusso = row[6];

                if (!row[0].equals("IDFIL")) {
                    if (!sD.isPresentSegmento(idSeg, idFil, tipo, satellite, con)) {
                        sD.insertSegmento(idSeg, idFil, tipo, satellite, con);

                    }
                    if (!pSegD.isPresentPuntoSegmento(lat, lon, con)){
                        if(!pD.isPresentPunto(lat, lon, con)){
                            pD.insertPunto(lat, lon, con);
                        }
                        pSegD.insertPuntoSegmento(idSeg, lat, lon, num, flusso, tipo, con);
                    }
                    /*
                    if (fD.isPresentFilamento(Integer.parseInt(row[0]), satellite, con)) {
                        if (!pSD.isPresentPuntoScheletro(Double.parseDouble(row[4]), Double.parseDouble(row[3]), con)) {
                            pSD.insertPuntoScheletro(Integer.parseInt(row[0]), satellite, Integer.parseInt(row[1]),
                                    Double.parseDouble(row[4]), Double.parseDouble(row[3]), con);
                        }
                        }
                        */
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dS.closeConnection(con);
        }
    }
    public static void main(String[] args) {
        String satellite1 = "Herschel";
        String satellite2 = "Spitzer";
        CsvScheletro cS = new CsvScheletro();
        /*for(int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }*/
        try {
            cS.insert(satellite2);
        }catch (ArrayIndexOutOfBoundsException a){
            a.printStackTrace();
        }
    }
}
