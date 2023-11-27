package edu.ntnu.stud.model;

import java.time.LocalTime;
import java.util.ArrayList;

public class TrainRegister {
  private final ArrayList<TrainDeparture> trainDepartures;

  public TrainRegister() {
    trainDepartures = new ArrayList<>();
  }

  private boolean trainNumberExists(TrainDeparture trainDeparture) {
    return trainDepartures.contains(trainDeparture);
  }

  public void addTrainDeparture(TrainDeparture trainDeparture) {
    if (trainNumberExists(trainDeparture)) {
      throw new IllegalArgumentException("Train number already exists");
    }

    trainDepartures.add(trainDeparture);
  }

  public ArrayList<TrainDeparture> getTrainDepartures() {
    return trainDepartures;
  }

  public TrainDeparture getTrainDepatureFromTrainNumber(int trainNumber) {
    for (TrainDeparture trainDeparture : trainDepartures) {
      if (trainDeparture.getTrainNumber() == trainNumber) {
        return trainDeparture;
      }
    }

    throw new IllegalArgumentException("Train number " + trainNumber + " not found");
  }

  public ArrayList<TrainDeparture> getTrainDeparturesFromDestination(String destination) {
    ArrayList<TrainDeparture> trainDeparturesFromDestination = new ArrayList<>();
    for (TrainDeparture trainDeparture : trainDepartures) {
      if (trainDeparture.getDestination().equals(destination)) {
        trainDeparturesFromDestination.add(trainDeparture);
      }
    }

    return trainDeparturesFromDestination;
    // Burde jeg throwe en exception her om det ikke er noen avganger?
  }

  public ArrayList<TrainDeparture> deleteDeparturesBeforeTime(LocalTime time) {
    ArrayList<TrainDeparture> deletedDepartures = new ArrayList<>();
    for (TrainDeparture trainDeparture : trainDepartures) {
      if (trainDeparture.getDelayedTime().isBefore(time)) {
        deletedDepartures.add(trainDeparture);
      }
    }

    trainDepartures.removeAll(deletedDepartures);
    return deletedDepartures;
    // Could use streams
  }

  public ArrayList<TrainDeparture> sortedByTime() {
    ArrayList<TrainDeparture> sortedTrainDepartures = new ArrayList<>(trainDepartures);
    sortedTrainDepartures.sort((trainDeparture1, trainDeparture2) -> trainDeparture1.getDelayedTime()
        .compareTo(trainDeparture2.getDelayedTime()));

    return sortedTrainDepartures;
  }
}
