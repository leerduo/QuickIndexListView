package com.dystu.quickindexbar;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/6.
 */
public class PeopleAdapter extends ArrayAdapter<People> implements SectionIndexer {

    private int resourceId;

    List<String> list;

    private SparseIntArray positionOfSection;
    private SparseIntArray sectionOfPosition;

    public PeopleAdapter(Context context, int textViewResourceId, List<People> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.peopleImage = (ImageView) view.findViewById(R.id.people_image);
            viewHolder.peopleName = (TextView) view.findViewById(R.id.people_name);
            viewHolder.tvHeader = (TextView) view.findViewById(R.id.header);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        People people = getItem(position);

        String header = people.getHeader();

        if (position == 0||header != null && !header.equals(getItem(position-1).getHeader())) {

            if ("".equals(header)) {
                viewHolder.tvHeader.setVisibility(View.GONE);
            } else {

                viewHolder.tvHeader.setVisibility(View.VISIBLE);

                viewHolder.tvHeader.setText(header);
            }
        } else {
            viewHolder.tvHeader.setVisibility(View.GONE);
        }

        viewHolder.peopleName.setText(people.getName());
        viewHolder.peopleImage.setImageResource(people.getImageId());
        return view;
    }


    @Override
    public People getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Object[] getSections() {

        positionOfSection = new SparseIntArray();

        sectionOfPosition = new SparseIntArray();

        int count = getCount();

        list = new ArrayList<>();

        list.add("搜");

        positionOfSection.put(0, 0);

        sectionOfPosition.put(0, 0);

        for (int i = 1; i < count; i++) {
            String letter = getItem(i).getHeader();
            int section = list.size() - 1;
            if (list.get(section) != null && !list.get(section).equals(letter)) {
                list.add(letter);
                section++;
                positionOfSection.put(section, i);
            }
            sectionOfPosition.put(i, section);

        }

        return list.toArray(new String[list.size()]);
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return positionOfSection.get(sectionIndex);
    }

    @Override
    public int getSectionForPosition(int position) {
        return sectionOfPosition.get(position);
    }

    class ViewHolder {
        ImageView peopleImage;
        TextView peopleName;
        TextView tvHeader;
    }
}
