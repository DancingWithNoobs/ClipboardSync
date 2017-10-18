package com.cedric.clipboardsync.activities;

import android.content.*;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import com.cedric.clipboardsync.services.ClipboardChecker;
import com.cedric.clipboardsync.R;
import com.cedric.clipboardsync.database.ClipboardDbAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private ArrayAdapter<String> adapter;
    private List<String> historyList;
    Context thisContext;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thisContext = getApplicationContext();

        Toast.makeText( getApplicationContext(), "On Create", Toast.LENGTH_SHORT).show();

        //SetupDebugButton();
        SetupToolbar();
        SetupHistoryList();
        SetupClipboardChecker();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        ClipboardDbAdapter dbAdapter = new ClipboardDbAdapter(getApplicationContext());
        dbAdapter.open();
        ArrayList<String> clipboards = dbAdapter.getAllClipboards();
        dbAdapter.close();

        Toast.makeText( getApplicationContext(), "On start" + clipboards, Toast.LENGTH_SHORT).show();

        historyList.clear();
        historyList.addAll(clipboards);

        adapter.notifyDataSetChanged();
    }

    private void SetupClipboardChecker()
    {
        ComponentName service = startService(new Intent(getApplicationContext(), ClipboardChecker.class));
    }


    private void SetupDebugButton()
    {
//        Button clickButton = (Button) findViewById(R.id.debug_button);
//        clickButton.setOnClickListener( new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v)
//            {
//                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//
//
//
//                Boolean textToDisplay = prefs.getBoolean("notifications_clipboard", false);
//                Toast.makeText(getApplicationContext() , textToDisplay.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void SetupHistoryList()
    {
        ListView myListView = (ListView) this.findViewById(R.id.historyList);
        historyList = new ArrayList<String>();

//        ClipboardDbAdapter dbAdapter = new ClipboardDbAdapter(getApplicationContext());
//        dbAdapter.open();
//        ArrayList<String> clipboards = dbAdapter.getAllClipboards();
//        dbAdapter.close();
//
//        historyList.addAll(clipboards);

        TextView emptyText = (TextView)findViewById(R.id.list_empty_text);
        myListView.setEmptyView(emptyText);

        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_black_text, historyList);
        myListView.setAdapter(adapter);
    }

    private void SetupToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // This adds stuff to 3 dot menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);

            return true;
        }
        else if (id == R.id.action_about)
        {
            // TODO add new Fragment
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
