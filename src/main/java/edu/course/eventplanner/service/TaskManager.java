package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Task;
import java.util.*;
//think this is done!
public class TaskManager {
    private final Queue<Task> upcoming = new LinkedList<>();
    private final Stack<Task> completed = new Stack<>();
    public void addTask(Task task) {upcoming.add(task);}
    public Task executeNextTask() {//check that this is right
        if(upcoming.size()>0) {
            Task task = upcoming.peek();
            completed.add(task);
            System.out.println("Task" + task + "completed");
            return upcoming.remove();//does this return what is being removed?

        }
            return null;
    }
    public Task undoLastTask() {
    if(completed.size()>0) {
        upcoming.add(completed.pop());
        return upcoming.peek();
    } else {
        return null;
    }
    }
    public int remainingTaskCount() { return upcoming.size(); }
}
