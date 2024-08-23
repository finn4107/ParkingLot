package Parking;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class Driver {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(50, 2); // 50 Plätze pro Stockwerk, 2 Stockwerke
        TicketMachine ticketMachine = new TicketMachine(parkingLot);
        EntranceBarrier entranceBarrier = new EntranceBarrier();
        PaymentCounter paymentCounter = new PaymentCounter();
        ExitBarrier exitBarrier1 = new ExitBarrier("Ausfahrt Schranke 1");
        ExitBarrier exitBarrier2 = new ExitBarrier("Ausfahrt Schranke 2");
        Display display = new Display(parkingLot);

        Scanner scanner = new Scanner(System.in);
        Ticket currentTicket = null;

        while (true) {
            if (currentTicket == null) { // Fahrzeug ist noch nicht im Parkhaus
                System.out.println("Wählen Sie eine Aktion: (1) Einfahrt, (3) Verfügbare Plätze anzeigen, (4) Beenden");
            } else { // Fahrzeug ist im Parkhaus
                System.out.println("Wählen Sie eine Aktion: (2) Bezahlen und Ausfahren, (3) Verfügbare Plätze anzeigen, (4) Beenden");
            }

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    if (currentTicket == null) {
                        System.out.println("Wählen Sie ein Stockwerk (1 oder 2): ");
                        int floor = scanner.nextInt();
                        currentTicket = ticketMachine.issueTicket(floor);
                        entranceBarrier.openBarrier(currentTicket);

                        if (currentTicket != null) {
                            System.out.println("Parken... (Parkzeit wird simuliert)");
                            try {
                                TimeUnit.SECONDS.sleep(5); // Simuliert 5 Sekunden Parkdauer (5 Minuten)
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Auto auf Stockwerk " + floor + " geparkt.");
                        }
                    } else {
                        System.out.println("Sie haben bereits ein geparktes Fahrzeug. Bitte fahren Sie erst aus.");
                    }
                    break;

                case 2:
                    if (currentTicket != null) {
                        System.out.println("Wählen Sie eine Ausfahrtschranke (1 oder 2): ");
                        int exitChoice = scanner.nextInt();
                        ExitBarrier selectedExitBarrier = (exitChoice == 1) ? exitBarrier1 : exitBarrier2;

                        currentTicket.setExitTime();  // Exit-Zeit wird gesetzt
                        double fee = paymentCounter.calculateFee(currentTicket);
                        System.out.println("Parkgebühr: " + fee + " EUR");
                        paymentCounter.payTicket(currentTicket);

                        selectedExitBarrier.openBarrier(currentTicket);
                        parkingLot.releaseSpace(currentTicket.getFloor());
                        currentTicket = null;  // Ticket wird zurückgesetzt, da Fahrzeug ausgefahren ist
                    } else {
                        System.out.println("Sie haben kein Fahrzeug im Parkhaus.");
                    }
                    break;

                case 3:
                    display.showAvailableSpaces();
                    break;

                case 4:
                    System.out.println("Beenden...");
                    return;

                default:
                    System.out.println("Ungültige Aktion, bitte erneut versuchen.");
            }
        }
    }
}
