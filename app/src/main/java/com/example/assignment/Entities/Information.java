package com.example.assignment.Entities;

import android.media.Image;

import com.example.assignment.R;

import java.util.ArrayList;

public class Information {
    private int id;
    private String information;
    private int picture;
    private int topicId;

    public Information(int id, String information, int picture, int topicId) {
        this.id = id;
        this.information = information;
        this.picture = picture;
        this.topicId = topicId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public static ArrayList<Information> getInfo() {
        ArrayList<Information> info = new ArrayList<>();
        info.add(new Information(1, "This is a crochet. Notice how crochets are filled in the middle and have a stem. Crochets are held for 1 beat.", R.drawable.crochet, 1));
        info.add(new Information(2, "This is a minim. Minims look very similar to crochets but are not filled in the middle. Minims are held for 2 beats. ", R.drawable.minim, 1));
        info.add(new Information(3, "This is a dotted minim. Dotted minims look very similar to minims but have a black dot on their right. Dotted minims are held for 3 beats. ", R.drawable.dotted_minim, 1));
        info.add(new Information(4, "This is a semibreve. Semibreves are held for 4 beats.", R.drawable.semibreve, 1));
        info.add(new Information(5, "In music, rhythms and notes are grouped in bars. Each bar will always have the same number of beats.", R.drawable.bars, 2));
        info.add(new Information(6, "Time signatures indicate how many beats there are in one bar. We will always look at the top number to determine how many beats there are in one bar.", R.drawable.time_signatures, 2));
        info.add(new Information(7, "For example, the 4/4 time signature means we count 4 beats crochet beats in one bar; the 3/4 time signature means we count 3 crochet beats in one bar.", R.drawable.time_signatures, 2));
        info.add(new Information(8, "The 4/4 time signature has 4 crochet beats in one bar. It's also known as \"Common time\" and is denoted with the \"C\" symbol.", R.drawable.time_signatures_common, 2));
        info.add(new Information(9, "This is a treble clef. Treble clef is a symbol placed on every line of music and it indicates the notes will be high sounds. For example, the flute and violin are written in treble clef.", R.drawable.treble_clef, 3));
        //youtube video  https://www.youtube.com/watch?v=7Bv-JiFnoJ4
        info.add(new Information(10, "Here is a video of explaining the treble clef, stave and pitch. Please click on the image to open the video in YouTube.", R.drawable.treble_video, 3));
        info.add(new Information(11, "This is what notes look like in the treble clef. The 5 lines are called a staff. This is where our notes will be written. The lowest note here is Middle C.", R.drawable.c_major, 3));
        info.add(new Information(12, "Notice how each note ascending will be on a line, then a space. We will follow this same pattern for all notes. Music letters will follow the alphabet from A-G, then it will repeat.", R.drawable.c_major, 3));

        info.add(new Information(13, "Middle C on the treble clef is below all the lines, and will have an extra line going through the middle.", R.drawable.treble_middle_c, 3));
        info.add(new Information(14, "The next note above Middle C is D. Notice how it follows the pattern line-space-line-space.", R.drawable.treble_d, 3));
        info.add(new Information(15, "Since D was on a space, the next note on the line is an E. With all notes that are on the treble clef lines, we can use a very simple mnemonic to memorise each note. \"Every Good Boy Deserves Fruit\". Starting from the bottom line, it is E-G-B-D-F.", R.drawable.every_good_boy, 3));
        info.add(new Information(16, "For the notes in the treble clef spaces, we have another simple mnemonic to help us memorise each note. All the notes inside a space spell face - \"F-A-C-E\". ", R.drawable.face, 3));
        info.add(new Information(17, "This is a bass clef. Bass clef is a symbol placed on every line of music and it indicates the notes will be low sounds. For example, the cello and tuba are written in bass clef.", R.drawable.bass_clef, 4));
        //youtube video  https://www.youtube.com/watch?v=Z40fcNNvu0E
        info.add(new Information(18, "Here is a video of explaining the bass clef. Please click on the image to open the video in YouTube.", R.drawable.bass_video, 4));
        info.add(new Information(19, "This is what notes look like on the bass clef. The 5 lines are called a staff. This is where our notes will be written. The highest note here is Middle C.", R.drawable.bass_clef_c_major, 4));
        info.add(new Information(20, "Notice how each note will be on a line, then a space. We will follow this same pattern for all notes. Music letters will follow the alphabet from A-G, then it will repeat.", R.drawable.bass_clef_c_major, 4));
        info.add(new Information(21, "Middle C on the bass clef is above all the lines, and will have an extra line going through the middle. ", R.drawable.bass_middle_c, 4));
        info.add(new Information(22, "The next note below Middle C is B. Notice how it follows the pattern line-space-line-space.", R.drawable.bass_b, 4));
        info.add(new Information(23, "Since B was on a space, the next note on the line is an A. With all notes that are on the bass clef lines, we can use a very simple mnemonic to memorise each note. \"Good Boys Do Fine Always\". Starting from the lowest line, it is G-B-D-F-A.", R.drawable.good_boys_do_fine_always, 4));
        info.add(new Information(24, "For the notes in the bass clef spaces, we have another simple mnemonic to help us memorise each note. \"All Cows Eat Grass\". Starting from the lowest line, it is \"A-C-E-G\".", R.drawable.all_cows_eat_grass, 4));
        info.add(new Information(25, "In music, we have dynamics that tell us how we should play each note. For example, there are different signs and terms to play loudly and to play softly. ", R.drawable.dynamics_symbols, 5));
        info.add(new Information(26, "\"p\" stands for piano. Piano means we should play softly. ", R.drawable.p, 5));
        info.add(new Information(27, "\"f\" stands for forte. Forte means we should play loudly. ", R.drawable.f, 5));
        info.add(new Information(28, "\"mp\" stands for mezzo piano. Mezzo Piano means we play moderately soft. Notice how the \"p\" stands for piano, just like how we learnt earlier. ", R.drawable.mp, 5));
        info.add(new Information(29, "\"mf\" stands for mezzo forte. Mezzo Forte means we play moderately loud. Notice how the \"f\" stands for forte, just like how we learnt earlier. ", R.drawable.mf, 5));
        info.add(new Information(30, "This is a crescendo. Crescendo means we start soft and gradually get louder. The smallest point of a crescendo means soft and the larger point of a crescendo means loud.", R.drawable.cresc, 5));
        info.add(new Information(31, "This is a decrescendo. Decrescendo means we start loud and gradually get softer. The largest point of a decrescendo means loud and the smaller point of a diminuendo means soft.", R.drawable.decresc, 5));

        return info;
    }


}
