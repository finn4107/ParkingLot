package Parking;

public class Display {
    private ParkingLot parkingLot;

    public Display(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void showAvailableSpaces() {
        parkingLot.displayAvailableSpaces();
    }
}

