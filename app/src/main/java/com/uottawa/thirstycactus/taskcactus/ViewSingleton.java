package com.uottawa.thirstycactus.taskcactus;

/**
 * Created by Nate Adams on 11/21/17.
 */

public class ViewSingleton
{
    private static ViewSingleton instance;
    private UserListview usr;

    // ASSOCIATIONS


    // CONSTRUCTOR
    /**
     * Set to private to avoid multiple instantiations
     */
    private ViewSingleton() {}


    // =============================================================================================

    // METHODS

    // =============================================================================================

    /**
     * Creates the instance of DataSingleton once and returns it
     */
    public static ViewSingleton getInstance()
    {
        if (instance == null)
            instance = new ViewSingleton();

        return instance;
    }


    public void setAdapter(UserListview usr)
    {
        this.usr = usr;
    }

    public void refresh()
    {
        usr.notifyDataSetChanged();
    }

}