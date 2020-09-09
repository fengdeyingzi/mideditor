package com.leff.midi.examples;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.leff.midi.MidiFile;
import com.leff.midi.MidiTrack;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;
import com.leff.midi.event.meta.TimeSignature;
import com.leff.midi.event.*;

public class MidiFileFromScratch
{
	
	//检测是否为手机系统
	static boolean isAndroid()
	{
		File file=new File("mnt/sdcard");
		if(file.isDirectory())
			return true;
		return false;
	}
	
    public static void main(String[] args)
    {
        // 1. Create some MidiTracks
        MidiTrack tempoTrack = new MidiTrack();
        MidiTrack noteTrack = new MidiTrack();

        // 2. Add events to the tracks
        // 2a. Track 0 is typically the tempo map
        TimeSignature ts = new TimeSignature();
        ts.setTimeSignature(4, 4, TimeSignature.DEFAULT_METER, TimeSignature.DEFAULT_DIVISION);

        Tempo t = new Tempo();
        t.setBpm(123);

        tempoTrack.insertEvent(ts);
        tempoTrack.insertEvent(t);
		ProgramChange pro =  new ProgramChange(0, 1, 116);
		tempoTrack.insertEvent(pro);
        // 2b. Track 1 will have some notes in it
        for(int i = 1; i < 108; i++)
        {
            int channel = 1, pitch = 1 + i, velocity = 100;
            NoteOn on = new NoteOn(i * 1000, channel, pitch, velocity);
            NoteOff off = new NoteOff(i * 1000 + 500, channel, pitch, 0);

            noteTrack.insertEvent(on);
            noteTrack.insertEvent(off);

            // There is also a utility function for notes that you should use
            // instead of the above.
            //noteTrack.insertNote(channel, pitch + 2, velocity, i * 480, 120);
        }

        // It's best not to manually insert EndOfTrack events; MidiTrack will
        // call closeTrack() on itself before writing itself to a file

        // 3. Create a MidiFile with the tracks we created
        ArrayList<MidiTrack> tracks = new ArrayList<MidiTrack>();
        tracks.add(tempoTrack);
        tracks.add(noteTrack);

        MidiFile midi = new MidiFile(MidiFile.DEFAULT_RESOLUTION, tracks);

        // 4. Write the MIDI data to a file
        File output = null;
		if(!isAndroid())
		output = new File("exampleout.mid");
		else 
		output = new File("mnt/sdcard/example.mid");
        try
        {
            midi.writeToFile(output);
        }
        catch(IOException e)
        {
            System.err.println(e);
        }
    }
}
