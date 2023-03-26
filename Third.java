package com.example.myapp;
import android.app.*;
import android.os.*;
import android.widget.*;
import java.util.*;
import android.view.View.*;
import android.view.*;
import android.content.*;
public class Third extends Activity 
{
	
	
	
	
	
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
		
		
	final Button newQuizebutton =findViewById(R.id.startNewQuize);
	TextView score =findViewById(R.id.score);
		Intent intent = getIntent();
		int correct = intent.getIntExtra("correct",0);
		int total = intent.getIntExtra("total", 0);

		
		score.setText("number of Correct Answers is :  " + correct );
	
	TextView inCorrectAnswer=findViewById(R.id.inCorrectAnswer);
		inCorrectAnswer.setText("number of InCorrect Answers is : "+ (total) );
		
		newQuizebutton.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					startActivity(new Intent(Third.this,MainActivity.class));
					finish();
					
					// TODO: Implement this method
				}
			});
	}

	@Override
	public void onBackPressed()
	{
		// TODO: Implement this method
		startActivity(new Intent(Third.this,MainActivity.class));
		finish();
	}
	
	
	
	
	}
