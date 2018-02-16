package cs3500.music.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.NoteMetaData;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;

/**
 * Created by a172772 on 4/2/16.
 */
public class ViewModel {
  private final IMusicModel model;

  public ViewModel(IMusicModel model) {
    this.model = model;
  }

  public ViewModel(String filename) {
    CompositionBuilder<MusicModel> builder = new MusicModel.Builder();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(new File(filename)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    this.model = MusicReader.parseFile(reader, builder);
  }


  /*public void addNote(int beatNum, Note n, int duration) {
    model.addNote(beatNum, n, duration);
  }

  public void addNote(int beatNum, Note n, int duration, int volume, int instrument) {
    model.addNote(beatNum, n, duration, volume, instrument);
  }

  public void removeNote(int beatNum, Note n) {
    model.removeNote(beatNum, n);
  }*/

  public HashMap<String, NoteMetaData> getNote(int beatNum) {
    return model.getNote(beatNum);
  }

  public boolean playingAt(int beatIndex) {
    return model.playingAt(beatIndex);
  }

  public Note lowestNote() {
    return model.lowestNote();
  }

  public Note highestNote() {
    return model.highestNote();
  }

  public int getBeatMax() {
    return model.getBeatMax();
  }

  public void setTempo(int tempo) {
    model.setTempo(tempo);
  }

  public int getTempo() {
    return model.getTempo();
  }

  public int duration(int beatNum, Note n) {
    return model.duration(beatNum, n);
  }

  public int getStartBeat(int beatNum, Note n) {
    return model.getNoteStartBeat(beatNum, n);
  }

  public int getEndBeat(int beatNum, Note n) {
    return model.getNoteEndBeat(beatNum, n);
  }
  public ArrayList<String> associatedNotes(int beatNum, Note n) {
    return model.associatedNotes(beatNum, n);
  }

  public String toString() {
    return this.model.toString();
  }
}