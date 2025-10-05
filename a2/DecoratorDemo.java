// Step 1: Component interface
interface Text {
    String getContent();
}

// Step 2: Concrete Component
class SimpleText implements Text {
    private String content;

    public SimpleText(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}

// Step 3: Base Decorator
abstract class TextDecorator implements Text {
    protected Text text;  // wrapped object

    public TextDecorator(Text text) {
        this.text = text;
    }
}

// Step 4: Concrete Decorator (adds lowercase conversion)
class LowerCaseDecorator extends TextDecorator {

    public LowerCaseDecorator(Text text) {
        super(text);
    }

    @Override
    public String getContent() {
        return text.getContent().toLowerCase();
    }
}

// Step 5: Test the decorator
public class DecoratorDemo {
    public static void main(String[] args) {
        // Original text object
        Text myText = new SimpleText("Hello JAVA WORLD!");

        // Wrapped with LowerCaseDecorator
        Text lowerCaseText = new LowerCaseDecorator(myText);

        // Output
        System.out.println("Original: " + myText.getContent());
        System.out.println("Lowercase (using Decorator): " + lowerCaseText.getContent());
    }
}
