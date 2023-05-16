package com.a.myapp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    // Database name
    public static String DATABASE_QUESTION = "questionBank.db";
    private static final int DATABASE_VERSION = 1; // Updated database version

    // Database table name
    public static final String QUESTION_QA_TABLE = "questionQa";
    private static final String ANSWERS_QA_TABLE = "answersQa";
    // private static final String TABLE_QUESTION_ANSWER = "QuestionAnswer";
    public static final String CATEGORY = "category";

    // All fields used in database table


    public static final String QUESTION_QA_ID = "id";
    public static final String QUESTION_QA_TEXT = "question";

    private static final String ANSWERS_QA_ID = "id";
    private static final String ANSWERS_QA_TEXT = "answer";
    private static final String ANSWERS_QA_QUESTION_ID = "question_id";

    // Question Table Create Query in this string

    // QuestionQa table create query
    private static final String CREATE_QUESTION_QA_TABLE = "CREATE TABLE " + QUESTION_QA_TABLE + " ("
	+ QUESTION_QA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	+ QUESTION_QA_TEXT + " TEXT NOT NULL, "
	+ CATEGORY + " TEXT);";

    // AnswersQa table create query
    private static final String CREATE_ANSWERS_QA_TABLE = "CREATE TABLE " + ANSWERS_QA_TABLE + " ("
	+ ANSWERS_QA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	+ ANSWERS_QA_TEXT + " TEXT NOT NULL, "
	+ ANSWERS_QA_QUESTION_ID + " INTEGER NOT NULL, "
	+ CATEGORY + " TEXT NOT NULL, " // Add the "category" column here
	+ "FOREIGN KEY(" + ANSWERS_QA_QUESTION_ID + ") REFERENCES " + QUESTION_QA_TABLE + "(" + QUESTION_QA_ID + ")"
	+ " ON DELETE CASCADE ON UPDATE CASCADE);";
    public MyDataBaseHelper(Context context) {
        super(context, DATABASE_QUESTION, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the questionQa and answersQa tables when the database is first created
        db.execSQL(CREATE_QUESTION_QA_TABLE);
        db.execSQL(CREATE_ANSWERS_QA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing tables
        // Drop the existing tables and recreate them if the database version has changed
        db.execSQL("DROP TABLE IF EXISTS " + QUESTION_QA_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ANSWERS_QA_TABLE);
        onCreate(db);

        // Recreate the tables with the new schema
        db.execSQL(CREATE_QUESTION_QA_TABLE);
        db.execSQL(CREATE_ANSWERS_QA_TABLE);
        //  db.execSQL(CREATE_TABLE_QUESTION_ANSWER);
    }



    public long addQuestion(Question2 question, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QUESTION_QA_TEXT, question.getQuestion());
        values.put(CATEGORY, category);
        long id = db.insert(QUESTION_QA_TABLE, null, values);
        db.close();
        return id;
    }

    public long addAnswer(String answer, long questionId, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ANSWERS_QA_TEXT, answer);
        values.put(ANSWERS_QA_QUESTION_ID, questionId);
        values.put(CATEGORY, category);
        long id = db.insert(ANSWERS_QA_TABLE, null, values);
        db.close();
        return id;
    }
  /*  public long addQuestions(Question2 question, List<String> answers, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues questionValues = new ContentValues();
        questionValues.put(QUESTION_QA_TEXT, question.getQuestion());
        questionValues.put(CATEGORY, category);
        long questionId = db.insert(QUESTION_QA_TABLE, null, questionValues);

        for (String answer : answers) {
            ContentValues answerValues = new ContentValues();
            answerValues.put(ANSWERS_QA_TEXT, answer);
            answerValues.put(ANSWERS_QA_QUESTION_ID, questionId);
            answerValues.put(CATEGORY, category);
            db.insert(ANSWERS_QA_TABLE, null, answerValues);
        }

        db.close();
        return questionId;
    }*/
	public long addQuestions(Question2 question, String category) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues questionValues = new ContentValues();
    questionValues.put(QUESTION_QA_TEXT, question.getQuestion());
    questionValues.put(CATEGORY, category);
    long questionId = db.insert(QUESTION_QA_TABLE, null, questionValues);

    List<String> answers = question.getAnswers();
    if (answers != null) {
        for (String answer : answers) {
            ContentValues answerValues = new ContentValues();
            answerValues.put(ANSWERS_QA_TEXT, answer);
            answerValues.put(ANSWERS_QA_QUESTION_ID, questionId);
            answerValues.put(CATEGORY, category);
            db.insert(ANSWERS_QA_TABLE, null, answerValues);
        }
    }
    db.close();
    return questionId;
}
    public List<String> getAnswersForQuestion(long questionId) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> answers = new ArrayList<>();
        String selectQuery = "SELECT " + ANSWERS_QA_TEXT + ", " + CATEGORY
			+ " FROM " + ANSWERS_QA_TABLE
			+ " WHERE " + ANSWERS_QA_QUESTION_ID + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(questionId)});
        if (cursor.moveToFirst()) {
            do {
                String answer = cursor.getString(cursor.getColumnIndex(ANSWERS_QA_TEXT));
                String category = cursor.getString(cursor.getColumnIndex(CATEGORY));
                answers.add(answer);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return answers;
    }
	public List<String> getAllAnswersForCategory(String category) {
		List<String> answers = new ArrayList<>();
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT " + ANSWERS_QA_TEXT +
			" FROM " + ANSWERS_QA_TABLE +
			" INNER JOIN " + QUESTION_QA_TABLE +
			" ON " + ANSWERS_QA_QUESTION_ID + " = " + QUESTION_QA_TABLE + "." + QUESTION_QA_ID +
			" WHERE " + QUESTION_QA_TABLE + "." + CATEGORY + " = ?";
		Cursor cursor = db.rawQuery(selectQuery, new String[]{category});
		if (cursor.moveToFirst()) {
			do {
				String answer = cursor.getString(cursor.getColumnIndex(ANSWERS_QA_TEXT));
				answers.add(answer);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return answers;
	}
    public Question2 getQuestion(long questionId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Question2 question = null;
        String selectQuery = "SELECT * FROM " + QUESTION_QA_TABLE
			+ " WHERE " + QUESTION_QA_ID + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(questionId)});
        if (cursor.moveToFirst()) {
            // Retrieve the question text and category from the cursor
            String questionText = cursor.getString(cursor.getColumnIndex(QUESTION_QA_TEXT));
            String category = cursor.getString(cursor.getColumnIndex(CATEGORY));

            // Retrieve the answers for the question
            List<String> answers = getAnswersForQuestion(questionId);

            // Create a new Question2 object with the retrieved data
            question = new Question2((int) questionId, questionText, answers, category);
        }
        cursor.close();
        db.close();
        return question;
    }
    public List<Question2> getAllQuestions(String category) {
        List<Question2> questions = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Select all questions with the given category from the questionQa table
        String selectQuestionsQuery = "SELECT * FROM " + QUESTION_QA_TABLE + " WHERE " + CATEGORY + "=?";
        Cursor cursor = db.rawQuery(selectQuestionsQuery, new String[]{category});

        // Iterate over the cursor and retrieve the questions and their answers
        if (cursor.moveToFirst()) {
            do {
                int questionId = cursor.getInt(cursor.getColumnIndex(QUESTION_QA_ID));
                String questionText = cursor.getString(cursor.getColumnIndex(QUESTION_QA_TEXT));
                List<String> answers = getAnswersForQuestion(questionId);
                String categoryValue = cursor.getString(cursor.getColumnIndex(CATEGORY));
                Question2 question = new Question2(questionId, questionText, answers, categoryValue);
                questions.add(question);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return questions;
    }

    public ArrayList<String> getAllCategoriesFromQuestionQa() {
        ArrayList<String> categories = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Retrieve distinct categories from questionbank table
        Cursor cursor = db.rawQuery("SELECT DISTINCT category FROM questionQa", null);
        if (cursor.moveToFirst()) {
            do {
                String category = cursor.getString(cursor.getColumnIndex("category"));
                categories.add(category);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // Return the categories as a result
        return categories;
    }
}
