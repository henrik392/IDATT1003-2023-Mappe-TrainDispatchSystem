package edu.ntnu.stud.view;

import java.util.Scanner;

public class UserInput {
  private final Scanner scanner;

  public UserInput() {
    this.scanner = new Scanner(System.in);
  }

  public int getMenuOption(Menu menu) {
    if (menu.isEmpty())
      throw new IllegalArgumentException("Menu cannot be empty");

    while (true) {
      System.out.println(menu);
      int option = readInt();

      if (menu.hasOption(option)) {
        return option;
      } else {
        System.out.println("Please enter a valid option number");
      }
    }
  }

  public String readString() {
    return scanner.nextLine();
  }

  public int readInt() {
    // Uses nextLine() instead of nextInt() to avoid problems with newlines in the
    // input stream
    while (true) {
      try {
        return Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Please enter a number");
      }
    }
  }
}
