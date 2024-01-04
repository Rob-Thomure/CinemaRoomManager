import java.util.Arrays;
import java.util.stream.IntStream;

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

    /** sets the value of an element in seatingScheme(2d char array) to the value = B for bought
     *
     * @param rowNum row number of seat to be bought
     * @param seatNum seat number in the row to be bought
     * @return returns the price of seat bought or if seat is an invalid selection or already bought returns -1
     */
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

    /**
     * checks if row number and seat number are within range of valid row and seat numbers
     * @param rowNum row number of seat
     * @param seatNum seat number within a row
     * @return true if rowNum and seatNum are within range of valid row and seat numbers
     */
    public boolean isValidSeatNum(int rowNum, int seatNum) {
        rowNum--; // subtract 1 to match array indexes
        seatNum--; // subtract 1 to match array indexes
        return rowNum >= 0 &&
                rowNum < seatingScheme.length &&
                seatNum >= 0 &&
                seatNum < seatingScheme[0].length;
    }

    /**
     * checks if row and seat number have not been purchased
     * @param rowNum row number of seat
     * @param seatNum seat number within row
     * @return true if rowNum and seatNum have not been marked as bought
     */
    public boolean isAvailableSeat(int rowNum, int seatNum) {
        rowNum--; // subtract 1 to match array indexes
        seatNum--; // subtract 1 to match array indexes
        return seatingScheme[rowNum][seatNum] == 'S';
    }

    /**
     * creates a seating chart for the cinema room representing available seats value S
     * @param numRows number of rows within the cinema room
     * @param numSeatsPerRow number of seats in a row in the cinema room
     * @return 2d char array representing seating chart with values S for available seat
     */
    private char[][] createSeatingScheme(int numRows, int numSeatsPerRow) {
        char[][] seatingScheme = new char[numRows][numSeatsPerRow];
        for (char[] chars : seatingScheme) {
            Arrays.fill(chars, 'S');
        }
        return seatingScheme;
    }

    /**
     * prints a 2d view of the seats in the cinema room
     * value S = available seat
     * value B = bought seat
     */
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

    /**
     * calculates the max earnings the cinema room can earn
     * if the total number of seats are 60 or less price is calculated based on front row seat prices
     * if the total number of seats are greater than 60, one half of the rows are calculated at the front row prices
     *      and the other half of the rows (plus 1 if odd number rows) are calculated using back row prices
     * @return int value of the calculated maximum amount the cinema room can earn
     */
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

    /**
     * calculate the price of ticket for a seat
     * if the number of seats are 60 or less the price is based on price of a front row seat
     * if the number of seats are greater than 60, lower half of the rows are based on front row price
     *      and the other half(plus 1 row if odd number of rows) are based on back row price
     * @param rowNum the row number of seat to be purchased
     * @return int value of ticket price
     */
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

    /**
     * prints the statistics of the cinema:
     *      number of purchased seats
     *      income based on the purchased seats
     *      total possible income based on the total number of seats
     */
    public void printStatistics() {
        int numPurchasedTickets = getNumPurchasedTickets();
        int currentIncome = getCurrentIncome();
        int maxIncome = calculateMaxIncome();
        double percentagePurchasedTickets = getPercentagePurchasedTickets(numPurchasedTickets);
        System.out.println(statisticsFormatter(numPurchasedTickets, percentagePurchasedTickets,
                                                currentIncome, maxIncome));
    }

    private int getNumPurchasedTickets() {
        return Arrays.stream(seatingScheme).mapToInt(chars -> (int) IntStream
                .range(0, chars.length)
                .filter(j -> chars[j] == 'B')
                .count()).sum();
    }

    private int getCurrentIncome() {
        int currentIncome = 0;
        for (int i = 0; i < seatingScheme.length; i++) {
            for (int j = 0; j < seatingScheme[i].length; j++) {
                if (seatingScheme[i][j] == 'B') {
                    currentIncome += checkTicketPrice(i + 1);
                }
            }
        }
        return currentIncome;
    }


    private double getPercentagePurchasedTickets(int numPurchasedTickets) {
        return (double)numPurchasedTickets / (double)TOTAL_NUM_SEATS * 100;
    }

    private String statisticsFormatter(int numPurchasedTickets, double percentagePurchasedTickets, int currentIncome,
                                       int maxIncome) {
        return String.format("""

                        Number of purchased tickets: %d
                        Percentage: %.2f%%
                        Current income: $%d
                        Total income: $%d
                        """, numPurchasedTickets,
                percentagePurchasedTickets, currentIncome, maxIncome);
    }
}

