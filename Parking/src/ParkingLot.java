import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.function.Function;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLot {
    private Map<Integer, Integer> availableSpacesPerFloor;

    // Konstruktor initialisiert die Anzahl der verfügbaren Plätze pro Stockwerk
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

    // Verfügbare Parkplätze pro Stockwerk anzeigen, nutzt Streams zur Darstellung
    public void displayAvailableSpaces() {
        availableSpacesPerFloor.entrySet().stream()
                .map(entry -> "Verfügbare Parkplätze auf Stock " + entry.getKey() + ": " + entry.getValue())
                .forEach(System.out::println);
    }

    // Testklasse für Unit-Tests
    public static class ParkingLotTest {
        private ParkingLot parkingLot;

        @BeforeEach
        void setUp() {
            parkingLot = new ParkingLot(50, 2);  // Initialisiert mit 50 Plätzen pro Stockwerk, 2 Stockwerke
        }

        @Test
        void testHasAvailableSpace() {
            assertTrue(parkingLot.hasAvailableSpace(1));
            assertTrue(parkingLot.hasAvailableSpace(2));
        }

        @Test
        void testOccupySpace() {
            parkingLot.occupySpace(1);
            assertEquals(49, parkingLot.getAvailableSpaces(1));
        }

        @Test
        void testReleaseSpace() {
            parkingLot.occupySpace(1);
            parkingLot.releaseSpace(1);
            assertEquals(50, parkingLot.getAvailableSpaces(1));
        }

        @Test
        void testGetAvailableSpaces() {
            assertEquals(50, parkingLot.getAvailableSpaces(1));
            assertEquals(50, parkingLot.getAvailableSpaces(2));
        }

        @Test
        void testReleaseSpaceWhenFull() {
            parkingLot.releaseSpace(1);
            assertEquals(51, parkingLot.getAvailableSpaces(1));  // Kapazität überschreiten
        }

        @Test
        void testOccupySpaceUntilFull() {
            for (int i = 0; i < 50; i++) {
                parkingLot.occupySpace(1);
            }
            assertFalse(parkingLot.hasAvailableSpace(1));
        }
    }
}
