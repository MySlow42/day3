package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;
import java.util.stream.Collectors;
public class Day3 {

    private List<List<String>> groups = new ArrayList<>();
    private ArrayList<Character> itemByGroup = new ArrayList<>();
    private ArrayList<Character> itemsIdentiques = new ArrayList<>();

    public static List<String> getInputByLine(String pathname) {
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

    public Integer resolvePuzzlePart1(List<String> backpacks) {
        for (String s : backpacks) {
            int length = s.length();
            String compart1 = s.substring(0, length / 2);
            String compart2 = s.substring(length / 2, length);
            itemsIdentiques.add(compareCompartiments(compart1, compart2));
        }
        return caclulate(itemsIdentiques);
    }

    public Integer resolvePuzzlePart2(List<String> backpacks) {
        groups = divideByGroups(backpacks);
        for (List<String> groupe : groups) {
            itemByGroup.add(compareGroup(groupe));
        }

        return caclulate(itemByGroup);
    }

    private Character compareGroup(List<String> groupe) {
        Character result = null;
        List<Character> elf1 = groupe.get(0).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> elf2 = groupe.get(1).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> elf3 = groupe.get(2).chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        elf1.retainAll(elf2);
        elf1.retainAll(elf3);

        for (Character c : elf1) {
            if (c != null) {
                result = c;
            }
        }
        return result;
    }

    private List<List<String>> divideByGroups(List<String> backpacks) {
        List<List<String>> elfGroups = new ArrayList<>();
        for (int i = 0; i < backpacks.size(); i += 3) {
            int end = Math.min(i + 3, backpacks.size());
            List<String> group = backpacks.subList(i, end);
            elfGroups.add(group);
        }
        return elfGroups;
    }

    private Integer caclulate(List<Character> itemsIdentiques) {
        List<Integer> intList = itemsIdentiques.stream().map(c -> {
            if (c >= 'a' && c <= 'z') {
                return c - 'a' + 1;
            } else if (c >= 'A' && c <= 'Z') {
                return c - 'A' + 27;
            } else {
                return null;
            }
        }).toList();

        return intList.stream().reduce(0, Integer::sum);
    }

    private Character compareCompartiments(String compartiment1, String compartiment2) {
        Character result = null;
        List<Character> charComp1 = compartiment1.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> charComp2 = compartiment2.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        charComp1.retainAll(charComp2);
        for (Character c : charComp1) {
            if (c != null) {
                result = c;
            }
        }
        return result;
    }



}
