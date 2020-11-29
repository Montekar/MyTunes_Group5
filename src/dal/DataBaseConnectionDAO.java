package dal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnectionDAO {

    /*
    Gets database information from file. Adds it to an array to be sent to all DAO classes
    so database connection could be easily possible
    */

    public List<String> getDatabaseInfo() throws IOException {
        List<String> info = new ArrayList();
        String source = "data/login.txt";
        File file = new File(source);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    try {
                        info.add(line);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        }
        return info;
    }
}
