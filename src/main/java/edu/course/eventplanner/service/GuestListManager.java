package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import java.util.*;

public class GuestListManager {
    private final LinkedList<Guest> guests = new LinkedList<>();
    private final Map<String, Guest> guestByName = new HashMap<>();
    public void addGuest(Guest guest) {
        guests.add(guest);
        guestByName.put(guest.getName().toLowerCase(), guest);
    }
    public boolean removeGuest(String guestName) {
        Guest guest = findGuest(guestName);
        if (guest!=null) {
            guestByName.remove(guest.getName().toLowerCase());
            guests.remove(guest);
            return true;
        } else {
            return false;
        }
    }
    public Guest findGuest(String guestName) {
        //split string that passed in to make sure it's the right person
        if(guestByName.size()>0) {
            String[] splitted = guestName.split(",",2);
            String guestNameSplitted = splitted[0];
            String guestTag = splitted[1];
            if (guestByName.containsKey(guestNameSplitted.toLowerCase())) {//if guest exists, make sure it's the right person-check the tag:
                if (guestTag.equals(guestByName.get(guestNameSplitted).getGroupTag())) {
                    return guestByName.get(guestNameSplitted);
                }
            }
        }
        return null;
    }
    public int getGuestCount() { return guests.size(); }
    public List<Guest> getAllGuests() { return guests; }
}
