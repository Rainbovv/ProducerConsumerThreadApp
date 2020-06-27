import java.util.Random;


public class Main {

    public static void main(String[] args) throws InterruptedException {

        ParkTheCar parker = new ParkTheCar();
        ShootCarData shooter = new ShootCarData();
        EvacuateTheCar evacuator = new EvacuateTheCar();

        parker.start();
        shooter.start();
        evacuator.start();


        evacuator.join();
        System.out.println(Parking.placeA);

    }
}



//////////////////////////////  RESOURCE  //////////////////////////////
class Car{

    private String model;
    private String number;


    public Car(String model, String number) {
        this.model = model;
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        if (this.number.equals("")) return "The place is empty!";
        return "Car[" +
                "model='" + model + '\'' +
                ", number='" + number + '\'' +
                ']';
    }
}


class Parking {
    static Car placeA = new Car("","");
}

//////////////////////////////  Threads  //////////////////////////////

class ParkTheCar extends Thread {

    public void run() {
        try {
            sleep(new Random().nextInt(300));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Parking.placeA.setModel("BMW"); Parking.placeA.setNumber("XYZ-987");
    }
}

class EvacuateTheCar extends Thread {

    public void run() {
        try {
            sleep(350);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            Parking.placeA.setNumber(""); Parking.placeA.setModel("");
    }
}

class ShootCarData extends Thread {

    public void run() {
        while (Parking.placeA.getNumber().equals("")) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (Parking.placeA) {
            System.out.println(Parking.placeA);
        }
    }
}
