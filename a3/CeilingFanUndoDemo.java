// Step 1: Command interface
interface Command {
    void execute();
    void undo();
}

// Step 2: Receiver class - CeilingFan
class CeilingFan {
    public static final int OFF = 0;
    public static final int LOW = 1;
    public static final int MEDIUM = 2;
    public static final int HIGH = 3;

    private int speed;
    private String location;

    public CeilingFan(String location) {
        this.location = location;
        speed = OFF;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        String speedStr = (speed == OFF) ? "OFF" :
                          (speed == LOW) ? "LOW" :
                          (speed == MEDIUM) ? "MEDIUM" : "HIGH";
        System.out.println(location + " ceiling fan is set to " + speedStr);
    }

    public int getSpeed() {
        return speed;
    }
}

// Step 3: Concrete Command - Set Ceiling Fan HIGH
class CeilingFanHighCommand implements Command {
    private CeilingFan fan;
    private int prevSpeed;

    public CeilingFanHighCommand(CeilingFan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        prevSpeed = fan.getSpeed();
        fan.setSpeed(CeilingFan.HIGH);
    }

    @Override
    public void undo() {
        fan.setSpeed(prevSpeed);
    }
}

// Step 4: Concrete Command - Set Ceiling Fan MEDIUM
class CeilingFanMediumCommand implements Command {
    private CeilingFan fan;
    private int prevSpeed;

    public CeilingFanMediumCommand(CeilingFan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        prevSpeed = fan.getSpeed();
        fan.setSpeed(CeilingFan.MEDIUM);
    }

    @Override
    public void undo() {
        fan.setSpeed(prevSpeed);
    }
}

// Step 5: Concrete Command - Turn Ceiling Fan OFF
class CeilingFanOffCommand implements Command {
    private CeilingFan fan;
    private int prevSpeed;

    public CeilingFanOffCommand(CeilingFan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        prevSpeed = fan.getSpeed();
        fan.setSpeed(CeilingFan.OFF);
    }

    @Override
    public void undo() {
        fan.setSpeed(prevSpeed);
    }
}

// Step 6: Invoker class with Undo
class RemoteControlWithUndo {
    private Command slot;
    private Command undoCommand;

    public void setCommand(Command command) {
        slot = command;
    }

    public void pressButton() {
        slot.execute();
        undoCommand = slot;
    }

    public void pressUndo() {
        if (undoCommand != null) {
            System.out.println("Undoing last command...");
            undoCommand.undo();
        }
    }
}

// Step 7: Client
public class CeilingFanUndoDemo {
    public static void main(String[] args) {
        RemoteControlWithUndo remote = new RemoteControlWithUndo();
        CeilingFan livingRoomFan = new CeilingFan("Living Room");

        // Commands
        Command fanHigh = new CeilingFanHighCommand(livingRoomFan);
        Command fanMedium = new CeilingFanMediumCommand(livingRoomFan);
        Command fanOff = new CeilingFanOffCommand(livingRoomFan);

        // Turn fan HIGH
        remote.setCommand(fanHigh);
        remote.pressButton();

        // Turn fan MEDIUM
        remote.setCommand(fanMedium);
        remote.pressButton();

        // Undo last command (should go back to HIGH)
        remote.pressUndo();

        // Turn fan OFF
        remote.setCommand(fanOff);
        remote.pressButton();

        // Undo last command (should go back to previous speed)
        remote.pressUndo();
    }
}
