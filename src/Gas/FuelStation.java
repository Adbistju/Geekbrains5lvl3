package Gas;
import java.util.concurrent.Semaphore;

public class FuelStation implements FuelStationInterf{

    private Semaphore semaphore;

    public FuelStation() {
        this.semaphore = new Semaphore(gasPools.length);
    }

    private static GasPool gasPools [] = {
            new GasPool(),
            new GasPool(),
            new GasPool()

    };

    public synchronized float doRefuel(float amount){
        try {
            semaphore.acquire();
            for (int i = 0; i < gasPools.length; i++) {
                if(gasPools[i].status&&gasPools[i].info()>0&&gasPools[i].info()-amount>=0){
                    System.out.println(gasPools[i].indexGas+": "+gasPools[i].balance+"/"+GasPool.stock);
                    float a = gasPools[i].request(amount);
                    semaphore.release();
                    return a;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }

        return 0f;

    }
    public void getIndex(){
        for (int i = 0; i < gasPools.length; i++) {
            System.out.println(gasPools[i].indexGas);
        }
    }
    public boolean checkFreeAllGasPool(){
        int a=0;
        for (int j = 0; j < gasPools.length; j++) {
            if (gasPools[j].info() == 0){
                a++;
            }
        }
        return gasPools.length==a;
    }

    public boolean checkFuel(float autoFuel){
        for (int j = 0; j < gasPools.length; j++) {
            if (gasPools[j].info() >= autoFuel){
                return true;
            }
        }
        return false;
    }
    public void getInfo(){
        System.out.println("___________________________________________________");
        System.out.println("___________________________________________________");
        System.out.println("___________________________________________________");
        for (int i = 0; i < gasPools.length; i++) {
            System.out.println("_________________"+gasPools[i].indexGas+":"+gasPools[i].info()+"/"+GasPool.stock+"______________");
        }
        System.out.println("___________________________________________________");
        System.out.println("___________________________________________________");
        System.out.println("___________________________________________________");
    }
}
