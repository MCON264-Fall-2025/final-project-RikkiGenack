package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Venue;
import java.util.*;

import static edu.course.eventplanner.util.Generators.generateVenues;

public class VenueSelector {
    private final List<Venue> venues;

    public VenueSelector(List<Venue> venues) { this.venues = venues; }
    public Venue selectVenue(double budget, int guestCount) {
        ArrayList<Venue> validVenues = new ArrayList<>();
        for(int i  = 0; i < venues.size(); i++) {//go through each venue to see which one is within budget and capcaity
            if (venues.get(i).getCost() <= budget && venues.get(i).getCapacity() >= guestCount) {
                validVenues.add(venues.get(i));
            }
        }
        Collections.sort(validVenues, new Comparator<Venue>() {
            @Override
            public int compare(Venue v1, Venue v2) {
                // Sort by cost
                if (v1.getCost() != v2.getCost()) {
                    return Double.compare(v1.getCost(), v2.getCost());
                }
                // Sort by capacity
                return Integer.compare(v1.getCapacity(), v2.getCapacity());
            }
        });
        if(validVenues.size()>0)
            return validVenues.get(0);
        return null;
    }
}
