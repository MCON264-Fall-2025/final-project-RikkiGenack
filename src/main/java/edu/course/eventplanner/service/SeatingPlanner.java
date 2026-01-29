package edu.course.eventplanner.service;

import edu.course.eventplanner.model.*;

import java.util.*;

public class SeatingPlanner {
    private final Venue venue;

    public SeatingPlanner(Venue venue) {
        this.venue = venue;
    }

    public Map<Integer, List<Guest>> generateSeating(List<Guest> guests) {
        // get the amount of tables & seats from the venue then start filling them up
        // based on tag
        Map<String, Queue<Guest>> guestGroups = new HashMap<>();
        int tableNums = venue.getTables();
        int seatsPerTable = venue.getSeatsPerTable();
        ArrayList<String> tags = new ArrayList<>();

        for (int i = 0; i < guests.size(); i++) {
            Guest currGuest = guests.get(i);
            String tag = currGuest.getGroupTag();
            if (guestGroups.containsKey(tag)) {
                guestGroups.get(tag).add(currGuest);
            } else {// when there's a new tag not yet in the map
                Queue<Guest> guestQueue = new LinkedList<>();
                guestQueue.add(currGuest);
                guestGroups.put(tag, guestQueue);
                tags.add(tag);
            }

        } // add guests to wrong tables when there's not enough seats at right table

        //TreeMap for sorting seats by table number
        Map<Integer, List<Guest>> seatingMap = new TreeMap<>();

        for (int i = 0; i < tableNums; i++) {
            // this loop puts an empty list of guests in each table
            seatingMap.put(i, new ArrayList<>(seatsPerTable));
        }
        Queue<Guest> extras = new LinkedList<>();

        for (String tag : tags) {
            Queue<Guest> currQueue = guestGroups.get(tag);
            int tableIndex = 0; // start with first table for this group

            while (!currQueue.isEmpty()) {
                // Skip tables that are full
                while (tableIndex < tableNums && seatingMap.get(tableIndex).size() >= seatsPerTable) {
                    tableIndex++;
                }

                if (tableIndex >= tableNums) {
                    // All tables full for this group  put remaining guests in extras
                    extras.add(currQueue.poll());
                } else {
                    seatingMap.get(tableIndex).add(currQueue.poll());
                }
            }
        }
        // Now seat any extras in tables that still have space
        for (int i = 0; i < tableNums && !extras.isEmpty(); i++) {
            List<Guest> currList = seatingMap.get(i);
            while (currList.size() < seatsPerTable && !extras.isEmpty()) {
                currList.add(extras.poll());
            }
        }

        return seatingMap;
    }

}
