// Step 1: Command interface
interface Command {
    void execute();
    void undo(); // Optional for undo functionality
}

// Step 2: Receiver classes
class Light {
    private String location;

    public Light(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println(location + " light is ON");
    }

    public void off() {
        System.out.println(location + " light is OFF");
    }
}

class TV {
    private String location;

    public TV(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println(location + " TV is ON");
    }

    public void off() {
        System.out.println(location + " TV is OFF");
    }
}

// Step 3: Concrete Commands
class LightOnCommand implements Command {
    private Light light;
    public LightOnCommand(Light light) { this.light = light; }

    @Override
    public void execute() { light.on(); }

    @Override
    public void undo() { light.off(); }
}

class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light) { this.light = light; }

    @Override
    public void execute() { light.off(); }

    @Override
    public void undo() { light.on(); }
}

class TVOnCommand implements Command {
    private TV tv;
    public TVOnCommand(TV tv) { this.tv = tv; }

    @Override
    public void execute() { tv.on(); }

    @Override
    public void undo() { tv.off(); }
}

class TVOffCommand implements Command {
    private TV tv;
    public TVOffCommand(TV tv) { this.tv = tv; }

    @Override
    public void execute() { tv.off(); }

    @Override
    public void undo() { tv.on(); }
}

// Step 4: Invoker class - RemoteControl
class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private Command undoCommand;

    public RemoteControl() {
        onCommands = new Command[5];
        offCommands = new Command[5];
        Command noCommand = new NoCommand();
        for (int i = 0; i < 5; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void pressOnButton(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    public void pressOffButton(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    public void pressUndoButton() {
        System.out.println("⏪ Undoing last command...");
        undoCommand.undo();
    }
}

// Step 5: Null Object pattern for empty slots
class NoCommand implements Command {
    @Override
    public void execute() {}
    @Override
    public void undo() {}
}

// Step 6: Client
public class RemoteControlTest {
    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl();

        // Receivers
        Light livingRoomLight = new Light("Living Room");
        TV bedroomTV = new TV("Bedroom");

        // Commands
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
        TVOnCommand bedroomTVOn = new TVOnCommand(bedroomTV);
        TVOffCommand bedroomTVOff = new TVOffCommand(bedroomTV);

        // Set commands in remote slots
        remote.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remote.setCommand(1, bedroomTVOn, bedroomTVOff);

        // Test remote
        System.out.println("---- Press Light ON ----");
        remote.pressOnButton(0);
        System.out.println("---- Press Light OFF ----");
        remote.pressOffButton(0);
        remote.pressUndoButton();

        System.out.println("\n---- Press TV ON ----");
        remote.pressOnButton(1);
        System.out.println("---- Press TV OFF ----");
        remote.pressOffButton(1);
        remote.pressUndoButton();
    }
}
