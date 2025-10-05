// Step 1: Implementor interface (the bridge implementor)
interface Workshop {
    void work();
}

// Step 2: Concrete Implementor 1 - Produce
class Produce implements Workshop {
    @Override
    public void work() {
        System.out.print("Producing vehicle parts ");
    }
}

// Step 3: Concrete Implementor 2 - Assemble
class Assemble implements Workshop {
    @Override
    public void work() {
        System.out.print("and assembling them together.\n");
    }
}

// Step 4: Abstraction - Vehicle
abstract class Vehicle {
    protected Workshop workshop1;
    protected Workshop workshop2;

    protected Vehicle(Workshop workshop1, Workshop workshop2) {
        this.workshop1 = workshop1;
        this.workshop2 = workshop2;
    }

    // Abstract operation
    abstract void manufacture();
}

// Step 5: Refined Abstraction 1 - Car
class Car extends Vehicle {
    public Car(Workshop workshop1, Workshop workshop2) {
        super(workshop1, workshop2);
    }

    @Override
    void manufacture() {
        System.out.print("Manufacturing Car: ");
        workshop1.work();
        workshop2.work();
    }
}

// Step 6: Refined Abstraction 2 - Bike
class Bike extends Vehicle {
    public Bike(Workshop workshop1, Workshop workshop2) {
        super(workshop1, workshop2);
    }

    @Override
    void manufacture() {
        System.out.print("Manufacturing Bike: ");
        workshop1.work();
        workshop2.work();
    }
}

// Step 7: Client
public class BridgePatternVehicleDemo {
    public static void main(String[] args) {
        // Create workshops
        Workshop produce = new Produce();
        Workshop assemble = new Assemble();

        // Step 8: Bridge both abstraction and implementation
        Vehicle car = new Car(produce, assemble);
        Vehicle bike = new Bike(produce, assemble);

        // Step 9: Manufacture both vehicles
        car.manufacture();
        bike.manufacture();
    }
}
