package com.dystu.quickindexbar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends Activity {

    private List<People> peopleList;

    private PeopleAdapter adapter;


    private ListView list;

    private QuickIndexBar sideBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        peopleList = new ArrayList<>();
        peopleList.clear();

        initPeople();

        list = (ListView) findViewById(R.id.list);
        sideBar = (QuickIndexBar) findViewById(R.id.sidebar);
        adapter = new PeopleAdapter(MainActivity.this, R.layout.people_item, peopleList);
        list.setAdapter(adapter);
        sideBar.setListView(list);


        for (int i = 0; i < peopleList.size(); i++) {

            People people = peopleList.get(i);

            String userName = people.getName();

            people.setName(userName);

            setUserHeader(userName, people);

        }


    }

    private void setUserHeader(String userName, People people) {

        String headerName = null;

        headerName = people.getName();

        System.out.println("headerName:" + headerName);

        if (Character.isDigit(headerName.charAt(0))) {
            people.setHeader("#");
        } else {
            people.setHeader(HanziToPinyin.getInstance().get(headerName.substring(0, 1)).get(0).target.substring(0, 1).toUpperCase());
            char header = people.getHeader().toLowerCase().charAt(0);
            if (header < 'a' || header > 'z') {
                people.setHeader("#");
            }
        }

    }

    private void initPeople() {

        People people00 = new People("123d",R.mipmap.ic_launcher);
        peopleList.add(people00);

        People people0 = new People("阿多", R.mipmap.ic_launcher);
        peopleList.add(people0);
        People people1 = new People("阿杜", R.mipmap.ic_launcher);
        peopleList.add(people1);

        People people2 = new People("白云", R.mipmap.ic_launcher);
        peopleList.add(people2);

        People people3 = new People("白百何", R.mipmap.ic_launcher);
        peopleList.add(people3);

        People people4 = new People("白衣天使", R.mipmap.ic_launcher);
        peopleList.add(people4);

        People people5 = new People("陈超", R.mipmap.ic_launcher);
        peopleList.add(people5);

        People people6 = new People("杜小丽", R.mipmap.ic_launcher);
        peopleList.add(people6);

        People people7 = new People("宋娟", R.mipmap.ic_launcher);
        peopleList.add(people7);

        People people8 = new People("贾忆", R.mipmap.ic_launcher);
        peopleList.add(people8);

        People people9 = new People("张大炮", R.mipmap.ic_launcher);
        peopleList.add(people9);

        People people10 = new People("张伟", R.mipmap.ic_launcher);
        peopleList.add(people10);

        People people11 = new People("吕子乔", R.mipmap.ic_launcher);
        peopleList.add(people11);

        People people12 = new People("曾小贤", R.mipmap.ic_launcher);
        peopleList.add(people12);

        People people13 = new People("关谷", R.mipmap.ic_launcher);
        peopleList.add(people13);

        People people14 = new People("胡一菲", R.mipmap.ic_launcher);
        peopleList.add(people14);

        People people15 = new People("罗兰", R.mipmap.ic_launcher);
        peopleList.add(people15);

        People people16 = new People("美嘉", R.mipmap.ic_launcher);
        peopleList.add(people16);

        People people17 = new People("陆展博", R.mipmap.ic_launcher);
        peopleList.add(people17);


       /* Collections.sort(peopleList, new Comparator<People>() {
            @Override
            public int compare(People lhs, People rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });*/

        Collections.sort(peopleList);

    }


}
