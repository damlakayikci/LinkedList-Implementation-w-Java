// Import the Scanner class
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Objects;
// import java.util.Scanner;

public class Project1 {
    public static void main(String[] args) throws IOException {
        FactoryImpl factory = new FactoryImpl();
        BufferedReader br = new BufferedReader( new FileReader(args[0]));
        PrintStream o = new PrintStream(args[1]);
        // Write the console output to the desired file.
        System.setOut(o);
        String line = br.readLine();
        while (line != null) {
            String[] commands = line.split(" ");
            if (Objects.equals(commands[0], "AF")) {
                Product inputProduct = new Product(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
                factory.addFirst(inputProduct);
            }
            if (Objects.equals(commands[0], "AL")) {
                Product inputProduct = new Product(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
                factory.addLast(inputProduct);
            }
            if (Objects.equals(commands[0], "A")) {
                Product inputProduct = new Product(Integer.parseInt(commands[2]), Integer.valueOf(commands[3]));
                try{
                    factory.add(Integer.parseInt(commands[1]), inputProduct);
                } catch (IndexOutOfBoundsException e){
                    System.out.println("Index out of bounds.");
                }
            }
            if (Objects.equals(commands[0], "RF")) {
                try {
                    factory.removeFirst();
                } catch (NoSuchElementException e) {
                    System.out.println("Factory is empty.");
                }
            }
            if (Objects.equals(commands[0], "RL")) {
                try {
                    factory.removeLast();
                } catch (NoSuchElementException e) {
                    System.out.println("Factory is empty.");
                }
            }
            if (Objects.equals(commands[0], "RI")) {
                try {
                    factory.removeIndex(Integer.parseInt(commands[1]));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index out of bounds.");
                }
            }
            if (Objects.equals(commands[0], "RP")){
                try{
                factory.removeProduct(Integer.parseInt(commands[1]));
                } catch (NoSuchElementException e){
                    System.out.println("Product not found.");
                }
            }
            if (Objects.equals(commands[0], "F")){
                try{
                    factory.find(Integer.parseInt(commands[1]));
                } catch (NoSuchElementException e){
                    System.out.println("Product not found.");
                }
            }
            if (Objects.equals(commands[0], "G")){
                try{
                    factory.get(Integer.parseInt(commands[1]));
                } catch (IndexOutOfBoundsException e){
                    System.out.println("Index out of bounds.");
                }
            }
            if (Objects.equals(commands[0], "U")){
                try{
                    factory.update(Integer.parseInt(commands[1]),Integer.parseInt(commands[2]));
                } catch (NoSuchElementException e){
                    System.out.println("Product not found.");
                }
            }
            if (Objects.equals(commands[0], "FD")){
                factory.filterDuplicates();
            }
            if (Objects.equals(commands[0], "R")){
                factory.reverse();
            }
            if (Objects.equals(commands[0], "P")){
                factory.print();
            }
            line = br.readLine();
        }
        br.close();

    }
}