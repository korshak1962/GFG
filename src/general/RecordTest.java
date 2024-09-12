package general;

public record RecordTest(String name, int age, String email) {
  // Compact constructor
  public RecordTest {
    if (age < 0) {
      throw new IllegalArgumentException("Age cannot be negative");
    }
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
  }
  // Overloaded constructor
  public RecordTest(String name, int age) {
    this(name, age, "noemail@example.com");
  }

  @Override
  public int age() { return 10; }

  // Additional method
  public boolean isAdult() {
    return age >= 18;
  }
}
