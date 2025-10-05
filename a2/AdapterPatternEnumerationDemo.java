import java.util.*;

// Step 1: Adapter Class implementing Iterator but using an Enumeration internally
class EnumerationIteratorAdapter<E> implements Iterator<E> {

    private Enumeration<E> enumeration;

    // Constructor takes an Enumeration object
    public EnumerationIteratorAdapter(Enumeration<E> enumeration) {
        this.enumeration = enumeration;
    }

    // Step 2: Adapt hasMoreElements() -> hasNext()
    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    // Step 3: Adapt nextElement() -> next()
    @Override
    public E next() {
        return enumeration.nextElement();
    }

    // Step 4: Iterator has remove(), but Enumeration doesn’t, so we can’t support it
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove not supported in Enumeration");
    }
}

// Step 5: Client Code demonstrating the Adapter Pattern
public class AdapterPatternEnumerationDemo {
    public static void main(String[] args) {
        // Step 6: Create a legacy Enumeration
        Vector<String> oldList = new Vector<>();
        oldList.add("Apple");
        oldList.add("Banana");
        oldList.add("Cherry");

        System.out.println("Using Legacy Enumeration:");
        Enumeration<String> enumeration = oldList.elements();
        while (enumeration.hasMoreElements()) {
            System.out.println(" - " + enumeration.nextElement());
        }

        // Step 7: Use the Adapter to make Enumeration work like an Iterator
        System.out.println("\nUsing Modern Iterator (via Adapter):");
        Enumeration<String> enumeration2 = oldList.elements();
        Iterator<String> iterator = new EnumerationIteratorAdapter<>(enumeration2);

        while (iterator.hasNext()) {
            System.out.println(" * " + iterator.next());
        }

        // Step 8: Demonstrate unsupported remove operation
        try {
            iterator.remove();
        } catch (UnsupportedOperationException e) {
            System.out.println("\n  " + e.getMessage());
        }
    }
}
