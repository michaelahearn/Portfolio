package com.example.michael.trailerviewer;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.MediaController;

public class MoviePlayer extends AppCompatActivity {

    VideoView videoView; //Displays video
    Bundle extras; //Holds data from previous activity
    int movieId;//Holds movie ID.
    String movieName; //Holds movie name.
    DBAdapter db; //Database object.
    RatingBar rateTrailer;//Allows rating to be changed.
    float movieRating;//Stores selected rating.
    EditText txtAddFileName;//Holds filename to be added to new trailer.
    EditText txtAddName;//Holds name to be added to new trailer.
    EditText txtAddDescription; //Holds description to be added to new trailer.
    String addFileName;//Holds filename to be added to new trailer.
    String addName;//Holds name to be added to new trailer.
    String addDescription;//Holds description to be added to new trailer.
    String rating;//Rating string to be added to database.
    Float ratingFloat;

//    Log.v("Movie Id: ", String.valueOf(movieId));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_player);
        extras = getIntent().getExtras(); //Get info from previous activity.
        movieId = extras.getInt("MovieId");
        movieName = extras.getString("MovieName");

        //Create textboxes
        txtAddFileName = (EditText)findViewById(R.id.tvAddFileName);
        txtAddName = (EditText)findViewById(R.id.tvAddName);
        txtAddDescription = (EditText)findViewById(R.id.tvAddDescription);

        //Create videoview and start playing trailer.
        videoView = (VideoView)findViewById(R.id.vViewMovieTrailer);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + movieId);
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();

        //Create rating bar.
        rateTrailer = (RatingBar)findViewById(R.id.rbRateTrailer);

        //Create database object
        db = new DBAdapter(this);

        //Get current rating of trailer.
        db.open();
        Cursor c = db.getTrailer(movieName);
        if(c.moveToFirst())
            rating = c.getString(3);
        else
            Toast.makeText(this,"No contact found",Toast.LENGTH_LONG).show();
        db.close();

        //Apply current rating to rating bar.
        rateTrailer.setRating(Float.valueOf(rating));

        Log.v("Rating: ", rating);

        //Create rating button. When it is clicked, change the trailer's rating to the current value of the rating bar.
        Button btnRate = (Button)findViewById(R.id.btnRate);

        btnRate.setOnClickListener(new View.OnClickListener()
             {
                 public void onClick(View v) {

                     movieRating = rateTrailer.getRating();
                     db.open();
                     db.updateRating(movieName, String.valueOf(movieRating));
                     Log.v("MovieName: ", movieName);
                     Log.v("Rating: ", String.valueOf(movieRating));
                     db.close();
                 }
             }
        );

        //Create delete button. When it is clicked, delete the current trailer.
        Button btnDelete = (Button)findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener()
          {
              public void onClick(View v) {

                  db.open();
                  db.deleteTrailer(movieName);
                  db.close();
              }
          }
        );

        //Create restart button. When it is clicked, restar the program.
        Button btnRestart = (Button)findViewById(R.id.btnRestart);
         btnRestart.setOnClickListener(new View.OnClickListener()
           {
               public void onClick(View v) {
                   System.exit(0);
               }
           }
         );

        //Create add button. When it is clicked, add a record to the database which corresponds to the current values of various controls.
        Button btnAdd = (Button)findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener()
             {
                 public void onClick(View v) {

                     addFileName = txtAddFileName.getText().toString();
                     addName = txtAddName.getText().toString();
                     addDescription = txtAddDescription.getText().toString();
                     if(!addFileName.equals("") && !addName.equals("") && !addDescription.equals("")) {
                         db.open();
                         db.insertTrailer(addFileName, addName, addDescription, String.valueOf(rateTrailer.getRating()));
                         db.close();
                     }
                 }
             }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_player, menu);
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
