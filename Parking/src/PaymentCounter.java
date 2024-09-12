import java.util.function.Function;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentCounter {
    private static final double RATE_PER_MINUTE = 0.05;  // 5 Cent pro Minute

    // Berechnet die Parkgebühr basierend auf der Parkdauer
    public double calculateFee(Ticket ticket) {
        Function<Ticket, Long> durationInSeconds = Ticket::getDuration;
        return durationInSeconds.apply(ticket) * RATE_PER_MINUTE;
    }

    public void payTicket(Ticket ticket) {
        System.out.println("Ticket bezahlt.");
    }

    // Testklasse
    public static class PaymentCounterTest {
        @Test
        void testCalculateFee() {
            Ticket ticket = new Ticket(1);
            try {
                Thread.sleep(2000);  // Simuliert 2 Sekunden Parkzeit
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticket.setExitTime();
            PaymentCounter counter = new PaymentCounter();
            double fee = counter.calculateFee(ticket);
            assertTrue(fee > 0);  // Gebühr sollte mehr als 0 sein
        }

        @Test
        void testPayTicket() {
            Ticket ticket = new Ticket(1);
            PaymentCounter counter = new PaymentCounter();
            counter.payTicket(ticket);
            // Hier könnte man weitere Tests für den Zahlungsprozess hinzufügen
        }
    }
}
