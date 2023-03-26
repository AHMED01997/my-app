package com.example.myapp;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;

import android.view.View.*;

public class Second extends Activity 
{
	
	private int currentQuistionPosition=0;
	private String selectedOptionByUser ="";
	String a ="";
	String c= "";
	private Timer quTime;
	private int totalMinute=0;
	private  int second =59;
	boolean hasAnswerdQuestion = false;
	
	private List<QuistionList>quistionList;
	LinearLayout l;
	Button b;
	
	TextView t1;
	Button b1;
	Button b2;
	Intent intent;
	TextView quistion;
	TextView quistions;
	Button op1,op2,op3,op4;
	Button ok;
	
	boolean coloredOption = false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
		
		final String getSelectedTobicName =getIntent().getStringExtra("selectedTobic");
		TextView texr=findViewById(R.id.er);
		settime(texr);
		quistions=findViewById(R.id.quistions);
		quistion=findViewById(R.id.quistion);
		TextView tobicName =findViewById(R.id.tobicName);
		tobicName.setText(getSelectedTobicName);


	op1=findViewById(R.id.op1);
		op2=findViewById(R.id.op2);
		op3=findViewById(R.id.op3);
		op4=findViewById(R.id.op4);
		ok=findViewById(R.id.ok);
		quistionList=QuistionBank.getQuistion(getSelectedTobicName);
		quistions.setText((currentQuistionPosition+1)+"/"+quistionList.size());
		quistion.setText(quistionList.get(0).getquistion());
		op1.setText(quistionList.get(0).getOp1());
		op2.setText(quistionList.get(0).getOp2());
		op3.setText(quistionList.get(0).getOp3());
	    op4.setText(quistionList.get(0).getOp4());

		
		op1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					selectedOptionByUser = op1.getText().toString();
					
					quistionList.get(currentQuistionPosition).setUserSelectedAnswer(selectedOptionByUser);
					// ...
					if((!selectedOptionByUser.equals(quistionList.get(currentQuistionPosition).getanswer()))&&a==""){
						op1.setBackgroundColor(Color.RED);
						op1.setTextColor(Color.WHITE);
					a=selectedOptionByUser;}
					revealCorrectAnswer();
						}
				
			});
			
	
		op2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					selectedOptionByUser = op2.getText().toString();
					quistionList.get(currentQuistionPosition).setUserSelectedAnswer(selectedOptionByUser);
					if((!selectedOptionByUser.equals(quistionList.get(currentQuistionPosition).getanswer()))&&a==""){
						op2.setBackgroundColor(Color.RED);
						op2.setTextColor(Color.WHITE);
						a=selectedOptionByUser;
					}
					revealCorrectAnswer();
				}
			});

		
		op3.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					selectedOptionByUser = ((Button)v).getText().toString();
					quistionList.get(currentQuistionPosition).setUserSelectedAnswer(selectedOptionByUser);
					if((!selectedOptionByUser.equals(quistionList.get(currentQuistionPosition).getanswer()))&&a==""){
						op3.setBackgroundColor(Color.RED);
						op3.setTextColor(Color.WHITE);
						a=selectedOptionByUser;
					}
					revealCorrectAnswer();
				}
				
			});
		
		op4.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					selectedOptionByUser = ((Button)v).getText().toString();
					quistionList.get(currentQuistionPosition).setUserSelectedAnswer(selectedOptionByUser);
					if((!selectedOptionByUser.equals(quistionList.get(currentQuistionPosition).getanswer()))&&a==""){
						op4.setBackgroundColor(Color.RED);
						op4.setTextColor(Color.WHITE);
						a=selectedOptionByUser;
					}
					revealCorrectAnswer();
				}
			});
		
		ok.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (currentQuistionPosition == quistionList.size() - 1) {
						quTime.purge();
						quTime.cancel();
						
						Intent intent = new Intent(Second.this, Third.class);
						intent.putExtra("correct", getResult()[0]);
						intent.putExtra("total", getResult()[1]);
						startActivity(intent);
					}else if(a==""){Toast.makeText(Second.this,"choooose😪",Toast.LENGTH_SHORT).show();}
					else {
						changeNextQuistion();
					}
				}
			});
		
		
		
				
}





	private void settime (final TextView timerTextView){

        quTime = new Timer();
        quTime.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {

					if (second==0&&totalMinute==0) {

						quTime.purge();

						quTime.cancel();

						op2=findViewById(R.id.op2);
						op2.setText("NotYour");
						intent=new Intent(Second.this,Third.class);
						
						intent.putExtra("correct", getResult()[0]);
						intent.putExtra("total", getResult()[1]);
						startActivity(intent);
						finish();
					}
					else if(second==0){totalMinute--;second=59;

					}

					else {second--;}




					runOnUiThread(new Runnable() {
							@Override
							public void run() {

								timerTextView.setText(totalMinute+":"+second);

								if(second==0&&totalMinute==0)
								{Toast.makeText(getApplicationContext(),"Time Over😒",Toast.LENGTH_SHORT).show();	}
							}
						});
				}
			},1000, 1000);
    }
	
	public int []getResult() {
		int CorrectAnswer = 0;
		int incorrectAnswer = 0;
		hasAnswerdQuestion=false;
		for (int i = 0; i < quistionList.size(); i++) {
			String getUserSelectedAnswer = quistionList.get(i).getUserSelectedAnswer();
			String getanswer = quistionList.get(i).getanswer();
			
			
			if (getUserSelectedAnswer.equals(getanswer)) {
				CorrectAnswer++;
				
			} else {
				incorrectAnswer++;
				
				
			
			
			
		}
		
		}
		return new int[]{CorrectAnswer,incorrectAnswer};
		
	}
	

	@Override
	public void onBackPressed()
	{
		// TODO: Implement this method
		quTime.purge();
		quTime.cancel();
		startActivity( new Intent(Second.this,MainActivity.class));
		finish();
	}

private void buttonDisable (){
	
	op1.setEnabled(false);
	op2.setEnabled(false);
	op3.setEnabled(false);
	op4.setEnabled(false);
}
	private void buttonEnable (){

		op1.setEnabled(true);
		op2.setEnabled(true);
		op3.setEnabled(true);
		op4.setEnabled(true);
	}

	private void revealCorrectAnswer() {
		String correctAnswer = quistionList.get(currentQuistionPosition).getanswer();
		buttonDisable();
a="anyword";		// Find the button with the correct answer and change its background and text color
		if (op1.getText().toString().equals(correctAnswer)) {
			op1.setBackgroundColor(Color.GREEN);
			op1.setTextColor(Color.WHITE);
		} else if (op2.getText().toString().equals(correctAnswer)) {
			op2.setBackgroundColor(Color.GREEN);
			op2.setTextColor(Color.WHITE);
		} else if (op3.getText().toString().equals(correctAnswer)) {
			op3.setBackgroundColor(Color.GREEN);
			op3.setTextColor(Color.WHITE);
		} else if (op4.getText().toString().equals(correctAnswer)) {
			op4.setBackgroundColor(Color.GREEN);
			op4.setTextColor(Color.WHITE);
	
		}
	}
	
private void changeNextQuistion (){
	a="";
	buttonEnable();
	hasAnswerdQuestion=false;
	currentQuistionPosition++;
	if((currentQuistionPosition+1)==quistionList.size()){
		
		ok.setText("submit Quize");
		
		
	}
	if (currentQuistionPosition<quistionList.size()){
		
	
	op1.setBackgroundColor(Color.GRAY);
	op1.setTextColor(Color.BLACK);
	
		op2.setBackgroundColor(Color.GRAY);
		op2.setTextColor(Color.BLACK);
		op3.setBackgroundColor(Color.GRAY);
		op3.setTextColor(Color.BLACK);
		op4.setBackgroundColor(Color.GRAY);
		op4.setTextColor(Color.BLACK);
		quistions.setText((currentQuistionPosition+1)+"/"+quistionList.size());
		quistion.setText(quistionList.get(currentQuistionPosition).getquistion());
		op1.setText(quistionList.get(currentQuistionPosition).getOp1());
		op2.setText(quistionList.get(currentQuistionPosition).getOp2());
		op3.setText(quistionList.get(currentQuistionPosition).getOp3());
	    op4.setText(quistionList.get(currentQuistionPosition).getOp4());
		
	}
	
	
}


}
