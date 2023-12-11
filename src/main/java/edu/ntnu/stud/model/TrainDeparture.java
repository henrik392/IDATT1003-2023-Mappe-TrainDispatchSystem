package edu.ntnu.stud.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 *
 * <h2>TrainDeparture</h2>
 *
 * The TrainDeparture class represents a train departure with its departure time, line, train
 * number, destination, track, and delay.
 */
public class TrainDeparture implements Comparable<TrainDeparture> {
  private LocalTime departureTime;
  private String line;
  private int trainNumber;
  private String destination;
  private int track;
  private Duration delay;

  private static final int MAX_TRAIN_NUMBER = 9999;

  /**
   * Adds a new item to the item register.
   *
   * @param departureTime the departure time of the train
   * @param line the line of the train
   * @param trainNumber the train number
   * @param destination the destination of the train
   * @param track the track of the train
   * @param delay the delay of the train
   * @throws IllegalArgumentException if any of the arguments are invalid
   * @throws NullPointerException if any of the arguments are null
   */
  public TrainDeparture(
      LocalTime departureTime,
      String line,
      int trainNumber,
      String destination,
      int track,
      Duration delay) {
    setDepartureTime(departureTime);
    setLine(line);
    setTrainNumber(trainNumber);
    setDestination(destination);
    setTrack(track);
    setDelay(delay);
  }

  public LocalTime getDepartureTime() {
    return departureTime;
  }

  public LocalTime getDepartureTimeWithDelay() {
    return departureTime.plus(delay);
  }

  /**
   * Sets the departure time of the train.
   *
   * @param departureTime the departure time to be set
   * @throws IllegalArgumentException if the departure time is null
   */
  private void setDepartureTime(LocalTime departureTime) {
    if (departureTime == null) {
      throw new NullPointerException("Departure time cannot be null");
    }
    this.departureTime = departureTime;
  }

  public String getLine() {
    return line;
  }

  /**
   * Sets the line of the train departure.
   *
   * @param line the line of the train departure
   * @throws IllegalArgumentException if the line is null
   */
  public void setLine(String line) {
    if (line == null) {
      throw new NullPointerException("Line cannot be null");
    }
    this.line = line;
  }

  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * Sets the train number for this TrainDeparture.
   *
   * @param trainNumber the train number to be set
   * @throws IllegalArgumentException if the train number is negative or greater than the maximum
   *     train number
   */
  private void setTrainNumber(int trainNumber) {
    if (trainNumber < 0) {
      throw new IllegalArgumentException("Train number cannot be negative");
    }
    if (trainNumber > MAX_TRAIN_NUMBER) {
      throw new IllegalArgumentException("Train number cannot be greater than " + MAX_TRAIN_NUMBER);
    }
    this.trainNumber = trainNumber;
  }

  public String getDestination() {
    return destination;
  }

  /**
   * Sets the destination of the train departure.
   *
   * @param destination the destination of the train departure
   * @throws IllegalArgumentException if the destination is null
   */
  private void setDestination(String destination) {
    if (destination == null) {
      throw new NullPointerException("Destination cannot be null");
    }
    this.destination = destination;
  }

  public int getTrack() {
    return track;
  }

  /**
   * Sets the track number for the train departure.
   *
   * @param track the track number to be set
   * @throws IllegalArgumentException if the track number is negative
   */
  public void setTrack(int track) {
    if (track < 0) {
      throw new IllegalArgumentException("Track cannot be negative");
    }
    this.track = track;
  }

  public Duration getDelay() {
    return delay;
  }

  /**
   * Sets the delay for the train departure.
   *
   * @param delay the delay duration
   * @throws IllegalArgumentException if the delay is negative
   */
  public void setDelay(Duration delay) {
    if (delay == null) {
      throw new NullPointerException("Delay cannot be null");
    }
    if (delay.isNegative()) {
      throw new IllegalArgumentException("Delay cannot be negative");
    }
    this.delay = delay;
  }

  /**
   * Adds the specified number of minutes to the delay of the train.
   *
   * @param minutes the number of minutes to add to the delay
   */
  public void addDelay(int minutes) {
    if (minutes < 0) {
      throw new IllegalArgumentException("Delay cannot be negative");
    }

    delay = delay.plusMinutes(minutes);
  }

  /**
   * Helper method when an ArrayList is required but only a TrainDeparture is returned.
   *
   * @return An ArrayList containing the TrainDeparture object.
   */
  public ArrayList<TrainDeparture> toArrayList() {
    ArrayList<TrainDeparture> list = new ArrayList<>();
    list.add(this);
    return list;
  }

  @Override
  public int compareTo(TrainDeparture other) {
    return this.getDepartureTimeWithDelay().compareTo(other.getDepartureTimeWithDelay());
  }

  /**
   * Indicates whether some other object is "equal to" this one. Asumes trainNumber is unique.
   *
   * @param obj the reference object with which to compare
   * @return true if this object is the same as the obj argument; false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }

    TrainDeparture other = (TrainDeparture) obj;
    return trainNumber == other.trainNumber;
  }
}