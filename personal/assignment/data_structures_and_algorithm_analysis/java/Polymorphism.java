public class Polymorphism {
    public static void main(String[] args) {
        Animal[] animals = new Animal[3];
        animals[0] = new Dog("Buddy");
        animals[1] = new Cat("Whiskers");
        animals[2] = new Bird("Tweety");

        for (Animal animal : animals) {
            animal.sound();
            animal.eat();
            System.out.println();
        }

        makeAnimalSound(new Dog("Rex"));
        makeAnimalSound(new Cat("Garfield"));
        makeAnimalSound(new Bird("Polly"));
    }

    public static void makeAnimalSound(Animal animal) {
        animal.sound();
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

        public void sound() {
            System.out.println(name + " mengeluarkan suara");
        }
    }

    static class Dog extends Animal {
        public Dog(String name) {
            super(name);
        }

        @Override
        public void eat() {
            System.out.println(name + " (anjing) sedang makan daging");
        }

        @Override
        public void sound() {
            System.out.println(name + " bersuara: Guk! Guk!");
        }
    }

    static class Cat extends Animal {
        public Cat(String name) {
            super(name);
        }

        @Override
        public void eat() {
            System.out.println(name + " (kucing) sedang makan ikan");
        }

        @Override
        public void sound() {
            System.out.println(name + " bersuara: Meow! Meow!");
        }
    }

    static class Bird extends Animal {
        public Bird(String name) {
            super(name);
        }

        @Override
        public void eat() {
            System.out.println(name + " (burung) sedang makan biji-bijian");
        }

        @Override
        public void sound() {
            System.out.println(name + " bersuara: Cuit! Cuit!");
        }
    }
}
