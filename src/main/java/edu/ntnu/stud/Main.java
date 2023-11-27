package edu.ntnu.stud;

import edu.ntnu.stud.model.TrainDeparture;
import edu.ntnu.stud.model.TrainRegister;

import java.time.LocalTime;
import java.time.Duration;

/**
 * The Main class contains the main method that starts the program.
 */
public class Main {
  public static void main(String[] args) {
    System.err.println("\n\n\n");

    TrainDeparture trainDeparture = new TrainDeparture(LocalTime.of(12, 15), "R2", 1234, "Trondheim", 3,
        Duration.ofMinutes(0));

    trainDeparture.addDelay(5);

    TrainRegister trainRegister = new TrainRegister();
    trainRegister.addTrainDeparture(trainDeparture);
    trainRegister.addTrainDeparture(new TrainDeparture(LocalTime.of(14, 30), "L2", 456, "Oslo", 2,
        Duration.ofMinutes(10)));

    System.out.println(trainRegister.getTrainDepatureFromTrainNumber(456));
    System.out.println(trainRegister.getTrainDeparturesToDestination("Trondheim"));

    System.out.println(trainRegister.sortedByTime());

    System.err.println("\n\n");

    System.out.println(trainRegister.deleteDeparturesBeforeTime(LocalTime.of(13, 0)));
  }
}
