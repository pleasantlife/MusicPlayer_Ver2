package com.kimjinhwan.android.musicpractice;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPS on 2017-06-19.
 */

public class Data {

    static ArrayList<Music> datas;

    public void create() {

    }

    public static ArrayList<Music> read(Context context) {
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String projection[] = {MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.ARTIST
        };

        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(musicUri, projection, null, null, null);
        datas = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Music music = new Music();
                music.id = getValue(cursor, projection[0]);
                music.title = getValue(cursor, projection[1]);
                music.albumId = getValue(cursor, projection[2]);
                music.artist = getValue(cursor, projection[3]);

                music.musicUri = makeMusicUri(music.id);
                datas.add(music);
            }
        }
        cursor.close();
        return datas;
    }


     public static String getValue(Cursor cursor, String proj){
        int index = cursor.getColumnIndex(proj);
        return cursor.getString(index);
    }


    public void update() {

    }

    public void delete() {

    }

    private static Uri makeMusicUri(String musicId){
        Uri contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        return Uri.withAppendedPath(contentUri, musicId);
    }

    static class Music {
        String id;
        String title;
        String albumId;
        String artist;

        Uri musicUri;
        String albumArt;
    }

}
