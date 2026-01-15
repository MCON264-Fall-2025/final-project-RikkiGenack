package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Task;
import java.util.*;
//think this is done!
public class TaskManager {
    private final Queue<Task> upcoming = new LinkedList<>();
    private final Stack<Task> completed = new Stack<>();
    public void addTask(Task task) {upcoming.add(task);}
    public Task executeNextTask() {
        if(upcoming.size()>0) {
            Task task = upcoming.peek();
            completed.add(task);
            return upcoming.remove();

        }
            return null;
    }
    public Task undoLastTask() {
    if(completed.size()>0) {
        Task t = completed.pop();
        upcoming.add(t);
        return t;
    } else {
        return null;
    }
    }
    public int remainingTaskCount() { return upcoming.size(); }
}
