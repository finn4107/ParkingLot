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
        return exitTime - entryTime;
    }

    public int getFloor() {
        return floor;
    }
}

