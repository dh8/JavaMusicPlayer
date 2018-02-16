package cs3500.music.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import cs3500.music.util.CompositionBuilder;

public class MusicModel implements IMusicModel {
  protected NoteMap nm;
  protected int tempo;

  public MusicModel() {
    this.nm = new NoteMap();
  }

  public MusicModel(String filename) throws IOException {
    this.nm = MusicFileReader.create(filename);
  }

  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public void addNote(int beatNum, Note n, int duration) {
    nm.addNote(beatNum, n, duration, 1, 64);
  }

  @Override
  public void addNote(int beatNum, Note n, int duration, int volume, int instrument) {
    nm.addNote(beatNum, n, duration, volume, instrument);
  }

  @Override
  public void removeNote(int beatNum, Note n) {
    nm.removeNote(beatNum, n);
  }

  @Override
  public ArrayList<String> associatedNotes(int beatNum, Note n) {
    return nm.associatedNotes(beatNum, n);
  }

  @Override
  public int getNoteStartBeat(int beatNum, Note n) {
    return nm.getNoteStartBeat(beatNum, n);
  }

  @Override
  public int getNoteEndBeat(int beatNum, Note n) {
    return nm.getNoteEndBeat(beatNum, n);
  }

  @Override
  public int duration(int beatNum, Note n) {
    return nm.queryNoteLength(beatNum, n);
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();

    if(nm.song.isEmpty()) {
      return "";
    }

    Note minNote = nm.getMinNote();
    Note maxNote = nm.getMaxNote();
    int beatMax = Collections.max(nm.song.keySet());

    int numColLength = Integer.toString(beatMax).length();

    for(int i = 0; i < numColLength; i++) {
      result.append(" ");
    }
    Note curr = minNote;
    while(curr.compareTo(maxNote) <= 0) {
      result.append(curr);
      curr = curr.inc(1);
    }
    result.append("\n");

    for(int i = 0; i <= beatMax; i++) {
      String beatStr = Integer.toString(i);
      while(beatStr.length() < numColLength) {
        beatStr = " " + beatStr;
      }
      result.append(beatStr);
      if(nm.song.containsKey(i)) {
        curr = minNote;
        while(curr.compareTo(maxNote) <= 0) {
          //if(nm.song.get(i).containsKey(curr.pitch().toString() + curr.octave())) {
          if(nm.song.get(i).containsKey(curr.pitch().toString() + curr.octave())) {
            if(this.getNote(i).get(curr.pitch().toString() + curr.octave()).articulate) {
              result.append("  X  ");
            }
            else {
              result.append("  |  ");
            }
          }
          else {
            result.append("     ");
          }
          curr = curr.inc(1);
        }
      }
      //if(i < beatMax)
      result.append("\n");
    }

    return result.toString();
  }

  @Override
  public HashMap<String, NoteMetaData> getNote(int beatNum) {
    if(this.nm.song.containsKey(beatNum)) {
      return this.nm.song.get(beatNum);
    }
    else {
      throw new IllegalArgumentException("No note playing here");
    }
  }

  @Override
  public boolean playingAt(int beatIndex) {
    return this.nm.song.containsKey(beatIndex);
  }

  @Override
  public Note lowestNote() {
    return nm.getMinNote();
  }

  @Override
  public Note highestNote() {
    return nm.getMaxNote();
  }

  @Override
  public int getBeatMax() {
    if(nm.song.keySet().size() > 0)
      return Collections.max(nm.song.keySet());
    else
      throw new IllegalStateException("No song!");
  }

  public static final class Builder implements CompositionBuilder<MusicModel> {
    MusicModel myModel;

    public Builder() {
      myModel = new MusicModel();
    }

    @Override
    public MusicModel build() {
      return myModel;
    }

    @Override
    public CompositionBuilder<MusicModel> setTempo(int tempo) {
      myModel.setTempo(tempo);
      return this;
    }

    @Override
    public CompositionBuilder<MusicModel> addNote(int start, int end, int instrument, int pitch,
                                                  int volume) {
      myModel.addNote(start, new Note(pitch, true), end - start, volume, instrument);
      return this;
    }
  }
}