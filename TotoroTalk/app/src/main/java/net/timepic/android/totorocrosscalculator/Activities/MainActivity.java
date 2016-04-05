package net.timepic.android.totorocrosscalculator.Activities;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.Toast;


import net.timepic.android.totorocrosscalculator.R;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public static final String TAG = "MainActivity";
    private static final String[] items={"AskingSth", "Bathing", "Comfort",
            "Company", "FeedBabies",
            "FemaleMating", "Fighting", "Goaway", "MaleMating", "Pain",
            "Scaring", "Seekingmates", "Sleep", "Wakeup", "Warning"};
    private MediaPlayer mp = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView g=(GridView) findViewById(R.id.sounds);
        g.setAdapter(new ArrayAdapter<String>(this,
                R.layout.button_cell,
                items));
        g.setOnItemClickListener(this);
        Log.d(TAG, "onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        setUpActionBar();
        return true;
    }

    public void setUpActionBar(){
        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_articles:
                Toast.makeText(this, R.string.action_articles, Toast.LENGTH_LONG)
                        .show();

                Intent intent = new Intent(this, ArticleListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_camera:
                Toast.makeText(this, R.string.action_Camera, Toast.LENGTH_LONG)
                        .show();
                return true;
            case R.id.action_photos:
                Toast.makeText(this, R.string.action_photos, Toast.LENGTH_LONG)
                        .show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, R.string.action_settings, Toast.LENGTH_LONG)
                        .show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.d(TAG, "position"+position+"ID:"+id);
        Log.d(TAG, items[position]);
        PlayChichilaSound(items[position]);
    }

    public void PlayChichilaSound(String fileName){
        mp.reset();
        try {
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            AssetFileDescriptor descriptor = getApplicationContext().getAssets().openFd("sounds/"+fileName+".mp3");
            mp.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            mp.prepare();

        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mp.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
    }
}
