// Step 1: Target Interface — BeatModel
interface BeatModel {
    void initialize();
    void on();
    void off();
    int getBPM();
}

// Step 2: Adaptee Class — HeartModel (already existing)
class HeartModel {
    private int heartRate;

    public HeartModel() {
        heartRate = 72; // normal resting heart rate
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void simulateBeat() {
        System.out.println("Heart is beating at " + heartRate + " BPM");
    }
}

// Step 3: Adapter Class — Adapts HeartModel to BeatModel
class HeartAdapter implements BeatModel {
    private HeartModel heartModel;

    public HeartAdapter(HeartModel heartModel) {
        this.heartModel = heartModel;
    }

    @Override
    public void initialize() {
        System.out.println("Initializing Heart-Beat Adapter...");
    }

    @Override
    public void on() {
        System.out.println("Heart monitoring started...");
        heartModel.simulateBeat();
    }

    @Override
    public void off() {
        System.out.println("Heart monitoring stopped.");
    }

    @Override
    public int getBPM() {
        return heartModel.getHeartRate();
    }
}

// Step 4: Client Class
public class AdapterPatternHeartDemo {
    public static void main(String[] args) {
        // Create HeartModel (Adaptee)
        HeartModel heart = new HeartModel();

        // Create Adapter that converts HeartModel to BeatModel interface
        BeatModel beatModel = new HeartAdapter(heart);

        // Use the BeatModel interface as if it’s a BeatModel
        beatModel.initialize();
        beatModel.on();
        System.out.println("Current BPM: " + beatModel.getBPM());
        beatModel.off();
    }
}
