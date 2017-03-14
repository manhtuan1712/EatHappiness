package com.example.user.fragmenttablayout.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.fragmenttablayout.R;

/**
 * Created by User on 9/5/2016.
 */

public class NewHeaderApdapter extends BaseAdapter {
    private int[] mImageID;
    private String[] mName;
    private LayoutInflater layoutInflater;
    private Context context;

    public NewHeaderApdapter(Context context, String[] dataInput){
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.mName = dataInput;

        final Resources resources = context.getResources();
        this.mName = resources.getStringArray(R.array.data_name_new);
        final TypedArray typedArray = context.getResources().obtainTypedArray(R.array.data_image_new);
        final int imageCount = typedArray.length();
        mImageID = new int[imageCount];

        for(int i = 0; i<imageCount; i++){
            mImageID[i] = typedArray.getResourceId(i,0);
        }

        typedArray.recycle();
    }
    @Override
    public int getCount() {
        return mName.length;
    }

    @Override
    public Object getItem(int position) {
        return mName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final HeaderHolder holder;
        View view = convertView;
        if(view == null){
            view = layoutInflater.inflate(R.layout.item_new_header,parent,false);
            holder = new HeaderHolder();
            holder.tvname = (TextView)view.findViewById(R.id.tv_namenew_header);
            holder.img = (ImageView)view.findViewById(R.id.img_new_header);
            view.setTag(holder);
        }
        else {
            holder = (HeaderHolder) view.getTag();
        }
        holder.tvname.setText(mName[position]);
        holder.img.setImageResource(mImageID[position]);
        return view;
    }

    public  class HeaderHolder{
        TextView tvname;
        ImageView img;
    }
}
