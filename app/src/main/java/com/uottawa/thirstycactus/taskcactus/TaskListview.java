package com.uottawa.thirstycactus.taskcactus;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.uottawa.thirstycactus.taskcactus.domain.Task;

import java.util.List;


/**
 * Created by michelbalamou on 11/11/17.
 */

public class TaskListview extends ArrayAdapter
{
    // ATTRIBUTES

    private LayoutInflater mInflater;

    private List<Task> def_tasks;

    // CONSTRUCTOR

    public TaskListview(Activity context, List<Task> tasks)
    {
        super(context, R.layout.user_listview, tasks);
        mInflater = LayoutInflater.from(context);

        def_tasks = tasks;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        ViewHolder viewHolder;

        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.task_listview, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // SET UP OUTPUT INFORMATION +++
        viewHolder.tasknameText.setText(def_tasks.get(position).getName());
        // SET UP OUTPUT INFORMATION ---

        return convertView;
    }


    // =============================================================================================

    // VIEW HOLDER: this class stores all the graphical elements that are displayed in one row

    // =============================================================================================

    class ViewHolder
    {
        TextView tasknameText;

        ViewHolder(View v)
        {
            tasknameText = v.findViewById(R.id.taskNameText);
        }
    }
}