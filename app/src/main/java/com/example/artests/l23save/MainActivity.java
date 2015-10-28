package com.example.artests.l23save;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private int mCountCrown;
    private int mCountCat;
    private Button mButtonCrown;
    private Button mButtonCat;
    private TextView mCountTextViewCrown;
    private TextView mCountTextViewCat;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNTER_CAT = "counterCat";
    public static final String APP_PREFERENCES_COUNTER_CROWN = "counterCrown";
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mSettings=getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mButtonCrown=(Button)findViewById(R.id.buttonCrown);
        mButtonCat=(Button)findViewById(R.id.buttonCat);
        mCountTextViewCat=(TextView)findViewById(R.id.textViewCat);
        mCountTextViewCrown=(TextView)findViewById(R.id.textViewCrown);
        mButtonCrown.setOnClickListener(this);
        mButtonCat.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor=mSettings.edit();
        editor.putInt(APP_PREFERENCES_COUNTER_CAT,mCountCat);
        editor.putInt(APP_PREFERENCES_COUNTER_CROWN,mCountCrown);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mSettings.contains(APP_PREFERENCES_COUNTER_CAT)){
            mCountCat=mSettings.getInt(APP_PREFERENCES_COUNTER_CAT,0);
            mCountTextViewCat.setText(getResources().getString(R.string.catsMessageCat)+ mCountCat);
        }
        if (mSettings.contains(APP_PREFERENCES_COUNTER_CROWN)){
            mCountCrown=mSettings.getInt(APP_PREFERENCES_COUNTER_CROWN,0);
            mCountTextViewCrown.setText(getResources().getString(R.string.catsMessageCrown)+ mCountCrown);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.buttonCrown):
                mCountTextViewCrown.setText(getResources().getString(R.string.catsMessageCrown)+ ++mCountCrown);
                break;
            case (R.id.buttonCat):
                mCountTextViewCat.setText(getResources().getString(R.string.catsMessageCat)+ ++mCountCat);
                break;
        }
    }
}
