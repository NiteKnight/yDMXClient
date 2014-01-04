/*
 * Copyright (c) 2014. by Michael "NiteKnight" Scheidl. Baden/AUSTRIA
 */

package com.example.yDMXClient;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

public class SetDMXValues extends Activity {
    private SeekBar sbRed;
    private SeekBar sbGreen;
    private SeekBar sbBlue;
    private SeekBar sbDimmer;
    private Button btnConnect;
    private Button btnDisconnect;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        InitializeApp();
    }

    private void InitializeApp() {
        sbRed = (SeekBar) findViewById(R.id.sbRed);
        sbGreen = (SeekBar) findViewById(R.id.sbGreen);
        sbBlue = (SeekBar) findViewById(R.id.sbBlue);
        sbDimmer = (SeekBar) findViewById(R.id.sbDimmer);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        btnDisconnect = (Button) findViewById(R.id.btnDisconnect);
    }
}
