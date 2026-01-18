package edu.course.eventplanner.service;

import edu.course.eventplanner.model.*;

import java.util.*;

import static java.lang.String.valueOf;

public class SeatingPlanner{
    private final Venue venue;

    public SeatingPlanner(Venue venue) {
        this.venue = venue;
    }

    public Map<Integer, List<Guest>> generateSeating(List<Guest> guests) {
        //get the amount of tables & seats from the venue then start filling them up based on tag
        Map<String, Queue<Guest>> guestGroups = new HashMap<>();
        int tableNums = venue.getTables();
        int seatsPerTable = venue.getSeatsPerTable();
        int guestNum = guests.size();
        ArrayList<String> tags = new ArrayList<>();
       
        for (int i = 0; i < guests.size(); i++) {
            Guest currGuest = guests.get(i);
            String tag = currGuest.getGroupTag();
            if (guestGroups.containsKey(tag)) {
                guestGroups.get(tag).add(currGuest);
            } else {//when there's a new tag not yet in the map
                Queue<Guest> guestQueue = new LinkedList<>();
                guestQueue.add(currGuest);
                guestGroups.put(tag, guestQueue);
                tags.add(tag);
            }

        }//add guests to wrong tables when there's not enough seats at right table
        Map<Integer, List<Guest>> seatingMap = new HashMap<>();

        for(int i= 0; i<tableNums;i++) {
            //this loop puts an empty list of guests in each table
            seatingMap.put(i, new ArrayList<>(seatsPerTable));
        }
        for(int i=0; i<tableNums; i++) {
            List<Guest> currList = seatingMap.get(i);//list for the current table
            for(int j =0;j< tags.size();j++) {
                Queue<Guest> currQueue = guestGroups.get(tags.get(j));
                while(!currQueue.isEmpty() && currList.size()<seatsPerTable) {
                    currList.add(currQueue.poll());//add a guest to it's table
                }
            }
        }
        //Binary Search Tree for storing tables by table number
        if(seatingMap.isEmpty()){
            return null;
        }
return seatingMap;
    }

}
