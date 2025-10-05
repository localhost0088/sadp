// Step 1: Abstract Product A
interface Camera {
    void takePhoto();
}

// Step 2: Abstract Product B
interface VideoRecorder {
    void recordVideo();
}

// Step 3: Concrete Product A1
class SamsungCamera implements Camera {
    public void takePhoto() {
        System.out.println("Samsung Camera: Taking a high-resolution photo with advanced AI optimization...");
    }
}

// Step 4: Concrete Product A2
class AppleCamera implements Camera {
    public void takePhoto() {
        System.out.println("Apple Camera: Capturing a stunning portrait photo with Deep Fusion...");
    }
}

// Step 5: Concrete Product B1
class SamsungVideoRecorder implements VideoRecorder {
    public void recordVideo() {
        System.out.println("Samsung Video Recorder: Recording 8K HDR10+ video...");
    }
}

// Step 6: Concrete Product B2
class AppleVideoRecorder implements VideoRecorder {
    public void recordVideo() {
        System.out.println("Apple Video Recorder: Recording 4K cinematic video with Dolby Vision...");
    }
}

// Step 7: Abstract Factory
interface MobileFactory {
    Camera createCamera();
    VideoRecorder createVideoRecorder();
}

// Step 8: Concrete Factory 1
class SamsungFactory implements MobileFactory {
    public Camera createCamera() {
        return new SamsungCamera();
    }

    public VideoRecorder createVideoRecorder() {
        return new SamsungVideoRecorder();
    }
}

// Step 9: Concrete Factory 2
class AppleFactory implements MobileFactory {
    public Camera createCamera() {
        return new AppleCamera();
    }

    public VideoRecorder createVideoRecorder() {
        return new AppleVideoRecorder();
    }
}

// Step 10: Client
public class AbstractFactoryMobileDemo {
    public static void main(String[] args) {
        // Create Samsung Factory
        MobileFactory samsungFactory = new SamsungFactory();
        System.out.println("----- Samsung Phone Functionalities -----");
        Camera samsungCamera = samsungFactory.createCamera();
        VideoRecorder samsungVideo = samsungFactory.createVideoRecorder();
        samsungCamera.takePhoto();
        samsungVideo.recordVideo();

        System.out.println();

        // Create Apple Factory
        MobileFactory appleFactory = new AppleFactory();
        System.out.println("----- Apple Phone Functionalities -----");
        Camera appleCamera = appleFactory.createCamera();
        VideoRecorder appleVideo = appleFactory.createVideoRecorder();
        appleCamera.takePhoto();
        appleVideo.recordVideo();
    }
}
