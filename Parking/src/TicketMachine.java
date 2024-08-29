import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
            System.out.println("Keine verfügbaren Parkplätze auf Stock " + floor);
            return null;
        }
    }

    // JUnit Testklasse als innere Klasse
    public static class TicketMachineTest {
        private ParkingLot parkingLot;
        private TicketMachine ticketMachine;

        @BeforeEach
        void setUp() {
            parkingLot = new ParkingLot(50, 2);
            ticketMachine = new TicketMachine(parkingLot);
        }

        @Test
        void testIssueTicket() {
            // Testet das Ausstellen eines Tickets
            Ticket ticket = ticketMachine.issueTicket(1);
            assertNotNull(ticket);
            assertEquals(1, ticket.getFloor());
            assertEquals(49, parkingLot.getAvailableSpaces(1));
        }

        @Test
        void testIssueTicketNoSpace() {
            // Testet das Verhalten, wenn keine freien Plätze vorhanden sind
            for (int i = 0; i < 50; i++) {
                ticketMachine.issueTicket(1);
            }
            Ticket ticket = ticketMachine.issueTicket(1);
            assertNull(ticket); // Sollte null zurückgeben, da keine Plätze verfügbar sind
        }

        @Test
        void testIssueTicketInvalidFloor() {
            // Testet das Ausstellen eines Tickets für ein nicht existentes Stockwerk
            Ticket ticket = ticketMachine.issueTicket(3);
            assertNull(ticket); // Ungültige Stockwerksnummer, sollte null zurückgeben
        }
    }
}