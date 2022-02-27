# SoundPrint
SoundPrint is a system that take as inputs some notes and produce a .gcode file that makes a printer play the associate music.
## Functional requirements
- The system take as input some notes in international format (A,B,C…);
- The system convert the input in gcode file that will be played by the printer;
  - The translator will require 
    - A Tempo (measured in BPM)
    - The input notes
    - The notes duration (Sixteenth note = 0.25, Eight note = 0.5, Quarter Note = 1, Half Note = 2, Whole Note = 4 (for 4/4 time signature))
  - At the beginning, the gcode file will contain some instructions:
    - M17: enable stepper 
    - M350 X1: set X-axis micro-stepping resolution to full steps (this makes the motor louder which, for playing music with your 3D printer, is a good thing)
    - G91: set relative positioning
- The system produces a gcode file.

## Translating music to gcode
### Translating Music to G-code
The G-code in question that we are looking to generate from music is the G0 command for a linear move. This command takes two main arguments. First, a distance to move one (or mroe but in this case just one) axis and a feedrate. So, for examnple, G0 X5 F100 means "move the X axis +5mm at a speed of 100 mm/min." Our goal is to map a note frequency to a feedrate and a note duration to a distance.
### Note Frequency to Feedrate
The first challenge is to convert a frequency (in Hz) to a feedrate (in mm/min) given the steps per millimeter for an axis. For the Ender 3, The X-axis has a steps per millimeter value of 80; this is a fixed value based on the design of the 3D printer.
First, the note's frequency in Hz is equal to the target step rate for the stepper in steps per second. For example, the note C4 (middle C) has a frequency of 261.6256 Hz. If the stepper does 261.6256 steps per second, it will vibrate in a way that makes the C4 tone. We can easily convert this step frequecy to steps per minute to match the time scale of the freedrate (millimeters per minte). 261.6256 steps/s * 60 s/min = 15697.536 steps/min.
Second, for that steps/min value we can calcualte how much distance the axis will cover in a minute based on the steps per millimeter value of 80 for the X-axis. In other words, if the stepper "plays" a note of C4, how much distance will be covered in a minute. 1597.536 steps/min ÷ 80 steps/mm = 196.2192 mm/min. So, while the X-stepper is "playing" C4, it moves at 196.2192 mm/minute. This is the feedrate for C4 then!
### Note Duration to Distance
Now, for duration, since we know the tempo for the song and the note value, we know how long each quarter note in the song lasts. So, for example, as discribed previously, in Song of Storms, a quarter note has a duration of 0.00625 minutes. The distance covered by the stepper motor in this time period depends upon the rate at which that stepper is moving (the feedrate). To calculate the duration for each note, we simply multiply the duration of each note (in minutes) by the feedrate (in mm/min). For a quarter note at a tempo of 160 beats/min playing C4, 0.00625 min * 196.2192 mm/min = 1.22637 mm.
### Creating the G-code
So, bringing together the frequency to feedrate and duration to distance calculations, we can create a G-code command that will make the stepper motor make a C4 quarter note. The only little caveat is that we round the feedrate so it does not have a decimal value.
G0 X1.22637 F196
There is one additional G-code needed to string a bunch of notes together. We will need to set the printer to use relative positioning rather than absolute so it will keep moving along as we issue more commands. We can set relative positioning using the "M305 X1" command.[...]

(source: https://github.com/Toglefritz/Musical_Marlin)

## Formulas
- Freq to feed-rate
  - freq Hz → freq step/s 
  - feedrate mm/min = freq in step/s * 60 s/min ÷ printer steps/mm 
    - example: note C4 → freq = 261.6256Hz → 261.6256step/s → feed-rate mm/min =  261.6256step/s * 60 s/min ÷ 80steps/mm = 196.2192 mm/minute
- Note duration to distance 
  - duration min = 1 beat ÷ (given by user) beats/min 
    - example: 1 beat ÷ 160 beats/min =  0,00625min (if tempo = 160 BPM)
  - distance = duration min * feed-rate mm/min 
    - example: 0,00625 min * 196.2192 mm/minute = 1.22 637 mm 
    - gcode = G0 X1.22637 F196