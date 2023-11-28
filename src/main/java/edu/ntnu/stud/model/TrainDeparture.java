package edu.ntnu.stud.model;

import java.time.LocalTime;
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
    this.departureTime = departureTime;
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;

    if (delay.isNegative())
      throw new IllegalArgumentException("Delay cannot be negative");

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

  /**
   * Returns a string representation of the TrainDeparture object.
   * 
   * @return a string representation of the TrainDeparture object
   */
  @Override
  public String toString() {
    return ((delay.isZero()) ? departureTime + "\t" : getDelayedTime() + " \u001b[9m" + departureTime + "\u001b[0m")
        + "\t\t" + line + "\t\t" + trainNumber
        + "\t\t" + destination + (destination.length() > 8 ? "" : "\t") + "\t" + track;

    // TODO: TO be replaced by TableBuilder
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

}