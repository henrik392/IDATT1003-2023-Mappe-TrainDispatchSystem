package edu.ntnu.stud.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * TrainRegister represents a register of train departures. It provides methods to add, delete, and
 * modify train departures, as well as retrieve information about the train departures.
 */
public class TrainRegister {
  private final ArrayList<TrainDeparture> trainDepartures;
  private LocalTime clock;

  public TrainRegister() {
    trainDepartures = new ArrayList<>();
    clock = LocalTime.of(0, 0);
  }

  public ArrayList<TrainDeparture> getTrainDepartures() {
    return trainDepartures;
  }

  public LocalTime getClock() {
    return clock;
  }

  /**
   * Sets the clock time of the train register.
   *
   * @param clock the new clock time to be set
   * @throws IllegalArgumentException if the new time is before the current time
   */
  public void setClock(LocalTime clock) {
    if (clock.isBefore(this.clock)) {
      throw new IllegalArgumentException("New time cannot be before current time");
    }

    this.clock = clock;
  }

  public int getNumTrains() {
    return trainDepartures.size();
  }

  /**
   * Departs trains from the train register. Deletes train departures that are scheduled before the
   * current time. Uses {@link #deleteDeparturesBeforeTime(LocalTime)}.
   *
   * @return the list of deleted train departures
   */
  public ArrayList<TrainDeparture> departTrains() {
    ArrayList<TrainDeparture> deletedDepartures = deleteDeparturesBeforeTime(clock);
    return deletedDepartures;
  }

  /**
   * Adds a train departure to the train register.
   *
   * @param trainDeparture the train departure to be added
   * @throws IllegalArgumentException if the train number already exists in the register
   */
  public void addTrainDeparture(TrainDeparture trainDeparture) {
    if (trainNumberExists(trainDeparture)) {
      throw new IllegalArgumentException("Train number already exists");
    }

    trainDepartures.add(trainDeparture);
  }

  /**
   * Retrieves the TrainDeparture object associated with the given train number.
   *
   * @param trainNumber the train number to search for
   * @return the TrainDeparture object with the given train number, or null if not found
   */
  public TrainDeparture findDepartureByTrainNumber(int trainNumber) {
    return trainDepartures.stream()
        .filter(trainDeparture -> trainDeparture.getTrainNumber() == trainNumber)
        .findFirst()
        .orElse(null);
  }

  /**
   * Retrieves a list of train departures that have the specified destination.
   *
   * @param destination The destination to filter train departures by.
   * @return An ArrayList of TrainDeparture objects that have the specified destination.
   */
  public ArrayList<TrainDeparture> findDeparturesToDestination(String destination) {
    return trainDepartures.stream()
        .filter(trainDeparture -> trainDeparture.getDestination().equals(destination))
        .collect(Collectors.toCollection(ArrayList::new));
  }

  /**
   * Deletes the specified train departures from the train register. Verifies arguments with {@link
   * #validateTrainDeparturesToModify(ArrayList)}.
   *
   * @param trainDepartures the list of train departures to be deleted
   * @throws IllegalArgumentException if the list is empty, contains null elements, or contains
   *     train departures that do not exist
   */
  public void deleteTrainDepartures(ArrayList<TrainDeparture> trainDepartures) {
    validateTrainDeparturesToModify(trainDepartures);

    this.trainDepartures.removeAll(trainDepartures);
  }

  /**
   * Changes the tracks for a list of train departures. Verifies arguments with {@link
   * #validateTrainDeparturesToModify(ArrayList)}.
   *
   * @param trainDepartures the list of train departures to modify
   * @param newTrack the new track number to set for the train departures
   * @throws IllegalArgumentException if the list is empty, contains null elements, or contains
   *     train departures that do not exist
   */
  public void changeTracks(ArrayList<TrainDeparture> trainDepartures, int newTrack) {
    validateTrainDeparturesToModify(trainDepartures);

    trainDepartures.forEach(trainDeparture -> trainDeparture.setTrack(newTrack));
  }

  /**
   * Adds a delay to the specified train departures. Verifies arguments with {@link
   * #validateTrainDeparturesToModify(ArrayList)}.
   *
   * @param trainDepartures the list of train departures to modify
   * @param minutes the number of minutes to add as a delay
   * @throws IllegalArgumentException if the list is empty, contains null elements, or contains
   *     train departures that do not exist
   */
  public void addDelay(ArrayList<TrainDeparture> trainDepartures, int minutes) {
    validateTrainDeparturesToModify(trainDepartures);

    trainDepartures.forEach(trainDeparture -> trainDeparture.addDelay(minutes));
  }

  /**
   * Sorts the train departures in the train register by departure time with delay. The original
   * order of train departures does not matter. Therefore, the method does not return a sorted copy,
   * but the train departures itself.
   *
   * @return A sorted ArrayList of TrainDeparture objects.
   */
  public ArrayList<TrainDeparture> sortByDelayedTime() {
    trainDepartures.sort(
        (trainDeparture1, trainDeparture2) ->
            trainDeparture1
                .getDepartureTimeWithDelay()
                .compareTo(trainDeparture2.getDepartureTimeWithDelay()));

    return trainDepartures;
  }

  /**
   * Validates the train departures to be modified.
   *
   * @param trainDepartures the list of train departures to be validated
   * @throws IllegalArgumentException if the list is empty, contains null elements, or contains
   *     train departures that do not exist
   */
  private void validateTrainDeparturesToModify(ArrayList<TrainDeparture> trainDepartures) {
    if (trainDepartures.isEmpty()) {
      throw new IllegalArgumentException("No train departures available.");
    }
    if (trainDepartures.stream().anyMatch(trainDeparture -> trainDeparture == null)) {
      throw new IllegalArgumentException("One or more train departures are null.");
    }
    if (!this.trainDepartures.containsAll(trainDepartures)) {
      throw new IllegalArgumentException("One or more train departures does not exist");
    }
  }

  /**
   * Checks if a train number already exists in the train departures list.
   *
   * @param trainDeparture the train departure to check
   * @return true if the train number exists, false otherwise
   */
  private boolean trainNumberExists(TrainDeparture trainDeparture) {
    return trainDepartures.contains(trainDeparture);
  }

  /**
   * Deletes all train departures that have a departure time before the specified time. Returns the
   * list of deleted train departures. Example use of an iterator.
   *
   * @param time the time to compare the departure time against
   * @return the list of deleted train departures
   */
  private ArrayList<TrainDeparture> deleteDeparturesBeforeTime(LocalTime time) {
    ArrayList<TrainDeparture> deletedDepartures = new ArrayList<>();
    Iterator<TrainDeparture> iterator = trainDepartures.iterator();
    while (iterator.hasNext()) {
      TrainDeparture trainDeparture = iterator.next();
      if (trainDeparture.getDepartureTimeWithDelay().isBefore(time)
          && (trainDeparture.getDepartureTimeWithDelay().isAfter(trainDeparture.getDepartureTime())
              || trainDeparture
                  .getDepartureTimeWithDelay()
                  .equals(trainDeparture.getDepartureTime()))) {
        deletedDepartures.add(trainDeparture);
        iterator.remove();
      }
    }

    return deletedDepartures;
  }
}
