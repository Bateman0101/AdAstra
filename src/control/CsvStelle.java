package control;

import dao.DataSource;
import dao.PuntoDao;
import dao.StellaDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

public class CsvStelle {

    public void insert(String filePath) {
        String line;
        String cvsSplitBy = ",";
        StellaDao sD = new StellaDao();
        PuntoDao pD = new PuntoDao();
        DataSource dS = new DataSource();
        Connection c = dS.getConnection();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator

                String[] row = line.split(cvsSplitBy);
                switch (row[5]) {
                    case "PRESTELLAR":
                        row[4] = "SPIRE";
                        break;
                    case "PROTOSTELLAR":
                        row[4] = "PACS";
                        break;
                    case "UNBOUND":
                        row[4] = "OTHER";
                        break;
                }
                if (!row[0].equals("IDSTAR")) {
                    if(!pD.isPresentPunto(Double.parseDouble(row[3]),Double.parseDouble(row[2]), c)){
                        pD.insertPunto(Double.parseDouble(row[3]),Double.parseDouble(row[2]), c);
                        System.out.println("INSERT PUNTO (" + Double.parseDouble(row[3]) + " , " +
                         Double.parseDouble(row[2]) + ") IN DB");
                    }else {
                        System.out.println("CHECKING IN DB IF PUNTO ARE PRESENT YET");
                    }
                    if(!sD.isPresentStella(Integer.parseInt(row[0]), c)){
                        sD.insertStella(Integer.parseInt(row[0]), row[1], row[5], Double.parseDouble(row[3]), row[4],
                                "Herschel", Double.parseDouble(row[3]), Double.parseDouble(row[2]), c);
                        System.out.println("INSERT STELLA (" + row[0] + " | " + row[1] + " | " + row[2] + " | " + row[3] + " | " +
                                row[4] + " | " + row[5] + ") IN DB");
                    }else {
                        System.out.println("CHECKING IN DB IF STELLE ARE PRESENT YET");
                    }
                }
                /*System.out.println(row[0] + " | " + row[1] + " | " + row[2] + " | " + row[3] + " | " +
                        row[4] + " | " + row[5]);*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dS.closeConnection(c);
        }
    }

public static void main(String[] args) {
        CsvStelle cS = new CsvStelle();
        /*for(int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }*/
        try {
            cS.insert("/home/riccardo/Desktop/AdAstra/CSV Files/stelle_Herschel.csv");
        }catch (ArrayIndexOutOfBoundsException a){
            a.printStackTrace();
        }
    }
}
