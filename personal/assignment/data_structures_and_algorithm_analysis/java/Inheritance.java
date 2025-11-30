public class Inheritance {
    public static void main(String[] args) {
        Dog dog = new Dog("Gugun");
        dog.eat();
        dog.bark();
        dog.sleep();

        System.out.println();

        Cat cat = new Cat("Si Kuning");
        cat.eat();
        cat.meow();
        cat.sleep();
    }

    static class Animal {
        String name;

        public Animal(String name) {
            this.name = name;
        }

        public void eat() {
            System.out.println(name + " sedang makan");
        }

        public void sleep() {
            System.out.println(name + " sedang tidur");
        }
    }

    static class Dog extends Animal {
        public Dog(String name) {
            super(name);
        }

        public void bark() {
            System.out.println(name + " bersuara: Guk! Guk!");
        }

        @Override
        public void eat() {
            System.out.println(name + " (anjing) sedang makan daging");
        }
    }

    static class Cat extends Animal {
        public Cat(String name) {
            super(name);
        }

        public void meow() {
            System.out.println(name + " bersuara: Meow! Meow!");
        }

        @Override
        public void eat() {
            System.out.println(name + " (kucing) sedang makan ikan");
        }
    }
}
