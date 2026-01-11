package edu.course.eventplanner.service;

import edu.course.eventplanner.model.*;
import java.util.*;

import static java.lang.String.valueOf;

public class SeatingPlanner {
    private final Venue venue;
    public SeatingPlanner(Venue venue) { this.venue = venue; }
    public Map<Integer, List<Guest>> generateSeating(List<Guest> guests) {
    //get the amount of tables & seats from the venue then start filling them up based on tag
        Map<String, Queue<Guest>> guestGroups = new HashMap<>();
        int tableNums= venue.getTables();
       int seats = venue.getSeatsPerTable();
       int guestNum = guests.size();
       for(int i=0;i<guests.size();i++){
           Guest currGuest = guests.get(i);
           String tag = currGuest.getGroupTag();
           //trying to name the based on name of tag
           String name = String.valueOf(tag);
           Queue<Guest> name = new LinkedList<>();
           if(guestGroups.containsKey(tag)){
               guestGroups.put(tag, currGuest);//fix- add to correct queue of guests
           }
           else{//when there's a new tag not yet in the map
               Queue<Guest> guestQueue = new LinkedList<>();
               guestQueue.add(currGuest);
               guestGroups.put(tag, guestQueue);
           }//change if and else so it just adds guests to a queue, name them by number or something
           //then afterwords add things to the hashmap.
           }//add guests to wrong tables when there's not enough seats at right table
        return null;
    }

}
