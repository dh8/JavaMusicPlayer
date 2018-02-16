package cs3500.music.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cs3500.music.util.CompositionBuilder;

import static org.junit.Assert.assertEquals;


public class MusicModelTest {

  @Test
  public void testSamplePiece() throws IOException {
    MusicModel model = new MusicModel("sample_piece_input.txt");
    String expectedFile = this.readFile("sample_piece_output.txt");
    assertEquals(expectedFile, model.toString());
  }

  @Test
  public void emptyPiece() {
    MusicModel model = new MusicModel();
    assertEquals(model.toString(), "");
  }

  @Test
  public void testRemove() throws IOException {
    MusicModel model1 = new MusicModel("sample_piece_input.txt");
    model1.removeNote(13,  new Note(Note.PITCH.E, 4));
    String expectedFile = this.readFile("remove1out.txt");
    assertEquals(expectedFile, model1.toString());

    model1.removeNote(0, new Note(Note.PITCH.G, 3));
    expectedFile = this.readFile("remove2out.txt");
    assertEquals(expectedFile, model1.toString());

    model1.addNote(12, new Note(Note.PITCH.E, 4), 3);
    model1.addNote(0, new Note(Note.PITCH.G, 3), 7);
    expectedFile = this.readFile("sample_piece_output.txt");
    assertEquals(expectedFile, model1.toString());

    model1.removeNote(90, new Note(Note.PITCH.E, 4));
    assertEquals(expectedFile, model1.toString());

    model1.removeNote(3, new Note(Note.PITCH.F, 3));
    assertEquals(expectedFile, model1.toString());
  }

  @Test
  public void testAdd() throws IOException {
    MusicModel model1 = new MusicModel("sample_piece_input.txt");
    model1.addNote(60, new Note(Note.PITCH.E, 3), 5);
    String expectedFile = this.readFile("add1out.txt");
    assertEquals(expectedFile, model1.toString());

  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testOctaveTooLow() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Invalid octave");
    Note myNote = new Note(Note.PITCH.AS, -5);
  }

  @Test
  public void testOctaveTooHigh() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Invalid octave");
    Note myNote = new Note(Note.PITCH.AS, 11);
  }

  @Test
  public void addNegativeDuration() throws IOException {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("That's a bogus note");
    MusicModel model1 = new MusicModel("sample_piece_input.txt");
    model1.addNote(-5, new Note(Note.PITCH.AS, 5), 5);
  }

  String readFile(String filename) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    String line;
    String result = "";

    while((line = reader.readLine()) != null) {
      result += line + "\n";
    }

    reader.close();

    return result;
  }


  ////////////////////////////////////////////////////////////
  /////////////// new tests for musicModelTest////////////////
  ////////////////////////////////////////////////////////////

  @Test
  public void builderTestOne()  throws IOException  {

    CompositionBuilder<MusicModel> builder = new MusicModel.Builder();

    builder.addNote(3, 7, 1, 47, 18);
    builder.addNote(0, 1, 1, 48, 18);
    MusicModel model = builder.build();

    assertEquals(model.toString(), "   B2   C3 \n"
                                   + "0       X  \n"
                                   + "1\n"
                                   + "2\n"
                                   + "3  X       \n"
                                   + "4  |       \n"
                                   + "5  |       \n"
                                   + "6  |       \n");


  }

  @Test
  public void builderTestTwo()  throws IOException  {
    CompositionBuilder<MusicModel> builder = new MusicModel.Builder();
    builder.addNote(3, 7, 1, 47, 18);
    builder.addNote(1, 19, 1, 34, 18);
    builder.addNote(3, 7, 1, 45, 18);
    builder.addNote(6, 13, 1, 34, 18);
    builder.addNote(9, 15, 1, 78, 18);
    builder.addNote(3, 8, 1, 98, 18);
    // build the model
    MusicModel model = builder.build();
    // check that the tempo is zero
    assertEquals(model.getTempo(), 0);
    // set tempo
    builder.setTempo(3);
    MusicModel model2 = builder.build();
    // check that the temp was set
    assertEquals(model2.getTempo(), 3);
  }


}
