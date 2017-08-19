import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import java.util.function.Consumer;

public class ReadingLines {
  public static void main(String[] args) {
    //File csv = new File("birthdays.txt");
    String fileName = "birthdays.txt";
    try {
      //Stream<String> stream = Files.lines(Paths.get(fileName));
      //stream.forEach((line) -> { System.out.println("Line: " + line); });
      Files
        .lines(Paths.get(fileName))
        .forEach((line) -> { System.out.println("Line: " + line); });
      System.out.println("using map:");
      Files
        .lines(Paths.get(fileName))
        .map(s -> "Line: " + s)
        .forEach(System.out::println);
      System.out.println("Using andThen:");
      Files
        .lines(Paths.get(fileName))
        .forEach(((Consumer<String>)(s) -> System.out.print("Line: "))
                 .andThen(System.out::println));
      /*
      BufferedReader reader =
        new BufferedReader(new FileReader(csv));
      */
      /*
      BufferedReader reader = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8);
      String line = null;
      while ((line = reader.readLine()) != null) {
        System.out.println("Line: " + line);
      }
      */
    } catch (IOException ioe) {
      System.out.println("Error opening file: " + ioe.getMessage());
    }
  }
}
