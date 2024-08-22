package Parking;

public class TicketMachine {
    private ParkingLot parkingLot;

    public TicketMachine(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket issueTicket(int floor) {
        if (parkingLot.hasAvailableSpace(floor)) {
            parkingLot.occupySpace(floor);
            return new Ticket(floor);
        } else {
            System.out.println("Verfügbare Parkplätze auf " + floor);
            return null;
        }
    }
}

