import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Ticket {
    private long entryTime;
    private long exitTime;
    private int floor;

    public Ticket(int floor) {
        this.entryTime = System.currentTimeMillis();
        this.floor = floor;
    }

    public void setExitTime() {
        this.exitTime = System.currentTimeMillis();
    }

    public long getDuration() {
        return (exitTime - entryTime) / 1000; // Gibt die Dauer in Sekunden zurück
    }

    public int getFloor() {
        return floor;
    }

    // JUnit Testklasse als innere Klasse
    public static class TicketTest {

        @Test
        void testGetDuration() throws InterruptedException {
            // Testet die Dauerberechnung
            Ticket ticket = new Ticket(1);
            Thread.sleep(1000); // Simuliert eine Parkdauer
            ticket.setExitTime();
            assertTrue(ticket.getDuration() >= 1); // Dauer sollte mindestens 1 Sekunde sein
        }

        @Test
        void testZeroDuration() {
            // Testet, wenn Ein- und Ausfahrtszeitpunkt gleich sind
            Ticket ticket = new Ticket(1);
            ticket.setExitTime();
            assertEquals(0, ticket.getDuration());
        }
    }
}
