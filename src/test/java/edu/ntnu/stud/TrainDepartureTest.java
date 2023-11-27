package edu.ntnu.stud;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.model.TrainDeparture;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

public class TrainDepartureTest {
  TrainDeparture trainDeparture;

  @BeforeEach
  public void setUp() {
    trainDeparture = new TrainDeparture(LocalTime.of(13, 45), "L1", 123, "Trondheim", 1,
        Duration.ofMinutes(5));
  }

  @Nested
  @DisplayName("Negative tests")
  public class MethodThrowsException {
    @Test
    @DisplayName("Add negative delay throws IllegalArgumentException")
    public void testAddNegativeDelay() {
      assertThrows(IllegalArgumentException.class, () -> trainDeparture.addDelay(-10), "Delay cannot be negative");
    }

    @Test
    @DisplayName("Constructor should throw IllegalArgumentException from negative delay")
    public void testInitWithNegativeDelay() {
      assertThrows(IllegalArgumentException.class,
          () -> new TrainDeparture(LocalTime.of(13, 45), "L1", 123, "Trondheim", 1, Duration.ofMinutes(-5)),
          "Delay cannot be negative");
    }
  }

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
      assertEquals(LocalTime.of(13, 55), trainDeparture.getDelayedTime());
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
