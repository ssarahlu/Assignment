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
        info.add(new Information(10, "Here is a video of explaining the treble clef, stave and pitch. Please click on the image to open the video in YouTube.", R.drawable.treble_video, 3));
        info.add(new Information(11, "This is what notes look like in the treble clef. The 5 lines are called a staff. This is where our notes will be written. The lowest note here is Middle C.", R.drawable.c_major, 3));
        info.add(new Information(12, "Notice how each note ascending will be on a line, then a space. We will follow this same pattern for all notes. Music letters will follow the alphabet from A-G, then it will repeat.", R.drawable.c_major, 3));
        info.add(new Information(13, "Middle C on the treble clef is below all the lines, and will have an extra line going through the middle.", R.drawable.treble_middle_c, 3));
        info.add(new Information(14, "The next note above Middle C is D. Notice how it follows the pattern line-space-line-space.", R.drawable.treble_d, 3));
        info.add(new Information(15, "Since D was on a space, the next note on the line is an E. With all notes that are on the treble clef lines, we can use a very simple mnemonic to memorise each note. \"Every Good Boy Deserves Fruit\". Starting from the bottom line, it is E-G-B-D-F.", R.drawable.every_good_boy, 3));
        info.add(new Information(16, "For the notes in the treble clef spaces, we have another simple mnemonic to help us memorise each note. All the notes inside a space spell face - \"F-A-C-E\". ", R.drawable.face, 3));
        info.add(new Information(17, "This is a bass clef. Bass clef is a symbol placed on every line of music and it indicates the notes will be low sounds. For example, the cello and tuba are written in bass clef.", R.drawable.bass_clef, 4));
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
        info.add(new Information(32,"This is a sharp. A sharp is a hash mark symbol (#) you'll find in a key signature or as an accidental next to a note.", R.drawable.sharp, 6));
        info.add(new Information(33, "Sharps are used to raise a specified note by one half step (or one semitone) and are always written on the line or space of the note they alter.", R.drawable.sharp_note, 6));
        info.add(new Information(34, "In this example the F note has a sharp next to it making it an F#. When a sharp, flat, or natural sign is placed directly next to a note it's called an accidental.", R.drawable.f_sharp, 6));
        info.add(new Information(35, "This is a flat. A flat is just the opposite of a sharp; it lowers a specified note by one half step. You can identify them by their resemblance to a lowercase b.", R.drawable.flat, 7));
        info.add(new Information(36, "Like sharps they're also used in key signatures and as accidentals and abide by the same guidelines when written; ie always on the line or space of the note they affect.", R.drawable.flat_note, 7));
        info.add(new Information(37, "In this example the B note has a flat next to it making it an B flat. When a sharp, flat, or natural sign is placed directly next to a note it's called an accidental.", R.drawable.flat_note, 7));
        info.add(new Information(38, "Major scales sound happy. This is a C Major scale. The steps of a major scale are (T, T, S, T, T, T, S). T = tone and S = semitone. In most cases we use sharps and flats to help us make these steps possible.", R.drawable.c_major_topic_eight, 8));
        info.add(new Information(39, "Minor scales sound sad. The steps of a harmonic minor scale are (T, S, T, T, S, T and a half, S). T = tone and S = semitone. In most cases we use sharps and flats to help us make these steps possible.", R.drawable.minor_harmonic, 8));
        info.add(new Information(40, "This is the circle of 5ths. This is a diagram of all the different relationships among the 12 tones of the chromatic scale, their corresponding key signatures, and the associated major and minor keys.", R.drawable.circle_of_fifths, 8));
        info.add(new Information(41, "In music, a dominant refers to the fifth note of any scale. A dominant seventh chord consists of the dominant triad (fifth note of the scale is the root of the dominant chord) and an added note a minor seventh above the root.", R.drawable.c_seven, 9));
        info.add(new Information(42, "For example, the dominant seventh chord in C major (or minor) is G-B-D-F.", R.drawable.c_seven, 9));
        info.add(new Information(43, "When using roman numerals to denote chords, dominant seventh chords are notated with “V7”. In piano/guitar chords, you’ll see a “7” written beside the letter of the chord root. For example, the chord above is a G7.", R.drawable.dom_seventh, 9));
        info.add(new Information(44, "A diminished seventh chord is a diminished triad, with an added note of a diminished seventh interval from the root. For example, this photo shows a diminished seventh chord in A. ", R.drawable.adim_seventh, 10));
        info.add(new Information(45, "A diminished seventh chord contrasts from the half-diminished seventh chord in its seventh note; the half-diminished seventh is a diminished triad with a note added that is a minor seventh from the root.", R.drawable.adim_seventh, 10));
        info.add(new Information(46, "Diminished seventh chords are typically defined by their root note. Here is an example of a diminished seventh chord on C.", R.drawable.c_dim_seventh, 10));
        info.add(new Information(47, "In pop guitar/piano chords, diminished seventh chords are usually written with a “dim7” or “°7” beside the tonic note. The diminished seventh chord illustrated above will be written as “Cdim7 or C°7.", R.drawable.dom_seventh, 10));
        info.add(new Information(48, "The brass instrument tuba has the lowest pitch in an orchestra.", R.drawable.tuba, 11));
        info.add(new Information(49, "The lead singer of Green Day is Billie Joe Armstrong.", R.drawable.greenday, 11));
        info.add(new Information(50, "The hit South Korean song Gangnam Style was released by PSY in 2012 and was the first YouTube video to reach 1 billion views.", R.drawable.gangnam_style, 11));
        info.add(new Information(51, "Adele's album \"Adele, 25\" was the best selling album in 2015.", R.drawable.adele, 11));

        return info;
    }


}
