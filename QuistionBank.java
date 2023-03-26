package com.example.myapp;
import java.util.*;

public class QuistionBank
{
	private static List<QuistionList>javaQuistion(){
		final List<QuistionList> quistionList= new ArrayList<>();
		final QuistionList quistion1=new QuistionList("what is you name","ahmed","moham","yass", "tam","ahmed","");
		final QuistionList quistion2=new QuistionList("what is your father name","ahmed","moham","yass", "tamm","moham","");
		final QuistionList quistion3=new QuistionList("what is your food","ahmed","moham","yass", "tammm","yass","");
		final QuistionList quistion4=new QuistionList("what is your food","ahmed","moham","yass", "tammmm","tammmm","");
		
		quistionList.add(quistion1);
	
		
		quistionList.add(quistion2);
		
		quistionList.add(quistion3);
		quistionList.add(quistion4);
		
		
		return quistionList;
		
	}
	
	 public static List<QuistionList>getQuistion (String selectedTobicName){
		switch (selectedTobicName){case "java":
		return javaQuistion();
		
		case "java2": return javaQuistion();
		case"java3":return javaQuistion();
		default :return javaQuistion();
		
		
		
		
		}
		 
	
		
		
		
	
	
	
	
	
	
	
}}
