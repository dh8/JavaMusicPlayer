package cs3500.music.view;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 *  to represent tests for the MidiView
 */

public class MidiViewTest {

  @Test
  /**
   * testCase1.txt
   tempo 111600
   note 0 4 1 70 127
   note 4 8 1 70 127
   note 4 7 1 72 127
   note 6 8 1 73 127
   -----------------
   overlapping start of notes (with different end times)
   */
  public void testMidi() throws FileNotFoundException {
    MidiDevice myDevice = new MidiDevice(111600);
    MidiView myMidiView = new MidiView("testCase1.txt", myDevice);
    myMidiView.initialize();
    assertEquals(myDevice.getLog(), "The notes in this composition: \n"
                                    + "Note 1 Pitch: 70 Starts at: 0 Ends at 4 "
                                    + "The instrument is: 1 The volume: 127\n"
                                    + "Note 2 Pitch: 70 Starts at: 4 Ends at 8 "
                                    + "The instrument is: 1 The volume: 127\n"
                                    + "Note 3 Pitch: 72 Starts at: 4 Ends at 7 "
                                    + "The instrument is: 1 The volume: 127\n"
                                    + "Note 4 Pitch: 73 Starts at: 6 Ends at 8 "
                                    + "The instrument is: 1 The volume: 127\n");
  }

  @Test
  public void testMidi2() throws FileNotFoundException {
    MidiDevice myDevice = new MidiDevice(111600);
    MidiView myMidiView = new MidiView("testCase3.txt", myDevice);
    myMidiView.initialize();
    assertEquals(myDevice.getLog(), "The notes in this composition: \n"
                                    + "Note 1 Pitch: 70 Starts at: 0 Ends at 3 "
                                    + "The instrument is: 1 The volume: 127\n"
                                    + "Note 2 Pitch: 70 Starts at: 3 Ends at 8 "
                                    + "The instrument is: 1 The volume: 127\n"
                                    + "Note 3 Pitch: 72 Starts at: 4 Ends at 7 "
                                    + "The instrument is: 1 The volume: 127\n"
                                    + "Note 4 Pitch: 73 Starts at: 6 Ends at 8 "
                                    + "The instrument is: 1 The volume: 127\n");
  }

  /**
   * EMPTY: NOT WORKING
   */
  @Test(expected = IllegalStateException.class)
  public void testMidi3() throws FileNotFoundException {
    MidiDevice myDevice = new MidiDevice(111600);
    MidiView myMidiView = new MidiView("testCase2.txt", myDevice);
    myMidiView.initialize();
    assertEquals(myDevice.getLog(), "bb");
  }




  @Test
  public void testMidi4() throws FileNotFoundException {
    MidiDevice myDevice = new MidiDevice(200000);
    MidiView myMidiView = new MidiView("mary-little-lamb.txt", myDevice);
    myMidiView.initialize();
    assertEquals(myDevice.getLog(), "The notes in this composition: \n"
                                    + "Note 1 Pitch: 55 Starts at: 0 Ends at 7 The instrument "
                                    + "is: 1 The volume: 70\n"
                                    + "Note 2 Pitch: 64 Starts at: 0 Ends at 2 The instrument "
                                    + "is: 1 The volume: 72\n"
                                    + "Note 3 Pitch: 62 Starts at: 2 Ends at 4 The instrument "
                                    + "is: 1 The volume: 72\n"
                                    + "Note 4 Pitch: 60 Starts at: 4 Ends at 6 The instrument "
                                    + "is: 1 The volume: 71\n"
                                    + "Note 5 Pitch: 62 Starts at: 6 Ends at 8 The instrument"
                                    + " is: 1 The volume: 79\n"
                                    + "Note 6 Pitch: 55 Starts at: 8 Ends at 15 The instrument"
                                    + " is: 1 The volume: 79\n"
                                    + "Note 7 Pitch: 64 Starts at: 8 Ends at 10 The instrument "
                                    + "is: 1 The volume: 85\n"
                                    + "Note 8 Pitch: 64 Starts at: 10 Ends at 12 The instrument "
                                    + "is: 1 The volume: 78\n"
                                    + "Note 9 Pitch: 64 Starts at: 12 Ends at 15 The instrument "
                                    + "is: 1 The volume: 74\n"
                                    + "Note 10 Pitch: 62 Starts at: 16 Ends at 18 The instrument"
                                    + " is: 1 The volume: 75\n"
                                    + "Note 11 Pitch: 55 Starts at: 16 Ends at 24 The instrument"
                                    + " is: 1 The volume: 77\n"
                                    + "Note 12 Pitch: 62 Starts at: 18 Ends at 20 The instrument "
                                    + "is: 1 The volume: 77\n"
                                    + "Note 13 Pitch: 62 Starts at: 20 Ends at 24 The instrument "
                                    + "is: 1 The volume: 75\n"
                                    + "Note 14 Pitch: 55 Starts at: 24 Ends at 26 The instrument "
                                    + "is: 1 The volume: 79\n"
                                    + "Note 15 Pitch: 64 Starts at: 24 Ends at 26 The instrument"
                                    + " is: 1 The volume: 82\n"
                                    + "Note 16 Pitch: 67 Starts at: 26 Ends at 28 The instrument "
                                    + "is: 1 The volume: 84\n"
                                    + "Note 17 Pitch: 67 Starts at: 28 Ends at 32 The instrument "
                                    + "is: 1 The volume: 75\n"
                                    + "Note 18 Pitch: 55 Starts at: 32 Ends at 40 The instrument"
                                    + " is: 1 The volume: 78\n"
                                    + "Note 19 Pitch: 64 Starts at: 32 Ends at 34 The instrument"
                                    + " is: 1 The volume: 73\n"
                                    + "Note 20 Pitch: 62 Starts at: 34 Ends at 36 The instrument "
                                    + "is: 1 The volume: 69\n"
                                    + "Note 21 Pitch: 60 Starts at: 36 Ends at 38 The instrument "
                                    + "is: 1 The volume: 71\n"
                                    + "Note 22 Pitch: 62 Starts at: 38 Ends at 40 The instrument "
                                    + "is: 1 The volume: 80\n"
                                    + "Note 23 Pitch: 55 Starts at: 40 Ends at 48 The instrument"
                                    + " is: 1 The volume: 79\n"
                                    + "Note 24 Pitch: 64 Starts at: 40 Ends at 42 The instrument"
                                    + " is: 1 The volume: 84\n"
                                    + "Note 25 Pitch: 64 Starts at: 42 Ends at 44 The instrument "
                                    + "is: 1 The volume: 76\n"
                                    + "Note 26 Pitch: 64 Starts at: 44 Ends at 46 The instrument "
                                    + "is: 1 The volume: 74\n"
                                    + "Note 27 Pitch: 64 Starts at: 46 Ends at 48 The instrument"
                                    + " is: 1 The volume: 77\n"
                                    + "Note 28 Pitch: 62 Starts at: 48 Ends at 50 The instrument "
                                    + "is: 1 The volume: 75\n"
                                    + "Note 29 Pitch: 55 Starts at: 48 Ends at 56 The instrument "
                                    + "is: 1 The volume: 78\n"
                                    + "Note 30 Pitch: 62 Starts at: 50 Ends at 52 The instrument"
                                    + " is: 1 The volume: 74\n"
                                    + "Note 31 Pitch: 64 Starts at: 52 Ends at 54 The instrument "
                                    + "is: 1 The volume: 81\n"
                                    + "Note 32 Pitch: 62 Starts at: 54 Ends at 56 The instrument "
                                    + "is: 1 The volume: 70\n"
                                    + "Note 33 Pitch: 60 Starts at: 56 Ends at 64 The instrument"
                                    + " is: 1 The volume: 73\n"
                                    + "Note 34 Pitch: 52 Starts at: 56 Ends at 64 The instrument "
                                    + "is: 1 The volume: 72\n");


  }



}
