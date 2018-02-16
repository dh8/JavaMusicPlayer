package cs3500.music.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MusicFileReader {
  /**
   *
   * @param filename  self-explanatory; the name of the file to read
   * @return      a NoteMap representing the piece
   * @throws IOException
   */

  public static NoteMap create(String filename) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    String line;
    NoteMap nm = new NoteMap();

    int currentBeat = 0;
    while((line = reader.readLine()) != null) {
      line = line.trim();

      if(line.equals("")) {
        currentBeat = 0;
      }
      else
      {
        String noteParts[] = line.split(":");
        int dur = Integer.parseInt(noteParts[1]);

        if(noteParts[0].length() > 0) {
          String pitch = noteParts[0];
          String note = "" + pitch.charAt(0);
          if(pitch.charAt(1) == '#') {
            pitch = pitch.replace("#", "");
            note += "#";
          }
          int octave = Integer.parseInt(pitch.substring(1));
          //nm.addNote(currentBeat, new Note(Note.stringToPitch(note), dur, octave), dur);
          nm.addNote(currentBeat, new Note(Note.PITCH.valueOf(note), octave), dur, 0, 0);
        }

        currentBeat += dur;
      }
    }
    reader.close();
    return nm;
  }
}
