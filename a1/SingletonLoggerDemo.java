// Step 1: Singleton class definition
class LoggerService {

    // Step 2: Private static instance variable
    private static LoggerService instance;

    // Step 3: Private constructor to prevent instantiation
    private LoggerService() {
        System.out.println("LoggerService instance created!");
    }

    // Step 4: Public static method to provide global access point
    public static LoggerService getInstance() {
        if (instance == null) {
            instance = new LoggerService();
        }
        return instance;
    }

    // Step 5: Example method for logging
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

// Step 6: Client code to test the Singleton
public class SingletonLoggerDemo {
    public static void main(String[] args) {

        System.out.println("Attempting to create first LoggerService instance...");
        LoggerService logger1 = LoggerService.getInstance();
        logger1.log("Application started.");

        System.out.println("\nAttempting to create second LoggerService instance...");
        LoggerService logger2 = LoggerService.getInstance();
        logger2.log("User logged in.");

        // Step 7: Verify that both references point to the same instance
        if (logger1 == logger2) {
            System.out.println("\nBoth logger1 and logger2 refer to the SAME instance.");
        } else {
            System.out.println("\nDifferent instances created!");
        }
    }
}
