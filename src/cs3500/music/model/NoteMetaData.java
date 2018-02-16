package cs3500.music.model;
public class NoteMetaData {
  /**
   * Wrapper for a note
   * Useful for information that is not directly pertinent to the tones
   * but is useful overall
   */

  public Note note;
  public boolean articulate;
  public int volume = 64;
  public int instrument = 0;

  public NoteMetaData(Note n, boolean articulate) {
    this.note = n;
    this.articulate = articulate;
  }

  public NoteMetaData(Note n, boolean articulate, int volume, int instrument) {
    this.note = n;
    this.articulate = articulate;
    this.volume = volume;
    this.instrument = instrument;
  }
}
