# Event Planner Mini

This project demonstrates practical use of data structures:
linked lists, stacks, queues, maps, trees, sorting, and searching.

## What You Must Do
- Implement all TODO methods 
- Write JUnit 5 tests for core logic
- Pass instructor autograding tests
- Explain your design choices in this README

See Canvas assignment for full requirements.
TaskManager:
I used the upcoming queue and completed stack to add, execute, and undo tasks. 
To add a stack, I pushed it to the upcoming queue. To execute the next task, 
I removed the last element from the upcoming queue and added it to the completed stack.
To undo a task, I popped a task from completed stack and pushed it back to upcoming queue.

GuestListManager:
To find a guest, I recieved the guest name and tag in one parameter 
which then gets splitted to endure it is the right guest.
I used the findGuest() method to find the correct guest in removeGuest() as well.
Both of these methods return the guest object if existent or null. 
Guests are stored in a linked list and in a map for easy access.

VenueSelector:
To select a venue, I looped through all venues and added all valid ones(withing budget and capacity) to an ArrayList. 
I then used a sorting algorithm to sort the ArrayList and return the first element.

SeatingPlanner:
To generate seating, I used a map, arrayList and queue to organize the seating. 
I looped through all guests and added them to the map based on their category.
I then added as many guests as possible within the same category to a map with numbers
as the key and the guests as the value and created a Binary Search Tree to sort the seating properly. 

Main:
In main I used a switch statement to call the appropriate method based on the user input.
Using all the above classes, a user can create an event and plan it.