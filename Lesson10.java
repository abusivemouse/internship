public class Lesson10 {
    public static void main(String[] args) {
        Dog dog1 = new Dog("Бим");
        Dog dog2 = new Dog("Барбос");

        Cat cat1 = new Cat("Мурзик");
        Cat cat2 = new Cat("Боря");
        Cat cat3 = new Cat("Рыжик");

        System.out.println("Всего животных: " + Animal.getAnimalCount());
        System.out.println("Всего собак: " + Dog.getDogCount());
        System.out.println("Всего котов: " + Cat.getCatCount());

        dog1.run(300);
        dog1.swim(5);

        dog2.run(600);
        dog2.swim(15);

        cat1.run(150);
        cat1.swim(10);

        Bowl bowl = new Bowl(20);
        Cat[] cats = {cat1, cat2, cat3};

        for (Cat cat : cats) {
            cat.eat(bowl, 10);
            System.out.println(cat.getName() + " сыт: " + cat.isFull());
        }
        System.out.println("Остаток еды в миске: " + bowl.getFood() + " ед.");

        bowl.addFood(15);
        System.out.println("Добавили еды. Теперь в миске: " + bowl.getFood() + " ед.");

        for (Cat cat : cats) {
            if (!cat.isFull()) {
                cat.eat(bowl, 10);
                System.out.println(cat.getName() + " сыт: " + cat.isFull());
            }
        }
        System.out.println("Остаток еды в миске: " + bowl.getFood() + " ед.");

        System.out.println("Фигуры");
        Shape circle = new Circle(5, "Red", "Black");
        Shape rectangle = new Rectangle(4, 7, "Blue", "Green");
        Shape triangle = new Triangle(3, 4, 5, "Yellow", "Purple");

        System.out.println("Круг:");
        circle.printCharacteristics();

        System.out.println("\nПрямоугольник:");
        rectangle.printCharacteristics();

        System.out.println("\nТреугольник:");
        triangle.printCharacteristics();
    }

}

abstract class Animal {
    protected String name;
    protected static int animalCount = 0;

    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public abstract void run(int distance);
    public abstract void swim(int distance);

    public static int getAnimalCount() {
        return animalCount;
    }

    public String getName() {
        return name;
    }
}

class Dog extends Animal {
    private static final int MAX_RUN_DISTANCE = 500;
    private static final int MAX_SWIM_DISTANCE = 10;
    private static int dogCount = 0;

    public Dog(String name) {
        super(name);
        dogCount++;
    }

    public void run(int distance) {
        if (distance <= MAX_RUN_DISTANCE) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м.");
        }
    }

    public void swim(int distance) {
        if (distance <= MAX_SWIM_DISTANCE) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не может проплыть " + distance + " м.");
        }
    }

    public static int getDogCount() {
        return dogCount;
    }
}

class Cat extends Animal {
    private static final int MAX_RUN_DISTANCE = 200;
    private static int catCount = 0;
    private boolean isFull;

    public Cat(String name) {
        super(name);
        catCount++;
        this.isFull = false;
    }

    public void run(int distance) {
        if (distance <= MAX_RUN_DISTANCE) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м.");
        }
    }

    public void swim(int distance) {
        System.out.println(name + " не умеет плавать");
    }

    public void eat(Bowl bowl, int amount) {
        if (bowl.decreaseFood(amount)) {
            this.isFull = true;
            System.out.println(name + " поел и теперь сыт");
        } else {
            System.out.println(name + " не смог поесть. Недостаточно еды в миске");
        }
    }

    public boolean isFull() {
        return isFull;
    }

    public static int getCatCount() {
        return catCount;
    }
}

class Bowl {
    private int food;

    public Bowl(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int amount) {
        if (amount <= 0) {
            System.out.println("Количество еды должно быть больше нуля");
            return false;
        }
        if (food >= amount) {
            food -= amount;
            return true;
        }
        System.out.println("Недостаточно еды в миске. Осталось " + food + " ед.");
        return false;
    }

    public void addFood(int amount) {
        if (amount > 0) {
            food += amount;
            System.out.println("Добавлено " + amount + " ед. еды в миску");
        } else {
            System.out.println("Количество добавляемой еды должно быть больше нуля");
        }
    }

    public int getFood() {
        return food;
    }
}
// Задача про фигуры //
interface Shape {
    double getPerimeter();
    double getArea();
    String getFillColor();
    String getBorderColor();

    default void printCharacteristics() {
        System.out.println("Периметр: " + getPerimeter());
        System.out.println("Площадь: " + getArea());
        System.out.println("Цвет фона: " + getFillColor());
        System.out.println("Цвет границы: " + getBorderColor());
    }
}

class Circle implements Shape {
    private double radius;
    private String fillColor;
    private String borderColor;

    public Circle(double radius, String fillColor, String borderColor) {
        this.radius = radius;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}

class Rectangle implements Shape {
    private double width;
    private double height;
    private String fillColor;
    private String borderColor;

    public Rectangle(double width, double height, String fillColor, String borderColor) {
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}

class Triangle implements Shape {
    private double sideA;
    private double sideB;
    private double sideC;
    private String fillColor;
    private String borderColor;

    public Triangle(double sideA, double sideB, double sideC, String fillColor, String borderColor) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public double getArea() {
        double s = getPerimeter() / 2.0;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));

    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}