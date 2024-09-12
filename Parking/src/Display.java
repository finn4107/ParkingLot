import java.util.function.Consumer;

public class Display {
    private ParkingLot parkingLot;

    public Display(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void showAvailableSpaces() {
        Consumer<ParkingLot> displaySpaces = ParkingLot::displayAvailableSpaces;
        displaySpaces.accept(parkingLot);
    }
}
