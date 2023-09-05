import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    private static Cinema cinema;

    public static void main(String[] args) {
        cinema = createCinema();
        boolean exit = false;

        while (!exit) {
            System.out.println("""

                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit""");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> cinema.printSeatingChart();
                case 2 -> purchaseTicket();
                case 3 -> cinema.printStatistics();
                case 0 -> exit = true;
                default -> System.out.println("invalid option");
            }
        }
    }

    public static void purchaseTicket() {
        boolean validSeat = false;
        int rowNum = 0;
        int seatNum = 0;

        while (!validSeat) {
            System.out.println("\nEnter a row number:");
            rowNum = scanner.nextInt();
            System.out.println("enter a seat Number in that row:");
            seatNum = scanner.nextInt();
            if (!cinema.isValidSeatNum(rowNum, seatNum)) {
                System.out.println("Wrong input!");
                continue;
            }
            if (!cinema.isAvailableSeat(rowNum, seatNum)) {
                System.out.println("That ticket has already been purchased!");
                continue;
            }
            validSeat = true;
        }
        int ticketPrice = cinema.purchaseTicket(rowNum, seatNum);
        System.out.printf("\nTicket price: $%d\n", ticketPrice);
    }

    public static Cinema createCinema() {
        boolean validInput = false;
        int numRows = 0;
        int numSeatsInRow = 0;
        while (!validInput) {
            System.out.println("Enter the number of rows:");
            numRows = scanner.nextInt();
            System.out.println("Enter the number of seats in each row:");
            numSeatsInRow = scanner.nextInt();
            if (numRows > 0 && numSeatsInRow > 0) {
                validInput = true;
            } else {
                System.out.println("Invalid input!");
            }
        }
        return new Cinema(numRows, numSeatsInRow);
    }

}


