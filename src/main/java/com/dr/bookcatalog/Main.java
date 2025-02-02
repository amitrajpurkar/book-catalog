package com.dr.bookcatalog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * a Single class that houses the concept of Book Catalog management
 * it has all relevant service functions and datamodels all encompassed
 * the reason for not modularizing the functions into packages, model-classes, 
 * service classes is because the free version of Replit only allows 
 * a single Main.java to run on its platform
 */
public class Main {

    /**
     * the main method provides the interface to interact with User
     * This method provides the main menu
     * user can select one choice from the menu
     * user can re-run his/ her choices as many times, toggle between choices as well
     * user can exter a key-word exit to break out of loop and stop the program
     * This method calls appropriate functions based on the menu selected by user
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("\n\n--BEGIN---------");
        List<Map<String, String>> catalog = loadResetCatalog();
        // printBookCatalog(catalog);
        printCatalogInTable(catalog);
        System.out.println("\n\n-----------");

        boolean exit_entered = Boolean.FALSE;
        try (Scanner kbReader = new Scanner(System.in)) {
            while (!exit_entered) {
                System.out.println("To add a new book, enter 1.");
                System.out.println("To add a borrowed book, enter 2.");
                System.out.println("To lend a book, enter 3.");
                System.out.println("To remove a book, enter 4.");
                System.out.println("To print the book catalog, enter 5.");
                System.out.println("To exit, enter 6.");
                int choice = kbReader.nextInt();
                switch (choice) {
                    case 1 -> catalog = addNewBookToCatalog(catalog, kbReader);
                    case 2 -> catalog = addBorrowedBookToCatalog(catalog, kbReader);
                    case 3 -> catalog = lendBookFromCatalog(catalog, kbReader);
                    case 4 -> catalog = removeBookFromCatalog(catalog, kbReader);
                    case 5 -> printCatalogInTable(catalog);
                    case 6 -> exit_entered = Boolean.TRUE;
                    default -> System.out.println("Invalid choice");
                }
            }
            System.out.println("\n\n---BYE BYE--------");
        }
    }

    private static List<Map<String, String>> removeBookFromCatalog(List<Map<String, String>> catalog, Scanner kbReader) {
        System.out.print("\n book title: ");
        String title = kbReader.nextLine();

        catalog.removeIf(bk -> bk.get("1.title").equals(title));
        return catalog;
    }

    /**
     * this method takes current catalog and a handle to the keyboard-reader
     * it asks the use which book is to be lend, to whom, when its lended, when are you expecting back
     * it also checks that you only lend the books that you own and not the books that you borrowed
     * 
     * user can lend one book for one call to this method
     * 
     * Note that the method explicitly takes parameters and return back the catalog
     * The method does not act on Class-variables or static class members
     * This is coded for testability; so that unit test classes can test different scenarios for the lend-method
     * 
     * @param catalog
     * @param kbReader
     * @return an updated catalog back
     */
    private static List<Map<String, String>> lendBookFromCatalog(List<Map<String, String>> catalog, Scanner kbReader) {
        System.out.print("\n book title: ");
        String title = kbReader.nextLine();

        catalog.forEach(bk -> {
            if (bk.get("1.title").equals(title)) {
                if("self".equals(bk.get("3.owner"))){
                    System.out.print("\n Whom do you want to lend this book to: ");
                    String lendTo = kbReader.nextLine();
                    System.err.print("\n when is this book borrowed on: ");
                    String borrowedOn = kbReader.nextLine();
                    System.err.print("\n when is this book due on: ");
                    String dueDate = kbReader.nextLine();

                    bk.put("5.borrowedOn", borrowedOn);
                    bk.put("6.dueDate", dueDate);
                    bk.put("7.lendTo", lendTo);
                }   else {
                    System.out.println("This book is not owned by you. you cannot lend it.");
                }
            } else {
                System.out.println("This book is not in the catalog.");
            }
        });

        return catalog;
    }

    /**
     * this method provides interaction with user to add one book borrowed from library or person into the catalog
     * user can only add one book for one call to this method
     * user will have to return to main menu to add more books
     * 
     * @param catalog
     * @param kbReader
     * @return an updated catalog back
     */
    private static List<Map<String, String>> addBorrowedBookToCatalog(List<Map<String, String>> catalog, Scanner kbReader) {
        System.out.print("\n book title: ");
        String title = kbReader.nextLine();

        System.out.print("\n book author: ");
        String author = kbReader.nextLine();

        System.out.print("\n book's owner: ");
        String owner = kbReader.nextLine();

        System.out.print("\n Library branch, if applicable: ");
        String libBranch = kbReader.nextLine();

        System.out.print("\n borrowed on: ");
        String borrowedOn = kbReader.nextLine();

        System.out.print("\n due date: ");
        String dueDate = kbReader.nextLine();

        System.out.print("\n lend to: ");
        String lendTo = kbReader.nextLine();

        Map<String, String> bk = new HashMap<>();
        bk.put("1.title", title);
        bk.put("2.author", author);
        bk.put("3.owner", owner);
        bk.put("4.libBranch", libBranch);
        bk.put("5.borrowedOn", borrowedOn);
        bk.put("6.dueDate", dueDate);
        bk.put("7.lendTo", lendTo);

        catalog.add(bk);

        return catalog;
    }

    /**
     * this method provides interaction with user of add one book purchased by self into the catalog
     * user can add one book at a time and return to main menu
     * 
     * @param catalog
     * @param kbReader
     * @return an updated catalog
     */
    private static List<Map<String, String>> addNewBookToCatalog(List<Map<String, String>> catalog, Scanner kbReader) {
        System.out.print("\n for the book you bought, enter the book title: ");
        String title = kbReader.nextLine();

        System.out.print("\n for the book you bought, enter the book author: ");
        String author = kbReader.nextLine();

        String owner = "SELF";
        String libBranch = "--";
        String borrowedOn = "--";
        String dueDate = "--";
        String lendTo = "--";

        Map<String, String> bk = new HashMap<>();
        bk.put("1.title", title);
        bk.put("2.author", author);
        bk.put("3.owner", owner);
        bk.put("4.libBranch", libBranch);
        bk.put("5.borrowedOn", borrowedOn);
        bk.put("6.dueDate", dueDate);
        bk.put("7.lendTo", lendTo);

        catalog.add(bk);

        return catalog;
    }

    /**
     * This method aims to print the book catalog supplied to it
     * the method attempts to print one header line
     * and then print one book per line
     * for each book it prints comma separated parameters in sequence of header
     * the method prints the output on console
     * 
     * https://www.baeldung.com/java-console-ascii-make-table
     * https://stackoverflow.com/questions/15215326/how-can-i-create-table-using-ascii-in-a-console
     * https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Java-print-table-format-printf-chart-console-scanner-println-line
     * 
     * @param catalog
     */
    private static void printBookCatalog(List<Map<String, String>> catalog) {
        Map<String, String> prntBk;
        StringBuilder hdr = new StringBuilder();
        StringBuilder line;
        // Set<String> keys = catalog.get(0).keySet();
        List<String> keyList = new ArrayList<>();
        keyList.add("1.title");
        keyList.add("2.author");
        keyList.add("3.owner");
        keyList.add("4.libBranch");
        keyList.add("5.borrowedOn");
        keyList.add("6.dueDate");
        keyList.add("7.lendTo");

        hdr.append("( ");
        for (String ky : keyList) {
            switch (ky) {
                case "1.title" -> hdr.append(ky).append(";\t");
                case "2.author" -> hdr.append(ky).append(";\t");
                case "3.owner" -> hdr.append(ky).append(";\t");
                case "4.libBranch" -> hdr.append(ky).append(";\t");
                case "5.borrowedOn" -> hdr.append(ky).append(";\t");
                case "6.dueDate" -> hdr.append(ky).append(";\t");
                case "7.lendTo" -> hdr.append(ky).append(";\t");
                default -> throw new AssertionError();
            }
        }
        hdr.append(" )");
        System.out.println(hdr.toString());

        
        for (int i = 0; i < catalog.size(); i++) {
            prntBk = catalog.get(i);
            line = new StringBuilder();
            for (String ky : keyList) {
                line.append(prntBk.get(ky)).append(";\t");
            }
            System.out.println(line.toString());
        }

        // throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * this is second attempt at print book catalog
     * it is using ascii table format
     * 
     * https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Java-print-table-format-printf-chart-console-scanner-println-line
     * @param catalog
     */
    private static void printCatalogInTable(List<Map<String, String>> catalog) {
        // -----------------  30 chars  -------------------+--20----------------+--10------+--15-----------+--12--------+--12--------+--15-----------+
        String separator = "+--------------------------------+----------------------+------------+-----------------+--------------+--------------+-----------------+";
        String formatString = "| %-30s | %-20s | %-10s | %-15s | %-12s | %-12s | %-15s |%n";
        System.out.println(separator);
        System.out.format(formatString, "title", "author", "owner", "libBranch", "borrowedOn", "dueDate", "lendTo");
        System.out.println(separator);
        for (Map<String, String> bk : catalog) {
            System.out.format(formatString, 
                bk.get("1.title").substring(0, bk.get("1.title").length()>30?30:bk.get("1.title").length()), 
                bk.get("2.author").substring(0, bk.get("2.author").length()>20?20:bk.get("2.author").length()), 
                bk.get("3.owner").substring(0, bk.get("3.owner").length()>10?10:bk.get("3.owner").length()), 
                bk.get("4.libBranch").substring(0, bk.get("4.libBranch").length()>15?15:bk.get("4.libBranch").length()), 
                bk.get("5.borrowedOn").substring(0, bk.get("5.borrowedOn").length()>12?12:bk.get("5.borrowedOn").length()), 
                bk.get("6.dueDate").substring(0, bk.get("6.dueDate").length()>12?13:bk.get("6.dueDate").length()), 
                bk.get("7.lendTo").substring(0, bk.get("7.lendTo").length()>15?15:bk.get("7.lendTo").length())
            );
            
        }
        System.out.println(separator);
    }

    /**
     * this method intends to load or make available a default set of four books in the book catalog
     * this ideally can be fetched from a persistant storage.
     * Also the method makes use of List and Map classes. This can be instead defined as a data-structure with properties and functions
     * restrictions of free version of Replit enforces this style of defining Book objects and collecting the books in a list
     * 
     * @return a List of "Book" HashMap
     */
    private static List<Map<String, String>> loadResetCatalog(){
        List<Map<String, String>> catalog = new ArrayList<>();
        Map<String, String> book = new HashMap<>();
        book.put("1.title", "The Lord of the Rings");
        book.put("2.author", "J.R.R. Tolkien");
        book.put("3.owner", "self");
        book.put("4.libBranch", "--");
        book.put("5.borrowedOn", "--");
        book.put("6.dueDate", "--");
        book.put("7.lendTo", "--");
        //book.put("8.returnedOn", "--");
        catalog.add(book);
        // book.clear();

        book = new HashMap<>();
        book.put("1.title", "The Hobbit");
        book.put("2.author", "Harper Lee");
        book.put("3.owner", "self");
        book.put("4.libBranch", "--");
        book.put("5.borrowedOn", "--");
        book.put("6.dueDate", "--");
        book.put("7.lendTo", "--");
        catalog.add(book);
        // book.clear();

        book = new HashMap<>();
        book.put("1.title", "Harry Potter and the Sorcerer's Stone");
        book.put("2.author", "J.K. Rowling");
        book.put("3.owner", "self");
        book.put("4.libBranch", "--");
        book.put("5.borrowedOn", "--");
        book.put("6.dueDate", "--");
        book.put("7.lendTo", "--");
        catalog.add(book);
        // book.clear();

        book = new HashMap<>();
        book.put("1.title", "Pride and Prejudice");
        book.put("2.author", "J.R.R. Tolkien");
        book.put("3.owner", "Jacksonville Public Library");
        book.put("4.libBranch", "Southeast Branch");
        book.put("5.borrowedOn", "--");
        book.put("6.dueDate", "--");
        book.put("7.lendTo", "--");
        catalog.add(book);
        // book.clear();

        return catalog;
    }


    // -- Dhanesh's code....

    // String libBooks[] = {"The Lord of the Rings", "The Hobbit", "Harry Potter and the Sorcerer's Stone", "Pride and Prejudice", "To Kill a Mockingbird", "1984", "The Catcher in the Rye", "The Great Gatsby", "The Adventures of Huckleberry Finn", "Moby Dick", "Alice's Adventures in Wonderland", "The Little Prince", "The Wind in the Willows", "The Secret Garden", "Anne of Green Gables", "Little Women", "The Call of the Wild", "The Jungle Book", "Peter Pan", "Treasure Island", "The Adventures of Sherlock Holmes", "The Picture of Dorian Gray", "Dracula", "Frankenstein", "The Time Traveler's Wife", "The Da Vinci Code", "The Girl with the Dragon Tattoo", "Gone Girl", "The Martian", "The Book Thief", "A Brief History of Time", "Sapiens: A Brief History of Humankind", "The Power of Habit", "Thinking, Fast and Slow", "Quiet: The Power of Introverts in a World That Can't Stop Talking", "The 7 Habits of Highly Effective People", "The Innovator's Dilemma", "The Lean Startup", "Zero to One" }; // the names were generated with the aid of the Gemini copilot
    // String libAuthors[] = {"J.R.R. Tolkien", "J.K. Rowling", "Jane Austen", "Harper Lee", "George Orwell", "J.D. Salinger", "F. Scott Fitzgerald", "Mark Twain", "Herman Melville", "Lewis Carroll", "Antoine de Saint-Exupéry", "Kenneth Grahame", "Frances Hodgson Burnett", "L.M. Montgomery", "Louisa May Alcott", "Jack London", "Rudyard Kipling", "J.M. Barrie", "Robert Louis Stevenson", "Arthur Conan Doyle", "Oscar Wilde", "Bram Stoker", "Mary Shelley", "Audrey Niffenegger", "Dan Brown", "Stieg Larsson", "Gillian Flynn", "Andy Weir", "Markus Zusak", "Stephen Hawking", "Yuval Noah Harari", "Charles Duhigg", "Daniel Kahneman", "Susan Cain", "Stephen R. Covey", "Clayton M. Christensen", "Eric Ries", "Peter Thiel" };// the names were generated with the aid of the Gemini copilot
    // String libName[] = {"Jacksonville Public Library", "Gainesville Public Library", "Orlando Public Library", "Miami-Dade Public Library", "Tampa Public Library", "St. Augustine Public Library", "Tallahassee Public Library", "Fort Lauderdale Public Library" };// the names were generated with the aid of the Gemini copilot
    // String libBranch[] = {"Southeast Branch", "Westside Branch", "Main Branch", "Northside Branch", "Beaches Branch", "South Trail Branch" };// the names were generated with the aid of the Gemini copilot
    // Scanner query = new Scanner(System.in);
    // System.out.println("Are you checking out a book or returning a book? ");
    // query.nextLine();
    // if (query.equals("I am checking out a book.") || query.equals("Checking out")){
    //   Scanner libraryName1 = new Scanner(System.in);
    //   libraryName1.nextLine();
    //   Scanner libraryBranch1 = new Scanner(System.in);
    //   libraryBranch1.nextLine();
    //   if (libraryBranch1.equals("I don't have a branch.")){
    //     System.out.println("Ok.");
    //   }
    //   Scanner bookTitle1 = new Scanner(System.in);
    //   System.out.println("What is the title of your book? ");
    //   bookTitle1.nextLine();
    //   Scanner authorName1 = new Scanner(System.in);
    //   System.out.println("What is the name of the author that wrote this book? ");
    //   authorName1.nextLine();
    //   //check User and Library arrays for all the inputs the user gave. If matching, then print out the book is available. Then, remove the book title and author from their respective arrays.
    // }
    // else if (query.equals("I am returning a book.") || query.equals("Returning")){
    //   Scanner libraryName2 = new Scanner(System.in);
    //   libraryName2.nextLine();
    //   Scanner libraryBranch2 = new Scanner(System.in);
    //   libraryBranch2.nextLine();
    //   if (libraryBranch2.equals("I don't have a branch.")){
    //     System.out.println("Ok.");
    //   }
    //   Scanner bookTitle2 = new Scanner(System.in);
    //   System.out.println("What is the title of your book? ");
    //   bookTitle2.nextLine();
    //   Scanner authorName2 = new Scanner(System.in);
    //   System.out.println("What is the name of the author that wrote this book? ");
    //   authorName2.nextLine();
    //   Scanner userStatement = new Scanner(System.in);
    //   System.out.println("Is this the book you are returning:" + bookTitle2 + "by" + authorName2 + "? (Type 'true' or 'false')");
    //   userStatement.nextLine();
    //   if (userStatement.equals("true") || userStatement.equals("True")){
    //     System.out.println(bookTitle2 + " by " + authorName2 + " has been returned.");
    //   }
    //}


}
