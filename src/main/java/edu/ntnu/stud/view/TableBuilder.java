package edu.ntnu.stud.view;

public class TableBuilder {
  private static int padding = 2;

  private TableBuilder() {
    throw new IllegalStateException("Utility class");
  }

  public static String buildTable(String[][] table) {
    if (table.length == 0)
      throw new IllegalArgumentException("Table cannot be empty");

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

  private static String buildHorizontalLine(int[] columnWidths) {
    StringBuilder sb = new StringBuilder();

    for (int columnWidth : columnWidths) {
      sb.append("+");
      sb.append("-".repeat(columnWidth + padding * 2));
    }

    sb.append("+\n");

    return sb.toString();
  }

  public static int getRenderedLength(String str) {
    String ansiEscapeRegex = "\u001b\\[[;\\d]*m";
    String withoutAnsi = str.replaceAll(ansiEscapeRegex, "");

    // Return the length of the string without ANSI codes
    return withoutAnsi.length();
  }

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
