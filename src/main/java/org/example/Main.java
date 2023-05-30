package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Persistance data = new Persistance();
        Day3 day3 = new Day3();
        System.out.println("Day3");
        List<String> Backpacks = data.getInputByLine("src\\main\\java\\org\\example\\inputDay3.txt");
        System.out.println(day3.resolvePuzzlePart1(Backpacks));
        System.out.println(day3.resolvePuzzlePart2(Backpacks) + "\n");

    }
}

