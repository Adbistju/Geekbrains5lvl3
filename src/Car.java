import Gas.FuelStation;

public class Car extends Auto{

    private static final float tank = 20f;//20
    private static float consumption = 2.5f;
    private float fuel;
    private String name;
    private FuelStation fuelStation;

    private int indexAuto;
    private static int index=0;

    boolean runOrStop = false;

    public Car(String name, FuelStation fuelStation) {
        this.name=name;
        fuel=tank;
        this.fuelStation=fuelStation;
        index++;
        indexAuto =index;
    }

    String getName(){
        return name;
    }

    @Override
    public void drive() {
        runOrStop=false;
        while (!(fuel <=0)&&fuel>=consumption){
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
    public void stop() {
        if (runOrStop==true){
            System.out.println(this.name+ " stop");
        }else {
            runOrStop=true;
        }
    }

    @Override
    public void doRefuel(float t) {
        System.out.println(fuel);
        fuel +=fuel + t;
        System.out.println(fuel);
    }

    @Override
    public float getFuel() {
        return fuel;
    }

    @Override
    public void run() {
        drive();
    }
}