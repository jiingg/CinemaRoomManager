package cinema;
import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    static int ten$ = 10;
    static int eight$ = 8;
    static int purchasedTickets = 0;
    static int totalIncome;
    static int currentIncome = 0;
    static double percentage;
    static int row;
    static int col;
    static int chooseRows = 0;
    static int chooseSeats = 0;
    static int rowOrig;
    static int seatOrig;
    static int option;

    public static void main(String[] args) throws InterruptedException {
        // Write your code here
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        row = sc.nextInt() + 1;
        System.out.println("Enter the number of seats in each row:");
        col = sc.nextInt() + 1;

        rowOrig = row - 1;
        seatOrig = col - 1;

        String[][] arr = new String[row][col];

        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        option = sc.nextInt();


        while (option != 0) {
            switch (option) {
                case 1:
                    System.out.println();
                    System.out.println("Cinema:");
                    System.out.println(Arrays.deepToString(boughtSeatsMap(row, col, chooseRows, chooseSeats, arr))
                            .replace(",", "").replace("[[", "").replace("]", "")
                            .replace("[", "\n"));
                    break;
                case 2:
                    while(true) {
                        try {
                            System.out.println();
                            System.out.println("Enter a row number:");
                            chooseRows = sc.nextInt();

                            System.out.println("Enter a seat number in that row:");
                            chooseSeats = sc.nextInt();

                            while (arr[chooseRows][chooseSeats] == "B") {
                                System.out.println();
                                System.out.println("That ticket has already been purchased!");

                                System.out.println();
                                System.out.println("Enter a row number:");
                                chooseRows = sc.nextInt();

                                System.out.println("Enter a seat number in that row:");
                                chooseSeats = sc.nextInt();
                            }

                            ticketPrice(rowOrig, seatOrig, chooseRows);
                            boughtSeatsMap(row, col, chooseRows, chooseSeats, arr);
                            purchasedTickets += 1;
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println();
                            System.out.println("Wrong input!");
                            Thread.sleep(0);
                        }
                    }
                    break;
                case 3:
                    System.out.println();
                    System.out.printf("Number of purchased tickets: %d\n", purchasedTickets);
                    System.out.printf("Percentage: %.2f", getPercentage());
                    System.out.println("%");
                    System.out.printf("Current income: $%d\n", currentIncome);
                    System.out.printf("Total income: $%d\n", getTotalIncome());
                    break;

                default:
                    System.out.println("Wrong option");
                    break;


            }
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            option = sc.nextInt();
        }




    }

    public static void ticketPrice(int rowOrig, int seatOrig, int chooseRows) {
        System.out.println();
        if (rowOrig * seatOrig <= 60) {
            System.out.printf("Ticket price: $%d\n", ten$);
            currentIncome += ten$;
        } else if (chooseRows < (rowOrig - rowOrig / 2)){
            System.out.printf("Ticket price: $%d\n", ten$);
            currentIncome += ten$;
        } else {
            System.out.printf("Ticket price: $%d\n", eight$);
            currentIncome += eight$;
        }
    }

    public static String[][] boughtSeatsMap(int row, int col, int chooseRows, int chooseSeats, String[][] arr) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    arr[i][j] = " ";
                } else if (i == 0) {
                    arr[i][j] = j + "";
                } else if (j == 0) {
                    arr[i][j] = i + "";
                } else if (i == chooseRows && j == chooseSeats) {
                    arr[i][j] = "B";
                } else if (arr[i][j] == null || !arr[i][j].equals("B")){
                    arr[i][j] = "S";
                }
            }
        }
        return arr;
    }

    public static int getTotalIncome () {
        int backSeats = (rowOrig - rowOrig / 2) * seatOrig;
        int frontSeats = (rowOrig * seatOrig) - backSeats;
        if (rowOrig * seatOrig <= 60) {
            totalIncome = (rowOrig * seatOrig) * ten$;
        } else {
            totalIncome = (frontSeats * 10) + (backSeats * 8);
        }
        return totalIncome;
    }

    public static double getPercentage () {
        percentage = ((purchasedTickets * 100.0) / (rowOrig * seatOrig));
        return percentage;
    }
}