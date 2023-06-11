package org.example;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;
import java.util.stream.Collectors;

// Il y a un comme un air de déjà vu sur ce code...
public class Day3 {

    // Ne créons pas d'état si pas nécessaire (attributs)

    public static List<String> getInputByLine(String pathname) {
        try(InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathname);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr)) {
            return br.lines().collect(Collectors.toList());
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Integer resolvePuzzlePart1(List<String> backpacks) {
        ArrayList<Character> itemsIdentiques = new ArrayList<>();
        for (String s : backpacks) {
            int length = s.length();
            String compart1 = s.substring(0, length / 2);
            String compart2 = s.substring(length / 2, length);
            itemsIdentiques.add(compareCompartiments(compart1, compart2));
        }
        return caclulate(itemsIdentiques);
    }

    public Integer resolvePuzzlePart2(List<String> backpacks) {
        List<List<String>> groups = divideByGroups(backpacks);
        List<Character> itemByGroup = new ArrayList<>();
        for (List<String> groupe : groups) {
            itemByGroup.add(compareGroup(groupe));
        }

        return caclulate(itemByGroup);
    }

    private Character compareGroup(List<String> groupe) {
        List<Character> elf1 = groupe.get(0).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> elf2 = groupe.get(1).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> elf3 = groupe.get(2).chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        elf1.retainAll(elf2);
        elf1.retainAll(elf3);

        return elf1.get(0);
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
        return itemsIdentiques.stream().mapToInt(c -> {
            if (c >= 'a' && c <= 'z') {
                return c - 'a' + 1;
            } else if (c >= 'A' && c <= 'Z') {
                return c - 'A' + 27;
            } else {
                return 0;
            }
        }).sum();
    }

    private Character compareCompartiments(String compartiment1, String compartiment2) {
        List<Character> charComp1 = compartiment1.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> charComp2 = compartiment2.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        charComp1.retainAll(charComp2);
        return charComp1.get(0);
    }



}
