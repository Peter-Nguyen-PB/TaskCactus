package com.uottawa.thirstycactus.taskcactus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.uottawa.thirstycactus.taskcactus.adapters.TaskListview;
import com.uottawa.thirstycactus.taskcactus.adapters.UserListview;
import com.uottawa.thirstycactus.taskcactus.domain.DataSingleton;
import com.uottawa.thirstycactus.taskcactus.domain.Resource;
import com.uottawa.thirstycactus.taskcactus.fragments.CalendarFragment;

import static android.R.attr.id;

/**
 * Created by Nate Adams on 11/21/17.
 */

public class ViewSingleton
{
    // ATTRIBUTES
    private static ViewSingleton instance;
    private UserListview usr;
    private TaskListview taskListview;

    private TaskInfo taskInfo;
    private UserInfo userInfo;

    private CalendarFragment calendarFragment;

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

    public void setTaskListview(TaskListview taskListview)
    {
        this.taskListview = taskListview;
    }

    public void refreshTasks()
    {
        taskListview.notifyDataSetChanged();
    }

    public void refresh()
    {
        usr.notifyDataSetChanged();
    }

    public void setTaskInfo(TaskInfo taskInfo)
    {
        this.taskInfo = taskInfo;
    }

    public void updateTaskInfo()
    {
        if (taskInfo!=null)
            taskInfo.update();
    }


    public void setUserInfo(UserInfo userInfo)
    {
        this.userInfo = userInfo;
    }

    public void updateUserInfo()
    {
        if (userInfo!=null)
            userInfo.update();
    }

    public void showPopup(Context context, String msg)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        alertDialog.setTitle("Note");

        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


    public AlertDialog.Builder confirmationBox(final Context context, String msg)
    {
        return new AlertDialog.Builder(context)
                .setTitle("Warning")
                .setMessage(msg)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setNegativeButton(android.R.string.no, null);
    }

    public void setCalendarFragment(CalendarFragment calendarFragment)
    {
        this.calendarFragment = calendarFragment;
    }

    public void updateCalendar()
    {
        if (calendarFragment!=null)
            calendarFragment.refreshGUI();
    }
}
