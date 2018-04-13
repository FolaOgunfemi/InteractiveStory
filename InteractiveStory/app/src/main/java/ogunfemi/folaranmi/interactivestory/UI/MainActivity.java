package ogunfemi.folaranmi.interactivestory.UI;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ogunfemi.folaranmi.interactivestory.R;

//Architecture set to have each page of the story operate under one activity. This activity is updates on each button click under StoryActivity.java.
     //Ordinarily, this would mean that Navigation like the 'BACK' button would bring us right to the beginning of the story instead of taking us to the previous page.
       //Steps have been taken to allow the Back button to bring us to the previous page, despite this. This is evident under StoryActivity.java
           //The Android Manifest has been updated so that StoryActivity is a child of MainActivity and The Up Button will be used instead, to have the App cycle to the beginning of the story while the back button will cycle through the Back Stack to show the most recent page
public class MainActivity extends AppCompatActivity {

    private EditText nameField;
    private Button startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = (EditText)findViewById(R.id.nameEditText);
        startButton = (Button)findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameField.getText().toString();
               /* Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG ).show();*/

               startStory(name);
            }
        });

    }

    @Override
    protected void onResume() {
        //Will always run after OnCreate and also each time the Activity is resumed (back button or up button)
        super.onResume();

        //make sure the Name is reset to blank each resume
        nameField.setText("");
    }

    private void startStory(String name) {
        //Activities (and many other things in Android) are started using an object called an Intent. The intent of this Intent is to start another Activity.
        Intent intent = new Intent(MainActivity.this, StoryActivity.class);
        //Intents can be assigned data called EXTRAS. The intent will assign data as a key value pair so that the same intent can be passed to any other activity, and the particular data put in can be accessed using the String key
        Resources resources = getResources();
        String key = resources.getString(R.string.key_name);

        intent.putExtra(key, name);
        startActivity(intent);
    }
}
