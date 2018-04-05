package control;

import dao.FilamentoDao;
import entity.Filamento;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CsvHelper {


    public void insert(String type, String csvFile) {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        switch (type) {

            case "Filamento": {
                int id;
                String nome;
                String strumento;
                String satellite;
                String flusso;
                String densita;
                double ellitticita;
                double contrasto;
                double temperatura;

                int n = 0;
                ArrayList<Filamento> arr = new ArrayList<>();

                FilamentoDao d = new FilamentoDao();
                try {

                    br = new BufferedReader(new FileReader(csvFile));
                    while ((line = br.readLine()) != null) {
                        if (n == 0) {
                            n++;
                            continue;
                        }
                        String[] list = line.split(cvsSplitBy);

                        id = Integer.parseInt(list[0]);
                        nome = list[1];
                        strumento = list[7];
                        satellite = list[8];
                        flusso = list[2];
                        densita = list[3];
                        ellitticita = Double.parseDouble(list[5]);
                        contrasto = Double.parseDouble(list[6]);
                        temperatura = Double.parseDouble(list[4]);

                        System.out.println("ID " + id + " nome " + list[1]);

                        Filamento f = new Filamento (id, nome, strumento, satellite, flusso, densita, ellitticita,
                                contrasto, temperatura);
                        arr.add(f);
                    }

                    d.insert(arr);

                    } catch(FileNotFoundException e){
                        e.printStackTrace();
                    } catch(IOException e){
                        e.printStackTrace();
                    } catch(NumberFormatException e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                break;
            }

            case "Scheletro":
            {
                int idF;
                int idS;
                String tipo;
                String satellite = nomeSatellite;
                float latitudine;
                float longitudine;
                int num;
                String flusso;

                int n = 0;
                int idSLast = 0;

                ScheletroDao d = new ScheletroDao();
                try {

                    br = new BufferedReader(new FileReader(csvFile));
                    while ((line = br.readLine()) != null) {
                        if (n == 0) {
                            n++;
                        }
                        else {
                            String[] list = line.split(cvsSplitBy);

                            idF = Integer.parseInt(list[0]);
                            idS = Integer.parseInt(list[1]);
                            tipo = list[2];
                            latitudine = Float.parseFloat(list[4]);
                            longitudine = Float.parseFloat(list[3]);
                            num = Integer.parseInt(list[5]);
                            flusso = list[6];
                            try {
                                d.insertPunto(longitudine, latitudine);
                            } catch (SQLException e) {
                                if (e.getErrorCode() == 1062) {
                                    if (d.verificaPerimetro(latitudine, longitudine, idF, satellite)) {
                                        //TODO messaggio errore(il punto appartine già al contorno dello stesso filamento)
                                    } else {
                                        continue;
                                    }
                                }
                            }
                            if (idS != idSLast) {
                                idSLast = idS;
                                try {
                                    d.insertSegmento(idS, idF, satellite, tipo);
                                } catch (SQLException e) {
                                    if (e.getErrorCode() == 1062) {
                                        continue;
                                    }
                                }
                            }
                            try {
                                d.insertPuntoSegmento(longitudine, latitudine, idS, num, flusso);
                            } catch (SQLException e){
                                if(e.getErrorCode() == 1062) {
                                    //TODO messaggio errore(il punto appartiene già allo scheletro di qualche filamento)
                                    continue;
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            default:
                return;


        }
    }
}
