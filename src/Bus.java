import java.util.Scanner;

class Bus implements Transport {
    private final int maxSpeed = 80;
    private final int numberOfSeats = 20;
    private final int finishDistance = 500;
    private int speed = 0;
    private int numberOfPassengers = 0;
    private int traveledDistance = 0;

    Bus(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    @Override
    public void move() {
        Scanner scanner = new Scanner(System.in);
        while (traveledDistance < finishDistance && numberOfPassengers > 0) {
            System.out.println("Введите 1: для ускорения на 10км   " +
                    "2: для замедления на 10 км");
            int choose = scanner.nextInt();
            int obstacle = (int) (1 + Math.random() * 16);
            int sign60 = (int) (1 + Math.random() * 16);
            if (obstacle == 1 && speed != 0) {
                System.out.println("Препятствие, пропуск хода");
                speed = 0;
                continue;
            }
            if (choose == 1) {
                accelerate();
            }
            else if (choose == 2) {
                reduceSpeed();
            }
            else {
                System.out.println("Ошибка ввода, останавливаемся");
                speed = 0;
            }
            if (sign60 == 1) {
                handleSign60();
            }
            traveledDistance += speed;
            showStatus();
        }
        System.out.println("Все пассажиры доставлены");
    }

    @Override
    public void reduceSpeed() {
        speed -= 10;
        if (speed < 0) {
            System.out.println("Невозможно замедлиться - скорость 0 км/ч");
            speed = 0;
        }
    }

    @Override
    public void accelerate() {
        speed += 10;
        if (speed > maxSpeed) {
            System.out.println("Достигнута максимальная скорость");
            speed = maxSpeed;
        }
    }

    public void showStatus() {
        if (traveledDistance >= finishDistance) {
            traveledDistance = 0;
            speed = 0;
            if (numberOfSeats > numberOfPassengers)
                System.out.println(numberOfPassengers + " пассажиров было доставлено");
            else
                System.out.println(numberOfSeats + " пассажиров было доставлено");
            numberOfPassengers -= numberOfSeats;
            if (numberOfPassengers > 0)
                System.out.println(numberOfPassengers + " пассажиров осталось");
        }
        System.out.println("Пройдено " + traveledDistance + " километров из "
                + finishDistance + "\nТекущая скорость: " + speed + " км/ч");
    }

    private void handleSign60() {
        System.out.println("Ограничение скорости 60 км/ч");
        if (speed > 60)
            speed = 60;
    }
}
