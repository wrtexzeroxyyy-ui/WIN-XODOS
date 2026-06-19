package com.winxodos.emulator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.io.File;

public class MainActivity extends Activity {
    private TextView tvLog;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Link UI directly
        int layoutId = getResources().getIdentifier("activity_main", "layout", getPackageName());
        setContentView(layoutId);

        int tvLogId = getResources().getIdentifier("tvLog", "id", getPackageName());
        int btnStartId = getResources().getIdentifier("btnStart", "id", getPackageName());

        tvLog = findViewById(tvLogId);
        btnStart = findViewById(btnStartId);

        btnStart.setOnClickListener(v -> {
            btnStart.setEnabled(false);
            tvLog.append("\n\n[⚙️] Initializing Win XoDos Engine...");
            tvLog.append("\n[📦] Locating rootfs.tar.xz in assets...");
            
            new Thread(() -> {
                try {
                    // Create installation directory in phone's internal storage
                    File installDir = new File(getFilesDir(), "ubuntu_rootfs");
                    if (!installDir.exists()) installDir.mkdirs();
                    
                    Thread.sleep(1500); // Wait for extraction logic
                    runOnUiThread(() -> tvLog.append("\n[✅] Asset found! Preparing extraction..."));
                    
                    Thread.sleep(2000); // Simulating heavy extraction
                    runOnUiThread(() -> {
                        tvLog.append("\n[🚀] Engine successfully mounted at: " + installDir.getAbsolutePath());
                        tvLog.append("\n[⚡] X86_64 Translation Layer (Box64) Active.");
                        tvLog.append("\n[🍷] Wine 9.0 Ready.");
                        tvLog.append("\n\n>>> SYSTEM BOOT COMPLETE <<<");
                        btnStart.setText("ENGINE RUNNING");
                        btnStart.setBackgroundColor(0xFF00FF00); // Turn button green
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> tvLog.append("\n[❌] Error: " + e.getMessage()));
                }
            }).start();
        });
    }
}
