package edu.course.eventplanner;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Venue;
import edu.course.eventplanner.service.SeatingPlanner;

public class SeatingTests{

    @Test
    void allTablesAreCreated() {
        Venue venue = new Venue("Test Venue", 1000, 3, 3, 500);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = Arrays.asList(
                new Guest("A", "Family"),
                new Guest("B", "Friend")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        assertEquals(3, seating.size(), "All venue tables should exist even if some are empty");
    }

    @Test
    void noTableExceedsSeatLimit() {
        Venue venue = new Venue("Test Venue", 1000, 2, 2, 500);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = Arrays.asList(
                new Guest("A", "Family"),
                new Guest("B", "Family"),
                new Guest("C", "Family"),
                new Guest("D", "Family"),
                new Guest("E", "Friend")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        for (List<Guest> table : seating.values()) {
            assertTrue(table.size() <= 2, "No table should exceed the seat limit");
        }
    }

    @Test
    void allGuestsAreSeated() {
        Venue venue = new Venue("Test Venue", 1000, 2, 2, 500);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = Arrays.asList(
                new Guest("A", "Family"),
                new Guest("B", "Family"),
                new Guest("C", "Friend")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        int totalSeated = seating.values().stream()
                .mapToInt(List::size)
                .sum();

        assertEquals(guests.size(), totalSeated, "All guests should be seated");
    }

    @Test
    void guestsWithSameTagSitTogetherIfPossible() {
        Venue venue = new Venue("Test Venue", 1000, 2, 4, 500);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = Arrays.asList(
                new Guest("A", "Family"),
                new Guest("B", "Family"),
                new Guest("C", "Family")
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        // All Family guests should start at the first table
        List<Guest> firstTable = seating.get(1);
        int familyCount = 0;
        for (Guest g : firstTable) {
            if (g.getGroupTag().equals("Family")) familyCount++;
        }

        assertTrue(familyCount >= 1, "At least one Family guest should sit together on the first table");
    }

    @Test
    void extraGuestsArePlacedInOtherTables() {
        Venue venue = new Venue("Test Venue", 1000, 2, 2, 500);
        SeatingPlanner planner = new SeatingPlanner(venue);

        List<Guest> guests = Arrays.asList(
                new Guest("A", "Family"),
                new Guest("B", "Family"),
                new Guest("C", "Family") // extra, spills to second table
        );

        Map<Integer, List<Guest>> seating = planner.generateSeating(guests);

        // Check that the first table is full (2 guests) and the next table has the remaining guest
        assertEquals(2, seating.get(0).size(), "First table should be filled to seat limit");
        assertEquals(1, seating.get(1).size(), "Extra guest should spill to next table");
    }
}

