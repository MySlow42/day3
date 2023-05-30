package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Persistance {

    public List<String> getInputByLine(String pathname) {
        List<String> backpacks = new ArrayList<>();
        try {
            File input = new File(pathname);
            Scanner scanner = new Scanner(input);

            while (scanner.hasNextLine()) {
                String backpack = scanner.nextLine();
                backpacks.add(backpack);
            }
            scanner.close();

        } catch (FileNotFoundException e) {

            System.out.println("Fichier non trouvé.");
            e.printStackTrace();
        }
        return backpacks;
    }
}
