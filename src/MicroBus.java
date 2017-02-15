import java.util.Scanner;

class MicroBus implements Transport {
    private final int maxSpeed = 110;
    private final int numberOfSeats = 14;
    private final int finishDistance = 500;
    private int speed = 0;
    private int numberOfPassengers = 0;
    private int traveledDistance = 0;
    private boolean isNitroAvailable = true;

    MicroBus(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    @Override
    public void move() {
        Scanner scanner = new Scanner(System.in);
        while (traveledDistance < finishDistance && numberOfPassengers > 0) {
            System.out.println("Введите 1: для ускорения на 20км   " +
                    "2: для замедления на 20 км   3: для использования нитро");
            int choose = scanner.nextInt();
            int obstacle = (int) (1 + Math.random() * 20);
            int trafficJam = (int) (1 + Math.random() * 20);
            int sign60 = (int) (1 + Math.random() * 20);
            int sign80 = (int) (1 + Math.random() * 20);
            if (trafficJam == 1 && speed != 0) {
                System.out.println("Пробка, пропуск хода");
                speed = 0;
                continue;
            }
            else if (obstacle == 1 && speed != 0) {
                System.out.println("Препятствие, пропуск хода");
                speed = 0;
                continue;
            }
            if (choose == 1)
                accelerate();
            else if (choose == 2)
                reduceSpeed();
            else if (choose == 3)
                useNitro();
            else {
                System.out.println("Ошибка ввода, останавливаемся");
                speed = 0;
            }
            if (sign60 == 1)
                handleSign60();
            else if (sign80 == 1)
                handleSign80();
            traveledDistance += speed;
            showStatus();
        }
        System.out.println("Все пассажиры доставлены");
    }

    public void reduceSpeed() {
        speed -= 20;
        if (speed < 0) {
            System.out.println("Невозможно замедлиться - скорость 0 км/ч");
            speed = 0;
        }
    }

    public void accelerate() {
        speed += 20;
        if (speed > maxSpeed) {
            System.out.println("Достигнута максимальная скорость");
            speed = maxSpeed;
        }
    }

    private void useNitro() {
        if (isNitroAvailable) {
            speed += 30;
            if (speed > maxSpeed) {
                System.out.println("Достигнута максимальная скорость");
                speed = maxSpeed;
            }
        }
        else {
            System.out.println("Нитро недоступно, ускоряемся на 20км");
            speed += 20;
        }
        isNitroAvailable = false;
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
            isNitroAvailable = true;
        }
        System.out.println("Пройдено " + traveledDistance + " километров из "
                + finishDistance + "\nТекущая скорость: " + speed + " км/ч");
    }

    private void handleSign60() {
        System.out.println("Ограничение скорости 60 км/ч");
        if (speed > 60)
            speed = 60;
    }

    private void handleSign80() {
        System.out.println("Ограничение скорости 80 км/ч");
        if (speed > 80)
            speed = 80;
    }

}