package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import java.util.*;

public class GuestListManager {
    private final LinkedList<Guest> guests = new LinkedList<>();
    private final Map<String, Guest> guestByName = new HashMap<>();
    public void addGuest(Guest guest) {
        guests.add(guest);
        guestByName.put(guest.getName(), guest);
        System.out.println("Guest added successfully.");
    }
    public boolean removeGuest(String guestName) {
        if (guestByName.containsKey(guestName)) {
            guestByName.remove(guestName);
            guests.remove(guestByName.get(guestName));
            System.out.println("Guest removed successfully.");
            return true;
        } else {
            System.out.println("Guest not found.");
            return false;
        }
    }
    public Guest findGuest(String guestName) {
        //split string that passed in to make sure it's the right person
        String[] splitted = guestName.split(",");
        String guestNameSplitted =splitted[0];
        String guestTag=splitted[2];
        if(guestByName.containsKey(guestNameSplitted)) {//if guest exists, make sure it's the right person-check the tag:
            if(guestTag.equals(guestByName.get(guestNameSplitted))) {
                return guestByName.get(guestNameSplitted);
            }
        }
        return null;
    }
    public int getGuestCount() { return guests.size(); }
    public List<Guest> getAllGuests() { return guests; }
}
