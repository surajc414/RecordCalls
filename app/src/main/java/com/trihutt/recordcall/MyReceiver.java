package com.trihutt.recordcall;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.FileDescriptor;
public class MyReceiver extends BroadcastReceiver {

    String AudioSavePathInDevice=null;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "Power Disconnected", Toast.LENGTH_SHORT).show();
        Log.e("Msg","Power Disconnected");


        try {
            AudioSavePathInDevice = Environment.getDownloadCacheDirectory().getAbsoluteFile() + "/" + "abc.3gp";
            Toast.makeText(context, AudioSavePathInDevice, Toast.LENGTH_SHORT).show();
            Log.e("aaaaa", AudioSavePathInDevice);
            MediaRecorder mr = new MediaRecorder();
            mr.setAudioSource(MediaRecorder.AudioSource.MIC);
            mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
            mr.setOutputFile(AudioSavePathInDevice);
        }catch (Exception e){
            Log.e("aaaa",e.toString());
        }
    }
}
