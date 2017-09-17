import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Collections;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
/**
 * A utility class for Birthday objects
 */
public class Birthdays {

  private final static String CSV_FILE = "birthdays.txt";

  /**
   * Parses a line of text from the CSV file to a Birthday object.
   * @param line The line from the CSV file to parse
   * @return A Birthday object from the parsed line
   */
  private static Birthday fromCsvLine(String line) {
    String[] fields = line.split(";");
    return new Birthday(fields[0], fields[1], Integer.parseInt(fields[2]));
  }

  /**
   * <p>
   * Parses the CSV file and returns a List&lt;Birthday&gt; parsed
   * from the lines in the file.
   * </p>
   * <p>
   * Doesn't use a Scanner, but rather the java.nio.Files lines() method
   * </p>
   * @return a List&lt;Birthday&gt; parsed from the CSV file
   * @throws IOException if the CSV file cannot be scanned.
   */
  public static List<Birthday> allBirthdays() throws IOException {        
    return Files.lines(Paths.get(CSV_FILE))
      .map(s -> new Birthday(s.split(";")[0],
                             s.split(";")[1],
                             Integer.parseInt(s.split(";")[2])))
      .collect(Collectors.toList());
  }



  
  /* // Alternative syntax (using helper method fromCsvLine(String)):
    return Files.lines(Paths.get(CSV_FILE))
      .map(s -> fromCsvLine(s))
      .collect(Collectors.toList());    
  */

  
  /**
   * Filters out all teenagers from a List&lt;Birthday&gt;
   * @param birthdays The list to be filtered
   * @return The filtered list with only teenagers
   */
  public static List<Birthday> teens(List<Birthday> birthdays) {
    return birthdays
      .stream()
      .filter(b ->
              b.yearsOld() > 12 &&
              b.yearsOld() < 20)
      .collect(Collectors.toList());
  }

  public static Birthday minimumAge(List<Birthday> birthdays) {
    return birthdays
      .stream()
      .reduce((b1, b2) -> b1.yearsOld() < b2.yearsOld() ? b1 : b2)
      .get();
  }

  /* Using Stream.max and a comparator for yearsOld() */
  public static Birthday max2(List<Birthday> birthdays) {
    return birthdays
      .stream()
      .max(Comparator.comparingInt(Birthday::yearsOld))
      .get();
  }

  /* Using reduce() - stream.max probably uses this anyway */
  public static Birthday maximumAge(List<Birthday> birthdays) {
    return birthdays
      .stream()
      // we give a lambda to reduce, to show you the syntax
      .reduce((b1, b2) -> b1.yearsOld() > b2.yearsOld() ? b1 : b2)
      .get();
  }
  
  /**
   * Prints information on the teenagers created from the CSV file:
   * How many teenagers, who is the youngest and who is the oldest of
   * them.
   */
  public static void printInfo() throws Exception {
    List<Birthday> birthdays = allBirthdays();
    List<Birthday> teens = teens(birthdays);
    System.out.println("We have " +
                       teens.size() +
                       " teenagers in the family.");
    System.out.println(minimumAge(teens).name() +
                       " is the youngest teen and is " +
                       minimumAge(teens).yearsOld() +
                       " years old");
    System.out.println(maximumAge(teens).name() +
                       " is the oldest teen and is " +
                       maximumAge(teens).yearsOld() +
                       " years old");

    // This is just to show an alternative implementation
    // of maximumAge:
    System.out.println("Alternative max method gives: " +
                       max2(teens).name() +
                       " which is " +
                       max2(teens).yearsOld() +
                       " years old");
  }
}
