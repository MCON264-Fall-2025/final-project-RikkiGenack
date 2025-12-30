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
        if(guestByName.containsKey(guestName))return guestByName.get(guestName);
        return null;
    }
    public int getGuestCount() { return guests.size(); }
    public List<Guest> getAllGuests() { return guests; }
}
