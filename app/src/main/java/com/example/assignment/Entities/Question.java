package com.example.assignment.Entities;

import com.example.assignment.R;

import java.util.ArrayList;

public class Question {

    private int id;
    private String question;
    private String op1, op2, op3, op4, answer;
    private int photo;
    private int topicId;

    public Question() {
    }

    public Question(int id, String question, String op1, String op2, String op3, String op4, String answer, int photo, int topicId) {
        this.id = id;
        this.question = question;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.answer = answer;
        this.photo = photo;
        this.topicId = topicId;
    }

    public static ArrayList<Question> getQuestions() {
        ArrayList<Question> q = new ArrayList<>();
        q.add(new Question(1, "What is this note called?", "Semibreve", "Crochet", "Semibreathe", "Semi", "Semibreve", R.drawable.semibreve, 1));
        q.add(new Question(2, "How many beats are in a crochet?", "1", "2", "4", "3", "1", R.drawable.crochet, 1));
        q.add(new Question(3, "What is this note called?", "Menum", "Minam", "Minim", "Minimum", "Minim", R.drawable.minim, 1));
        q.add(new Question(4, "How many beats are in a minim?", "3", "1", "4", "2", "2", R.drawable.minim, 1));
        q.add(new Question(5,  "How many beats are there in a 3/4 time bar?", "3", "4", "7", "0.75", "3", R.drawable.three_four_bar, 2));
        q.add(new Question(6,  "What does common time mean?", "4 crotchet beats in one bar", "The same time as before", "The most common speed", "None of the above", "4 crotchet beats in one bar", R.drawable.common_time,2));
        q.add(new Question(7,  "How many beats are there in a 4/4 time bar?", "4","8","1","2","4", R.drawable.four_four_time, 2 ));
        q.add(new Question(8,  "What note is this? Hint: We are in the treble clef. ", "D", "E", "B", "C", "D", R.drawable.treble_d, 3));
        q.add(new Question(9,  "What note is this? Hint: We are in the treble clef so use the \"Every Good Bird Deserves Fruit\" mnemonic. ", "E","F","G","B","E", R.drawable.treble_e, 3 ));
        q.add(new Question(10,  "What is the mnemonic for treble clef spaces?", "F-A-C-E","Good Birds Don't Fly Away", "B-E-A-D", "C-A-F-E", "F-A-C-E", R.drawable.face_no_label, 3 ));
        q.add(new Question(11,  "From left to right, what are these 2 notes?", "D, E", "E, A", "C, D", "F, G","D, E", R.drawable.treble_d_e, 3));
        q.add(new Question(12,  "From left to right, what are these 3 notes?","E, G, B", "E, F, B", "E, D, B","E, C, B","E, C, B", R.drawable.treble_e_c_b, 3));
        q.add(new Question(13,  "From left to right, what are these 4 notes?", "B, C, E, G", "A, C, E, G", "F, E, D, A", "G, B, C, E","G, B, C, E", R.drawable.treble_g_b_c_e, 3 ));
        q.add(new Question(14,  "What note is this? Hint: We are in the bass clef so use the \"All-Cows-Eat-Grass\" mnemonic.", "A", "C", "E","G","A",R.drawable.bass_a, 4 ));
        q.add(new Question(15,  "What note is this? Hint: We are in the bass clef. ", "B", "D", "A", "C", "B", R.drawable.bass_b,4 ));
        q.add(new Question(16,  "From left to right, what are these 2 notes?", "B, A", "B, F", "B, G", "B, D", "B, D", R.drawable.bass_b_d, 4));
        q.add(new Question(17,  "From left to right, what are these 3 notes?","G, A, D", "F, A, D", "D, A, D", "E, A, D", "G, A, D",R.drawable.bass_g_a_d, 4 ));
        q.add(new Question(18,  "From left to right, what are these 4 notes?", "B, A, B, A", "F, A, D, E", "E, A, D, B", "B, E, A, D", "E, A, D, B",R.drawable.bass_e_a_d_b,4 ));
        q.add(new Question(19,  "What is the mnemonic for bass clef spaces? ", "All Cows Eat Grass","F-A-C-E", "D-E-A-D", "B-E-G-A", "All Cows Eat Grass", R.drawable.all_cows_eat_grass_no_label, 4));
        q.add(new Question(20,  "What does mp mean?","Moderately Loud", "Minimum Soft", "Moderately Soft", "Maximum Piano", "Moderately Soft", R.drawable.mp, 5));
        q.add(new Question(21,  "What does mf stand for? ", "Mezzo Forte","Mezzazine Forte", "Medium Forte", "Mezz Fort", "Mezzo Forte", R.drawable.mf, 5 ));
        q.add(new Question(22,  "What is the name of this sign?", "Decrescendo", "Crescent", "Crescendo","Colour","Crescendo",R.drawable.cresc,5 ));

//add olivias questions here
        q.add(new Question(23, "How many semitones does a sharp raise a note?", "1", "0.5", "0.25", "2", "1", R.drawable.sharp, 6));
        q.add(new Question(24, "Which note here is a sharp?", "F#", "G#", "A#", "D#", "F#", R.drawable.accidental_gadf, 6));
        q.add(new Question(25, "Which note here is a sharp?", "D#","A#","G#", "F#", "F#", R.drawable.accidental_dgaf, 6));
        q.add(new Question(26, "How many semitones does a flat lower a note?", "0.5", "1", "0.25", "2", "1", R.drawable.flat, 7));
        q.add(new Question(27, "Which note here is a flat?", "Bb","Db", "Ab", "Eb", "Bb",R.drawable.accidental_bdae_treble, 7));
        q.add(new Question(28, "Which note here is a flat?", "Db", "Bb", "Eb", "Ab", "Bb", R.drawable.accidental_bdae_treble, 7));
        q.add(new Question(29, "What major key is this?","G Major", "F Major", "B Major", "D Major", "G Major", R.drawable.g_major_key, 8));
        q.add(new Question(30, "What major key is this?", "C Major", "B Major", "A Major", "D Major", "A Major",R.drawable.a_major_key, 8));
        q.add(new Question(31, "What minor key is this?", "A Minor", "G Minor", "E Minor", "D Minor", "D Minor", R.drawable.d_minor_key, 8));
        q.add(new Question(32, "What major key is this?","F# Major", "A# Major", "C# Major", "D# Major", "C# Major", R.drawable.c_sharp_major_key, 8));
        q.add(new Question(33, "What dominant 7th chord is this?", "C7", "E7", "A7", "G3","C7", R.drawable.c_seven, 9));
        q.add(new Question(34, "Which note in this C Major scale is the dominant?", "C", "E", "G", "B", "G", R.drawable.c_major, 9));
        q.add(new Question(35, "Which roman numeral is used to denote a dominant 7th", "V7", "D7", "C7", "G7","V7",R.drawable.c_seven , 9 ));
        q.add(new Question(36, "This is a C Major scale. What are the notes in a C7 chord?", "C,E,G,B", "C,E,G,B#", "G,B,D,Eb", "C,E,G,Bb", "C,E,G,Bb", R.drawable.c_major, 9));
        q.add(new Question(37, "A dominant 7th chord consists of the dominant triad and an added minor ______ above the root.", "Seventh", "Dominant", "Sixth", "First", "Seventh", R.drawable.dom_seventh, 9));
        q.add(new Question(38, "What diminished 7th chord is this?", "Cdim1", "Edim7", "Cdim7", "Ddim7", "Cdim7", R.drawable.c_dim_seventh, 10));
        q.add(new Question(39, "How is the C diminished 7th denoted?", "C7", "Cdim7", "Dim7C", "C7Dim", "Cdim7", R.drawable.c_dim_seventh, 10));
        q.add(new Question(40, "A diminished 7th is a diminished triad, with an added diminished _____ from the root.", "7th", "3rd", "5th", "9th", "7th", R.drawable.adim_seventh, 10));
        q.add(new Question(41, "Diminished 7th chords are typically defined by their _______ note.","Flat", "Seventh", "Last", "Root", "Root", R.drawable.c_dim_seventh, 10));
        q.add(new Question(42, "A diminished 7th is a diminished _______, with an added diminished seventh from the root. ", "Triad", "7th", "Third", "Fourth", "Triad", R.drawable.adim_seventh, 10));

        q.add(new Question(43,  "What is the instrument in the orchestra has the lowest pitch?", "Clarinet", "Tuba", "Saxophone","Violin","Tuba",R.drawable.orchestra,11 ));
        q.add(new Question(44,  "Who is the lead singer of Green Day?", "Billie May Cyrus", "Billie Jean", "Billie Eilish","Billie Joe Armstrong","Billie Joe Armstrong",R.drawable.greenday,11 ));
        q.add(new Question(45,  "Which song was the first to reach 1 billion views on YouTube?", "Gangnam Style - PSY", "Baby - Justin Bieber", "See You Again - Charlie Puth","Symphony no. 40 in G Minor - Mozart","Gangnam Style - PSY",R.drawable.youtube,11 ));
        q.add(new Question(46,  "Which album was the best selling album in 2015?", "Gangnam Style - PSY", "1989 - Taylor Swift", "25 - Adele","Purpose - Justin Bieber","25 - Adele",R.drawable.best_seller,11 ));

        return q;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
}
