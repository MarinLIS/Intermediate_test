package Task_2_Java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;



public class Raffle_pq {

    private static ArrayList<Toy> toys = new ArrayList<>();
    private static PriorityQueue<Toy> prizeList = new PriorityQueue<>();

    private static int idCounter = 0;

    public void addToy() {
        Scanner scan = new Scanner(System.in);
        String title;
        int frequency;
        while (true) {
            System.out.print("Enter toy title: ");
            title = scan.nextLine();
            if (title.isEmpty()) {
                System.out.println("Incorrect input. Try again.");
                break;
            }
            System.out.print("Enter frequency (like: 1 or 2 or 3 up to 10): ");
            String value = scan.nextLine();
            if (isDigit(value)) {
                frequency = Integer.parseInt(value);
                if (frequency <= 0) {
                    System.out.println("Incorrect input. Try again.");
                } else {
                    Toy toy = new Toy(idCounter, title, frequency);
                    if (!toys.contains(toy) || toys.size() == 0) {
                        idCounter++;
                        toys.add(toy);
                        System.out.println("You've just added a new toy");

                    } else {
                        System.out.println("This toy is already in prize fund.");
                    }
                }
            } else {
                System.out.println("Incorrect input. Try again.");
            }
            System.out.println("Prize fund is: ");
            for (Toy toy : toys) {
                System.out.println(toy);
            }
            break;
        }
    }

    public void setFrequency() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Toy ID: ");
        String value = scan.nextLine();
        if (isDigit(value)) {
            int selectedId = Integer.parseInt(value);
            if (selectedId >= 0 && selectedId < toys.size()) {
                System.out.println("Toy " + toys.get(selectedId).getToyTitle() +
                        " has frequency of victory " + toys.get(selectedId).getToyFrequency());
                System.out.print("Enter new Frequency of dropping out (like: 1 or 2 or 3 up to 10): ");
                value = scan.nextLine();
                if (isDigit(value)) {
                    int newFrequency = Integer.parseInt(value);
                    toys.get(selectedId).setToyFrequency(newFrequency);
                    System.out.println("You've changed frequency for: " + toys.get(selectedId).getToyTitle());
                } else {
                    System.out.println("Incorrect input. Try again.");
                }
            } else {
                System.out.println("There is NO such toy.");
            }
        } else {
            System.out.println("Incorrect input. Try again.");
        }
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public PriorityQueue<Toy> getShuffle() {
        if (prizeList.size() == 0) {
            Random rnd = new Random();
            for (Toy toy : toys) {
                for (int i = 0; i < toy.getToyFrequency(); i++) {
                    Toy temp = new Toy(toy.getToyId(), toy.getToyTitle(), rnd.nextInt(1, 10));
                    prizeList.add(temp);
                }
            }
        }
        return prizeList;
    }

    public void raffle() {
        if (toys.size() >= 2) {
            getShuffle();
            saveResult(prizeList.toString());
        } else {
            System.out.println("You should add at least two toys into prize fund.");
        }
    }

    private void saveResult(String text) {
        File file = new File("UPDATE.txt");
        try {
            file.createNewFile();
        } catch (Exception ignored) {
            throw new RuntimeException();
        }
        try (FileWriter fw = new FileWriter("UPDATE.txt", false)) {
            for (Toy toy : prizeList) {
                fw.write(toy + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getPrize() {
        if (!prizeList.isEmpty()) {
            Toy prizeToy = prizeList.poll();
            System.out.println("Your prize is: " + prizeToy.getToyTitle());
            saveResult(prizeList.toString());
        } else {
            System.out.println("Toys fund is empty!");
        }
    }
}
    


