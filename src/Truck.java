import Gas.FuelStation;

public class Truck extends Auto{

    private static final float tank = 60f;
    private static float consumption = 15f;
    private float fuel;
    private String name;
    private FuelStation fuelStation;

    int indexGas;
    static int index=0;

    boolean runOrStop = false;

    public Truck(String name, FuelStation fuelStation) {
        this.name=name;
        fuel=tank;
        this.fuelStation=fuelStation;
        index++;
        indexGas=index;
    }

    String getName(){
        return name;
    }

    @Override
    void drive() {
        runOrStop=false;
        while (!(fuel <=0)){
            if(runOrStop==true){
                System.out.println(this.name+ " stop");
                runOrStop=false;
                break;
            }
            try {
                Thread.sleep(3000);//3000
                fuel=fuel-consumption;
                System.out.println(this.name+": "+fuel+" -> edem");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(fuelStation.checkFuel(consumption)){
            fuel=fuelStation.doRefuel(tank-fuel);
        }
        if((fuel>=consumption)){
            drive();
        }
    }

    @Override
    void stop() {
        if (runOrStop==true){
            System.out.println(this.name+ " stop");
        }else {
            runOrStop=true;
        }
    }

    @Override
    void doRefuel(float t) {
        System.out.println(fuel);
        fuel +=fuel + t;
        System.out.println(fuel);
    }

    @Override
    float getFuel() {
        return fuel;
    }

    @Override
    public void run() {
        drive();
    }
}