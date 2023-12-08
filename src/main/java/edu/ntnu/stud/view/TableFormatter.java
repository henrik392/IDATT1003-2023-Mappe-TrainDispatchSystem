package edu.ntnu.stud.view;

/** The TableFormatter class provides utility methods for building formatted tables. */
public class TableFormatter {
  private static int padding = 2;

  private TableFormatter() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Builds a formatted table as a string.
   *
   * @param table The 2D array representing the table data.
   * @return The formatted table as a string.
   * @throws IllegalArgumentException if the table is empty.
   */
  public static String buildTable(String[][] table) {
    if (table.length == 0) {
      throw new IllegalArgumentException("Table cannot be empty");
    }

    int[] columnWidths = getColumnWidths(table);
    StringBuilder sb = new StringBuilder();

    sb.append(buildHorizontalLine(columnWidths));
    sb.append(buildRow(table[0], columnWidths));
    sb.append(buildHorizontalLine(columnWidths));

    for (int i = 1; i < table.length; i++) {
      sb.append(buildRow(table[i], columnWidths));
    }

    sb.append(buildHorizontalLine(columnWidths));

    return sb.toString();
  }

  /**
   * Builds a row of a table with the given rowData and columnWidths.
   *
   * @param rowData the data for each column in the row
   * @param columnWidths the widths of each column in the table
   * @return the formatted row as a string
   */
  private static String buildRow(String[] rowData, int[] columnWidths) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < rowData.length; i++) {
      sb.append("|");
      sb.append(" ".repeat(padding));
      sb.append(rowData[i]);
      sb.append(" ".repeat(columnWidths[i] - getRenderedLength(rowData[i])));
      sb.append(" ".repeat(padding));
    }

    sb.append("|\n");

    return sb.toString();
  }

  /**
   * Builds a horizontal line with '-' and '+' characters based on the given column widths.
   *
   * @param columnWidths an array of column widths
   * @return the horizontal line string
   */
  private static String buildHorizontalLine(int[] columnWidths) {
    StringBuilder sb = new StringBuilder();

    for (int columnWidth : columnWidths) {
      sb.append("+");
      sb.append("-".repeat(columnWidth + padding * 2));
    }

    sb.append("+\n");

    return sb.toString();
  }

  /**
   * Calculates the rendered length of a string by removing ANSI escape codes.
   *
   * @param str the input string
   * @return the length of the string without ANSI codes
   */
  private static int getRenderedLength(String str) {
    String ansiEscapeRegex = "\u001b\\[[;\\d]*m";
    String withoutAnsi = str.replaceAll(ansiEscapeRegex, "");

    return withoutAnsi.length();
  }

  /**
   * Calculates the maximum width for each column in a table. Uses {@link
   * #getRenderedLength(String)} to calculate the rendered length of each string.
   *
   * @param table The table represented as a 2D array of strings.
   * @return An array of integers representing the maximum width for each column.
   */
  private static int[] getColumnWidths(String[][] table) {
    int[] columnWidths = new int[table[0].length];

    for (int i = 0; i < table.length; i++) {
      for (int j = 0; j < table[0].length; j++) {
        columnWidths[j] = Math.max(columnWidths[j], getRenderedLength(table[i][j]));
      }
    }

    return columnWidths;
  }
}
