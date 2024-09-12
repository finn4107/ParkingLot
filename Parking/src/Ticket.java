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

    // Setzt die Ausfahrtszeit
    public void setExitTime() {
        this.exitTime = System.currentTimeMillis();
    }

    // Berechnet die Parkdauer in Sekunden
    public long getDuration() {
        return (exitTime - entryTime) / 1000;
    }

    public int getFloor() {
        return floor;
    }

    // Testklasse
    public static class TicketTest {
        @Test
        void testGetDuration() {
            Ticket ticket = new Ticket(1);
            try {
                Thread.sleep(2000);  // Simuliert 2 Sekunden Parkzeit
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticket.setExitTime();
            assertTrue(ticket.getDuration() >= 2);  // Dauer sollte mindestens 2 Sekunden betragen
        }

        @Test
        void testGetFloor() {
            Ticket ticket = new Ticket(1);
            assertEquals(1, ticket.getFloor());
        }
    }
}
