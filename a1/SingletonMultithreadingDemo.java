// Singleton class
class Singleton {
    // Step 1: Create a private static volatile instance variable
    private static volatile Singleton instance;

    // Step 2: Make the constructor private to prevent external instantiation
    private Singleton() {
        System.out.println("Singleton Instance Created by Thread: " + Thread.currentThread().getName());
    }

    // Step 3: Provide a global access method (Thread-safe with Double-Checked Locking)
    public static Singleton getInstance() {
        if (instance == null) {  // First check (no locking)
            synchronized (Singleton.class) {
                if (instance == null) {  // Second check (with locking)
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // Example method
    public void showMessage() {
        System.out.println("Hello from Singleton instance! Called by " + Thread.currentThread().getName());
    }
}

// Test class for multithreading
public class SingletonMultithreadingDemo {
    public static void main(String[] args) {
        // Create multiple threads that try to access Singleton instance
        Runnable task = () -> {
            Singleton singleton = Singleton.getInstance();
            singleton.showMessage();
        };

        // Step 4: Create multiple threads
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");
        Thread t4 = new Thread(task, "Thread-4");

        // Step 5: Start threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
