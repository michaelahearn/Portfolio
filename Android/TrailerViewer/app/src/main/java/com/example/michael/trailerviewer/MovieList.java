package com.example.michael.trailerviewer;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.database.sqlite.*;
import android.widget.Toast;

import java.util.ArrayList;

public class MovieList extends AppCompatActivity {
        String movieName = "junglebook"; //Holds movie name
        int movieId; //Holds ID of movie.
        int imageId;//Holds ID of image.
        ListView lstMovieList;//Lists movie titles.
        ImageView ivThumbnail;//Displays movie thumbnail.
        Button btnPlayMovie;//Starts next activity.
        TextView txtMovieDescription;//Displays movie description.
        public ArrayList<String> movieNames = new ArrayList(); //Holds movie filenames.
        ArrayList<String> movieDescriptions = new ArrayList(); //Holds movie descriptions.
        ArrayList<String> movieTitles = new ArrayList();//Holds movie titles.
        SQLiteDatabase movieDB = null;
        DBAdapter db;//Database object
        ArrayAdapter<String> adapter;//Used to build listview.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

//            //Working Raw Database example from http://stackoverflow.com/questions/3037767/create-sqlite-database-in-android
//            movieDB = this.openOrCreateDatabase("movieDB", MODE_PRIVATE, null);
//            movieDB.execSQL("DELETE FROM trailers");
//            movieDB.execSQL("CREATE TABLE IF NOT EXISTS trailers (filename VARCHAR, name VARCHAR, description VARCHAR);");
//            movieDB.execSQL("INSERT INTO trailers (filename, name, description) VALUES ('junglebook', 'Jungle Book', 'OHMAGADTHEKITTY!');");
//            movieDB.execSQL("INSERT INTO trailers (filename, name, description) VALUES ('deadpool', 'Deadpool', 'Wear the brown pants.');");
//            movieDB.execSQL("INSERT INTO trailers (filename, name, description) VALUES ('starwars', 'Star Wars', 'Watch it, you will.');");
//            Cursor c = movieDB.rawQuery("SELECT * FROM trailers" , null);
//            int Column1 = c.getColumnIndex("filename");
//            int Column2 = c.getColumnIndex("name");
//            int Column3 = c.getColumnIndex("description");
//
//            // Check if our result was valid.
//            c.moveToFirst();
//            if (c != null) {
//                // Loop through all Results
//                do {
//                    movieNames.add(c.getString(Column1));
//                    movieTitles.add(c.getString(Column2));
//                    movieDescriptions.add(c.getString(Column3));
//                }while(c.moveToNext());
//            }

            db = new DBAdapter(this);

            //add a contact- CREATE
//            db.deleteDatabase();
            //When the program opens, create or open a database, delete the current trailers table and re-create it with initial values.
            db.open();
        db.deleteTrailers();

        db.insertTrailer("junglebook", "Jungle Book", "OHMAGADTHEKITTY!", "0");
            db.insertTrailer("deadpool", "Deadpool", "Wear the brown pants.", "5");
            db.insertTrailer("starwars", "Star Wars", "Watch it, you will.", "2");
            db.insertTrailer("krampus", "Krampus", "Deck the hells.", "1");
            db.close();

            //Get all trailer info from database.
            db.open();
            Cursor c = db.getAllTrailers();
            if(c.moveToFirst())
            {
                do{
                    movieNames.add(c.getString(0));
                    movieTitles.add(c.getString(1));
                    movieDescriptions.add(c.getString(2));
                }while(c.moveToNext());
            }
            db.close();

        //Instantiate trailer information display controls and make them invisible.
        ivThumbnail = (ImageView)findViewById(R.id.ivThumbnail);
        ivThumbnail.setVisibility(View.INVISIBLE);
        txtMovieDescription = (TextView)findViewById(R.id.txtMovieDescription);
        txtMovieDescription.setVisibility(View.INVISIBLE);

//        Listview example from http://androidexample.com/Create_A_Simple_Listview_-_Android_Example/index.php?view=article_discription&aid=65&aaid=90
        lstMovieList = (ListView) findViewById(R.id.lstMovieList);

//Test used to add values directly to movie arrays.
//        ArrayList<String> movieNames = new ArrayList();
//        movieNames.add("junglebook");
//        movieNames.add("deadpool");
//        movieNames.add("starwars");
//        movieNames.add("krampus");
//        movieTitles.add("The Jungle Book");
//        movieTitles.add("Deadpool");
//        movieTitles.add("Star Wars");
//        movieTitles.add("Krampus");
//        movieDescriptions.add("OHMAGADTHEKITTY!");
//        movieDescriptions.add("Wear the brown pants.");
//        movieDescriptions.add("Watch it, you will.");
//        movieDescriptions.add("Deck the halls.");

        //Build list using movie title array.
         adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, movieTitles);
        // Assign adapter to ListView
        lstMovieList.setAdapter(adapter);

        lstMovieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

//                // ListView Clicked item index
//                int itemPosition = position;

                // ListView Clicked item value
                movieName = movieNames.get(position);
                movieId = getResources().getIdentifier(movieName, "raw", getPackageName());
                imageId = getResources().getIdentifier(movieName, "drawable", getPackageName());
                Log.v("Image ID: ", String.valueOf(movieId));
                ivThumbnail.setImageResource(imageId);
//                Intent i = new Intent(MovieList.this, MoviePlayer.class);
//                i.putExtra("MovieId", movieId);
//                startActivity(i);
                btnPlayMovie.setVisibility(View.VISIBLE);
                txtMovieDescription.setText(movieDescriptions.get(position));
                txtMovieDescription.setVisibility(View.VISIBLE);
                ivThumbnail.setVisibility(View.VISIBLE);
            }
        });

        //Variables for play button.
        movieId = getResources().getIdentifier(movieName, "raw", getPackageName());
        imageId = getResources().getIdentifier(movieName, "drawable", getPackageName());

        //Play Button
        btnPlayMovie = (Button) findViewById(R.id.btnPlayMovie);
        btnPlayMovie.setVisibility(View.INVISIBLE);
//        btnPlayMovie.setBackgroundResource(imageId);
//        btnPlayMovie.setText(movieName);
        btnPlayMovie.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.v("MovieId: ", String.valueOf(movieId));
                Intent i = new Intent(MovieList.this, MoviePlayer.class); //Put relevant movie data into bundle.
                i.putExtra("MovieId", movieId);
                i.putExtra("MovieName", movieName);
                Log.v("Movie Name: ", movieName);
                startActivity(i);//Start movie player activity.
            }
        });

    }

        @Override
        //When the user presses the back button, clear the movie arrays and re-fill with current trailer info. Update listview adapter. Return other controls to default invisible state.
        public void onResume(){
        super.onResume();
        // put your code here...
        movieNames.clear();
        movieTitles.clear();
        movieDescriptions.clear();

            db.open();
            Cursor c = db.getAllTrailers();
            if(c.moveToFirst())
            {
                do{
                    movieNames.add(c.getString(0));
                    movieTitles.add(c.getString(1));
                    movieDescriptions.add(c.getString(2));
                }while(c.moveToNext());
            }
            db.close();

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, movieTitles);
            // Assign adapter to ListView
            lstMovieList.setAdapter(adapter);
//            ivThumbnail.setImageResource();
            ivThumbnail.setVisibility(View.INVISIBLE);
            txtMovieDescription.setVisibility(View.INVISIBLE);
            btnPlayMovie.setVisibility(View.INVISIBLE);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
