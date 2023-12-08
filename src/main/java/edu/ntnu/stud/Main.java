package edu.ntnu.stud;

import edu.ntnu.stud.view.UserInterface;

/** The Main class contains the main method that starts the program. */
public class Main {
  /**
   * The main method is the entry point of the program. It creates an instance of the UserInterface
   * class, initializes dummy data, and starts the program by calling the start method of the
   * UserInterface.
   *
   * @param args the command line arguments, should be none
   */
  public static void main(String[] args) {
    UserInterface ui = new UserInterface();
    // Initialize dummy data
    ui.init();

    // Start the program
    ui.start();
  }
}
