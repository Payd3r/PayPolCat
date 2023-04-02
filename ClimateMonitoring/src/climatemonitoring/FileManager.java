package climatemonitoring;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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
            list.add(new MonitoringStation(split[0], split[2], split[3], split[4], split[5], split[6]));
        }
        return list;
    }

    public static void write(String content, Path path) throws IOException {
        Charset charset = Charset.forName("UTF-8");
        try (BufferedWriter writer = Files.newBufferedWriter(path, charset, StandardOpenOption.APPEND)) {
            writer.write(content, 0, content.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}

