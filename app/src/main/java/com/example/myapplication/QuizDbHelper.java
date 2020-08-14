package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                QuizContract.CategoriesTable.TABLE_NAME + "( " +
                QuizContract.CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuizContract.QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuizContract.QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                QuizContract.CategoriesTable.TABLE_NAME + "(" + QuizContract.CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Programming");
        insertCategory(c1);
        Category c2 = new Category("Geography");
        insertCategory(c2);
        Category c3 = new Category("Math");
        insertCategory(c3);
    }

    public void addCategory(Category category) {
        db = getWritableDatabase();
        insertCategory(category);
    }

    public void addCategories(List<Category> categories) {
        db = getWritableDatabase();

        for (Category category : categories) {
            insertCategory(category);
        }
    }

    private void insertCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Programming, SELECT AN SGBD",
                "ORACLE", "JAVA", "HTML", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);

        insertQuestion(q1);

        Question q12 = new Question("Programming,www is ",
                "what we want", "world wide web", "west wild world", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q12);

        Question q13 = new Question("ANDROID IS FOR ",
                "web", "MObile", "IOS", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q13);

        Question q14 = new Question("IOS IS ONLY FOR ",
                "IPHONE", "SUMSUNG", "NOKIA", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);

        insertQuestion(q14);

        Question q15 = new Question("AVAST IS  ",
                "ANTIVIRUS ", "JEUX", "CARTE GRAPHIC", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);

        insertQuestion(q15);



        Question q11 = new Question("c# is for",
                "MICROSOFT", "APPLE", "GOOGLE", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);

        insertQuestion(q11);

        Question q121 = new Question("FULLTER IS BY ",
                "GOOGLE", "ORACLE", "SUN", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q121);

        Question q131 = new Question("ANGULAR IS  ",
                "LANGUAGE", "FRAMEWORK", "LIBRARY", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q131);

        Question q141 = new Question("IBM IS ",
                "SOFT-HARDWARE COMPANY", "HARD DISK", "RAM", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);

        insertQuestion(q141);

        Question q151 = new Question("RAM IS  ",
                "RANDOM MEMORY  ", "GAME", "RANDOM ACCES MEMORY", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);

        insertQuestion(q151);

        //medium programmation


        Question q112 = new Question("What is the most basic language Microsoft made?",
                "VISUAL BASIC", "DIRECT X", "BATCH", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);

        insertQuestion(q112);

        Question q122 = new Question("angular is a framework for ",
                "back end", "front-end", "full-stack", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q122);

        Question q132 = new Question("node js is for ",
                "back-end", "front-end", "database", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q132);

        Question q142 = new Question("mongo is ",
                "database", "compiler", "ide", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);

        insertQuestion(q142);

        Question q152 = new Question(" frame work symfony ",
                "go ", "springboot", "symfony", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);

        insertQuestion(q152);



        Question q113 = new Question("visual studio is created by",
                "MICROSOFT", "APPLE", "GOOGLE", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);

        insertQuestion(q113);

        Question q123 = new Question("springboot is for ",
                "Google", "java", "SUN", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q123);

        Question q133 = new Question("react is created by  ",
                "amazon", "Facebook", "twitter", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q133);

        Question q143 = new Question("bootstrap is created by ",
                "facebook", "oracle", "twitter", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);

        insertQuestion(q143);

        Question q153 = new Question("cpu is   ",
                "memory  ", "storage item", "process unit", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);

        insertQuestion(q153);

        // programmation hard:



        Question q30 = new Question(" what is a middleware in express js  ",
                "library ", "file", "module", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);

        insertQuestion(q30);
        Question q31 = new Question(" what is poo  ",
                "programmation oriented object ", "simple procudural ", "module", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);

        insertQuestion(q31);

        Question q32 = new Question(" what is a class in poo  ",
                "is construction moule for an object ", " compiler", "simple file", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);

        insertQuestion(q32);

        Question q33 = new Question(" express js  ",
                "library ", " framework", "micro framework", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);

        insertQuestion(q33);

        Question q34 = new Question(" jest js  ",
                "library ", " framework", "micro framework", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);

        insertQuestion(q34);

        Question q35 = new Question(" most populaire language in 2020 ",
                "javascript ", " java", "c#", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);

        insertQuestion(q35);

        Question q36 = new Question(" in react there are functional component ? ",
                "yes ", "no", "...", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);

        insertQuestion(q36);

        Question q37 = new Question(" what is commmand that generate a component ",
                "ng g c ", "ng g s", "ng g t", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);

        insertQuestion(q37);









        // ******************************************************************
        Question q22 = new Question("italy is from",
                "africa", "europe", "america", 2,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        insertQuestion(q22);

        Question q23 = new Question("tunisia is from",
                "africa", "asia", "sud america", 1,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        insertQuestion(q23);

        Question q24 = new Question("brazil is from",
                "north america", "sud america", "africa", 2,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        insertQuestion(q24);

        Question q25 = new Question("malisia is from",
                "america", "asia", "europe", 2,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        insertQuestion(q25);

        Question q26 = new Question("madagascar is from",
                "africa", "europe", "asia", 1,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        insertQuestion(q26);

        Question q27 = new Question("france is from",
                "europe", "asia", "africa", 1,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        insertQuestion(q27);

        Question q28 = new Question("malisia is from",
                "asia ", "europe", "america", 1,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        insertQuestion(q28);

        Question q29 = new Question("china is from",
                "europe", "asia", "africa", 2,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        insertQuestion(q29);

        Question q299 = new Question("japan is from",
                "asia", "africa", "america", 1,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        insertQuestion(q299);

        Question q291 = new Question("algeria is from",
                "europe", "asia", "africa", 3,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        insertQuestion(q291);
        // medium geographic *******************************


        Question q223 = new Question("cairo is the capital of ",
                "egype ", "algeria", "tunisia", 1,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q223);

        Question q233 = new Question("roma is the capital of",
                "italy", "germany", "france", 1,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q233);

        Question q244 = new Question("paris is the capital ",
                "italy", "france", "germany", 2,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q244);

        Question q253 = new Question("berlin is the capital of ",
                "spain", "greece", "germany", 3,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q253);

        Question q263 = new Question("madrid is the capital of ",
                "spain", "italy", "brazil", 1,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q263);

        Question q273 = new Question("casablanca is the capital of ",
                "moroco", "egypte", "japan", 1,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q273);

        Question q283 = new Question("tokyo is the capital of ",
                "japan ", "malisiya", "south africa", 1,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q283);

        Question q293 = new Question("moscow is the capital of",
                "greece", "russia", "azerbijan", 2,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q293);

        Question q2993 = new Question("washington is the capital of",
                "usa", "canada", "greenland", 1,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q2993);

        Question q2913 = new Question("buones aires",
                "brazil", "usa", "argentina", 3,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q2913);
        // geographic hard **************************************************

        Question q80 = new Question("amazon river is in",
                "africa", "europe", "america", 3,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q80);

        Question q81 = new Question("sein river is in",
                "europe", "asia", "sud america", 1,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q81);

        Question q82 = new Question("nile river is in",
                "north america", "sud america", "africa", 3,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q82);

        Question q83 = new Question("misisipi river is in",
                "america", "asia", "europe", 1,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q83);

        Question q84 = new Question("yangji river is in ",
                "africa", "europe", "asia", 3,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q84);

        Question q85 = new Question("ganj river is in",
                "europe", "asia", "africa", 2,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q85);

        Question q86 = new Question("danube river is in ",
                "asia ", "europe", "america", 2,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q86);

        Question q87 = new Question("volga river is in",
                "europe", "asia", "africa", 1,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q87);

        Question q88 = new Question("mekang river is in",
                "asia", "africa", "america", 1,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q88);

        Question q89 = new Question("zambeze river is in ",
                "europe", "asia", "africa", 3,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q89);


        // *******************************************************************


        Question q60 = new Question("2+2 ",
                "5", "6", "4", 3,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q60);
        Question q61 = new Question("2-2 ",
                "5", "6", "0", 3,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q61);

        Question q62 = new Question("2+3 ",
                "5", "6", "4", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q62);

        Question q63 = new Question("6+3 ",
                "1", "9", "4", 2,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q63);

        Question q64 = new Question("8+2 ",
                "11", "10", "19", 2,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q64);

        Question q65 = new Question("12-3 ",
                "5", "6", "9", 3,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q65);

        Question q66 = new Question("2+1 ",
                "3", "6", "4", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q66);

        Question q67 = new Question("11-2 ",
                "9", "100", "4", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q67);

        Question q68 = new Question("1+1 ",
                "5", "2", "4", 2,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q68);
        Question q69 = new Question("13+6 ",
                "5", "6", "19", 3,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q69);

        // math medium *******************************************

        Question q70 = new Question("2+2*3 ",
                "51", "6", "8", 3,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q60);
        Question q71 = new Question("2-2-5 ",
                "58", "6-", "-9", 3,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q71);

        Question q72 = new Question("2*3*3 ",
                "18", "16", "41", 1,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q62);

        Question q73 = new Question("6-3/3 ",
                "17", "5", "4", 2,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q73);

        Question q74 = new Question("8+2*3 ",
                "11", "14", "19", 2,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q74);

        Question q75 = new Question("12*3*0 ",
                "5", "6", "0", 3,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q65);

        Question q76 = new Question("2+1*0 ",
                "0", "6", "4", 1,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q76);

        Question q77 = new Question("11-2*2 ",
                "7", "100", "4", 1,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q67);

        Question q78 = new Question("1+7*1 ",
                "5", "8", "4", 2,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q68);
        Question q79 = new Question("13+6/2",
                "5", "61", "16", 3,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q79);























        Question q4 = new Question("Math, Easy: A is correct",
                "A", "B", "C", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q4);




















        Question q5 = new Question("Non existing, Easy: A is correct",
                "A", "B", "C", 1,
                Question.DIFFICULTY_EASY, 4);
        insertQuestion(q5);
        Question q6 = new Question("Non existing, Medium: B is correct",
                "A", "B", "C", 2,
                Question.DIFFICULTY_MEDIUM, 5);
        insertQuestion(q6);
    }

    public void addQuestion(Question question) {
        db = getWritableDatabase();
        insertQuestion(question);
    }

    public void addQuestions(List<Question> questions) {
        db = getWritableDatabase();

        for (Question question : questions) {
            insertQuestion(question);
        }
    }

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuizContract.QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuizContract.QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(QuizContract.CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(QuizContract.CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuizContract.QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuizContract.QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuizContract.QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }


}