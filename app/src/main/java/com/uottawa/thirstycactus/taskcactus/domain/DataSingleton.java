package com.uottawa.thirstycactus.taskcactus.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Peter Nguyen on 11/20/17.
 *
 * Singleton class to sync in all data throughout the app.
 */

public class DataSingleton
{
    private static DataSingleton instance;
    private boolean load = false; // flag that checks if the classes have been loaded from the database


    // ASSOCIATIONS
    private List<Task> default_tasks; // Default tasks, they cannot be removed nor added
    private List<Task> new_tasks;
    private List<Person> people;
    private List<Resource> resources;

    // CONSTRUCTOR
    /**
     * Set to private to avoid multiple instantiations
     */
    private DataSingleton()
    {
        default_tasks = new LinkedList<>();
        new_tasks = new LinkedList<>();
        people = new LinkedList<>();
        resources = new LinkedList<>();
    }

    // =============================================================================================

    // METHODS

    // =============================================================================================

    /**
     * Creates the instance of DataSingleton once and returns it
     */
    public static DataSingleton getInstance()
    {
        if (instance == null)
            instance = new DataSingleton();

        return instance;
    }

    /**
     * Loads data from the database.
     *
     *      ** Temporarily filled with Random data
     *
     */
    private void loadData()
    {
        if (!load)
        {
            // Placeholder data +++
            Parent michel = new Parent("Michel", "Balamou", null, 1, "1234");

            Person peter = new Person("Peter", "Nguyen", null);
            Person nate = new Person("Nate", "Adams", null);

            people.add(michel);
            people.add(peter);
            people.add(nate);


            default_tasks.add(new Task("Wash dishes", "", 2, null));
            default_tasks.add(new Task("Clean room", "", 4, null));
            default_tasks.add(new Task("Recycle", "", 5, null));


            new_tasks.add(new Task("Clean basement", "", 2, getDate(2017, 11, 10)));
            new_tasks.add(new Task("Finish app", "", 5, getDate(2017, 11, 15)));

            michel.assignTask(peter, new_tasks.get(1));
            michel.assignTask(nate, new_tasks.get(0));
            michel.assignTask(nate, default_tasks.get(1));

            nate.getTasks().get(1).setDone(true);
            // Placeholder data ---
        }

        load = true;
    }

    /**
     * Converts Year, Month and Day into Date.
     *
     * The reason for using this is because the Date constructor has been depreciated and
     * this is the new accepted way of making a date due to Internalization issues.
     */
    private Date getDate(int year, int month, int day)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);

        return cal.getTime();
    }


    // =============================================================================================

    // GETTERS: returns full lists

    // =============================================================================================

    /**
     * Returns the list of default/premade tasks
     */
    public List<Task> getDefaultTasks()
    {
        loadData();
        return default_tasks;
    }

    /**
     * Returns the list of new tasks
     */
    public List<Task> getNewTasks()
    {
        loadData();
        return new_tasks;
    }

    /**
     * Returns the list of users from the database.
     */
    public List<Person> getUsers()
    {
        loadData();
        return people;
    }


    /**
     * Returns the list of resources from the database.
     */
    public List<Resource> getResources()
    {
        loadData();
        return resources;
    }

}