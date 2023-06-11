package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Day3 day3 = new Day3();
        System.out.println("Day3");
        List<String> backpacks = Day3.getInputByLine("inputDay3.txt");
        System.out.println("La première somme recherchée est: "  + day3.resolvePuzzlePart1(backpacks));
        System.out.println("La seconde somme recherchée est: "+day3.resolvePuzzlePart2(backpacks) + "\n");

    }
}

