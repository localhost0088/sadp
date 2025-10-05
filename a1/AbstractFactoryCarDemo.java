// Step 1: Abstract Product A - Car
interface Car {
    void assemble();
}

// Step 2: Abstract Product B - Specification
interface Specification {
    void showSpecs();
}

// Step 3: Concrete Product A1 - North America Car
class NorthAmericaCar implements Car {
    public void assemble() {
        System.out.println("Assembling a North America model car with left-hand drive...");
    }
}

// Step 4: Concrete Product A2 - Europe Car
class EuropeCar implements Car {
    public void assemble() {
        System.out.println("Assembling a Europe model car with right-hand drive...");
    }
}

// Step 5: Concrete Product B1 - North America Specification
class NorthAmericaSpecification implements Specification {
    public void showSpecs() {
        System.out.println("Specifications (North America):");
        System.out.println("- Emission Standards: EPA Tier 3");
        System.out.println("- Speed: MPH");
        System.out.println("- Fuel Type: Gasoline");
        System.out.println("- Navigation: Miles-based GPS\n");
    }
}

// Step 6: Concrete Product B2 - Europe Specification
class EuropeSpecification implements Specification {
    public void showSpecs() {
        System.out.println("Specifications (Europe):");
        System.out.println("- Emission Standards: Euro 6");
        System.out.println("- Speed: KM/H");
        System.out.println("- Fuel Type: Diesel");
        System.out.println("- Navigation: Kilometer-based GPS\n");
    }
}

// Step 7: Abstract Factory
interface CarFactory {
    Car createCar();
    Specification createSpecification();
}

// Step 8: Concrete Factory 1 - North America Factory
class NorthAmericaFactory implements CarFactory {
    public Car createCar() {
        return new NorthAmericaCar();
    }

    public Specification createSpecification() {
        return new NorthAmericaSpecification();
    }
}

// Step 9: Concrete Factory 2 - Europe Factory
class EuropeFactory implements CarFactory {
    public Car createCar() {
        return new EuropeCar();
    }

    public Specification createSpecification() {
        return new EuropeSpecification();
    }
}

// Step 10: Client
public class AbstractFactoryCarDemo {
    public static void main(String[] args) {
        // Step 11: Create Factory for North America
        System.out.println("===== North America Car Factory =====");
        CarFactory naFactory = new NorthAmericaFactory();
        Car naCar = naFactory.createCar();
        Specification naSpecs = naFactory.createSpecification();
        naCar.assemble();
        naSpecs.showSpecs();

        // Step 12: Create Factory for Europe
        System.out.println("===== Europe Car Factory =====");
        CarFactory euFactory = new EuropeFactory();
        Car euCar = euFactory.createCar();
        Specification euSpecs = euFactory.createSpecification();
        euCar.assemble();
        euSpecs.showSpecs();
    }
}
