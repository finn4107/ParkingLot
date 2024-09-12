import java.util.function.Consumer;

public class EntranceBarrier {
    public void openBarrier(Ticket ticket) {
        Consumer<Ticket> open = t -> {
            if (t != null) {
                System.out.println("Eingangsschranke geöffnet.");
            } else {
                System.out.println("Eingangsschranke bleibt geschlossen.");
            }
        };
        open.accept(ticket);
    }
}
