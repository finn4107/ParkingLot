package Parking;

public class EntranceBarrier {
    public void openBarrier(Ticket ticket) {
        if (ticket != null) {
            System.out.println("Eingangsschranke geöffnet.");
        } else {
            System.out.println("Eingangsschranke bleibt geschlossen.");
        }
    }
}

