package edu.ntnu.stud.view;

import java.util.TreeMap;

/** The Menu class represents a menu with options. */
public class Menu {
  private final TreeMap<Integer, String> options;
  private final String menuName;

  /**
   * Constructs a new Menu object with the specified menu name.
   *
   * @param menuName the name of the menu
   */
  public Menu(String menuName) {
    this.options = new TreeMap<>();
    this.menuName = menuName;
  }

  /**
   * Adds an option to the menu with the specified number and text.
   *
   * @param i the number of the option
   * @param string the text of the option
   * @throws IllegalArgumentException if the option number is negative, already exists, or if the
   *     option text is null
   */
  public void addOption(int i, String string) {
    if (i < 0) {
      throw new IllegalArgumentException("Option number cannot be negative");
    }
    if (options.containsKey(i)) {
      throw new IllegalArgumentException("Option number already exists");
    }
    if (string == null) {
      throw new IllegalArgumentException("Option text cannot be null");
    }

    options.put(i, string);
  }

  /**
   * Checks if the menu has an option with the specified number.
   *
   * @param option the number of the option
   * @return true if the menu has the option, false otherwise
   */
  public boolean hasOption(int option) {
    return options.containsKey(option);
  }

  /**
   * Checks if the menu is empty (has no options).
   *
   * @return true if the menu is empty, false otherwise
   */
  public boolean isEmpty() {
    return options.isEmpty();
  }

  /**
   * Returns a string representation of the menu.
   *
   * @return a string representation of the menu
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(menuName + ":\n");
    for (Integer i : options.keySet()) {
      sb.append(i + ": " + options.get(i) + "\n");
    }

    return sb.toString();
  }
}
