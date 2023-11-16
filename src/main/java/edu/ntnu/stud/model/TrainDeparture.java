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