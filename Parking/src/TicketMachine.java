import java.util.function.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicketMachine {
    private ParkingLot parkingLot;

    public TicketMachine(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    // Stellt ein Ticket aus, wenn ein Platz verfügbar ist, nutzt Predicate zur Überprüfung
    public Ticket issueTicket(int floor) {
        Predicate<Integer> hasSpace = parkingLot::hasAvailableSpace;
        if (hasSpace.test(floor)) {
            parkingLot.occupySpace(floor);
            return new Ticket(floor);
        } else {
            System.out.println("Keine verfügbaren Parkplätze auf Stock " + floor);
            return null;
        }
    }

    // Testklasse
    public static class TicketMachineTest {
        private ParkingLot parkingLot;
        private TicketMachine ticketMachine;

        @BeforeEach
        void setUp() {
            parkingLot = new ParkingLot(50, 2);
            ticketMachine = new TicketMachine(parkingLot);
        }

        @Test
        void testIssueTicketWithSpace() {
            Ticket ticket = ticketMachine.issueTicket(1);
            assertNotNull(ticket);
        }

        @Test
        void testIssueTicketWithoutSpace() {
            for (int i = 0; i < 50; i++) {
                ticketMachine.issueTicket(1);  // Alle Plätze besetzen
            }
            Ticket ticket = ticketMachine.issueTicket(1);
            assertNull(ticket);  // Kein Platz mehr verfügbar
        }
    }
}
