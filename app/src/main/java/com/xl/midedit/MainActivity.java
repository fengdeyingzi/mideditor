package com.xl.midedit;

import android.app.*;
import android.os.*;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;

import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.leff.midi.*;
import java.io.*;
import java.util.*;
import com.leff.midi.event.meta.*;
import com.leff.midi.event.*;
import android.media.*;
import android.content.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

	@Override
	public void onClick(View p1)
	{
		int value=0;
		switch(p1.getId())
		{
			case R.id.mvalue1:
				value=1;
				break;
			case R.id.mvalue2:
				value=2;
				break;
			case R.id.mvalue3:
				value=3;
				break;
			case R.id.mvalue4:
				value=4;
				break;
			case R.id.mvalue5:
				value=5;
				break;
			case R.id.mvalue6:
				value=6;
				break;
			case R.id.mvalue7:
				value=7;
				break;
			case R.id.mvalue8:
				value=8;
				break;
			case R.id.mvalue9:
				value=9;
				break;
			case R.id.mvalue10:
				value=10;
				break;
			case R.id.mvalue11:
				value=11;
				break;
			case R.id.mvalue12:
				value=12;
				break;
			case R.id.jvalue1:
				noteLeve = 1;
				break;
			case R.id.jvalue2:
				noteLeve=2;
				break;
			case R.id.jvalue3:
				noteLeve=3;
				break;
			case R.id.jvalue4:
				noteLeve=4;
				break;
			case R.id.jvalue5:
				noteLeve=5;
				break;
			case R.id.jvalue6:
				noteLeve=6;
				break;
			case R.id.jvalue7:
				noteLeve=7;
				break;
			case R.id.btn_setNotes:
				showDialog(DLG_NOTES);
		}
		if(value>=1 && value<=12)
		{
			playNote(value+noteLeve*12,velocity);
		}
		
		
	}
	
	int noteTime=1000; //音符时间
	int velocity=100;
	int noteLeve=6; // 音调
	int noteType = 0; //乐器
	// 1. Create some MidiTracks
	MidiTrack tempoTrack = new MidiTrack();
	MidiTrack noteTrack = new MidiTrack();
	
	Button btn_value1,btn_value2,btn_value3,btn_value4,btn_value5,btn_value6,btn_value7,btn_value8,btn_value9,btn_value10,btn_value11,btn_value12;
	Button btn_jvalue1,btn_jvalue2,btn_jvalue3,btn_jvalue4,btn_jvalue5,btn_jvalue6,btn_jvalue7,btn_jvalue8,btn_jvalue9,btn_jvalue10;
	Button btn_setNotes;
	
	MediaPlayer mediaPlayer ;
	
	static final int DLG_NOTES =100;
	
	
	String notes = " 0 大钢琴（声学钢琴） \n"
	+" 1 明亮的钢琴 \n"
	+" 2 电钢琴 \n"
	+" 3 酒吧钢琴 \n"
	+" 4 柔和的电钢琴 \n"
	+" 5 加合唱效果的电钢琴 \n"
	+" 6 羽管键琴（拨弦古钢琴） \n"
	+" 7 科拉维科特琴（击弦古钢琴） \n"
	+" 8 钢片琴 \n"
	+" 9 钟琴 \n"
	+" 10 八音盒 \n"
	+" 11 颤音琴 \n"
	+" 12 马林巴 \n"
	+" 13 木琴 \n"
	+" 14 管钟 \n"
	+" 15 大扬琴 \n"
	+" 16 击杆风琴 \n"
	+" 17 打击式风琴 \n"
	+" 18 摇滚风琴 \n"
	+" 19 教堂风琴 \n"
	+" 20 簧管风琴 \n"
	+" 21 手风琴 \n"
	+" 22 口琴 \n"
	+" 23 探戈手风琴 \n"
	+" 24 尼龙弦吉他 \n"
	+" 25 钢弦吉他 \n"
	+" 26 爵士电吉他 \n"
	+" 27 清音电吉他 \n"
	+" 28 闷音电吉他 \n"
	+" 29 加驱动效果的电吉他 \n"
	+" 30 加失真效果的电吉他 \n"
	+" 31 吉他和音 \n"
	+" 32 大贝司（声学贝司） \n"
	+" 33 电贝司（指弹） \n"
	+" 34 电贝司（拨片） \n"
	+" 35 无品贝司 \n"
	+" 36 掌击Bass 1 \n"
	+" 37 掌击Bass 2 \n"
	+" 38 电子合成Bass 1 \n"
	+" 39 电子合成Bass 2 \n"
	+" 40 小提琴 \n"
	+" 41 中提琴 \n"
	+" 42 大提琴 \n"
	+" 43 低音大提琴 \n"
	+" 44 弦乐群颤音音色 \n"
	+" 45 弦乐群拨弦音色 \n"
	+" 46 竖琴 \n"
	+" 47 定音鼓 \n"
	+" 48 弦乐合奏音色1 \n"
	+" 49 弦乐合奏音色2 \n"
	+" 50 合成弦乐合奏音色1 \n"
	+" 51 合成弦乐合奏音色2 \n"
	+" 52 人声合唱“啊” \n"
	+" 53 人声“嘟” \n"
	+" 54 合成人声 \n"
	+" 55 管弦乐敲击齐奏 \n"
	+" 56 小号 \n"
	+" 57 长号 \n"
	+" 58 大号 \n"
	+" 59 加弱音器小号 \n"
	+" 60 法国号（圆号） \n"
	+" 61 铜管组（铜管乐器合奏音色） \n"
	+" 62 合成铜管音色1 \n"
	+" 63 合成铜管音色2 \n"
	+" 64 高音萨克斯风 \n"
	+" 65 次中音萨克斯风 \n"
	+" 66 中音萨克斯风 \n"
	+" 67 低音萨克斯风 \n"
	+" 68 双簧管 \n"
	+" 69 英国管 \n"
	+" 70 巴松（大管） \n"
	+" 71 单簧管（黑管） \n"
	+" 72 短笛 \n"
	+" 73 长笛 \n"
	+" 74 竖笛 \n"
	+" 75 排箫 \n"
	+" 76 Bottle Blow[中文名称暂缺]\n"
	+" 77 日本尺八 \n"
	+" 78 口哨声 \n"
	+" 79 Ocarina 奥卡雷那 \n"
	+" 80 合成主音1（方波） \n"
	+" 81 合成主音2（锯齿波） \n"
	+" 82 合成主音3 \n"
	+" 83 合成主音4 \n"
	+" 84 合成主音5 \n"
	+" 85 合成主音6（人声） \n"
	+" 86 合成主音7（平行五度） \n"
	+" 87 合成主音8（贝司加主音） \n"
	+" 88 合成音色1（新世纪） \n"
	+" 89 合成音色2 （温暖） \n"
	+" 90 合成音色3 \n"
	+" 91 合成音色4 （合唱） \n"
	+" 92 合成音色5 \n"
	+" 93 合成音色6 （金属声） \n"
	+" 94 合成音色7 （光环） \n"
	+" 95 合成音色8 \n"
	+" 96 合成效果 1 雨声 \n"
	+" 97 合成效果 2 音轨 \n"
	+" 98 合成效果 3 水晶 \n"
	+" 99 合成效果 4 大气 \n"
	+" 100 合成效果 5 明亮 \n"
	+" 101 合成效果 6 鬼怪 \n"
	+" 102 合成效果 7 回声 \n"
	+" 103 合成效果 8 科幻 \n"
	+" 104 西塔尔（印度） \n"
	+" 105 班卓琴（美洲） \n"
	+" 106 三昧线（日本） \n"
	+" 107 十三弦筝（日本） \n"
	+" 108 卡林巴 \n"
	+" 109 风笛 \n"
	+" 110 民族提琴 \n"
	+" 111 山奈 \n"
	+" 112 叮当铃 \n"
	+" 113 Agogo [中文名称暂缺]\n"
	+" 114 钢鼓 \n"
	+" 115 木鱼 \n"
	+" 116 太鼓 \n"
	+" 117 通通鼓 \n"
	+" 118 合成鼓 \n"
	+" 119 铜钹\n"
	+" 120 吉他换把杂音 \n"
	+" 121 呼吸声 \n"
	+" 122 海浪声 \n"
	+" 123 鸟鸣 \n"
	+" 124 电话铃 \n"
	+" 125 直升机 \n"
	+" 126 鼓掌声 \n"
	+" 127 枪声\n";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		mediaPlayer = new MediaPlayer();
		
		btn_setNotes = (Button)findViewById(R.id.btn_setNotes);
		btn_setNotes.setOnClickListener(this);
		btn_value1 = (Button)findViewById(R.id.mvalue1);
		btn_value1.setOnClickListener(this);
		btn_value2 = (Button)findViewById(R.id.mvalue2);
		btn_value2.setOnClickListener(this);
		btn_value3 = (Button)findViewById(R.id.mvalue3);
		btn_value3.setOnClickListener(this);
		btn_value4 = (Button)findViewById(R.id.mvalue4);
		btn_value4.setOnClickListener(this);
		btn_value5 = (Button)findViewById(R.id.mvalue5);
		btn_value5.setOnClickListener(this);
		btn_value6 = (Button)findViewById(R.id.mvalue6);
		btn_value6.setOnClickListener(this);
		btn_value7 = (Button)findViewById(R.id.mvalue7);
		btn_value7.setOnClickListener(this);
		btn_value8 = (Button)findViewById(R.id.mvalue8);
		btn_value8.setOnClickListener(this);
		btn_value9 = (Button)findViewById(R.id.mvalue9);
		btn_value9.setOnClickListener(this);
		btn_value10 = (Button)findViewById(R.id.mvalue10);
		btn_value10.setOnClickListener(this);
		btn_value11 = (Button)findViewById(R.id.mvalue11);
		btn_value11.setOnClickListener(this);
		btn_value12 = (Button)findViewById(R.id.mvalue12);
		btn_value12.setOnClickListener(this);
		
		btn_jvalue1 = (Button)findViewById(R.id.jvalue1);
		btn_jvalue1.setOnClickListener(this);
		btn_jvalue2 = (Button)findViewById(R.id.jvalue2);
		btn_jvalue2.setOnClickListener(this);
		btn_jvalue3 = (Button)findViewById(R.id.jvalue3);
		btn_jvalue3.setOnClickListener(this);
		btn_jvalue4 = (Button)findViewById(R.id.jvalue4);
		btn_jvalue4.setOnClickListener(this);
		
		btn_jvalue5 = (Button)findViewById(R.id.jvalue5);
		btn_jvalue5.setOnClickListener(this);
		btn_jvalue6 = (Button)findViewById(R.id.jvalue6);
		btn_jvalue6.setOnClickListener(this);
		btn_jvalue7 = (Button)findViewById(R.id.jvalue7);
		btn_jvalue7.setOnClickListener(this);
		requestPermission();
    }

	private void requestPermission() {
		PermissionsUtil.requestPermission(getApplication(), new PermissionListener() {
			@Override
			public void permissionGranted(@NonNull String[] permissions) {
				//toast("");
//				createDir();
			}

			@Override
			public void permissionDenied(@NonNull String[] permissions) {
				//Toast.makeText(MainActivity.this, "用户拒绝了访问摄像头", Toast.LENGTH_LONG).show();
			}
		},  android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
	}
	
	//检测是否为手机系统
	static boolean isAndroid()
	{
		File file=new File("mnt/sdcard");
		if(file.isDirectory())
			return true;
		return false;
	}
	
	public ArrayList<MidiTrack> createMidi()
	{
		
        // 2. Add events to the tracks
        // 2a. Track 0 is typically the tempo map
        TimeSignature ts = new TimeSignature();
        ts.setTimeSignature(4, 4, TimeSignature.DEFAULT_METER, TimeSignature.DEFAULT_DIVISION);

        Tempo t = new Tempo();
        t.setBpm(123);

        tempoTrack.insertEvent(ts);
        tempoTrack.insertEvent(t);
		ProgramChange pro =  new ProgramChange(0, 1, noteType);
		tempoTrack.insertEvent(pro);
		ArrayList<MidiTrack> tracks = new ArrayList<MidiTrack>();
        tracks.add(tempoTrack);
        tracks.add(noteTrack);
		return tracks;
	}
	
	//播放一个音符
	public void playNote(int pitch,int velocity)
	{
		// 1. Create some MidiTracks
		MidiTrack tempoTrack = new MidiTrack();
		MidiTrack noteTrack = new MidiTrack();
		int starttime=0;
		int channel =1;
		NoteOn on = new NoteOn(starttime, channel, pitch, velocity);
		NoteOff off = new NoteOff(starttime + noteTime, channel, pitch, 0);

		noteTrack.insertEvent(on);
		noteTrack.insertEvent(off);
		/*
		for(int i = 1; i < 108; i++)
        {
             channel = 1;
			 pitch = 1 + i;
			 velocity = 100;
             on = new NoteOn(i * 1000, channel, pitch, velocity);
             off = new NoteOff(i * 1000 + 500, channel, pitch, 0);

            noteTrack.insertEvent(on);
            noteTrack.insertEvent(off);

            // There is also a utility function for notes that you should use
            // instead of the above.
            //noteTrack.insertNote(channel, pitch + 2, velocity, i * 480, 120);
        }
		*/
		//ArrayList<MidiTrack> tracks= null;
		
		// 2. Add events to the tracks
        // 2a. Track 0 is typically the tempo map
        TimeSignature ts = new TimeSignature();
        ts.setTimeSignature(4, 4, TimeSignature.DEFAULT_METER, TimeSignature.DEFAULT_DIVISION);

        Tempo t = new Tempo();
		//节拍？？
        t.setBpm(123);

        tempoTrack.insertEvent(ts);
        tempoTrack.insertEvent(t);
		ProgramChange pro =  new ProgramChange(0, channel, noteType);
		tempoTrack.insertEvent(pro);
		ArrayList<MidiTrack> tracks = new ArrayList<MidiTrack>();
        tracks.add(tempoTrack);
        tracks.add(noteTrack);
		MidiFile midi = new MidiFile(MidiFile.DEFAULT_RESOLUTION, tracks);

		// 4. Write the MIDI data to a file
		File output = null;
		if(!isAndroid())
			output = new File("example.mid");
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
		
		mediaPlayer.reset();
		try
		{
			mediaPlayer.setDataSource(output.getPath());
			mediaPlayer.prepare(); //同步
		}
		catch (IllegalArgumentException e)
		{}
		catch (IOException e)
		{}
		catch (SecurityException e)
		{}
		catch (IllegalStateException e)
		{}
		
		mediaPlayer.start();
		
	}
		
	
	
	//写入一个音符
	void noteMidi(int starttime,int channel,int pitch,int velocity)
	{
		NoteOn on = new NoteOn(starttime, channel, pitch, velocity);
		NoteOff off = new NoteOff(starttime + noteTime, channel, pitch, 0);

		noteTrack.insertEvent(on);
		noteTrack.insertEvent(off);
		
	}
	
	
	//保存文件
	void saveMidi(ArrayList<MidiTrack> tracks )
	{
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

	@Override
	protected Dialog onCreateDialog(int id)
	{
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		switch(id)
		{
			case DLG_NOTES:
				return builder
					.setTitle("选择乐器")

					.setItems(notes.split("\n"),
					new DialogInterface.OnClickListener()
					{

						@Override
						public void onClick(DialogInterface dialog,int id)
						{
						noteType = id;

						}


					})
					.create();
		}
		return super.onCreateDialog(id);
	}
	
	
	
	
}
