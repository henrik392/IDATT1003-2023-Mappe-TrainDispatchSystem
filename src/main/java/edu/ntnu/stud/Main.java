package edu.ntnu.stud;

import edu.ntnu.stud.view.UserInterface;

/**
 * The Main class contains the main method that starts the program.
 */
public class Main {
  public static void main(String[] args) {
    UserInterface ui = new UserInterface();
    // Initialize dummy data
    ui.init();

    // Start the program
    ui.start();
  }
}
