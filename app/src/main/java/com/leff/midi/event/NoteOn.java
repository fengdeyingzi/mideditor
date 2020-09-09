//////////////////////////////////////////////////////////////////////////////
//	Copyright 2011 Alex Leffelman
//	
//	Licensed under the Apache License, Version 2.0 (the "License");
//	you may not use this file except in compliance with the License.
//	You may obtain a copy of the License at
//	
//	http://www.apache.org/licenses/LICENSE-2.0
//	
//	Unless required by applicable law or agreed to in writing, software
//	distributed under the License is distributed on an "AS IS" BASIS,
//	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//	See the License for the specific language governing permissions and
//	limitations under the License.
//////////////////////////////////////////////////////////////////////////////

package com.leff.midi.event;
/*
音符 单个音符
 上级类：MidiTrack
*/
public class NoteOn extends ChannelEvent
{
	/*
	参数：起始时间？ 通道 音调编号(12～108) 力度
	*/
    public NoteOn(long tick, int channel, int note, int velocity)
    {
        super(tick, ChannelEvent.NOTE_ON, channel, note, velocity);
    }

    public NoteOn(long tick, long delta, int channel, int note, int velocity)
    {
        super(tick, delta, ChannelEvent.NOTE_ON, channel, note, velocity);
    }

    public int getNoteValue()
    {
        return mValue1;
    }

    public int getVelocity()
    {
        return mValue2;
    }

    public void setNoteValue(int p)
    {
        mValue1 = p;
    }

    public void setVelocity(int v)
    {
        mValue2 = v;
    }
}