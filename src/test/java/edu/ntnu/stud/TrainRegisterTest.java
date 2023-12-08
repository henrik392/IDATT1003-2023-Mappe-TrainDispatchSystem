package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.stud.model.TrainDeparture;
import edu.ntnu.stud.model.TrainRegister;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * This class is a test class for the TrainRegister class. It contains positive and negative test
 * methods to verify the functionality of the TrainRegister class.
 */
public class TrainRegisterTest {
  private TrainRegister trainRegister;
  private TrainDeparture trainDeparture;
  private TrainDeparture lateTrainDeparture;

  /** Sets up the test environment before each test case. */
  @BeforeEach
  public void setUp() {
    trainRegister = new TrainRegister();

    // Existing train departure
    trainDeparture =
        new TrainDeparture(LocalTime.of(13, 45), "L1", 123, "Trondheim", 1, Duration.ofMinutes(5));
    trainRegister.addTrainDeparture(trainDeparture);

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

    // Train departure leaving over midnight because of delay
    lateTrainDeparture =
        new TrainDeparture(LocalTime.of(23, 0), "L8", 400, "Oslo", 8, Duration.ofMinutes(60));
    trainRegister.addTrainDeparture(lateTrainDeparture);
  }

  /** This nested class contains negative tests for the TrainRegister class. */
  @Nested
  @DisplayName("Negative tests")
  public class MethodThrowsException {
    @Test
    @DisplayName("Adding train departure with same train number throws exception")
    public void testAddTrainDepartureWithSameTrainNumber() {
      assertThrows(
          IllegalArgumentException.class,
          () -> trainRegister.addTrainDeparture(trainDeparture),
          "Train number already exists");
    }

    @Test
    @DisplayName("Adding delay to an empty list of train departures throws exception")
    public void testAddDelayToEmptyList() {
      ArrayList<TrainDeparture> emptyList = new ArrayList<>();
      int minutes = 10;

      assertThrows(
          IllegalArgumentException.class,
          () -> trainRegister.addDelay(emptyList, minutes),
          "One or more train departures does not exist");
    }
  }

  /**
   * This nested class contains positive test methods that verify that the train register methods do
   * not throw exceptions.
   */
  @Nested
  @DisplayName("Positive tests")
  public class MethodDoesNotThrowException {
    @Test
    @DisplayName("Adding valid train departure does not throw exception")
    public void testAddTrainDeparture() {
      TrainDeparture newTrainDeparture =
          new TrainDeparture(LocalTime.of(8, 15), "E6", 1, "Askoy", 1, Duration.ofMinutes(20));
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
      TrainDeparture foundTrainDeparture = trainRegister.findDepartureByTrainNumber(123);
      assertEquals(trainDeparture, foundTrainDeparture);
    }

    @Test
    @DisplayName(
        "getTrainDepartureFromTrainNumber() returns null when no train departure with the specified"
            + " train number exists")
    public void testGetTrainDepartureFromTrainNumberReturnsNull() {
      TrainDeparture foundTrainDeparture = trainRegister.findDepartureByTrainNumber(404);
      assertNull(foundTrainDeparture);
    }

    @Test
    @DisplayName(
        "getTrainDeparturesFromDestination() returns a list of all departures to the specified"
            + " destination")
    public void testGetTrainDeparturesFromDestination() {
      ArrayList<TrainDeparture> trainDeparturesFromDestination =
          trainRegister.findDeparturesToDestination("Trondheim");
      assertTrue(trainDeparturesFromDestination.contains(trainDeparture));
    }

    @Test
    @DisplayName(
        "Deletes all departures before the specified time and returns a list of the deleted"
            + " departures")
    public void testDepartTrains() {
      trainRegister.setClock(LocalTime.of(14, 0));

      int prevSize = trainRegister.getTrainDepartures().size();
      ArrayList<TrainDeparture> deletedDepartures = trainRegister.departTrains();
      assertTrue(deletedDepartures.contains(trainDeparture));
      assertFalse(trainRegister.getTrainDepartures().contains(trainDeparture));
      assertTrue(prevSize == trainRegister.getTrainDepartures().size() + deletedDepartures.size());
    }

    @Test
    @DisplayName("Trains departing over midnight are not deleted")
    public void testDepartTrainsOverMidnight() {
      trainRegister.setClock(LocalTime.of(23, 59));
      trainRegister.departTrains();

      assertTrue(trainRegister.getTrainDepartures().contains(lateTrainDeparture));
    }

    @Test
    @DisplayName("returns an ArrayList of the train departures sorted by getDelayedTime()")
    public void testSortedByTime() {
      ArrayList<TrainDeparture> sortedTrainDepartures = trainRegister.sortByDelayedTime();
      assertTrue(trainRegister.getTrainDepartures().containsAll(sortedTrainDepartures));

      LocalTime previousTime = null;
      for (TrainDeparture trainDeparture : sortedTrainDepartures) {
        if (previousTime != null) {
          assertTrue(
              trainDeparture.getDepartureTimeWithDelay().isAfter(previousTime)
                  || trainDeparture.getDepartureTimeWithDelay().equals(previousTime));
        }

        previousTime = trainDeparture.getDepartureTimeWithDelay();
      }
    }
  }
}