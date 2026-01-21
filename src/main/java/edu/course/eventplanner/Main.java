package edu.course.eventplanner;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Task;
import edu.course.eventplanner.model.Venue;
import edu.course.eventplanner.service.BSTseating;
import edu.course.eventplanner.service.GuestListManager;
import edu.course.eventplanner.service.SeatingPlanner;
import edu.course.eventplanner.service.TaskManager;
import edu.course.eventplanner.service.VenueSelector;


import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static edu.course.eventplanner.util.Generators.GenerateGuests;
import static edu.course.eventplanner.util.Generators.generateVenues;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Event Planner Mini — see README for instructions.");

        GuestListManager guestListManager = new GuestListManager();
        int guestAmt = 0;
        List<Venue> venues = null;
        TaskManager tm = null;
        Venue myVenue = null;
        Map<Integer, List<Guest>> seating = null;
        BSTseating.BinarySearchTree bst = null;
        //make sure to add kb.nextLine(); so ints don't get swallowed by buffer

        int option = displayMenu();
        while(option!=0){
            switch (option) {
                case 1:
                    System.out.println("Enter number of guests: ");
                    guestAmt = kb.nextInt();
                    kb.nextLine();
                    System.out.println("Sample guests loaded successfully:");
                    for (Guest g : GenerateGuests(guestAmt)) {
                        guestListManager.addGuest(g);
                        System.out.print(g.getName() + " - " + g.getGroupTag() + ", 	");
                    }
                    
                    tm = new TaskManager();
                    String guestName;
                    String guestTag;
                    String task;
                    Task myTask;
                    venues = generateVenues();
                    System.out.println("Sample venues loaded successfully: " + venues.toString() + ".");
                    break;
                case 2://adding a guest:
                    System.out.println("Enter guest name: ");
                    guestName = kb.nextLine();

                    System.out.println("Enter guest category: ");
                    guestTag = kb.nextLine();
                    Guest guest = new Guest(guestName, guestTag);
                    guestListManager.addGuest(guest);
                    System.out.println("Guest added successfully.");
                    break;
                case 3://removing a guest:
                    System.out.println("Enter guest first and last name: ");
                    guestName = kb.nextLine();
                    System.out.println("Enter guest category: ");
                    guestTag = kb.nextLine();
                    String param = guestName + "," + guestTag;
                    if(guestListManager.removeGuest(param)==true)//make this into a bool, if true- worked, else didn't
                   System.out.println("Guest removed successfully.");
                    else System.out.println("Guest not found.");
                    break;
                case 4: //select a venue
                    System.out.println("Enter your event budget: ");
                    double budget = kb.nextDouble();
                    kb.nextLine();

                    System.out.println("Enter number of guests: ");
                    guestAmt = kb.nextInt();
                    kb.nextLine();

                    VenueSelector venSelect = new VenueSelector(venues);
                    myVenue = venSelect.selectVenue(budget, guestAmt);
                    if(myVenue==null){
                        System.out.println("No venue found.");
                    } else {
                        System.out.println("Selected venue: " + myVenue.getName());
                    }
                    break;
                case 5:
                    if(myVenue!=null)
                    { SeatingPlanner seatingPlanner = new SeatingPlanner(myVenue);
                        seating = seatingPlanner.generateSeating(guestListManager.getAllGuests());
                        System.out.println("Seating chart generated successfully.");
                        System.out.println("Seating chart:");
                        for (int i = 0; i < myVenue.getTables(); i++) {
                            List<Guest> tableGuests = seating.get(i);
                            System.out.print("Table " + i + ": ");
                            for (Guest g : tableGuests) {
                                System.out.print(g.getName() + "(" + g.getGroupTag() + ") ");
                            }
                            System.out.println();
                        }

                        //Binary Search Tree for storing tables by table number
                        bst =
                                new BSTseating().new BinarySearchTree();
                        for(int i=0; i<myVenue.getTables();i++) {
                            List<Guest> gs = seating.get(i);
                            bst.insert(i, gs);
                        }
                    } else {
                   System.out.println("Cannot generate seating chart without a venue.");
                    }
                   break;
                case 6://adding task
                    System.out.println("Enter task you would like to add: ");
                    task = kb.nextLine();
                    myTask = new Task(task);
                    tm.addTask(myTask);
                    break;
                case 7:
                    if(tm.executeNextTask()==null){
                        System.out.println("No tasks to complete.");
                    } System.out.println("Task completed");
                    break;
                case 8:
                    if(tm.undoLastTask()!=null){
                        System.out.println("Task undone successfully.");
                    } else {
                        System.out.println("No tasks to undo.");
                    }
                    break;
                case 9:
                    System.out.println("Event Summary: " +
                            "\n Event Venue: " + myVenue.getName() + " \nAmount of Guests: " + guestAmt);
                    if(!tm.completed.isEmpty()) {
                    	System.out.println("Completed Tasks: " + tm.completed);
                    } 
                    if(!tm.upcoming.isEmpty()) {
                    	System.out.println("Upcoming Tasks: " + tm.upcoming);
                    }
            }
            option = displayMenu();
        }

    }
    public static int displayMenu(){
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter your choice: " +
                "1.Load sample data\n" +
                "2.Add guest\n" +
                "3.Remove guest\n" +
                "4.Select venue\n" +
                "5.Generate seating chart\n" +
                "6.Add preparation task\n" +
                "7.Execute next task\n" +
                "8.Undo last task\n" +
                "9.Print event summary\n" +
                "0. to exit.");
        int option = kb.nextInt();
        kb.nextLine();
        return option;
    }
}
