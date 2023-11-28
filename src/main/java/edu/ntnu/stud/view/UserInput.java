package edu.ntnu.stud.view;

import java.time.Duration;
import java.time.LocalTime;
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

  public LocalTime readTime() {
    while (true) {
      try {
        String timeString = scanner.nextLine();
        return LocalTime.parse(timeString);
      } catch (Exception e) {
        System.out.println("Please enter a valid time in the format (hh:mm)");
      }
    }
  }

  public Duration readDelay() {
    while (true) {
      try {
        String delayString = scanner.nextLine();

        if (delayString.isEmpty())
          return Duration.ZERO;

        return Duration.parse("PT" + delayString.toUpperCase());
      } catch (Exception e) {
        System.out.println("Please enter a valid delay in the format (hh:mm) or empty string");
      }
    }
  }
}
