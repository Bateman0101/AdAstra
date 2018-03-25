package control;

public class InsertFile {

    public void insert(String type, String filePath) {

        CsvHelper csv = new CsvHelper();
        csv.insert(type, filePath);
    }
}
