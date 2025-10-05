// Step 1: Subject Interface
interface Image {
    void display();
}

// Step 2: Real Subject - RealImage
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading image from disk: " + fileName);
        try {
            Thread.sleep(1000); // Simulate time delay for loading
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}

// Step 3: Proxy - ImageProxy
class ImageProxy implements Image {
    private RealImage realImage;
    private String fileName;

    public ImageProxy(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        // Load the image only when it’s needed
        if (realImage == null) {
            System.out.println("Accessing image via proxy...");
            realImage = new RealImage(fileName);
        } else {
            System.out.println("Image already loaded. Using cached version...");
        }
        realImage.display();
    }
}

// Step 4: Client
public class ProxyPatternImageDemo {
    public static void main(String[] args) {
        Image image1 = new ImageProxy("photo1.jpg");
        Image image2 = new ImageProxy("photo2.png");

        // Images are not loaded yet
        System.out.println("\n-- First display call --");
        image1.display(); // Loads from disk
        image2.display(); // Loads from disk

        // Images already cached, so no reload
        System.out.println("\n-- Second display call --");
        image1.display(); // Uses cached image
        image2.display(); // Uses cached image
    }
}
