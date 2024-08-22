package Parking;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<Integer, Integer> availableSpacesPerFloor;

    public ParkingLot(int spacesPerFloor, int floors) {
        availableSpacesPerFloor = new HashMap<>();
        for (int i = 1; i <= floors; i++) {
            availableSpacesPerFloor.put(i, spacesPerFloor);
        }
    }

    public boolean hasAvailableSpace(int floor) {
        return availableSpacesPerFloor.getOrDefault(floor, 0) > 0;
    }

    public void occupySpace(int floor) {
        availableSpacesPerFloor.put(floor, availableSpacesPerFloor.get(floor) - 1);
    }

    public void releaseSpace(int floor) {
        availableSpacesPerFloor.put(floor, availableSpacesPerFloor.get(floor) + 1);
    }

    public int getAvailableSpaces(int floor) {
        return availableSpacesPerFloor.getOrDefault(floor, 0);
    }

    public void displayAvailableSpaces() {
        for (Map.Entry<Integer, Integer> entry : availableSpacesPerFloor.entrySet()) {
            System.out.println("Verfügbare Parkplätze auf Stock " + entry.getKey() + ": " + entry.getValue());
        }
    }
}

