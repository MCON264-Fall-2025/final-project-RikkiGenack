package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Venue;
import java.util.*;

import static edu.course.eventplanner.util.Generators.generateVenues;

public class VenueSelector {
    private final List<Venue> venues;
    public VenueSelector(List<Venue> venues) { this.venues = venues; }
    public Venue selectVenue(double budget, int guestCount) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Your venues withing budget are: ");
        int j = 1;
        for(int i  = 0; i < venues.size(); i++) {
            if (venues.get(i).getCost() <= budget && venues.get(i).getCapacity() >= guestCount) {
                System.out.println("Option " + j + " : "+ venues.get(i).getName());
                j++;
            } else {
                System.out.println("There are no venues within your budget");
            }
        }
        //need to use tree
        System.out.println("Enter the option number of the venue you would like to select: ");
        String selectedVen =kb.nextLine();
        //use get name to get the right venue:

        for(int i  = 0; i < venues.size(); i++) {
        if(venues.get(i).getName().equals(selectedVen)) {
            return venues.get(i);
        }
        }
        return null;
    }
}
