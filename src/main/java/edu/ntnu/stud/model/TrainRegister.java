package edu.ntnu.stud.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class TrainRegister {
  private final ArrayList<TrainDeparture> trainDepartures;
  private final LocalTime clock;

  private final int MAX_TRAIN_NUMBER = 9999;

  public TrainRegister() {
    trainDepartures = new ArrayList<>();
    clock = LocalTime.of(0, 0);
  }

  private boolean trainNumberExists(TrainDeparture trainDeparture) {
    return trainDepartures.contains(trainDeparture);
  }

  public ArrayList<TrainDeparture> addToClockAndDepartTrains(int hours, int minutes) {
    LocalTime newTime = clock.plusHours(hours).plusMinutes(minutes);
    ArrayList<TrainDeparture> deletedDepartures = deleteDeparturesBeforeTime(newTime);
    return deletedDepartures;
  }

  public LocalTime getClock() {
    return clock;
  }

  public void addTrainDeparture(TrainDeparture trainDeparture) {
    if (trainNumberExists(trainDeparture))
      throw new IllegalArgumentException("Train number already exists");
    if (trainDeparture.getTrainNumber() > MAX_TRAIN_NUMBER)
      throw new IllegalArgumentException("Train number cannot be greater than " + MAX_TRAIN_NUMBER);

    trainDepartures.add(trainDeparture);
  }

  public ArrayList<TrainDeparture> getTrainDepartures() {
    return trainDepartures;
  }

  public TrainDeparture getTrainDepatureFromTrainNumber(int trainNumber) {
    return trainDepartures.stream().filter(trainDeparture -> trainDeparture.getTrainNumber() == trainNumber)
        .findFirst().orElse(null);
  }

  public ArrayList<TrainDeparture> getTrainDeparturesToDestination(String destination) {
    return trainDepartures.stream().filter(trainDeparture -> trainDeparture.getDestination().equals(destination))
        .collect(Collectors.toCollection(ArrayList::new));

    // Sort by departure time?
  }

  public ArrayList<TrainDeparture> deleteDeparturesBeforeTime(LocalTime time) {
    ArrayList<TrainDeparture> deletedDepartures = new ArrayList<>();
    Iterator<TrainDeparture> iterator = trainDepartures.iterator();
    while (iterator.hasNext()) {
      TrainDeparture trainDeparture = iterator.next();
      if (trainDeparture.getDelayedTime().isBefore(time)) {
        deletedDepartures.add(trainDeparture);
        iterator.remove();
      }
    }

    return deletedDepartures;
  }

  public ArrayList<TrainDeparture> sortByTime() {
    // Returned before a sorted copy, however the original order does not matter and
    // it is more effiecient to sort the trainDepartures itself when needed
    trainDepartures.sort((trainDeparture1, trainDeparture2) -> trainDeparture1.getDelayedTime()
        .compareTo(trainDeparture2.getDelayedTime()));

    return trainDepartures;
  }
}
