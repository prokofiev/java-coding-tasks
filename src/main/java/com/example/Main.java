package com.example;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter dimensions of the first box (A1 B1 C1):");
    int a1 = scanner.nextInt();
    int b1 = scanner.nextInt();
    int c1 = scanner.nextInt();

    System.out.println("Enter dimensions of the second box (A2 B2 C2):");
    int a2 = scanner.nextInt();
    int b2 = scanner.nextInt();
    int c2 = scanner.nextInt();

    BoxComparisonResult result = BoxComparator.compareBoxes(a1, b1, c1, a2, b2, c2);
    System.out.println(result.getMessage());

    scanner.close();
  }
}