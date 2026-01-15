package edu.course.eventplanner;

import edu.course.eventplanner.model.Venue;
import edu.course.eventplanner.service.VenueSelector;
import edu.course.eventplanner.util.Generators;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VenueTests {
    VenueSelector vs = new VenueSelector(Generators.generateVenues());

    @Test
    void selectingVenueWithTiedCost() {
    Venue venue = vs.selectVenue(1600, 100);
    assertEquals(venue.getName(), "Palace");
    }
    @Test
    void selectingBestVenue() {
    Venue venue = vs.selectVenue(3000, 100);
    assertEquals(venue.getName(), "Palace");
    }
    @Test
    void selectingVenueWhenNoVenueInBudgetWithCapacity() {
        Venue venue = vs.selectVenue(3000, 130);
        assertEquals(venue, null);
    }
    @Test
    void selectingVenueWhenNoVenueInBudget() {
        Venue venue = vs.selectVenue(1000, 130);
        assertEquals(venue, null);
    }
}
