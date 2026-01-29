package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Task;
import java.util.*;

public class TaskManager {
    public final Queue<Task> upcoming = new LinkedList<>();
    public final Stack<Task> completed = new Stack<>();
    public void addTask(Task task) {upcoming.add(task);}
    public Task executeNextTask() {
        if(!upcoming.isEmpty()) {
            Task task = upcoming.remove();
            completed.push(task);
            return task;
        }
        return null;
    }
    public Task undoLastTask() {
        if(!completed.isEmpty()) {
            Task t = completed.pop();
            //AI helped me with this line to add task to front of queue
            ((LinkedList<Task>) upcoming).addFirst(t);
            return t;
        } else {
            return null;
        }
    }
    public int remainingTaskCount() { return upcoming.size(); }
}
