import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int maxCapacity = 0;
        int totalTickets = 0;
        int ticketReleaseRate = 0;
        int ticketRetrievalRate = 0;

        System.out.print("Do you want to use previously saved Configuration data (yes/no): ");
        String useSavedData = sc.nextLine();
        while (true) {
            if (useSavedData.equals("yes") || useSavedData.equals("no")) {
                break; // Exit loop if input is valid
            } else {
                System.out.println("Error: Invalid input. Please enter 'yes' or 'no'.");
                System.out.print("Do you want to use previously saved Configuration data (yes/no): ");
                useSavedData = sc.nextLine();
            }
        }

        if (useSavedData.equals("yes")) {
            try {
                int[] configValues = readConfigurations("config.txt");
                maxCapacity = configValues[0];
                totalTickets = configValues[1];
                ticketReleaseRate = configValues[2];
                ticketRetrievalRate = configValues[3];
                System.out.println("Configuration loaded successfully.");
                System.out.println("Maximum Capacity: " + maxCapacity);
                System.out.println("Total Tickets: " + totalTickets);
                System.out.println("Ticket Release Rate: " + ticketReleaseRate);
                System.out.println("Ticket Retrieval Rate: " + ticketRetrievalRate);
            } catch (IOException e) {
                System.out.println("Error reading configuration file: " + e.getMessage());
                System.out.println("Switching to manual configuration input...");
                useSavedData = "no";
            }
        }

        if (useSavedData.equals("no")) {
            while (true) {
                try {
                    System.out.print("Enter the maximum capacity for the ticket queue: ");
                    maxCapacity = sc.nextInt();
                    sc.nextLine(); // Consume the newline character
                    if (maxCapacity <= 0) {
                        System.out.println("Error: Maximum capacity cannot be less than or equal to zero. Please try again.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Invalid input. Please enter a positive integer.");
                    sc.nextLine(); // Clear the invalid input
                }
            }

            while (true) {
                try {
                    System.out.print("Enter the total number of Tickets for each vendor: ");
                    totalTickets = sc.nextInt();
                    sc.nextLine();
                    if (totalTickets <= 0) {
                        System.out.println("Error: Total number of tickets cannot be less than or equal to zero. Please try again.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Invalid input. Please enter a positive integer.");
                    sc.nextLine();
                }
            }

            while (true) {
                try {
                    System.out.print("Enter the ticket release rate for each vendor: ");
                    ticketReleaseRate = sc.nextInt();
                    sc.nextLine();
                    if (ticketReleaseRate <= 0) {
                        System.out.println("Error: Ticket Release Rate cannot be less than or equal to zero. Please try again.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Invalid input. Please enter a positive integer.");
                    sc.nextLine();
                }
            }

            while (true) {
                try {
                    System.out.print("Enter the ticket retrieval rate for each vendor: ");
                    ticketRetrievalRate = sc.nextInt();
                    sc.nextLine();
                    if (ticketRetrievalRate <= 0) {
                        System.out.println("Error: Ticket Retrieval Rate cannot be less than or equal to zero. Please try again.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Invalid input. Please enter a positive integer.");
                    sc.nextLine();
                }
            }

            // Save parameters to a text file
            saveConfigurations(maxCapacity, totalTickets, ticketReleaseRate, ticketRetrievalRate);
            System.out.println("Configuration parameters saved to config.txt.");
        }

        String command;
        while (true) {
            System.out.print("Enter 'start' to begin: ");
            command = sc.nextLine();

            if (command.equals("stop")) {
                System.out.println("Stopping program...");
                break;

            }else if (command.equals("start")) {
                System.out.println("Starting program...");

                TicketPool ticketPool = new TicketPool(maxCapacity); //The Ticket pool which is shared among the vendors and customers

                Vendor[] vendors = new Vendor[5]; //Array of vendor, for convenience i have used an array of objects
                for (int i = 0; i < vendors.length; i++) {
                    vendors[i] = new Vendor(ticketPool,totalTickets, ticketReleaseRate);
                    Thread vendorThread = new Thread(vendors[i], "Vendor " + (i+1)); // used 3rd constructor of thread class
                    vendorThread.start();// start the vendor thread
                }

                Customer[] customers = new Customer[5];// Array of customers, for convenience i have used an array of objects
                for (int i = 0; i < customers.length; i++) {
                    customers[i] = new Customer(ticketPool,ticketRetrievalRate,3);
                    Thread customerThread = new Thread(customers[i], "Customer " + (i+1));// used 3rd constructor of thread class
                    customerThread.start();
                }
            }
        }
    }

//    private static void saveConfigurations(int maxCapacity, int totalTickets, int ticketReleaseRate, int ticketRetrievalRate) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"))) {
//            writer.write("Maximum Capacity: " + maxCapacity);
//            writer.newLine();
//            writer.write("Total Tickets: " + totalTickets);
//            writer.newLine();
//            writer.write("Ticket Release Rate: " + ticketReleaseRate);
//            writer.newLine();
//            writer.write("Ticket Retrieval Rate: " + ticketRetrievalRate);
//            writer.newLine();
//        } catch (IOException e) {
//            System.out.println("Error saving configurations: " + e.getMessage());
//        }
//    }

    private static void saveConfigurations(int maxCapacity, int totalTickets, int ticketReleaseRate, int ticketRetrievalRate) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"))) {
            writer.write("Maximum Capacity: " + maxCapacity);
            writer.newLine();
            writer.write("Total Tickets: " + totalTickets);
            writer.newLine();
            writer.write("Ticket Release Rate: " + ticketReleaseRate);
            writer.newLine();
            writer.write("Ticket Retrieval Rate: " + ticketRetrievalRate);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving configurations: " + e.getMessage());
        }
    }

//    private static int[] readConfigurations(String fileName) throws IOException {
//        int[] configValues = new int[4];
//        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
//            for (int i = 0; i < configValues.length; i++) {
//                configValues[i] = Integer.parseInt(reader.readLine().trim());
//            }
//        }
//        return configValues;
//    }

    private static int[] readConfigurations(String fileName) throws IOException {
        int[] configValues = new int[4];
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            configValues[0] = parseConfigLine(reader.readLine(), "Maximum Capacity: ");
            configValues[1] = parseConfigLine(reader.readLine(), "Total Tickets: ");
            configValues[2] = parseConfigLine(reader.readLine(), "Ticket Release Rate: ");
            configValues[3] = parseConfigLine(reader.readLine(), "Ticket Retrieval Rate: ");
        }
        return configValues;
    }

    private static int parseConfigLine(String line, String prefix) {
        if (line.startsWith(prefix)) {
            return Integer.parseInt(line.substring(prefix.length()).trim());
        } else {
            throw new IllegalArgumentException("Invalid configuration format: " + line);
        }
    }



}


