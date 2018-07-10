package control;

import dao.DataSource;
import dao.FilamentoDao;
import dao.PerimetroDao;
import dao.PuntoDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

public class CsvPerimetro {

    public void insert(String filepath, String satellite) {
        String line;
        String cvsSplitBy = ",";
        PerimetroDao perD = new PerimetroDao();
        FilamentoDao fD = new FilamentoDao();
        PuntoDao pD = new PuntoDao();
        DataSource dS = new DataSource();
        Connection c = dS.getConnection();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {

            while ((line = br.readLine()) != null) {

                String[] row = line.split(cvsSplitBy);

                if (!row[0].equals("IDFIL")) {
                    if (!pD.isPresentPunto(Double.parseDouble(row[2]), Double.parseDouble(row[1]), c)) {
                        pD.insertPunto(Double.parseDouble(row[2]), Double.parseDouble(row[1]), c);
                        System.out.println("INSERT PUNTO (" + Double.parseDouble(row[2]) + " , "
                                + Double.parseDouble(row[1]) + ") IN DB");
                    } else {
                        System.out.println("CHECKING IN DB IF PUNTI ARE PRESENT YET");

                    }
                    if (!fD.isPresentFilamento(Integer.parseInt(row[0]), satellite, c)) {
                        if (!perD.isPresentPerimetro(Double.parseDouble(row[2]), Double.parseDouble(row[1]),
                                satellite, Integer.parseInt(row[0]), c)) {
                            perD.insertPuntoPerimetro(Double.parseDouble(row[2]),
                                    Double.parseDouble(row[1]), satellite, Integer.parseInt(row[0]), c);
                            System.out.println("INSERT PERIMETRO_" + satellite + " (" + Integer.parseInt(row[0]) + " , " + Double.parseDouble(row[1]) +
                                    " , " + row[2] + ") IN DB");
                        } else {
                            System.out.println("CHECKING IN DB IF PERIMETRO ARE PRESENT YET");
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dS.closeConnection(c);
        }
    }

    public static void main(String[] args) {
        CsvPerimetro cPer = new CsvPerimetro();
        //cPer.insert("CSV Files\\contorni_filamenti_Herschel.csv", "Herschel");
        //cPer.insert("CSV Files\\contorni_filamenti_Spitzer.csv", "Spitzer");
    }
}