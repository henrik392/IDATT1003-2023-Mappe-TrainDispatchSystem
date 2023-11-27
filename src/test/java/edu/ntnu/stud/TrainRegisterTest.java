package edu.ntnu.stud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import edu.ntnu.stud.model.TrainDeparture;
import edu.ntnu.stud.model.TrainRegister;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TrainRegisterTest {
  private TrainRegister trainRegister;
  private TrainDeparture trainDeparture;

  @BeforeEach
  public void setUp() {
    trainRegister = new TrainRegister();

    // Existing train departure
    trainDeparture = new TrainDeparture(LocalTime.of(13, 45), "L1", 123, "Trondheim", 1, Duration.ofMinutes(5));
    trainRegister.addTrainDeparture(trainDeparture);

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

  @Nested
  @DisplayName("Negative tests")
  public class MethodThrowsException {
    @Test
    @DisplayName("Adding train departure with same train number throws exception")
    public void testAddTrainDepartureWithSameTrainNumber() {
      assertThrows(IllegalArgumentException.class, () -> trainRegister.addTrainDeparture(trainDeparture),
          "Train number already exists");
    }

    // @Test
    // @DisplayName("")
  }

  @Nested
  @DisplayName("Positive tests")
  public class MethodDoesNotThrowException {
    @Test
    @DisplayName("Adding valid train departure does not throw exception")
    public void testAddTrainDeparture() {
      TrainDeparture newTrainDeparture = new TrainDeparture(LocalTime.of(8, 15), "E6", 1, "Askoy", 1,
          Duration.ofMinutes(20));
      trainRegister.addTrainDeparture(newTrainDeparture);

      assertTrue(trainRegister.getTrainDepartures().contains(newTrainDeparture));
    }

    @Test
    @DisplayName("getTrainDepartures() returns a list of all train departures")
    public void testGetTrainDepartures() {
      ArrayList<TrainDeparture> trainDepartures = trainRegister.getTrainDepartures();
      assertTrue(trainDepartures.contains(trainDeparture));
    }

    @Test
    @DisplayName("getTrainDepartureFromTrainNumber() returns the correct train departure")
    public void testGetTrainDepartureFromTrainNumber() {
      TrainDeparture foundTrainDeparture = trainRegister.getTrainDepatureFromTrainNumber(123);
      assertEquals(trainDeparture, foundTrainDeparture);
    }

    @Test
    @DisplayName("getTrainDepartureFromTrainNumber() returns null when no train departure with the specified train number exists")
    public void testGetTrainDepartureFromTrainNumberReturnsNull() {
      TrainDeparture foundTrainDeparture = trainRegister.getTrainDepatureFromTrainNumber(404);
      assertNull(foundTrainDeparture);
    }

    @Test
    @DisplayName("getTrainDeparturesFromDestination() returns a list of all departures to the specified destination")
    public void testGetTrainDeparturesFromDestination() {
      ArrayList<TrainDeparture> trainDeparturesFromDestination = trainRegister
          .getTrainDeparturesToDestination("Trondheim");
      assertTrue(trainDeparturesFromDestination.contains(trainDeparture));
    }

    @Test
    @DisplayName("deleteDeparturesBeforeTime() deletes all departures before the specified time and returns a list of the deleted departures")
    public void testDeleteDeparturesBeforeTime() {
      int prevSize = trainRegister.getTrainDepartures().size();
      ArrayList<TrainDeparture> deletedDepartures = trainRegister.deleteDeparturesBeforeTime(LocalTime.of(14, 00));
      assertTrue(deletedDepartures.contains(trainDeparture));
      assertFalse(trainRegister.getTrainDepartures().contains(trainDeparture));
      assertTrue(prevSize == trainRegister.getTrainDepartures().size() + deletedDepartures.size());
    }

    @Test
    @DisplayName("sortedByTime() returns an ArrayList og the train departures sorted by getDelayedTime()")
    public void testSortedByTime() {
      ArrayList<TrainDeparture> sortedTrainDepartures = trainRegister.sortedByTime();
      assertTrue(trainRegister.getTrainDepartures().containsAll(sortedTrainDepartures));

      LocalTime previousTime = null;
      for (TrainDeparture trainDeparture : sortedTrainDepartures) {
        if (previousTime != null) {
          assertTrue(trainDeparture.getDelayedTime().isAfter(previousTime)
              || trainDeparture.getDelayedTime().equals(previousTime));
        }

        previousTime = trainDeparture.getDelayedTime();
      }
    }
  }
}