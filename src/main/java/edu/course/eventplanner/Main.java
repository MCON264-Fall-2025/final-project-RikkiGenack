package edu.course.eventplanner;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Venue;
import edu.course.eventplanner.service.GuestListManager;
import edu.course.eventplanner.service.SeatingPlanner;
import edu.course.eventplanner.service.VenueSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static edu.course.eventplanner.util.Generators.GenerateGuests;
import static edu.course.eventplanner.util.Generators.generateVenues;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        //add option to say I'm done
        System.out.println("Event Planner Mini — see README for instructions.");
        System.out.println("Enter your event budget: ");
        double budget = kb.nextDouble();

        System.out.println("Enter number of guests: ");
        int guestAmt = kb.nextInt();
        //need to use sorting algorithm or tree?!

        //create a switch statement with dif methods and what to deal with
        List<Venue> venues = generateVenues();
        VenueSelector venSelect = new VenueSelector(venues);
        Venue myVenue = venSelect.selectVenue(budget, guestAmt);//this either returns null or the right venue
        //For venues still have to: If tied, smallest capacity that still fits
        //You must use a sorting algorithm or a Binary Search Tree to justify your choice.

        //GuestListManager done!

        GuestListManager guestListManager = new GuestListManager();

        for (Guest g : GenerateGuests(guestAmt)) {
            guestListManager.addGuest(g);
        }
        //add switch here now with guest options

        SeatingPlanner seatingPlanner = new SeatingPlanner(myVenue);

        int option=0;//switch this after
        //menu:
        switch (option) {
            case 1:
                break;
            case 2:
                break;

        }
        //To add a guest here's code to pass in to add guest:
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter guest name: ");
        String guestName = sc.nextLine();
        //can do it with numbers after - this category 1...
        System.out.println("Enter guest category: ");
        String guestCategory = sc.nextLine();
        Guest guest = new Guest(guestName, guestCategory);
        guestListManager.addGuest(guest);
    }
}
