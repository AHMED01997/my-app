package com.example.myapp;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import org.apache.commons.codec.net.*;
import android.view.View.*;
import android.graphics.drawable.*;
public class MainActivity extends Activity 
{
	Intent intent;
	private String selectedTopicName ="";


	
		
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	final LinearLayout java =findViewById(R.id.lay1);
	final LinearLayout java2 =findViewById(R.id.lay2);
	final LinearLayout java3 =findViewById(R.id.lay3);
	final LinearLayout java4 =findViewById(R.id.lay4);
final Button startButton =findViewById(R.id.startbutton);

		java.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					
					selectedTopicName="java";
					java.setBackgroundColor(Color.GREEN);
					java2.setBackgroundColor(Color.GRAY);
					java3.setBackgroundColor(Color.GRAY);
					java4.setBackgroundColor(Color.GRAY);
					
					// TODO: Implement this method
				}
			});
		java2.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{

					selectedTopicName="java2";
					java2.setBackgroundColor(Color.GREEN);
					java.setBackgroundColor(Color.GRAY);
					java3.setBackgroundColor(Color.GRAY);
					java4.setBackgroundColor(Color.GRAY);

					// TODO: Implement this method
				}
			});
		java3.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{

					selectedTopicName="java3";
					java3.setBackgroundColor(Color.GREEN);
					java2.setBackgroundColor(Color.GRAY);
					java.setBackgroundColor(Color.GRAY);
					java4.setBackgroundColor(Color.GRAY);

					// TODO: Implement this method
				}
			});
		java4.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{

					selectedTopicName="java4";
					java4.setBackgroundColor(Color.GREEN);
					java2.setBackgroundColor(Color.GRAY);
					java3.setBackgroundColor(Color.GRAY);
					java.setBackgroundColor(Color.GRAY);
					
					// TODO: Implement this method
				}
			});
	
		startButton.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					if(selectedTopicName.isEmpty()){
						Toast.makeText(MainActivity.this,"please complete",Toast.LENGTH_SHORT).show();
						
						}
						else{Intent intent=new Intent(getApplicationContext(),Second.class);
						intent.putExtra("selectedTobic",selectedTopicName);
						startActivity(intent);}
					// TODO: Implement this method
				}
			});
			
			
			
			
			
			
	
			
			
			
			
			
			
			
			
			
			
			
			
}




}
