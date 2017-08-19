public class SplitLine {
  public static void main(String[] args) {
    String line = "Henrik,April 13,46";
    String[] elements = line.split(",");
    for (int i = 0; i < elements.length; i++) {
      System.out.println("Element: " + elements[i]);
    }
  }
}
