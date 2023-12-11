package edu.ntnu.stud.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/** This class contains unit tests for the TrainDeparture class. */
public class TrainDepartureTest {
  TrainDeparture trainDeparture;

  @BeforeEach
  public void setUp() {
    trainDeparture =
        new TrainDeparture(LocalTime.of(13, 45), "L1", 123, "Trondheim", 1, Duration.ofMinutes(5));
  }

  /** This nested class contains negative tests for the TrainDeparture class. */
  @Nested
  @DisplayName("Negative tests")
  public class MethodThrowsException {
    @Test
    @DisplayName("Add negative delay throws IllegalArgumentException")
    public void testAddNegativeDelay() {
      assertThrows(
          IllegalArgumentException.class,
          () -> trainDeparture.addDelay(-10),
          "Delay cannot be negative");
    }

    @Test
    @DisplayName("Constructor should throw IllegalArgumentException from negative delay")
    public void testInitWithNegativeDelay() {
      assertThrows(
          IllegalArgumentException.class,
          () ->
              new TrainDeparture(
                  LocalTime.of(13, 45), "L1", 123, "Trondheim", 1, Duration.ofMinutes(-5)),
          "Delay cannot be negative");
    }

    @Test
    @DisplayName("Constructor should throw NullPointerException from null departure time")
    public void testConstructorWithNullDepartureTime() {
      assertThrows(
          NullPointerException.class,
          () -> new TrainDeparture(null, "L1", 123, "Destination", 1, Duration.ofMinutes(5)),
          "Departure time cannot be null");
    }

    @Test
    @DisplayName("Constructor should throw NullPointerException from null line")
    public void testConstructorWithNullLine() {
      assertThrows(
          NullPointerException.class,
          () ->
              new TrainDeparture(
                  LocalTime.of(13, 45), null, 123, "Destination", 1, Duration.ofMinutes(5)),
          "Line cannot be null");
    }

    @Test
    @DisplayName("Constructor should throw IllegalArgumentException from negative train number")
    public void testConstructorWithNegativeTrainNumber() {
      assertThrows(
          IllegalArgumentException.class,
          () ->
              new TrainDeparture(
                  LocalTime.of(13, 45), "L1", -1, "Destination", 1, Duration.ofMinutes(5)),
          "Train number cannot be negative");
    }

    @Test
    @DisplayName(
        "Constructor should throw IllegalArgumentException from train number greater than max")
    public void testConstructorWithTrainNumberGreaterThanMax() {
      assertThrows(
          IllegalArgumentException.class,
          () ->
              new TrainDeparture(
                  LocalTime.of(13, 45), "L1", 10000, "Destination", 1, Duration.ofMinutes(5)),
          "Train number cannot be greater than max");
    }

    @Test
    @DisplayName("Constructor should throw NullPointerException from null destination")
    public void testConstructorWithNullDestination() {
      assertThrows(
          NullPointerException.class,
          () -> new TrainDeparture(LocalTime.of(13, 45), "L1", 123, null, 1, Duration.ofMinutes(5)),
          "Destination cannot be null");
    }

    @Test
    @DisplayName("Constructor should throw IllegalArgumentException from negative track")
    public void testConstructorWithNegativeTrack() {
      assertThrows(
          IllegalArgumentException.class,
          () ->
              new TrainDeparture(
                  LocalTime.of(13, 45), "L1", 123, "Destination", -1, Duration.ofMinutes(5)),
          "Track cannot be negative");
    }
  }

  /** This nested class contains positive tests for the TrainDeparture class. */
  @Nested
  @DisplayName("Positive tests")
  public class MethodDoesNotThrowException {
    @Test
    @DisplayName("Adding valid delay does not throw exception")
    public void testAddDelay() {
      trainDeparture.addDelay(10);
      assertEquals(Duration.ofMinutes(15), trainDeparture.getDelay());
    }

    @Test
    @DisplayName("Delay gets added to departureTime on calling getDelayedTime")
    public void testGetDelayedTime() {
      trainDeparture.addDelay(5);
      assertEquals(LocalTime.of(13, 55), trainDeparture.getDepartureTimeWithDelay());
    }

    @Test
    @DisplayName("Train number getter return correct value")
    public void testGetTrainNumber() {
      assertEquals(123, trainDeparture.getTrainNumber());
    }

    @Test
    @DisplayName("Destination getter return correct value")
    public void testGetDestination() {
      assertEquals("Trondheim", trainDeparture.getDestination());
    }
  }
}
