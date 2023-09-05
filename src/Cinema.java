import java.util.Arrays;

public class Cinema {
    private final int FRONT_ROW_PRICE = 10;
    private final int BACK_ROW_PRICE = 8;
    private final int TOTAL_NUM_OF_ROWS;
    private final int TOTAL_SEATS_PER_ROW;
    private final int TOTAL_NUM_SEATS;

    private final char[][] seatingScheme;


    public Cinema(int numRows, int numSeats) {
        this.seatingScheme = createSeatingScheme(numRows, numSeats);
        this.TOTAL_NUM_OF_ROWS = numRows;
        this.TOTAL_SEATS_PER_ROW = numSeats;
        this.TOTAL_NUM_SEATS = numRows * numSeats;
    }

    public int purchaseTicket(int rowNum, int seatNum) {
        if (isValidSeatNum(rowNum, seatNum) && isAvailableSeat(rowNum, seatNum)) {
            rowNum--; // subtract 1 to match array indexes
            seatNum--; // subtract 1 to match array indexes
            seatingScheme[rowNum][seatNum] = 'B';
            return checkTicketPrice(rowNum);
        } else {
            return -1;
        }
    }

    public boolean isValidSeatNum(int rowNum, int seatNum) {
        rowNum--; // subtract 1 to match array indexes
        seatNum--; // subtract 1 to match array indexes
        return rowNum >= 0 &&
                rowNum < seatingScheme.length &&
                seatNum >= 0 &&
                seatNum < seatingScheme[0].length;
    }

    public boolean isAvailableSeat(int rowNum, int seatNum) {
        rowNum--; // subtract 1 to match array indexes
        seatNum--; // subtract 1 to match array indexes
        return seatingScheme[rowNum][seatNum] == 'S';
    }

    private char[][] createSeatingScheme(int numRows, int numSeatsPerRow) {
        char[][] seatingScheme = new char[numRows][numSeatsPerRow];
        for (char[] chars : seatingScheme) {
            Arrays.fill(chars, 'S');
        }
        return seatingScheme;
    }

    public void printSeatingChart() {
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

    public int calculateMaxIncome() {
        int totalIncome;
        if (TOTAL_NUM_SEATS <= 60) {
            totalIncome = TOTAL_NUM_SEATS * FRONT_ROW_PRICE;
        } else {
            int totalNumFrontRowS = TOTAL_NUM_OF_ROWS / 2;
            int totalNumBackRows = totalNumFrontRowS + TOTAL_NUM_OF_ROWS % 2;
            totalIncome = totalNumFrontRowS * TOTAL_SEATS_PER_ROW * FRONT_ROW_PRICE
                    + totalNumBackRows * TOTAL_SEATS_PER_ROW * BACK_ROW_PRICE;
        }
        return totalIncome;
    }

    public int checkTicketPrice(int rowNum) {
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

    public void printStatistics() {
        int numPurchasedTickets = 0;
        int currentIncome = 0;
        int maxIncome = calculateMaxIncome();
        for (int i = 0; i < seatingScheme.length; i++) {
            for (int j = 0; j < seatingScheme[i].length; j++) {
                if (seatingScheme[i][j] == 'B') {
                    numPurchasedTickets++;
                    currentIncome += checkTicketPrice(i + 1);
                }
            }
        }
        double percentagePurchasedTickets = (double)numPurchasedTickets / (double)TOTAL_NUM_SEATS * 100;
        System.out.printf("""

                        Number of purchased tickets: %d
                        Percentage: %.2f%%
                        Current income: $%d
                        Total income: $%d
                        """, numPurchasedTickets,
                percentagePurchasedTickets, currentIncome, maxIncome);

    }
}

