// Step 1: Abstract Product
abstract class Pizza {
    String name;
    String dough;
    String sauce;

    void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough...");
        System.out.println("Adding sauce...");
        System.out.println("Adding toppings...");
    }

    void bake() {
        System.out.println("Baking " + name + " for 25 minutes at 350°F");
    }

    void cut() {
        System.out.println("Cutting " + name + " into diagonal slices");
    }

    void box() {
        System.out.println("Placing " + name + " in official PizzaStore box");
    }

    public String getName() {
        return name;
    }
}

// Step 2: Concrete Products
class NYStyleCheesePizza extends Pizza {
    public NYStyleCheesePizza() {
        name = "NY Style Sauce and Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";
    }

    @Override
    void cut() {
        System.out.println("Cutting the pizza into large thin slices (NY Style)");
    }
}

class ChicagoStyleCheesePizza extends Pizza {
    public ChicagoStyleCheesePizza() {
        name = "Chicago Style Deep Dish Cheese Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";
    }

    @Override
    void cut() {
        System.out.println("Cutting the pizza into square slices (Chicago Style)");
    }
}

// Step 3: Creator (Abstract Factory)
abstract class PizzaStore {
    public Pizza orderPizza(String type) {
        Pizza pizza;

        pizza = createPizza(type); // Factory Method

        System.out.println("---- Making a " + pizza.getName() + " ----");
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    // Factory Method (to be implemented by subclasses)
    protected abstract Pizza createPizza(String type);
}

// Step 4: Concrete Creators
class NYPizzaStore extends PizzaStore {
    protected Pizza createPizza(String type) {
        if (type.equalsIgnoreCase("cheese")) {
            return new NYStyleCheesePizza();
        }
        // Additional types (e.g., veggie, pepperoni) can be added here
        else {
            System.out.println("Sorry, only cheese pizza available in NY store.");
            return null;
        }
    }
}

class ChicagoPizzaStore extends PizzaStore {
    protected Pizza createPizza(String type) {
        if (type.equalsIgnoreCase("cheese")) {
            return new ChicagoStyleCheesePizza();
        } 
        else {
            System.out.println("Sorry, only cheese pizza available in Chicago store.");
            return null;
        }
    }
}

// Step 5: Client
public class PizzaFactoryDemo {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        // Order NY Style Pizza
        System.out.println("\n--- Ordering NY Style Cheese Pizza ---");
        Pizza pizza1 = nyStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza1.getName() + "\n");

        // Order Chicago Style Pizza
        System.out.println("--- Ordering Chicago Style Cheese Pizza ---");
        Pizza pizza2 = chicagoStore.orderPizza("cheese");
        System.out.println("Joel ordered a " + pizza2.getName() + "\n");
    }
}
