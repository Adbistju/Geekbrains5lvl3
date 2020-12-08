import Gas.FuelStation;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FuelStation fuelStation =new FuelStation();

        Auto c = new Car("Car1", fuelStation);
        Auto c1 = new Car("Car2", fuelStation);
        Auto c2 = new Truck("Truck3", fuelStation);
        Auto c3 = new Car("Car4", fuelStation);
        Auto c4 = new Car("Car5", fuelStation);
        Auto c5 = new Bus("Bus5", fuelStation);

        Thread t1 = new Thread(c::run);
        Thread t2 = new Thread(c1::run);
        Thread t3 = new Thread(c2::run);
        Thread t4 = new Thread(c3::run);
        Thread t5 = new Thread(c4::run);
        Thread t7 = new Thread(c5::run);

        Thread t6 = new Thread(()->{
            Scanner scanner = new Scanner(System.in);
            while (true){
                String a = scanner.nextLine();
                    fuelStation.getInfo();
                if (a.equals("1")){
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t7.start();

        t6.start();

    }
}