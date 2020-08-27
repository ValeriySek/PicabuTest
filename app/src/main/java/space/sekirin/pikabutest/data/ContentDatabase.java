package space.sekirin.pikabutest.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import space.sekirin.pikabutest.converters.Converter;
import space.sekirin.pikabutest.data.models.FavouritePostContent;
import space.sekirin.pikabutest.data.models.PostContent;

@Database(entities = {PostContent.class, FavouritePostContent.class}, version = 2, exportSchema = false)
@TypeConverters(value = Converter.class)
public abstract class ContentDatabase extends RoomDatabase {

    private static final String DB_NAME = "content.db";

    private static ContentDatabase sDatabase;

    public static ContentDatabase getInstance(Context context){
        if(sDatabase == null){
            sDatabase = Room.databaseBuilder(context, ContentDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sDatabase;
    }

    public abstract FavouriteContentDao favouriteContentDao();
}
