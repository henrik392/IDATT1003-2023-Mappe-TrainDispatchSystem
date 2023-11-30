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

  private String readString() {
    return scanner.nextLine();
  }

  private int readInt() {
    // Uses nextLine() instead of nextInt() to avoid problems with newlines in the
    // input stream
    while (true) {
      try {
        return Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Please enter an integer");
      }
    }
  }

  public LocalTime readTime() {
    while (true) {
      try {
        System.out.println("Enter departure time in format (hh:mm):");
        String timeString = scanner.nextLine();
        return LocalTime.parse(timeString);
      } catch (Exception e) {
        System.out.println("Please enter a valid time in the format (hh:mm)\n");
      }
    }
  }

  public Duration readDelay() {
    while (true) {
      try {
        System.out.println("Enter delay in minutes (or empty for no delay):");
        String delayString = scanner.nextLine();

        if (delayString.isEmpty())
          return Duration.ZERO;

        return Duration.ofMinutes(Long.parseLong(delayString));
      } catch (Exception e) {
        System.out.println("Please enter a valid delay in minutes\n");
      }
    }
  }

  public int readTrainNumber() {
    while (true) {
      System.out.println("Enter train number:");
      int trainNumber = readInt();

      if (trainNumber > 0)
        return trainNumber;

      System.out.println("Train number cannot be negative\n");
    }
  }

  public int readTrack() {
    while (true) {
      System.out.println("Enter track:");
      int track = readInt();

      if (track > 0)
        return track;

      System.out.println("Track cannot be negative\n");
    }
  }

  public String readLine() {
    while (true) {
      System.out.println("Enter line:");
      String line = readString();

      if (!line.isEmpty())
        return line;

      System.out.println("Line cannot be empty\n");
    }
  }

  public String readDestination() {
    while (true) {
      System.out.println("Enter destination:");
      String destination = readString();

      if (!destination.isEmpty())
        return destination;

      System.out.println("Destination cannot be empty\n");
    }
  }

  public void close() {
    scanner.close();
  }

  public boolean confirmationDialog() {
    while (true) {
      System.out.println("Are you sure? (yes/no)");
      String input = readString().toLowerCase();

      if (input.charAt(0) == 'y')
        return true;

      if (input.charAt(0) == 'n')
        return false;

      System.out.println("Please enter yes or no\n");
    }
  }
}
