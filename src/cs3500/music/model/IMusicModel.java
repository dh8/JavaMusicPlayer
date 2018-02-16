package cs3500.music.model;

import java.util.ArrayList;
import java.util.HashMap;

public interface IMusicModel {
  /**
   *
   * @param beatNum the beat number on which the note should start
   * @param n the note to add
   * @param duration the duration of the note in beats
   */
  public void addNote(int beatNum, Note n, int duration);
  public void addNote(int beatNum, Note n, int duration, int volume, int instrument);

  /**
   * Will remove the entire note up to an articulation
   * @param beatNum a beat number where the note is active
   * @param n the note to remove
   */
  public void removeNote(int beatNum, Note n);

  /**
   *
   * @param beatNum the beat number you'd like to see data for
   * @return a HashMap containing all the active notes at the given beat
   */
  public HashMap<String, NoteMetaData> getNote(int beatNum);

  /**
   * true if there is an active note at the specified beat index, false if resting
   * @param beatIndex
   * @return
   */
  public boolean playingAt(int beatIndex);

  /**
   * Returns the lowest note
   * @return
   */
  public Note lowestNote();

  /**
   * Returns the highest note
   * @return
   */
  public Note highestNote();

  /**
   * Get the max beat
   * @return
   */
  public int getBeatMax();

  /**
   * Set the tempo
   * @param tempo
   */
  public void setTempo(int tempo);

  /**
   * Get the tempo
   * @return
   */
  public int getTempo();

  /**
   * Get the duration of the note at the specified beat
   * @param beatNum
   * @param n
   * @return
   */
  public int duration(int beatNum, Note n);

  /**
   * Get all notes associated with the note at the sepcified beat
   * @param beatNum
   * @param n
   * @return
   */
  public ArrayList<String> associatedNotes(int beatNum, Note n);

  /**
   * Get note start beat
   * @param beatNum
   * @param n
   * @return
   */
  public int getNoteStartBeat(int beatNum, Note n);

  /**
   * Get note end beat
   * @param beatNum
   * @param n
   * @return
   */
  public int getNoteEndBeat(int beatNum, Note n);
}
