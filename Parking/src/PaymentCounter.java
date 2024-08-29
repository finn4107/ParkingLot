public class PaymentCounter {
    private static final double RATE_PER_MINUTE = 0.05; // 5 Cent pro Sekunde (1 Sekunde = 1 Minute)

    public double calculateFee(Ticket ticket) {
        long durationInMinutes = ticket.getDuration(); // Dauer in Sekunden
        return durationInMinutes * RATE_PER_MINUTE;
    }

    public void payTicket(Ticket ticket) {
        System.out.println("Ticket bezahlt.");
    }
}
