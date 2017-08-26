/**
 * Represents a Birthday.
 */
public class Birthday {
  private String name;
  private String birthday; // A date in the format "7 Januari"

  // Since birthday doesn't contain year
  // and the assignment says that we should
  // have this field, it is here.
  // However, we don't recommend saving "years old" as data
  // since it is a function of the birth date (and changes every
  // day for people who has their birthday that day)
  // Since the CSV file also contains this field for some reason,
  // we are forced to have it.
  // Note that the file must be updated every day to be valid
  // since someone might have had their birthday yesterday...
  private int yearsOld;

  public Birthday(String name, String birthday, int yearsOld) {
    this.name = name;
    this.birthday = birthday;
    this.yearsOld = yearsOld;
  }

  public String name() { return name; }
  public String birthday() { return birthday; }
  public int yearsOld() { return yearsOld; }
  
}
