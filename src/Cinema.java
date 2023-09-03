
import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        char[][] seatingScheme = createSeatingScheme();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    printSeatingScheme(seatingScheme);
                    break;
                case 2:
                    purchaseTicket(seatingScheme);
                    break;
                case 3:
                    printStatistics(seatingScheme);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("invalid option");
                    break;
            }
        }
    }

    public static void purchaseTicket(char[][] seatingScheme) {
        boolean validSeat = false;
        int rowNum = 0;
        int seatNum = 0;

        while (!validSeat) {
            System.out.println("\nEnter a row number:");
            rowNum = scanner.nextInt();
            System.out.println("enter a seat Number in that row:");
            seatNum = scanner.nextInt();
            validSeat = isValidSeat(seatingScheme, rowNum, seatNum);
        }
        int ticketPrice = checkTicketPrice(seatingScheme, rowNum);
        System.out.printf("\nTicket price: $%d\n", ticketPrice);
        seatingScheme[rowNum - 1][seatNum - 1] = 'B';
    }

    public static boolean isValidSeat(char[][] seatingScheme, int rowNum, int seatNum) {
        if (rowNum < 1 || rowNum > seatingScheme.length ||
                seatNum < 1 || seatNum > seatingScheme[0].length) {
            System.out.println("Wrong input!");
            return false;
        } else if (seatingScheme[rowNum - 1][seatNum - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            return false;
        } else {
            return true;
        }
    }

    public static char[][] createSeatingScheme() {
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
        char[][] seatingScheme = new char[numRows][numSeatsInRow];
        for (char[] chars : seatingScheme) {
            Arrays.fill(chars, 'S');
        }
        return seatingScheme;
    }

    public static void printSeatingScheme(char[][] seatingScheme) {
        System.out.print("\nCinema:\n  ");
        for (int i = 1; i <= seatingScheme[0].length; i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();
        int rowNumber = 1;
        for (char[] row: seatingScheme) {
            System.out.printf("%d ", rowNumber++);
            for (char seat : row) {
                System.out.printf("%c ", seat);
            }
            System.out.println();
        }
    }

    public static int calculateMaxIncome(char[][] seatingScheme) {
        int frontRowPrice = 10;
        int backRowPrice = 8;
        int numRows = seatingScheme.length;
        int numSeatsPerRow = seatingScheme[0].length;
        int totalNumSeats = numRows * numSeatsPerRow;
        int totalIncome;
        if (totalNumSeats <= 60) {
            totalIncome = totalNumSeats * frontRowPrice;
        } else {
            int totalNumFrontRowS = numRows / 2;
            int totalNumBackRows = totalNumFrontRowS + numRows % 2;
            totalIncome = totalNumFrontRowS * numSeatsPerRow * frontRowPrice
                    + totalNumBackRows * numSeatsPerRow * backRowPrice;
        }
        return totalIncome;
    }

    public static int checkTicketPrice(char[][] seatingScheme, int rowNum) {
        final int FRONT_ROW_PRICE = 10;
        final int BACK_ROW_PRICE = 8;
        final int TOTAL_NUM_OF_ROWS = seatingScheme.length;
        final int TOTAL_SEATS_PER_ROW = seatingScheme[0].length;
        if (TOTAL_NUM_OF_ROWS * TOTAL_SEATS_PER_ROW <= 60) {
            return FRONT_ROW_PRICE;
        } else {
            int numOfFrontRows = TOTAL_NUM_OF_ROWS / 2;
            if (rowNum <= numOfFrontRows) {
                return FRONT_ROW_PRICE;
            } else {
                return BACK_ROW_PRICE;
            }
        }
    }

    public static void printStatistics(char[][] seatingScheme) {
        int totalNumSeats = seatingScheme.length * seatingScheme[0].length;
        int numPurchasedTickets = 0;
        int currentIncome = 0;
        int maxIncome = calculateMaxIncome(seatingScheme);
        for (int i = 0; i < seatingScheme.length; i++) {
            for (int j = 0; j < seatingScheme[i].length; j++) {
                if (seatingScheme[i][j] == 'B') {
                    numPurchasedTickets++;
                    currentIncome += checkTicketPrice(seatingScheme, i + 1);
                }
            }
        }
        double percentagePurchasedTickets = (double)numPurchasedTickets / (double)totalNumSeats * 100;
        System.out.printf("\nNumber of purchased tickets: %d\n" +
                        "Percentage: %.2f%%\nCurrent income: $%d\nTotal income: $%d\n", numPurchasedTickets,
                percentagePurchasedTickets, currentIncome, maxIncome);

    }
}
