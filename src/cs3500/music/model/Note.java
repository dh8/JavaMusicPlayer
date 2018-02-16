package cs3500.music.model;
public class Note implements Comparable<Note> {
  public static enum PITCH {
    /**
     * Represent pitches for a whole octave; rests may be
     * represented with NONE
     */

    NONE(""), C("C"), CS("C#"), D("D"), DS("D#"), E("E"), F("F"),
    FS("F#"), G("G"), GS("G#"),A("A"), AS("A#"), B("B");

    private final String name;

    private PITCH(String str) {
      this.name = str;
    }

    @Override
    public String toString() {
      return this.name;
    }
  }

  private final int octave;
  private final PITCH pitch;

  public Note(PITCH pitch, int octave)
  {
    if(octave < 0 || octave > 10) {
      throw new IllegalArgumentException("Invalid octave");
    }

    this.pitch = pitch;
    this.octave = octave;
  }

  public Note(int midiVal, boolean midi) {
    int octave = (midiVal / 12) - 1;
    int ordinal = (midiVal % 12) + 1;

    this.pitch = PITCH.values()[ordinal];
    this.octave = octave;
  }

  public Note(int beats)
  {
    this.pitch = PITCH.NONE;
    this.octave = 0;
  }

  /**
   * compare by overall pitch (octave and otherwise)
   */
  public int compareTo(Note other) {
    if(this.octave > other.octave)
      return 1;
    else if(this.octave < other.octave)
      return -1;
    else {
      return this.pitch.compareTo(other.pitch);
    }
  }

  public boolean equals(Note other) {
    return this.compareTo(other) == 0;
  }

  public PITCH pitch() {
    return this.pitch;
  }

  public int octave() {
    return this.octave;
  }

  /**
   *
   * @return a unique key represnting this note
   */
  public String key() {
    return this.pitch.toString() + this.octave;
  }

  public int mod(int n, int m) {
    // thanks to SO for a proper mod function!
    return (n < 0) ? (m - (Math.abs(n) % m) ) %m : (n % m);
  }

  /**
   *
   * @param steps the number of musical half-steps by which
   * to increase (or decrease) the note's pitch
   * @return a new Note shifted by the specified amount
   */
  public Note inc(int steps) {
    int newNoteOctave = this.octave;
    int newNoteOrdinal = (this.pitch.ordinal() % 12) + steps;

    if(newNoteOrdinal <= 0) {
      //newNoteOrdinal = 1 + mod(newNoteOrdinal-1, 12);//12 + newNoteOrdinal;
      newNoteOrdinal = 12 + newNoteOrdinal;
    }
    if(steps > 0 && newNoteOrdinal < this.pitch.ordinal()) {
      newNoteOctave++;
    }
    else if(steps < 0 && newNoteOrdinal > this.pitch.ordinal()) {
      newNoteOctave--;
    }
    PITCH newNotePitch = PITCH.values()[newNoteOrdinal];

    return new Note(newNotePitch, newNoteOctave);
  }

  public int toMidi() {
    //System.out.println(this.pitch.ordinal() + ", " + this.octave);
    return (this.pitch.ordinal() + 12 * (this.octave + 1)) - 1;
  }

  @Override
  public String toString() {
    String noteName = this.pitch.toString() + this.octave;
    if(noteName.length() == 2) {
      noteName = "  " + noteName + " ";
    }
    else {
      noteName = " " + noteName + " ";
    }

    return noteName;
  }
}
