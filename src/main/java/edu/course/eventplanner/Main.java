package edu.course.eventplanner;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Task;
import edu.course.eventplanner.model.Venue;
import edu.course.eventplanner.service.GuestListManager;
import edu.course.eventplanner.service.SeatingPlanner;
import edu.course.eventplanner.service.TaskManager;
import edu.course.eventplanner.service.VenueSelector;


import java.util.List;
import java.util.Scanner;

import static edu.course.eventplanner.util.Generators.GenerateGuests;
import static edu.course.eventplanner.util.Generators.generateVenues;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        //add option to say I'm done
        System.out.println("Event Planner Mini — see README for instructions.");




       //this either returns null or the right venue
        //For venues still have to: If tied, smallest capacity that still fits
        //You must use a sorting algorithm or a Binary Search Tree to justify your choice.



        GuestListManager guestListManager = new GuestListManager();
        int guestAmt;


        //make sure to add kb.nextLine(); so ints don't get swallowed by buffer

        int option = displayMenu();
        while(option!=0){
            switch (option) {
                case 1:
                    System.out.println("Enter number of guests: ");
                    guestAmt = kb.nextInt();
                    for (Guest g : GenerateGuests(guestAmt)) {
                        guestListManager.addGuest(g);
                    }
                    TaskManager tm = new TaskManager();
                    String guestName;
                    String guestTag;
                    Venue myVenue = null;
                    String task;
                    Task myTask;
                    break;
                case 2://adding a guest:
                    System.out.println("Enter guest name: ");
                    guestName = kb.nextLine();
                    //can do it with numbers after - this category 1...
                    System.out.println("Enter guest category: ");
                    guestTag = kb.nextLine();
                    Guest guest = new Guest(guestName, guestTag);
                    guestListManager.addGuest(guest);
                    break;
                case 3://removing a guest:
                    //can update this similar to find to include tag if have time??
                    System.out.println("Enter guest first and last name: ");
                    guestName = kb.nextLine();
                    guestListManager.removeGuest(guestName);//make this into a bool, if true- worked, else didn't
                    break;
                case 4: //select a venue
                    System.out.println("Enter your event budget: ");
                    double budget = kb.nextDouble();

                    System.out.println("Enter number of guests: ");
                    guestAmt = kb.nextInt();
                    //need to use sorting algorithm or tree?!
                    List<Venue> venues = generateVenues();
                    VenueSelector venSelect = new VenueSelector(venues);
                    myVenue = venSelect.selectVenue(budget, guestAmt);
                    break;
                case 5:
                    //finish this code here and in its method
                    if(myVenue!=null) //if case 4 never happened
                    {
                        //add code to go up to case 4 first
                    }
                    SeatingPlanner seatingPlanner = new SeatingPlanner(myVenue);
                break;
                case 6://adding task
                    System.out.println("Enter task you would like to add: ");
                    task = kb.nextLine();
                    myTask = new Task(task);
                    tm.addTask(myTask);
                    break;
                case 7:
                    tm.executeNextTask();
                    break;
                case 8:
                    tm.undoLastTask();
                    break;
                case 9:
            }
            option = displayMenu();
        }





        //when doing findGuest() pass in name , tag
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
                "9.Print event summary" +
                "0. to exit.");
        int option = kb.nextInt();
        return option;
    }
}
