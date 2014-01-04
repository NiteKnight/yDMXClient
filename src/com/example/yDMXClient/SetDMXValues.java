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
    private SeekBar sbDIMMER;
    private Button btnConnect;
    private Button btnDisconnect;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
