public class ExitBarrier {
    private String name;

    public ExitBarrier(String name) {
        this.name = name;
    }

    public void openBarrier(Ticket ticket) {
        if (ticket != null) {
            System.out.println(name + " Ausgangsschranke geöffnet.");
        } else {
            System.out.println(name + " Ausgangsschranke bleibt geschlossen.");
        }
    }
}

