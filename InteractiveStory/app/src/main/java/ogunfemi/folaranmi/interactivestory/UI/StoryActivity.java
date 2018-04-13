package ogunfemi.folaranmi.interactivestory.UI;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Stack;

import ogunfemi.folaranmi.interactivestory.Model.Page;
import ogunfemi.folaranmi.interactivestory.Model.Story;
import ogunfemi.folaranmi.interactivestory.R;

//Architecture set to have each page of the story operate under one activity. This activity is updates on each button click under StoryActivity.java.
    //Ordinarily, this would mean that Navigation like the 'BACK' button would bring us right to the beginning of the story instead of taking us to the previous page.
       //Steps have been taken to allow the Back button to bring us to the previous page, despite this. This is evident under StoryActivity.java
            //The Up Button will be used instead, to have the App cycle to the beginning of the story while the back button will cycle through the Back Stack to show the most recent page

public class StoryActivity extends AppCompatActivity {

    private Story story;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;
    private String name;
    private Stack<Integer> pageStack = new Stack <Integer> ();  //push every new page loaded onto this stack

    public static final String TAG = StoryActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set Member Variables
        setContentView(R.layout.activity_story);

        storyImageView = (ImageView) findViewById(R.id.storyImageView);
        storyTextView = (TextView) findViewById(R.id.storyTextView);
        choice1Button = (Button) findViewById(R.id.choice1Button);
        choice2Button = (Button) findViewById(R.id.choice2Button);


        Intent intent = getIntent();

       name = intent.getStringExtra(getString(R.string.key_name));
        //Note: Best practice to test and make sure that the intent extra is available. This is to avoid a null pointer.
        if (name==null || name.isEmpty()){
            name = "friend";
        }
        Log.d(TAG, name);
        //testing to make sure that this name that was passed here is available

        story = new Story();
        //load page from Story whenever when activity is created and when a button is tapped on
        loadPage(0);



    }

    private void loadPage(int pageNumber) {
        pageStack.push(pageNumber);

        final Page page = story.getPage(pageNumber);


        //Setting the Source Image View
        //ContextCompat is better way to get a drawable
        Drawable image = ContextCompat.getDrawable(StoryActivity.this, page.getImageId());

        storyImageView.setImageDrawable(image);

        //Format the User's name and assign to pageText
        String pageText = getString(page.getTextId());
        //NOTE: Add Name IF AND ONLY IF Placeholder is included
        pageText = String.format(pageText , name);

        storyTextView.setText(pageText);

       //final page has no choices. Use the relevant constructor
        if (page.isFinalPage()){
            //on final page, we hide the top button and change the bottom button to "play again"

            choice1Button.setVisibility(View.INVISIBLE);
            choice2Button.setText(R.string.PLAY_AGAIN_BUTTON_TEXT);

            choice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 /*   finish(); //finishes current activity and brings us back to the main activity*/ //cAN USE THIS TO RETURN TO THE VERY FIRST, NAME ENTRY PAGE
                 loadPage(0);
                    ///IF RESTART, THIS WILL START THE USER ON PAGE0 AFTER THEY HAVE ALREADY ENTERED THEIR NAME
                }
            });
        }
        else {
            loadButtons(page);
        }
        }

    private void loadButtons(final Page page) {

        choice1Button.setVisibility(View.VISIBLE); //makes sure that the button is visible on subsequent playthroughs where the button is set to invisible on the last page

        choice1Button.setText(page.getChoice1().getTextId());
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextPage = page.getChoice1().getNextPage();
                loadPage(nextPage);
            }
        });


        //loads these pages on each click
        choice2Button.setVisibility(View.VISIBLE); //makes sure that the button is visible on subsequent playthroughs where the button is set to invisible on the last page

        choice2Button.setText(page.getChoice2().getTextId());
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextPage = page.getChoice2().getNextPage();
                loadPage(nextPage);
            }
        });
    }

    @Override
    public void onBackPressed() {
        pageStack.pop();  //removing the page from the stack

        //check to see if it is empty
        if(pageStack.isEmpty()) {
            super.onBackPressed();
        } else {
            loadPage(pageStack.pop());
                //Whenever loadPage is run, the first page(page0) will be pushed. So if the stack is not empty after the first pop, the second pop will be performed, making the stack empty, and the loadPage will then load it back on
        }
    }
}
