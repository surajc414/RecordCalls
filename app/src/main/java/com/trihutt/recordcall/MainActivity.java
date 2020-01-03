package com.trihutt.recordcall;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button start,stop;
    MediaRecorder mr = new MediaRecorder();
    File audiofile=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);



        try {

            mr = new MediaRecorder();
            /*mr.setAudioSource(MediaRecorder.AudioSource.MIC);
            mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
            *///mr.setOutputFile(AudioSavePathInDevice);
        }catch (Exception e){
            Log.e("aaaa",e.toString());
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Toast.makeText(MainActivity.this, "Start Recording...", Toast.LENGTH_SHORT).show();
                File dir = Environment.getExternalStorageDirectory();



                try {
                    audiofile = File.createTempFile("sound", ".mp3", dir);
                    Toast.makeText(getApplicationContext(),audiofile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                    Log.e("aaaaa", audiofile.getAbsolutePath());

                } catch (IOException e) {
                    Log.e("sss", "external storage access error");
                    return;
                }

                mr.setAudioSource(MediaRecorder.AudioSource.MIC);
                mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                mr.setOutputFile(audiofile.getAbsolutePath());
                try {
                    mr.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mr.start();
                Log.e("Msg","Start Recording...");

                Toast.makeText(MainActivity.this, "Recording Start...", Toast.LENGTH_LONG).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    mr.stop();
                    mr.release();
                    mr.setOutputFile(audiofile.getAbsoluteFile().toString());
                    Toast.makeText(MainActivity.this, "Stop Recording...", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("Msg = > ",e.toString());
                }

            }
        });
    }
}
