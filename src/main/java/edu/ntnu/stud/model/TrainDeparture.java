package edu.ntnu.stud.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.time.Duration;

/**
 * Represents a train departure with departure time, line, train number,
 * destination, track, and delay.
 */
public class TrainDeparture {
  private LocalTime departureTime;
  private String line;
  private int trainNumber;
  private String destination;
  private int track;
  private Duration delay;

  /**
   * Constructs a TrainDeparture object with the specified departure time, line,
   * train number, destination, track, and delay.
   * 
   * @param departureTime the departure time of the train
   * @param line          the line of the train
   * @param trainNumber   the train number
   * @param destination   the destination of the train
   * @param track         the track number
   * @param delay         the delay of the train
   */
  public TrainDeparture(LocalTime departureTime, String line, int trainNumber, String destination, int track,
      Duration delay) {
    if (departureTime == null)
      throw new IllegalArgumentException("Departure time cannot be null");
    if (line == null)
      throw new IllegalArgumentException("Line cannot be null");
    if (trainNumber < 0)
      throw new IllegalArgumentException("Train number cannot be negative");
    if (destination == null)
      throw new IllegalArgumentException("Destination cannot be null");
    if (track < 0)
      throw new IllegalArgumentException("Track cannot be negative");
    if (delay.isNegative())
      throw new IllegalArgumentException("Delay cannot be negative");

    this.departureTime = departureTime;
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;

    this.delay = delay == null ? Duration.ZERO : delay;
  }

  public ArrayList<TrainDeparture> toArrayList() {
    ArrayList<TrainDeparture> list = new ArrayList<>();
    list.add(this);
    return list;
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

  /**
   * Returns the delayed time of the train.
   * 
   * @return the delayed time of the train
   */
  public LocalTime getDelayedTime() {
    return departureTime.plus(delay);
  }

  /**
   * Returns the train number.
   * 
   * @return the train number
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * Returns the destination of the train.
   * 
   * @return the destination of the train
   */
  public String getDestination() {
    return destination;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;

    TrainDeparture other = (TrainDeparture) obj;
    return trainNumber == other.trainNumber;
  }

  public int getPlatform() {
    return track;
  }

  public LocalTime getDepartureTime() {
    return departureTime;
  }

  public String getLine() {
    return line;
  }

  public int getTrack() {
    return track;
  }

  // Set track
  public void setTrack(int track) {
    if (track < 0)
      throw new IllegalArgumentException("Track cannot be negative");

    this.track = track; 
  }
}