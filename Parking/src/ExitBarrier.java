import java.util.function.Consumer;

public class ExitBarrier {
    private String name;

    public ExitBarrier(String name) {
        this.name = name;
    }

    public void openBarrier(Ticket ticket) {
        Consumer<Ticket> open = t -> {
            if (t != null) {
                System.out.println(name + " Ausgangsschranke geöffnet.");
            } else {
                System.out.println(name + " Ausgangsschranke bleibt geschlossen.");
            }
        };
        open.accept(ticket);
    }
}
