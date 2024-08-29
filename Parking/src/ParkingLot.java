import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

    // JUnit Testklasse als innere Klasse
    public static class ParkingLotTest {
        private ParkingLot parkingLot;

        @BeforeEach
        void setUp() {
            parkingLot = new ParkingLot(50, 2); // 50 Plätze pro Stockwerk, 2 Stockwerke
        }

        @Test
        void testHasAvailableSpace() {
            // Testet, ob freie Plätze verfügbar sind
            assertTrue(parkingLot.hasAvailableSpace(1));
            assertTrue(parkingLot.hasAvailableSpace(2));
        }

        @Test
        void testOccupySpace() {
            // Testet das Belegen eines Platzes
            parkingLot.occupySpace(1);
            assertEquals(49, parkingLot.getAvailableSpaces(1));
        }

        @Test
        void testReleaseSpace() {
            // Testet das Freigeben eines Platzes
            parkingLot.occupySpace(1);
            parkingLot.releaseSpace(1);
            assertEquals(50, parkingLot.getAvailableSpaces(1));
        }

        @Test
        void testGetAvailableSpaces() {
            // Überprüft die Anzahl der verfügbaren Plätze
            assertEquals(50, parkingLot.getAvailableSpaces(1));
            assertEquals(50, parkingLot.getAvailableSpaces(2));
        }

        @Test
        void testReleaseSpaceWhenFull() {
            // Testet das Freigeben von Plätzen, wenn das Stockwerk bereits voll ist
            parkingLot.releaseSpace(1);
            assertEquals(51, parkingLot.getAvailableSpaces(1)); // Kapazität überschreiten
        }

        @Test
        void testOccupySpaceUntilFull() {
            // Testet das Belegen aller Plätze auf einem Stockwerk
            for (int i = 0; i < 50; i++) {
                parkingLot.occupySpace(1);
            }
            assertFalse(parkingLot.hasAvailableSpace(1));
        }
    }
}
