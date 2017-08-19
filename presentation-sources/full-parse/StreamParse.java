import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.List;

public class StreamParse {
  private static final String CSV_FILE = "birthdays.txt";
  private static final String DELIMITER = ";";

  public static void main(String[] args) {
    try {
      List<Birthday> birthdays = getBirthdays();
      System.out.println("Found " + birthdays.size() + " birthdays.");
    } catch (IOException ioe) {
      System.err.println("Couldn't read file: " + ioe.getMessage());
    }
  }

  static Birthday birthdayFromLine(String line) {
    String[] elements = line.split(DELIMITER);
    String name = elements[0];
    String birthday = elements[1];
    int yearsOld = Integer.parseInt(elements[2]);
    return new Birthday(name, birthday, yearsOld);    
  }

  /*
  static List<Birthday> getBirthdays() throws IOException {
    List<Birthday> birthdays = Files.lines(Paths.get(CSV_FILE))
      .map(StreamParse::birthdayFromLine)
      .collect(Collectors.toList());
    return birthdays;
  } 
  */
  
  static List<Birthday> getBirthdays() throws IOException {
    return Files.lines(Paths.get(CSV_FILE))
      .map(s -> new Birthday(s.split(DELIMITER)[0],
                             s.split(DELIMITER)[1],
                             Integer.parseInt(s.split(DELIMITER)[2])))
      .collect(Collectors.toList());
  }  
  
}
