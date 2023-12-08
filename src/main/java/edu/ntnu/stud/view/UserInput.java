package edu.ntnu.stud.view;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

/** The UserInput class provides utility methods for reading user input. */
public class UserInput {
  private static final Scanner scanner = new Scanner(System.in);

  private UserInput() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Retrieves the user's menu option selection.
   *
   * @param menu the menu object containing the available options
   * @return the selected menu option
   * @throws IllegalArgumentException if the menu is empty
   */
  public static int getMenuOption(Menu menu) {
    if (menu.isEmpty()) {
      throw new IllegalArgumentException("Menu cannot be empty");
    }

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

  /**
   * Reads a non-empty string from the user.
   *
   * @return The non-empty string entered by the user.
   */
  private static String readNonEmptyString() {
    while (true) {
      String input = scanner.nextLine();
      if (!input.isEmpty()) {
        return input;
      }
      System.out.println("Enter non-empty input");
    }
  }

  /**
   * Reads an integer value from the user input.
   *
   * @return the integer value entered by the user
   */
  private static int readInt() {
    while (true) {
      try {
        return Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Please enter an integer");
      }
    }
  }

  /**
   * Reads a time input from the user in the format (hh:mm) and returns it as a LocalTime object.
   *
   * @return the LocalTime object representing the user's input
   */
  public static LocalTime readTime() {
    while (true) {
      try {
        System.out.println("Enter departure time in format (hh:mm):");
        String timeString = readNonEmptyString();
        return LocalTime.parse(timeString);
      } catch (Exception e) {
        System.out.println("Please enter a valid time in the format (hh:mm)\n");
      }
    }
  }

  /**
   * Reads user input for delay in minutes and returns a Duration object representing the delay. If
   * the user enters an empty string, it returns a Duration of zero.
   *
   * @return the Duration object representing the delay in minutes
   */
  public static Duration readDelay() {
    while (true) {
      try {
        System.out.println("Enter delay in minutes (or empty for no delay):");
        String delayString = scanner.nextLine();

        if (delayString.isEmpty()) {
          return Duration.ZERO;
        }

        return Duration.ofMinutes(Long.parseLong(delayString));
      } catch (Exception e) {
        System.out.println("Please enter a valid delay in minutes\n");
      }
    }
  }

  /**
   * Reads and returns the train number entered by the user.
   *
   * @return the train number entered by the user
   */
  public static int readTrainNumber() {
    while (true) {
      System.out.println("Enter train number:");
      int trainNumber = readInt();

      if (trainNumber > 0) {
        return trainNumber;
      }

      System.out.println("Train number cannot be negative\n");
    }
  }

  /**
   * Reads and returns the user input for the track number.
   *
   * @return the track number entered by the user
   */
  public static int readTrack() {
    while (true) {
      System.out.println("Enter track:");
      int track = readInt();

      if (track > 0) {
        return track;
      }

      System.out.println("Track cannot be negative\n");
    }
  }

  /**
   * Reads a train-line of input from the user.
   *
   * @return the train-line of input as a String
   */
  public static String readLine() {
    while (true) {
      System.out.println("Enter line:");
      String line = readNonEmptyString();

      if (!line.isEmpty()) {
        return line;
      }

      System.out.println("Line cannot be empty\n");
    }
  }

  /**
   * Reads and returns the user input for the destination.
   *
   * @return the destination entered by the user as a String
   */
  public static String readDestination() {
    while (true) {
      System.out.println("Enter destination:");
      String destination = readNonEmptyString();

      if (!destination.isEmpty()) {
        return destination;
      }

      System.out.println("Destination cannot be empty\n");
    }
  }

  /**
   * Displays a confirmation dialog to the user and returns their response. The user is prompted to
   * enter "yes" or "no" and the method will keep asking until a valid response is given. If the
   * user enters "yes", the method returns true. If the user enters "no", the method returns false.
   *
   * @return true if the user confirms, false otherwise.
   */
  public static boolean confirmationDialog() {
    while (true) {
      System.out.println("Are you sure? (yes/no)");
      String input = readNonEmptyString().toLowerCase();

      if (input.charAt(0) == 'y') {
        return true;
      }

      if (input.charAt(0) == 'n') {
        return false;
      }

      System.out.println("Please enter yes or no\n");
    }
  }
}
