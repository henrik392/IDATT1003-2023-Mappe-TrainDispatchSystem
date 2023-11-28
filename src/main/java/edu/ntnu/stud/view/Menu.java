package edu.ntnu.stud.view;

import java.util.TreeMap;

public class Menu {
  private final TreeMap<Integer, String> options;
  private final String menuName;

  public Menu(String menuName) {
    this.options = new TreeMap<>();
    this.menuName = menuName;
  }

  public void addOption(int i, String string) {
    if (i < 0)
      throw new IllegalArgumentException("Option number cannot be negative");
    if (options.containsKey(i))
      throw new IllegalArgumentException("Option number already exists");
    if (string == null)
      throw new IllegalArgumentException("Option text cannot be null");

    options.put(i, string);
  }

  public boolean hasOption(int option) {
    return options.containsKey(option);
  }

  public boolean isEmpty() {
    return options.isEmpty();
  }

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
