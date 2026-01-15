package edu.course.eventplanner;

import edu.course.eventplanner.model.Task;
import edu.course.eventplanner.service.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTests {
    TaskManager tm = new TaskManager();
    Task task = new Task("Test Task");

    @Test
    void testTaskExecutes() {
        tm.addTask(task);
        Task t = tm.executeNextTask();
        assert(tm.remainingTaskCount()==0);
        assertEquals(task,t);
    }
    @Test
    void testTaskUndo() {
        tm.addTask(task);
        tm.executeNextTask();
        Task t = tm.undoLastTask();
        assert(tm.remainingTaskCount()==1);
        assertEquals(task,t);
    }
    @Test
    void testUndoWithNoCompletedTasks() {
        tm.addTask(task);
        Task t = tm.undoLastTask();
        assertEquals(t,null);
    }
}
