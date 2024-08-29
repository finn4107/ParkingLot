import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentCounter {
    private static final double RATE_PER_MINUTE = 0.05; // 5 Cent pro Sekunde (1 Sekunde = 1 Minute)

    public double calculateFee(Ticket ticket) {
        long durationInSeconds = ticket.getDuration(); // Dauer in Sekunden
        return durationInSeconds * RATE_PER_MINUTE;
    }

    public void payTicket(Ticket ticket) {
        System.out.println("Ticket bezahlt.");
    }

    // JUnit Testklasse als innere Klasse
    public static class PaymentCounterTest {
        @Test
        void testCalculateFee() {
            // Testet die Gebührenberechnung für verschiedene Parkzeiten
            Ticket ticket = new Ticket(1);
            try {
                Thread.sleep(2000); // Simuliert 2 Sekunden Parkzeit
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticket.setExitTime();
            PaymentCounter paymentCounter = new PaymentCounter();
            double fee = paymentCounter.calculateFee(ticket);
            assertEquals(0.10, fee, 0.01); // Erwartet 0.10 EUR Gebühr
        }

        @Test
        void testCalculateFeeZeroDuration() {
            // Testet die Gebührenberechnung bei einer Parkdauer von 0
            Ticket ticket = new Ticket(1);
            ticket.setExitTime();
            PaymentCounter paymentCounter = new PaymentCounter();
            double fee = paymentCounter.calculateFee(ticket);
            assertEquals(0.0, fee);
        }
    }
}
