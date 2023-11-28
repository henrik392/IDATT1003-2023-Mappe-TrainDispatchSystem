package edu.ntnu.stud.view;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

import edu.ntnu.stud.model.TrainDeparture;
import edu.ntnu.stud.model.TrainRegister;

public class UserInterface {
  // Default options.
  private final int EXIT_OPTION = 9;

  // Main menu options.
  private final int DISPLAY_DEPARTURES_OPTION = 1;
  private final int UPDATE_CLOCK_OPTION = 2;
  private final int SEARCH_MENU_OPTION = 3;
  private final int ADD_DEPARTURE_OPTION = 4;

  // Search menu options.
  private final int SEARCH_BY_TRAIN_NUMBER_OPTION = 1;
  private final int SEARCH_BY_DESTINATION_OPTION = 2;

  // Dependencies
  private final TrainRegister trainRegister;
  private final UserInput userInput;

  // Menus
  private final Menu mainMenu;
  private final Menu searchMenu;

  public UserInterface() {
    this.trainRegister = new TrainRegister();
    this.userInput = new UserInput();

    this.mainMenu = new Menu("Main Menu");
    this.mainMenu.addOption(DISPLAY_DEPARTURES_OPTION, "Display train departures");
    this.mainMenu.addOption(UPDATE_CLOCK_OPTION, "Update clock");
    this.mainMenu.addOption(SEARCH_MENU_OPTION, "Search (delete, change track, add delay)");
    this.mainMenu.addOption(ADD_DEPARTURE_OPTION, "Add train departure");
    this.mainMenu.addOption(EXIT_OPTION, "Exit application");

    this.searchMenu = new Menu("Search Menu");
    this.searchMenu.addOption(SEARCH_BY_TRAIN_NUMBER_OPTION, "Search by train number");
    this.searchMenu.addOption(SEARCH_BY_DESTINATION_OPTION, "Search by destination");
    this.searchMenu.addOption(EXIT_OPTION, "Exit to main menu");
  }

  private void handleMainMenu() {
    int option;
    do {
      option = userInput.getMenuOption(mainMenu);
      switch (option) {
        case DISPLAY_DEPARTURES_OPTION:
          displayDepartures();
          break;
        case UPDATE_CLOCK_OPTION:
          break;
        case SEARCH_MENU_OPTION:
          handleSearchMenu();
          break;
        case ADD_DEPARTURE_OPTION:
          addDeparture();
          break;
      }
    } while (option != EXIT_OPTION);
  }

  private void addDeparture() {
    System.out.println("Enter departure time in format (hh:mm):");
    LocalTime departureTime = userInput.readTime();
    System.out.println("Enter line:");
    String line = userInput.readString();
    System.out.println("Enter train number:");
    int trainNumber = userInput.readInt();
    System.out.println("Enter destination:");
    String destination = userInput.readString();
    System.out.println("Enter track:");
    int track = userInput.readInt();
    System.out.println("Enter delay in minutes (or empty for no delay):");
    Duration delay = userInput.readDelay();

    trainRegister.addTrainDeparture(new TrainDeparture(departureTime, line, trainNumber, destination, track, delay));
  }

  private void displayDepartures() {
    displayTable(trainRegister.sortByTime());
  }

  private void handleSearchMenu() {
    int option;
    do {
      option = userInput.getMenuOption(searchMenu);
      switch (option) {
        case SEARCH_BY_TRAIN_NUMBER_OPTION:
          break;
        case SEARCH_BY_DESTINATION_OPTION:
          break;
      }
    } while (option != EXIT_OPTION);
  }

  private String formatDelayedDepartureTime(TrainDeparture trainDeparture) {
    return trainDeparture.getDelay().isZero() ? trainDeparture.getDepartureTime().toString()
        : trainDeparture.getDelayedTime() + " \u001b[9m" + trainDeparture.getDepartureTime() + "\u001b[0m";
  }

  private void displayTable(ArrayList<TrainDeparture> trainDepartures) {
    String[][] table = new String[trainDepartures.size() + 1][6];
    table[0] = new String[] { "Departure", "Line", "Number", "Destination", "Track", "Delay" };

    for (int i = 0; i < trainDepartures.size(); i++) {
      TrainDeparture trainDeparture = trainDepartures.get(i);
      // Maybe more clean to use ArrayList?
      table[i + 1] = new String[] {
          formatDelayedDepartureTime(trainDeparture),
          trainDeparture.getLine(),
          Integer.toString(trainDeparture.getTrainNumber()),
          trainDeparture.getDestination(),
          Integer.toString(trainDeparture.getTrack()),
          (trainDeparture.getDelay().isZero() ? "" : trainDeparture.getDelay().toMinutes() + " min")
      };
    }

    System.out.println(TableBuilder.buildTable(table));
  }

  public void init() {
    // Additional train departures not added in order of departure time
    trainRegister
        .addTrainDeparture(new TrainDeparture(LocalTime.of(12, 00), "L2", 100, "Oslo", 2, Duration.ofMinutes(0)));
    trainRegister
        .addTrainDeparture(new TrainDeparture(LocalTime.of(15, 30), "L3", 150, "Bergen", 3, Duration.ofMinutes(10)));
    trainRegister
        .addTrainDeparture(new TrainDeparture(LocalTime.of(17, 15), "L4", 200, "Stavanger", 4, Duration.ofMinutes(2)));
    trainRegister
        .addTrainDeparture(new TrainDeparture(LocalTime.of(9, 00), "L5", 250, "Bodø", 5, Duration.ofMinutes(7)));

    // Example of a train with a longer delay
    trainRegister
        .addTrainDeparture(new TrainDeparture(LocalTime.of(18, 45), "L6", 300, "Tromsø", 6, Duration.ofMinutes(20)));

    // Example of an early morning train
    trainRegister
        .addTrainDeparture(new TrainDeparture(LocalTime.of(5, 30), "L7", 350, "Arendal", 7, Duration.ofMinutes(0)));
  }

  public void start() {
    handleMainMenu();
  }
}
