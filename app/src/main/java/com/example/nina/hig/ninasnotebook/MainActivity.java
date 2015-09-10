package com.example.nina.hig.ninasnotebook;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    EditText enterText;
    TextView outputText;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterText = (EditText) findViewById(R.id.enterText);
        outputText = (TextView) findViewById(R.id.outputText);
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();
    }

    //Add note
    public void addButtonClicked(View view){
        Notes note = new Notes(enterText.getText().toString());
        dbHandler.addNote(note);
        printDatabase();
    }

    //Delete note
    public void deleteButtonClicked(View view){
        String inputText = enterText.getText().toString();
        dbHandler.deleteNote(inputText);
        printDatabase();
    }

    //Display notes
    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        outputText.setText(dbString);
        enterText.setText("");
    }

    //View Notes-button
    public void listButtonOnClick (View view) {

        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);

    }

    //Google search-button
    public void a2ButtonOnClick (View view) {
        Button button = (Button) view;
        Uri uriUrl = Uri.parse("http://google.com/");
        Intent launchGoogle = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchGoogle);

    }

}

