package amazon;

public class CustomeDefined {
    enum Color {
        White,
        Red,
        Black
    }

    enum Shape {
        Circle,
        Triangle,
        Rectangle
    }

    Color color;
    Shape shape;

    public CustomeDefined(Color color, Shape shape) {
        this.color = color;
        this.shape = shape;
    }
}
