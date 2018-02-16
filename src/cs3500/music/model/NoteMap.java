package cs3500.music.model;
import java.util.ArrayList;
import java.util.HashMap;

public class NoteMap {
  /**
   * Represent a composition as a HashMap with beat numbers as keys
   */

  protected HashMap<Integer, HashMap<String, NoteMetaData>> song;
  private Note minNote = new Note(Note.PITCH.G, 10);
  private Note maxNote = new Note(Note.PITCH.C, 1);

  public NoteMap() {
    this.song = new HashMap<Integer, HashMap<String, NoteMetaData>>();
  }

  /**
   * lowest note
   * @return lowest note in the piece
   */
  public Note getMinNote() {
    return this.minNote;
  }

  /**
   * highest note
   * @return highest note in the piece
   */
  public Note getMaxNote() {
    return this.maxNote;
  }

  private void addNoteHelper(int beatNum, Note n, boolean articulate, int volume, int instrument) {
    HashMap<String, NoteMetaData> notes = new HashMap<String, NoteMetaData>();
    if(song.containsKey(beatNum)) {
      notes = song.get(beatNum);
    }
    else {
      song.put(beatNum, notes);
    }

    if(!notes.containsKey(n.pitch().toString() + n.octave())) {
      notes.put(n.pitch().toString() + n.octave(), new NoteMetaData(n, articulate, volume,
                                                                    instrument));
    }
    else if(notes.containsKey(n.pitch().toString() + n.octave()) && articulate) {
      notes.get(n.pitch().toString() + n.octave()).articulate = true;
    }
  }

  /**
   *
   * @param beatNum bemt number at which to start the note
   * @param n note to add
   * @param duration length of the note
   */
  public void addNote(int beatNum, Note n, int duration, int volume, int instrument) {
    if(beatNum < 0 || duration < 0) {
      throw new IllegalArgumentException("That's a bogus note");
    }

    if(n.compareTo(this.minNote) < 0 && n.pitch() != Note.PITCH.NONE) {
      this.minNote = n;
    }
    if(n.compareTo(this.maxNote) > 0 && n.pitch() != Note.PITCH.NONE) {
      this.maxNote = n;
    }

    for(int i = beatNum; i < beatNum + duration; i++) {
      addNoteHelper(i, n, i == beatNum, volume, instrument);
    }
  }

  /**
   *
   * @param beatNum beat where note to remove starts
   * @param n  number of beats
   */
  public void removeNote(int beatNum, Note n) {
    if(beatNum < 0) {
      throw new IllegalArgumentException("That's a bogus note to remove");
    }

    int bn = beatNum;

    int dir = -1;
    // do we have a note at this beat number?
    while(song.containsKey(bn)) {
      boolean articulated = false;
      if(song.get(bn).containsKey(n.key())) {
        articulated = song.get(bn).get(n.key()).articulate;
        if(!articulated || dir < 0)
          song.get(bn).remove(n.key());
      }
      if(!song.containsKey(bn) || articulated) {
        if(dir < 0)
          dir = 1;
        else
          break;
      }
      bn += dir;
    }
  }

  public ArrayList<String> associatedNotes(int beatNum, Note n) {
    ArrayList<String> associated = new ArrayList<>();

    if(beatNum < 0) {
      throw new IllegalArgumentException("You can't select that");
    }

    int bn = beatNum;

    int dir = -1;
    // do we have a note at this beat number?
    while(song.containsKey(bn)) {
      boolean articulated = false;
      if(song.get(bn).containsKey(n.key())) {
        articulated = song.get(bn).get(n.key()).articulate;
        if(!articulated || dir < 0)
          associated.add(bn + "," + n.key());
      }
      if(!song.containsKey(bn) || articulated) {
        if(dir < 0)
          dir = 1;
        else
          break;
      }
      bn += dir;
    }

    return associated;
  }

  public int getNoteStartBeat(int bn, Note n) {
    while(song.containsKey(bn)) {
      String key = n.key();
      if(song.get(bn).containsKey(key)) {
        if(song.get(bn).get(key).articulate)
          break;
      }
      bn--;
    }

    return bn;
  }

  public int getNoteEndBeat(int bn, Note n) {
    int startBn = bn;

    while(song.containsKey(bn)) {
      String key = n.key();
      if(song.get(bn).containsKey(key)) {
        if(song.get(bn).get(key).articulate && bn != startBn)
          break;
      }
      else {
        break;
      }
      bn++;
    }

    return bn;
  }

  public int queryNoteLength(int bn, Note n) {
    int length = 0;

    while(song.containsKey(bn)) {
      String key = n.key();
      if(song.get(bn).containsKey(key)) {
        if(song.get(bn).get(key).articulate && length != 0)
          break;
        else
          length++;
      }
      else {
        break;
      }
      bn++;
    }

    return length;
  }
}