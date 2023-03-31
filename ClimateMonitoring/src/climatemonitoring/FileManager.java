package climatemonitoring;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

    public static List<User> readUser(Path path) throws IOException {
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        List<User> list = new ArrayList<User>();
        for (String line : allLines) {
            String[] split = line.split(";");
            list.add(new User(split[0], split[1], split[2], split[3], split[4], split[5], split[6]));
        }
        return list;
    }

    public static List<MonitoringStation> readStation(Path path) throws IOException {
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        List<MonitoringStation> list = new ArrayList<MonitoringStation>();
        for (String line : allLines) {
            String[] split = line.split(";");
            list.add(new MonitoringStation(split[0], split[1], split[2], split[3], split[4], split[5]));
        }
        return list;
    }
    /*public void scrivi(String s) {
        try {
            FileWriter fw = new FileWriter(filename, true);
            fw.append(s + '\n');
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(MyFile.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(MyFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void riscriviFile(List<String> persone) {
        clear();
        try {
            FileWriter fw = new FileWriter(filename, true);
            for(String x : persone)
                fw.append(x.toString() + '\n');
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(MyFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void clear() {
        PrintWriter writer;
        try {
            writer = new PrintWriter(filename);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}

