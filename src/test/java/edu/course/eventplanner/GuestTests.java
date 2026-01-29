package edu.course.eventplanner;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.service.GuestListManager;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GuestTests {
    GuestListManager glm = new GuestListManager();
    LinkedList<Guest> guests = new LinkedList<>();
    Map<String, Guest> guestByName = new HashMap<>();
    Guest guest = new Guest("Sarah", "Family Friend");

/**Tests for GuestListManger: **/
    @Test
    void addingGuestIncreasesList() {
        glm.addGuest(guest);
        assertEquals(1, glm.getGuestCount());
    }

    @Test
    void addingGuestIncreasesListWithName() {
        glm.addGuest(guest);
        assertEquals("Sarah", glm.getGuests().get(0).getName());
    }

    @Test
    void removingExistingGuestReturnsTrue() {
        String name = guest.getName();
        String tag = guest.getGroupTag();
        String param = name + "," + tag;
        glm.addGuest(guest);
        assertTrue(glm.removeGuest(param));
    }
    @Test
    void removingExistingGuestReturnsFalse() {
        assertFalse(glm.removeGuest(guest.getName()));
    }

    @Test
    void findGuestReturnsCorrectName() {
        glm.addGuest(guest);
        String param = guest.getName() + "," + guest.getGroupTag();
        Guest result = glm.findGuest(param);
        assertEquals(guest, result);
    }

    @Test
    void findUserReturnsNullWhenNotFound() {
        assertNull(glm.findGuest("Jake"));
    }
}
