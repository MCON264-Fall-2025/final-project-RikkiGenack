package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Task;
import java.util.*;
//think this is done!
public class TaskManager {
    private final Queue<Task> upcoming = new LinkedList<>();
    private final Stack<Task> completed = new Stack<>();
    public void addTask(Task task) {upcoming.add(task);}
    public Task executeNextTask() {//check that this is right
        completed.add(upcoming.peek());
        return upcoming.remove();//does this return what is being removed?
    }
    public Task undoLastTask() {
    upcoming.add(completed.pop());
    return upcoming.peek();
    }
    public int remainingTaskCount() { return upcoming.size(); }
}
