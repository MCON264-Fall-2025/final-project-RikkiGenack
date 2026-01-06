package edu.course.eventplanner;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.service.GuestListManager;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EventPlannerTests {
    GuestListManager glm = new GuestListManager();
    LinkedList<Guest> guests = new LinkedList<>();
    Map<String, Guest> guestByName = new HashMap<>();
    Guest guest = new Guest("Sarah", "Family Friend");

/**Tests for GuestListManger: **/
    @Test
    void addingGuestIncreasesList() {
        glm.addGuest(guest);
        assertEquals(1, guests.size());
    }

    @Test
    void addingGuestIncreasesListWithName() {
        assertEquals("Sarah", guest.getName());
    }

    @Test
    void removingExistingGuestReturnsTrue() {
        glm.addGuest(guest);
        assertTrue(glm.removeGuest(guest.getName()));
    }
    @Test
    void removingExistingGuestReturnsFalse() {
        assertFalse(glm.removeGuest(guest.getName()));
    }

    @Test
    void findGuestReturnsCorrectName() {
        glm.addGuest(guest);
        Guest result = glm.findGuest("Sarah");
        assertEquals(guest, result);
    }

    @Test
    void findUserReturnsNullWhenNotFound() {
        assertNull(glm.findGuest("Jake"));
    }
}

// so far tested guest methods