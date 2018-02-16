The Music Editor

TLDR:
I have a build of this program that you can download on dropbox 
https://www.dropbox.com/sh/1upfwq2e2y76kfg/AAAZgPg79PevMhhWsVbs8vjVa?dl=0

Run on the command line.
run java -cp musicPlayer.jar cs3500.music.MusicEditor mystery-3.txt magic


THE DESIGN
MusicEditor:
The top level class containing our main method

Factory:
Static class within MusicEditor that returns one of our views
based on a String argument; one of "console", "midi", or
"visual"

IMusicModel:
Interface for dealing with music

MusicModel:
Sample implementation of IMusicModel
Capable of reading pieces in the nice simple
input format I created (see sample_piece_input.txt)
Has a NoteMap

Builder:
Static Builder class within MusicModel
Implements CompositionBuilder
Method BUILD returns a fully realized MidiModel

Note:
Represents a musical note by its pitch and its octave

NoteMetaData:
Has a Note
Simple wrapper for a Note, includes additional MetaData
that would otherwise clog the Note class, and which is also
not inherent to the musical concept of a NOTE.
CHANGE: NoteMetaData now includes VOLUME and INSTRUMENT data,
though to maintain compatibility these values are OPTIONAL and
will be initialized to defaults

NoteMap:
Represents an entire musical piece as a hash map
where keys are the beat numbers, rests are not
represented
For each key in the map, there is another hash map indexed
by NOTE value (pitch+octave)
CHANGE: To make playing in sequence easier, we added a method
which makes it possible to query any note at any beat and establish its remaining duration

ALL VIEWS contain a model which they construct using the BUILDER

MusicEditorView:
Interface which all views are expected to implement

Controller:
Tells the MagicView what to do and passes pertinent information
along to a fully fledged MusicModel
------------------------------------------------------------
ConsoleView:
A view which can be used to render a console (ie text output
representing the notes)

GuiView:
A view which can be used to render graphical output of the notes

MidiView:
A "view" which can be used to audibly play back the piece using
MIDI
------------------------------------------------------------

MidiDevice:
Used only for testing
Implements Syntesizer and emulates ONLY
the behavior of the getReceiver function
Not useful for playing notes, but very useful
for logging and debugging

============================================================
HOW TO USE THE MUSIC EDITOR:

MOVE A NOTE:
Click the desired note-
Type: 'a' - Moves the note right
Type: 's' - Moves the note down
Type: 'w' - Moves the note up
Type: 'd' - Moves the note left

DELETE A NOTE:
Click the desired note-
Type: 'x'

RETRACT A NOTE:
Click the desired note-
type: 'q'

EXTEND A NOTE:
Click the desired note-
type: 'e'


MOVE TO THE END OF A COMPOSITION:
type: 'end'


MOVE TO THE BEGINNING OF A COMPOSITION:
type: 'home'

PAUSE THE COMPOSITION:
type: 'space'


