package edu.ntnu.stud.view;

import edu.ntnu.stud.model.TrainDeparture;
import edu.ntnu.stud.model.TrainRegister;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * The UserInterface class represents the user interface of the Train Dispatch System. It provides
 * methods for handling and displaying menus, and interacting with the TrainRegister. It can update
 * time and depart trains. search, add, delete, change track, and add delay to train departures
 */
public class UserInterface {
  // Default options.
  private static final int EXIT_OPTION = 9;

  // Main menu options.
  private static final int DISPLAY_DEPARTURES_OPTION = 1;
  private static final int UPDATE_CLOCK_OPTION = 2;
  private static final int SEARCH_MENU_OPTION = 3;
  private static final int ADD_DEPARTURE_OPTION = 4;

  // Search menu options.
  private static final int SEARCH_BY_TRAIN_NUMBER_OPTION = 1;
  private static final int SEARCH_BY_DESTINATION_OPTION = 2;

  // Process trains menu options.
  private static final int DISPLAY_FOUND_TRAINS_OPTION = 1;
  private static final int DELETE_TRAIN_OPTION = 2;
  private static final int CHANGE_TRACK_OPTION = 3;
  private static final int ADD_DELAY_OPTION = 4;

  // Dependencies
  private final TrainRegister trainRegister;

  // Menus
  private final Menu mainMenu;
  private final Menu searchMenu;
  private final Menu processTrainsMenu;

  /**
   * Constructs a new UserInterface object and initlizes the train register and menus. Takes no
   * arguments.
   */
  public UserInterface() {
    this.trainRegister = new TrainRegister();

    this.mainMenu = new Menu("Main Menu");
    this.searchMenu = new Menu("Search Menu");
    this.processTrainsMenu = new Menu("Process Found Trains Menu");
  }

  /**
   * Initializes the user interface by adding options to the main menu, search menu, and process
   * trains menu.
   */
  public void init() {
    // Add options to main menu
    this.mainMenu.addOption(DISPLAY_DEPARTURES_OPTION, "Display train departures");
    this.mainMenu.addOption(UPDATE_CLOCK_OPTION, "Update clock");
    this.mainMenu.addOption(SEARCH_MENU_OPTION, "Search (delete, change track, add delay)");
    this.mainMenu.addOption(ADD_DEPARTURE_OPTION, "Add train departure");
    this.mainMenu.addOption(EXIT_OPTION, "Exit application");

    // Add options to search menu
    this.searchMenu.addOption(SEARCH_BY_TRAIN_NUMBER_OPTION, "Search by train number");
    this.searchMenu.addOption(SEARCH_BY_DESTINATION_OPTION, "Search by destination");
    this.searchMenu.addOption(EXIT_OPTION, "Exit to main menu");

    // Add options to process trains menu
    this.processTrainsMenu.addOption(DISPLAY_FOUND_TRAINS_OPTION, "Display found trains");
    this.processTrainsMenu.addOption(DELETE_TRAIN_OPTION, "Delete train");
    this.processTrainsMenu.addOption(CHANGE_TRACK_OPTION, "Change track");
    this.processTrainsMenu.addOption(ADD_DELAY_OPTION, "Add delay");
    this.processTrainsMenu.addOption(EXIT_OPTION, "Back to search menu");
  }

  /** Starts the user interface and initializes the train departures. */
  public void start() {
    // Additional train departures not added in order of departure time
    trainRegister.addTrainDeparture(
        new TrainDeparture(LocalTime.of(12, 00), "L2", 100, "Oslo", 2, Duration.ofMinutes(0)));
    trainRegister.addTrainDeparture(
        new TrainDeparture(LocalTime.of(15, 30), "L3", 150, "Bergen", 3, Duration.ofMinutes(10)));
    trainRegister.addTrainDeparture(
        new TrainDeparture(LocalTime.of(17, 15), "L4", 200, "Stavanger", 4, Duration.ofMinutes(2)));
    trainRegister.addTrainDeparture(
        new TrainDeparture(LocalTime.of(9, 00), "L5", 250, "Bodø", 5, Duration.ofMinutes(7)));

    // Example of a train with a longer delay
    trainRegister.addTrainDeparture(
        new TrainDeparture(LocalTime.of(18, 45), "L6", 300, "Tromsø", 6, Duration.ofMinutes(20)));

    // Example of an early morning train
    trainRegister.addTrainDeparture(
        new TrainDeparture(LocalTime.of(5, 30), "L7", 350, "Arendal", 7, Duration.ofMinutes(0)));

    // Example of a train to same destination as another train
    trainRegister.addTrainDeparture(
        new TrainDeparture(LocalTime.of(20, 00), "L8", 400, "Oslo", 8, Duration.ofMinutes(5)));

    // Sort by time
    trainRegister.sortByDelayedTime();

    handleMainMenu();
  }

  /**
   * Handles the main menu of the user interface. Allows the user to display train departures,
   * update clock and depart trains, open search menu, and add a new train departure.
   */
  private void handleMainMenu() {
    int option;
    do {
      option = UserInput.getMenuOption(mainMenu);
      switch (option) {
        case DISPLAY_DEPARTURES_OPTION:
          displayDepartures();
          break;
        case UPDATE_CLOCK_OPTION:
          handleUpdateClock();
          break;
        case SEARCH_MENU_OPTION:
          handleSearchMenu();
          break;
        case ADD_DEPARTURE_OPTION:
          handleAddDeparture();
          break;
        default:
          break;
      }
    } while (option != EXIT_OPTION);
  }

  /**
   * Handles the search menu functionality. Allows the user to search for train departures by train
   * number and destination.
   */
  private void handleSearchMenu() {
    int option;
    do {
      option = UserInput.getMenuOption(searchMenu);
      switch (option) {
        case SEARCH_BY_TRAIN_NUMBER_OPTION:
          handleSearchByTrainNumber();
          break;
        case SEARCH_BY_DESTINATION_OPTION:
          handleSearchByDestination();
          break;
        default:
          break;
      }
    } while (option != EXIT_OPTION);
  }

  /**
   * Handles the process trains menu by allowing the user to perform various operations on a list of
   * found train departures. The found train departures are passed as an argument by search methods.
   *
   * @param foundTrainDepartures The list of found train departures
   */
  private void handleProcessTrainsMenu(ArrayList<TrainDeparture> foundTrainDepartures) {
    int option;
    do {
      option = UserInput.getMenuOption(processTrainsMenu);
      switch (option) {
        case DISPLAY_FOUND_TRAINS_OPTION:
          displayTable(foundTrainDepartures);
          break;
        case DELETE_TRAIN_OPTION:
          handleDeleteTrains(foundTrainDepartures);
          break;
        case CHANGE_TRACK_OPTION:
          handleChangeTrack(foundTrainDepartures);
          break;
        case ADD_DELAY_OPTION:
          handleAddDelay(foundTrainDepartures);
          break;
        default:
          break;
      }
    } while (option != EXIT_OPTION && !foundTrainDepartures.isEmpty());
  }

  /**
   * Displays the departures of trains. If there are no trains registered, it prints "No trains
   * registered". Otherwise, it displays a table of departures sorted by delayed time.
   */
  private void displayDepartures() {
    if (trainRegister.getNumTrains() == 0) {
      System.out.println("No trains registered\n");
      return;
    }

    displayTable(trainRegister.sortByDelayedTime());
  }

  /**
   * Handles the update of the clock in the user interface. Prompts the user to enter a new time in
   * the format (hh:mm), updates the train register's clock with the new time, and displays the list
   * of departed trains if any.
   */
  private void handleUpdateClock() {
    System.out.println("Current time: " + trainRegister.getClock());
    LocalTime newTime = UserInput.readTime();
    trainRegister.setClock(newTime);
    ArrayList<TrainDeparture> departedTrains = trainRegister.departTrains();

    if (departedTrains.isEmpty()) {
      System.out.println("No trains departed");
    } else {
      System.out.println("Departed trains:");
      displayTable(departedTrains);
    }

    System.out.println();
  }

  /**
   * Handles the addition of a train departure. Reads user input for departure time, line, train
   * number, destination, track, and delay. Creates a new TrainDeparture object with the provided
   * information and adds it to the train register.
   */
  private void handleAddDeparture() {
    LocalTime departureTime = UserInput.readTime();
    String line = UserInput.readLine();
    int trainNumber = UserInput.readTrainNumber();
    String destination = UserInput.readDestination();
    int track = UserInput.readTrack();
    Duration delay = UserInput.readDelay();

    trainRegister.addTrainDeparture(
        new TrainDeparture(departureTime, line, trainNumber, destination, track, delay));
  }

  /**
   * Handles the search by train number functionality. Reads the train number from user input and
   * searches for the corresponding train departure in the train register. If the train departure is
   * found, it displays the train information and proceeds to the process trains menu. If the train
   * departure is not found, it prints a message indicating that the train number was not found.
   */
  private void handleSearchByTrainNumber() {
    int trainNumber = UserInput.readTrainNumber();
    TrainDeparture trainDeparture = trainRegister.findDepartureByTrainNumber(trainNumber);

    if (trainDeparture == null) {
      System.out.println("Train number not found\n");
      return;
    }

    System.out.println("Train found:");
    displayTable(trainDeparture.toArrayList());

    handleProcessTrainsMenu(trainDeparture.toArrayList());
  }

  /**
   * Handles the search for train departures by destination. Prompts the user to enter a destination
   * and retrieves a list of train departures to that destination from the train register. If no
   * train departures are found, a message is displayed. Otherwise, the list of train departures is
   * displayed and the user is prompted to process the trains further.
   */
  private void handleSearchByDestination() {
    String destination = UserInput.readDestination();
    ArrayList<TrainDeparture> trainDepartures =
        trainRegister.findDeparturesToDestination(destination);

    if (trainDepartures.isEmpty()) {
      System.out.println("No trains found\n");
      return;
    }

    System.out.println("Trains found:");
    displayTable(trainDepartures);

    handleProcessTrainsMenu(trainDepartures);
  }

  /**
   * Handles the deletion of trains. Prompts the user for confirmation before deleting the trains.
   * If the user confirms, the specified train departures are deleted from the train register. If
   * the user cancels, the trains are not deleted.
   *
   * @param foundTrainDepartures The list of train departures to be deleted.
   */
  private void handleDeleteTrains(ArrayList<TrainDeparture> foundTrainDepartures) {
    System.out.println("Are you sure you want to delete these trains?");
    boolean answer = UserInput.confirmationDialog();

    if (answer) {
      System.out.println("Deleted " + foundTrainDepartures.size() + " trains");
      trainRegister.deleteTrainDepartures(foundTrainDepartures);
      foundTrainDepartures.clear();
      return;
    }

    System.out.println("Trains not deleted");
  }

  /**
   * Handles the change of track for a list of train departures.
   *
   * @param foundTrainDepartures The list of train departures to be changed.
   */
  private void handleChangeTrack(ArrayList<TrainDeparture> foundTrainDepartures) {
    // Maybe not allow mulitple trains to change to the same track??
    int newTrack = UserInput.readTrack();
    trainRegister.changeTracks(foundTrainDepartures, newTrack);

    System.out.println("Changed " + foundTrainDepartures.size() + " trains to track " + newTrack);
  }

  /**
   * Handles the addition of delay to a list of train departures.
   *
   * @param foundTrainDepartures The list of train departures to add delay to.
   */
  private void handleAddDelay(ArrayList<TrainDeparture> foundTrainDepartures) {
    int minutes = (int) UserInput.readDelay().toMinutes();
    trainRegister.addDelay(foundTrainDepartures, minutes);

    System.out.println(
        "Added " + minutes + " minutes to " + foundTrainDepartures.size() + " trains");
  }

  /**
   * Formats the delayed departure time of a train with escape sequences if the departure is
   * delayed.
   *
   * @param trainDeparture the TrainDeparture object representing the train's departure information
   * @return the formatted departure time as a String
   */
  private String formatDelayedDepartureTime(TrainDeparture trainDeparture) {
    return trainDeparture.getDelay().isZero()
        ? trainDeparture.getDepartureTime().toString()
        : trainDeparture.getDepartureTimeWithDelay()
            + " \u001b[9m"
            + trainDeparture.getDepartureTime()
            + "\u001b[0m";
  }

  /**
   * Displays a table of train departures.
   *
   * @param trainDepartures the list of train departures to display
   */
  private void displayTable(ArrayList<TrainDeparture> trainDepartures) {
    String[][] table = new String[trainDepartures.size() + 1][6];
    table[0] = new String[] {"Departure", "Line", "Number", "Destination", "Track", "Delay"};

    for (int i = 0; i < trainDepartures.size(); i++) {
      TrainDeparture trainDeparture = trainDepartures.get(i);
      // Maybe more clean to use ArrayList?
      table[i + 1] =
          new String[] {
            formatDelayedDepartureTime(trainDeparture),
            trainDeparture.getLine(),
            Integer.toString(trainDeparture.getTrainNumber()),
            trainDeparture.getDestination(),
            Integer.toString(trainDeparture.getTrack()),
            (trainDeparture.getDelay().isZero()
                ? ""
                : trainDeparture.getDelay().toMinutes() + " min")
          };
    }

    System.out.println(TableFormatter.buildTable(table));
  }
}
