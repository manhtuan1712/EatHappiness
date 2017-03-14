package com.example.user.fragmenttablayout.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.user.fragmenttablayout.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 9/4/2016.
 */


public class ZoneAdapter extends BaseExpandableListAdapter {
    private List<String> quan;
    private HashMap<String,List<String>> duong;
    private Context context;

    public ZoneAdapter(Context context, List<String> quan, HashMap<String, List<String>> duong){
        this.context = context;
        this.quan = quan;
        this.duong = duong;
    }
    @Override
    public int getGroupCount() {
        return quan.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return duong.get(quan.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return quan.get(groupPosition);
    } // get đối tượng của thằng cha đang đứng

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return duong.get(quan.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String title = (String)this.getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_layout_tphcm, null);
        }
        TextView textView = (TextView)convertView.findViewById(R.id.tvparent);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(title);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String title = (String) this.getChild(groupPosition,childPosition);
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_layout_tphcm,null);
        }
        TextView textView = (TextView)convertView.findViewById(R.id.tvchild);
        textView.setTypeface(null, Typeface.ITALIC);
        textView.setText(title);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
