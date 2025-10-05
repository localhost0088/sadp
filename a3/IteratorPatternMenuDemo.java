import java.util.*;

// Step 1: MenuItem class
class MenuItem {
    private String name;
    private String description;
    private double price;

    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
}

// Step 2: Menu Interface
interface Menu {
    Iterator<MenuItem> createIterator(); // Use built-in Iterator<MenuItem>
}

// Step 3: Breakfast Menu (uses ArrayList)
class BreakfastMenu implements Menu {
    private List<MenuItem> menuItems;

    public BreakfastMenu() {
        menuItems = new ArrayList<>();
        addItem("Pancakes", "Delicious fluffy pancakes", 5.99);
        addItem("Omelette", "Cheese omelette with veggies", 6.99);
    }

    private void addItem(String name, String description, double price) {
        menuItems.add(new MenuItem(name, description, price));
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return menuItems.iterator(); // Works fine with built-in Iterator
    }
}

// Step 4: Lunch Menu (uses array)
class LunchMenu implements Menu {
    private static final int MAX_ITEMS = 6;
    private int numberOfItems = 0;
    private MenuItem[] menuItems;

    public LunchMenu() {
        menuItems = new MenuItem[MAX_ITEMS];
        addItem("Burger", "Beef burger with fries", 8.99);
        addItem("Salad", "Fresh garden salad", 7.49);
    }

    private void addItem(String name, String description, double price) {
        if (numberOfItems >= MAX_ITEMS) {
            System.out.println("Lunch menu full!");
            return;
        }
        menuItems[numberOfItems++] = new MenuItem(name, description, price);
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return new ArrayIterator(menuItems); // Custom iterator for array
    }
}

// Step 5: ArrayIterator (custom iterator for arrays)
class ArrayIterator implements Iterator<MenuItem> {
    private MenuItem[] items;
    private int position = 0;

    public ArrayIterator(MenuItem[] items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return position < items.length && items[position] != null;
    }

    @Override
    public MenuItem next() {
        return items[position++];
    }
}

// Step 6: Dinner Menu (uses HashMap)
class DinnerMenu implements Menu {
    private Map<String, MenuItem> menuMap;

    public DinnerMenu() {
        menuMap = new HashMap<>();
        addItem("Steak", "Grilled steak with sides", 14.99);
        addItem("Pasta", "Penne pasta with tomato sauce", 12.49);
    }

    private void addItem(String name, String description, double price) {
        menuMap.put(name, new MenuItem(name, description, price));
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return menuMap.values().iterator(); // Works fine now
    }
}

// Step 7: Client - Waitress
class Waitress {
    private List<Menu> menus;

    public Waitress(List<Menu> menus) {
        this.menus = menus;
    }

    public void printMenu() {
        for (Menu menu : menus) {
            Iterator<MenuItem> iterator = menu.createIterator();
            while (iterator.hasNext()) {
                MenuItem item = iterator.next();
                System.out.println(item.getName() + " - " + item.getDescription() + " : $" + item.getPrice());
            }
            System.out.println();
        }
    }
}

// Step 8: Main program
public class IteratorPatternMenuDemo {
    public static void main(String[] args) {
        Menu breakfastMenu = new BreakfastMenu();
        Menu lunchMenu = new LunchMenu();
        Menu dinnerMenu = new DinnerMenu();

        List<Menu> menus = new ArrayList<>();
        menus.add(breakfastMenu);
        menus.add(lunchMenu);
        menus.add(dinnerMenu);

        Waitress waitress = new Waitress(menus);
        System.out.println("---- FULL MENU ----\n");
        waitress.printMenu();
    }
}
