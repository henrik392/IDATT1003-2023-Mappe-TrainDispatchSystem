package edu.ntnu.stud.model;

import java.time.LocalTime;
import java.time.Duration;

public class TrainDeparture {
  private LocalTime departureTime;
  private String line;
  private int trainNumber;
  private String destination;
  private int track;
  private Duration delay;

  // Exempted delay
  public TrainDeparture(LocalTime departureTime, String line, int trainNumber, String destination, int track,
      Duration delay) {
    this.departureTime = departureTime;
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
    this.delay = delay;
  }

  /**
   * Adds the specified number of minutes to the delay of the train.
   * 
   * @param minutes the number of minutes to add to the delay
   */
  public void addDelay(int minutes) {
    if (minutes < 0)
      throw new IllegalArgumentException("Delay cannot be negative");

    delay = delay.plusMinutes(minutes);
  }

  public Duration getDelay() {
    return delay;
  }
  public LocalTime getDelayedTime() {
    return departureTime.plus(delay);
  }

  @Override
  public String toString() {
    return "TrainDeparture [departureTime=" + departureTime + ", line=" + line + ", trainNumber=" + trainNumber
        + ", destination=" + destination + ", track=" + track + ", delay=" + delay + "]" + "\nDelayed time: "
        + getDelayedTime();
  }

  public int getTrainNumber() {
    return trainNumber;
  }

  public String getDestination() {
    return destination;
  }

}