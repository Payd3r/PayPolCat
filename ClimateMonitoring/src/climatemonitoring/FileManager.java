/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.util.List;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Ficara Paolo, Mauri Andrea, Luca Cattaneo
 */
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

    public static List<InterestingAreas> readAreas(Path path) throws IOException {
        List<String> allLines = (ArrayList) Files.readAllLines(path, StandardCharsets.UTF_8);
        List<InterestingAreas> list = new ArrayList<InterestingAreas>();
        for (String line : allLines) {
            String[] split = line.split(";");
            list.add(new InterestingAreas(split[0], split[2], split[3], split[4], split[5].split(",")[0], split[5].split(",")[1]));
        }
        return list;
    }

    public static List<MonitoringStation> readStation(Path path) throws IOException {
        List<String> allLines = (ArrayList) Files.readAllLines(path, StandardCharsets.UTF_8);
        List<MonitoringStation> list = new ArrayList<MonitoringStation>();
        for (String line : allLines) {
            String[] split = line.split(";");
            String[] areas = split[2].split(",");
            list.add(new MonitoringStation(split[0], split[1], areas));
        }
        return list;
    }

    public static List<Forecast> readForecast(Path path) throws IOException, ParseException {
        List<String> allLines = (ArrayList) Files.readAllLines(path, StandardCharsets.UTF_8);
        List<Forecast> list = new ArrayList<Forecast>();
        for (String line : allLines) {
            String[] split = line.split(";");
            list.add(new Forecast(split[0], split[1], new SimpleDateFormat("dd/MM/yyyy").parse(split[2]), new SimpleDateFormat("hh:mm:ss").parse(split[3]), split[4].split(","), split[5].split(","), split[6].split(","), split[7].split(","), split[8].split(","), split[9].split(","), split[10].split(",")));
        }
        return list;
    }

    public static void write(String content, Path path) throws IOException {
        Charset charset = Charset.forName("UTF-8");
        try (BufferedWriter writer = Files.newBufferedWriter(path, charset, StandardOpenOption.APPEND)) {
            writer.write(content + "\n", 0, content.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

}
