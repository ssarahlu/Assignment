package com.example.assignment.Entities;

import java.util.ArrayList;

import com.example.assignment.R;


public class Quiz {

    private int id;
    private String difficulty;
    private String question;
    private String op1, op2, op3, op4, answer;
    private int photo;

    public Quiz() {
    }

    public Quiz(int id, String difficulty, String question, String op1, String op2, String op3, String op4, String answer, int photo) {
        this.id = id;
        this.difficulty = difficulty;
        this.question = question;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.answer = answer;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getOp4() {
        return op4;
    }

    public void setOp4(String op4) {
        this.op4 = op4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }


    public static ArrayList<Quiz> getQuiz() {
        ArrayList<Quiz> quiz = new ArrayList<>();
        quiz.add(new Quiz(1, "easy", "What is this note called?", "Cricket", "Crochet", "Quaver", "Krocket", "Crochet", R.drawable.crochet));
        quiz.add(new Quiz(2, "easy", "How many beats are in a minim?", "3", "1", "2", "4", "2", R.drawable.minim));
        quiz.add(new Quiz(3, "easy", "How many beats are in each bar?", "3", "4", "1", "2", "2", R.drawable.two_four_time));
        quiz.add(new Quiz(4, "easy", "What note is this?", "Middle C", "D", "G", "B", "Middle C", R.drawable.treble_middle_c));
        quiz.add(new Quiz(5, "easy", "What note is this?", "A", "C", "E", "G", "A", R.drawable.bass_a));
        quiz.add(new Quiz(6, "easy", "What note is this?", "B", "A", "D", "G", "B", R.drawable.treble_b));
        quiz.add(new Quiz(7, "easy", "What note is this?", "F", "G", "A", "C", "F", R.drawable.bass_f));
        quiz.add(new Quiz(8, "easy", "What note is this?", "C", "G", "A", "D", "D", R.drawable.treble_d));
        quiz.add(new Quiz(9, "easy", "What note is this?", "F", "G", "A", "C", "C", R.drawable.bass_middle_c));
        quiz.add(new Quiz(10, "easy", "What note is this?", "E", "G", "A", "D", "E", R.drawable.treble_e));
        quiz.add(new Quiz(11, "easy", "What is the mnemonic for treble clef lines?", "Every Good Boy Deserves Fruit", "Good Birds Don't Fly Away", "B-E-A-D", "All Cows Eat Grass", "Every Good Boy Deserves Fruit", R.drawable.every_good_boy_no_label));
        quiz.add(new Quiz(12, "easy", "What is the mnemonic for bass clef spaces?", "F-A-C-E", "All Cows Eat Grass", "C-A-F-E", "Good Birds Don't Fly Away", "All Cows Eat Grass", R.drawable.all_cows_eat_grass_no_label));
        quiz.add(new Quiz(13, "easy", "What does mezzo piano mean?", "Moderately Soft", "Moderately Loud", "Soft", "Moderately Slower", "Moderately Soft", R.drawable.mp));
        quiz.add(new Quiz(14, "easy", "What is the name of this sign?", "Diminuendo", "Decrescendo", "Descending", "Decreasing", "Decrescendo", R.drawable.decresc));
        quiz.add(new Quiz(15, "easy", "What does f stand for?", "Forte", "Faster", "Fading", "Freely", "Forte", R.drawable.f));
        quiz.add(new Quiz(16, "easy", "What is this note called?", "Semibreve", "Semibead", "Semibread", "Semitruck", "Semibreve", R.drawable.semibreve));
        quiz.add(new Quiz(17, "easy", "What is this note called?", "Dotted Minim", "Minim", "Crochet", "Speckled Minim", "Dotted Minim", R.drawable.dotted_minim));
        quiz.add(new Quiz(18, "easy", "What is this note called?", "Menum", "Minam", "Minim", "Minimum", "Minim", R.drawable.minim));
        quiz.add(new Quiz(19, "easy", "How many beats are in each bar?", "4", "8", "2", "3", "4", R.drawable.four_four_time));
        quiz.add(new Quiz(20, "easy", "From left to right, what are these 4 notes?", "B, C, E, G", "A, C, E, G", "F, E, D, A", "G, B, C, E", "G, B, C, E", R.drawable.treble_g_b_c_e));
        quiz.add(new Quiz(21, "easy", "From left to right, what are these 4 notes?", "A, C, E, D", "F, E, G, A", "D, A, F, E", "C, A, F, E", "C, A, F, E", R.drawable.treble_c_a_f_e));
        quiz.add(new Quiz(22, "easy", "From left to right, what are these 3 notes?", "G, A, D", "B, A, D", "D, A, D", "C, A, D", "B, A, D", R.drawable.treble_b_a_d));
        quiz.add(new Quiz(23, "easy", "From left to right, what are these 3 notes?", "A, C, B", "A, D, B", "E, D, B", "E, C, B", "E, C, B", R.drawable.treble_e_c_b));
        quiz.add(new Quiz(24, "easy", "From left to right, what are these 3 notes?", "D, B, C", "E, F, D", "E, G, A", "E, A, D", "E, G, A", R.drawable.bass_e_g_a));
        quiz.add(new Quiz(25, "easy", "From left to right, what are these 3 notes?", "G, A, D", "B, A, D", "D, A, D", "C, A, D", "G,A,D", R.drawable.bass_g_a_d));
        quiz.add(new Quiz(26, "easy", "From left to right, what are these 4 notes?", "B, A, B, A", "C, A, D, E", "E, A, D, B", "A, C, D, E", "E, A, D, B", R.drawable.bass_e_a_d_b));
        quiz.add(new Quiz(27, "easy", "From left to right, what are these 4 notes?", "C, A, D, G", "A, D, G, E", "E, D, G, E", "B, G, D, E", "E, D, G, E", R.drawable.bass_e_d_g_e));
        quiz.add(new Quiz(28, "easy", "From left to right, what are these 2 notes?", "A, G", "F, E", "A, F", "F, E", "A, G", R.drawable.treble_a_g));
        quiz.add(new Quiz(29, "easy", "From left to right, what are these 2 notes?", "D, E", "E, A", "C, D", "F, G", "D, E", R.drawable.treble_d_e));
        quiz.add(new Quiz(30, "easy", "From left to right, what are these 2 notes?", "B, G", "B, E", "B, D", "A, C", "B, D", R.drawable.bass_b_d));
        quiz.add(new Quiz(31, "easy", "From left to right, what are these 2 notes?", "C, D", "C, E", "C, F", "C, G", "C, E", R.drawable.bass_c_e));
        quiz.add(new Quiz(32, "easy", "What does common time mean?", "4 crochet beats in one bar", "The same time as before", "The most common speed", "None of the above", "4 crochet beats in one bar", R.drawable.common_time));

        quiz.add(new Quiz(33,"intermediate", "How many semitones does a sharp raise a note?", "1", "0.5", "0.25","2", "1", R.drawable.sharp));
        quiz.add(new Quiz(34,"intermediate", "Which note here is a sharp?","F#", "G#", "A#", "D#","F#", R.drawable.accidental_gadf));
        quiz.add(new Quiz(35,"intermediate", "Which note here is a sharp? ", "D#", "A#", "G#", "F#","F#",R.drawable.accidental_dgaf));
        quiz.add(new Quiz(36,"intermediate", "How many semitones does a flat lower a note?", "0.5", "1", "0.25", "2", "1", R.drawable.flat));
        quiz.add(new Quiz(37,"intermediate", "Which note here is a flat?", "Bb", "Db", "Ab","Eb", "Bb", R.drawable.accidental_bdae_treble));
        quiz.add(new Quiz(38,"intermediate", "Which note here is a flat?", "Db","Bb","Eb","Ab", "Bb", R.drawable.accidental_bdae_bass));
        quiz.add(new Quiz(39,"intermediate", "What major key is this?","G Major", "F Major", "B Major", "D Major", "G Major", R.drawable.g_major_key));
        quiz.add(new Quiz(40,"intermediate", "What major key is this?", "C Major", "B Major", "A Major", "D Major", "A Major",R.drawable.a_major_key));
        quiz.add(new Quiz(41,"intermediate", "What minor key is this?", "A Minor", "G Minor", "E Minor", "D Minor", "D Minor", R.drawable.d_minor_key));
        quiz.add(new Quiz(42,"intermediate", "What major key is this?","F# Major", "A# Major", "C# Major", "D#  Major", "C# Major", R.drawable.c_sharp_major_key));
        quiz.add(new Quiz(43, "advanced", "What dominant 7th chord is this?", "C7", "E7", "A7", "G3","C7", R.drawable.c_seven));
        quiz.add(new Quiz(44, "advanced", "Which note in this C Major scale is the dominant?", "C", "E", "G", "B", "G", R.drawable.c_major));
        quiz.add(new Quiz(45, "advanced", "Which roman numeral is used to denote a dominant 7th", "V7", "D7", "C7", "G7","V7",R.drawable.c_seven ));
        quiz.add(new Quiz(46, "advanced", "This is a C Major scale. What are the notes in a C7 chord?", "C,E,G,B", "C,E,G,B#", "G,B,D,Eb", "C,E,G,Bb", "C,E,G,Bb", R.drawable.c_major));
        quiz.add(new Quiz(47, "advanced", "A dominant 7th chord consists of the dominant triad and an added minor ______ above the root.", "Seventh", "Dominant", "Sixth", "First", "Seventh", R.drawable.dom_seventh));
        quiz.add(new Quiz(48, "advanced", "What diminished 7th chord is this?", "Cdim1", "Edim7", "Cdim7", "Ddim7", "Cdim7", R.drawable.c_dim_seventh));
        quiz.add(new Quiz(49, "advanced", "How is the C diminished 7th denoted?", "C7", "Cdim7", "Dim7C", "C7Dim", "Cdim7", R.drawable.c_dim_seventh));
        quiz.add(new Quiz(50, "advanced", "A diminished 7th is a diminished triad, with an added diminished _____ from the root.", "7th", "3rd", "5th", "9th", "7th", R.drawable.adim_seventh));
        quiz.add(new Quiz(51, "advanced", "Diminished 7th chords are typically defined by their _______ note.","Flat", "Seventh", "Last", "Root", "Root", R.drawable.c_dim_seventh));
        quiz.add(new Quiz(52, "advanced","A diminished 7th is a diminished _______, with an added diminished seventh from the root. ", "Triad", "7th", "Third", "Fourth", "Triad", R.drawable.adim_seventh));
        //add olivias info her e


        return quiz;
    }
}


