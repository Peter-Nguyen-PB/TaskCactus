package com.uottawa.thirstycactus.taskcactus.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Peter Nguyen on 11/20/17.
 *
 * General purpose task class.
 */

public class Task {

    // ATTRIBUTES

    private int id; // database id of the task (DB~)

    private String name;   // Name of the task
    private String desc;   // Task description
    private int points;    // Points received for the task

    private String type; // defines the type of task; can be DEFAULT or NEW

    //ASSOCIATIONS

    private List<TaskDate> taskDates; // association class liked to Person; [*] multiplicity
    private List<Resource> resources; // allocated resources; [*] multiplicity


    //CONSTRUCTORS

    public Task(int id, String name, String desc, int points, String type)
    {
        this.id = id;

        this.name = name;
        this.desc = desc;
        this.points = points;
        this.type = type;

        taskDates = new LinkedList<>();
        resources = new LinkedList<>();
    }

    public Task(String name, String desc, int points, String type)
    {
        this(0, name, desc, points, type);
    }


    // DEFAULT TYPE OF A TASK IS NEW
    public Task(String name, String desc, int points)
    {
        // minimum arguments required for the Task class
        this(name, desc, points, "NEW");
    }


    // =============================================================================================

    // BIDIRECTIONAL LINKS

    // =============================================================================================

    /**
     * Links a taskDate to Task (UNIDIRECTIONAL)
     */
    protected void linkTaskDate(TaskDate taskDate)
    {
        taskDates.add(taskDate);
    }


    /**
     * Unlinks a taskDate to Task (UNIDIRECTIONAL)
     */
    protected void unlinkTaskDate(TaskDate taskDate)
    {
        taskDates.remove(taskDate);
    }

    /**
     * Allocate a resource.
     * Adds the resource to the list of resources.
     * Creates a BIDIRECTIONAL link between Task and Resource.
     *
     * @param r resource allocated
     */
    public void allocateResource(Resource r)
    {
        resources.add(r);
        r.useInTask(this);
    }

    /**
     * Deallocate a resource.
     * Removes the resource to the list of resources.
     * Removes the BIDIRECTIONAL link between Task and Resource.
     *
     * @param r resource deallocated
     */
    public void deallocateResource(Resource r)
    {
        resources.remove(r);
        r.removeFromTask(this);
    }


    /**
     * Deallocate a resource.
     * Removes the resource to the list of resources.
     * Removes the UNIDIRECTIONAL link between Task and Resource.
     *
     * @param r resource deallocated
     */
    public void partiallyDeallocateResource(Resource r)
    {
        resources.remove(r);
    }

    /**
     * Removes all existing association classes;
     * This allows the Task instance to be deleted without causing problems.
     */
    public void prepareToDelete()
    {
        for (TaskDate taskDate : taskDates)
            taskDate.partialRemoveTask();

        for (Resource res : resources)
            res.removeFromTask(this);

        taskDates = null; // remove reference for Garbage Collector
        resources = null; // remove reference for Garbage Collector
    }


    // =============================================================================================

    // GETTERS/SETTERS (comments omitted due to self explanatory nature)

    // =============================================================================================

    public List<Resource> getResources()
    {
        return resources;
    }

    public List<TaskDate> getTaskDates()
    {
        return taskDates;
    }


    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public int getPoints()
    {
        return points;
    }

    public String getType()
    {
        return type;
    }

    public void setID(int id)
    {
        this.id = id;
    }

    public int getID()
    {
        return id;
    }
}
