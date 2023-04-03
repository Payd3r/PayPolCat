/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 *
 * @author Catta
 */
public class FileManager {
    public static ArrayList<User> readUser(Path path) throws IOException {
        ArrayList<String> allLines = (ArrayList) Files.readAllLines(path, StandardCharsets.UTF_8);
        ArrayList<User> list = new ArrayList<User>();
        for (String line : allLines) {
            String[] split = line.split(";");
            list.add(new User(split[0], split[1], split[2], split[3], split[4], split[5], split[6]));
        }
        return list;
    }

    public static ArrayList<MonitoringStation> readStation(Path path) throws IOException {
        ArrayList<String> allLines = (ArrayList) Files.readAllLines(path, StandardCharsets.UTF_8);
        ArrayList<MonitoringStation> list = new ArrayList<MonitoringStation>();
        for (String line : allLines) {
            String[] split = line.split(";");
            list.add(new MonitoringStation(split[0], split[2], split[3], split[4], split[5], split[6]));
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
