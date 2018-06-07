package control;

import dao.DataSource;
import dao.FilamentoDao;

import java.io.*;
import java.sql.Connection;

public class CsvFilamenti {

    /*
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Projects\\AdAstra\\CSV Files\\filamenti_Spitzer.csv"));
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
            System.out.print(scanner.next()+" | ");
        }
        scanner.close();
    }
    */

    public void insertFilamenti(String satellite) {
        String csvFileSpitzer = "C:\\Projects\\AdAstra\\CSV Files\\filamenti_Spitzer.csv";
        String csvFileHerschel = "C:\\Projects\\AdAstra\\CSV Files\\filamenti_Herschel.csv";

        switch (satellite) {
            case "Spitzer":
                System.out.println(csvFileSpitzer);
                insert(csvFileSpitzer);
                break;

            case "Herschel":
                System.out.println(csvFileHerschel);
                insert(csvFileHerschel);
                break;
        }

    }

    public void insert(String filePath) {
        String line;
        String cvsSplitBy = ",";
        FilamentoDao fD = new FilamentoDao();
        DataSource dS = new DataSource();
        Connection c = dS.getConnection();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator

                String[] row = line.split(cvsSplitBy);
                if (!row[0].equals("IDFIL")) {
                    if(!fD.isPresentFilamento(Integer.parseInt(row[0]), row[7], c)) {
                        fD.insertFil(Integer.parseInt(row[0]), row[1], row[2], row[3], Double.parseDouble(row[4]),
                                Double.parseDouble(row[5]), Double.parseDouble(row[6]), row[7], row[8], c);
                        System.out.println("INSERITO IN DB IL FILAMENTO " + row[0] + " | " + row[1] + " | " + row[2] + " | " + row[3] + " | " +
                                row[4] + " | " + row[5] + " | " + row[6] + " | " + row[7] + " | " + row[8]);
                    }else {
                        System.out.println("FILAMENTO " + row[0] + " E' GIA' PRESENTE NEL DB");
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
        String satellite1 = "Herschel";
        String satellite2 = "Spitzer";
        CsvFilamenti cS = new CsvFilamenti();
        /*for(int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }*/
        try {
            cS.insertFilamenti(satellite2);
        }catch (ArrayIndexOutOfBoundsException a){
            a.printStackTrace();
        }
    }
}
