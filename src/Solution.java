import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать. Ваша задача перевести пассажиров");
        Scanner scan = new Scanner(System.in);
        System.out.println("В гараже две машины. Выберите:");
        System.out.println("1-автобус. Максимальная скорость 80, количество пассажиров 20");
        System.out.println("2-микроавтобус. Максимальная скорость 110, количество пассажиров 14");
        int numberOfPassengers = (int) (15 + Math.random() * 16);
        System.out.println("Нужно перевезти из пункта А в пункт Б " + numberOfPassengers + " пасажиров.");
        int transportType = scan.nextInt();
        Transport transport;
        while (true) {
            if (transportType == 1) {
                transport = new Bus(numberOfPassengers);
                transport.move();
                break;
            } else if (transportType == 2) {
                transport = new MicroBus(numberOfPassengers);
                transport.move();
                break;
            } else {
                System.out.println("Ошибка ввода, попробуйте еще");
                transportType = scan.nextInt();
            }
        }
    }
}
