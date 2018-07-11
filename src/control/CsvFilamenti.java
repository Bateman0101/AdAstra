package control;

import dao.DataSource;
import dao.FilamentoDao;
import entity.Filamento;

import java.io.*;
import java.sql.Connection;

public class   CsvFilamenti {

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
                insert(csvFileSpitzer, satellite);
                break;

            case "Herschel":
                System.out.println(csvFileHerschel);
                insert(csvFileHerschel, satellite);
                break;
        }

    }

    public void insert(String filePath, String satellite) {
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
                    if(!fD.isPresentFilamento(Integer.parseInt(row[0]), satellite, c)) {
                        fD.insertFil(Integer.parseInt(row[0]), row[1], row[2], row[3], Double.parseDouble(row[5]),
                                Double.parseDouble(row[6]), Double.parseDouble(row[4]),  row[8], satellite, c);
                        System.out.println("INSERITO IN DB IL FILAMENTO " + row[0] + " | " + row[1] + " | " + row[2] + " | " + row[3] + " | " +
                                row[5] + " | " + row[6] + " | " + row[4] + " | " + row[8] + " | " + satellite);
                    }else {
                        Filamento filamento = fD.getFilamento(Integer.parseInt(row[0]), satellite, c);

                        if (!filamento.getNome().equals(row[1]) ||
                                !filamento.getFlusso().equals(row[2]) ||
                                !filamento.getDensita().equals(row[3]) ||
                                !(filamento.getEllitticita() == Double.parseDouble(row[5])) ||
                                !(filamento.getContrasto() == Double.parseDouble(row[6])) ||
                                !(filamento.getTemperatura() == Double.parseDouble(row[4])) ||
                                !filamento.getStrumento().equals(row[8])){
                            fD.updateFilamento(Integer.parseInt(row[0]), row[1], row[2], row[3], Double.parseDouble(row[5]),
                                    Double.parseDouble(row[6]), Double.parseDouble(row[4]),  satellite, row[8], c);

                        }


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
