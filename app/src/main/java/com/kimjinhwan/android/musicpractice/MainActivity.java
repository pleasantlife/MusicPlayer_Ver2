package com.kimjinhwan.android.musicpractice;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Data.Music> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datas = Data.read(this);
        listView = (ListView) findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(datas, this);
        listView.setAdapter(adapter);
    }
}


class CustomAdapter extends BaseAdapter {

    ArrayList<Data.Music> datas;
    LayoutInflater inflater;
    Context context;

    public CustomAdapter(ArrayList<Data.Music> datas, Context context) {
        this.datas = datas;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_list, null);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        Data.Music music = datas.get(position);

        holder.title.setText(music.title);
        holder.artist.setText(music.artist);


        return convertView;
    }



    class Holder {
        ImageButton imageButton;
        TextView title, artist;

        public Holder(View view){
            imageButton = (ImageButton) view.findViewById(R.id.imageButton);
            title = (TextView) view.findViewById(R.id.title);
            artist = (TextView) view.findViewById(R.id.artist);

        }


    }
}
