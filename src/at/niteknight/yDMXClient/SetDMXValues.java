/*
 * Copyright (c) 2014. by Michael "NiteKnight" Scheidl. Baden/AUSTRIA
 */

package at.niteknight.yDMXClient;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.app.AlertDialog;
import android.widget.TextView;

import java.text.DecimalFormat;

public class SetDMXValues extends Activity {
    private SeekBar sbRed;
    private SeekBar sbGreen;
    private SeekBar sbBlue;
    private SeekBar sbDimmer;
    private Button btnConnect;
    private Button btnDisconnect;
    private Button btnBlackOut;
    private TextView lblRed;
    private TextView lblGreen;
    private TextView lblBlue;
    private TextView lblDimmer;
    private tcpClient _yClient;
    private View cvCurrentColor;

    private SeekBar.OnSeekBarChangeListener redbarListener;
    private SeekBar.OnSeekBarChangeListener greenbarListener;
    private SeekBar.OnSeekBarChangeListener bluebarListener;
    private SeekBar.OnSeekBarChangeListener dimmerbarListener;
    private Button.OnClickListener connectButtonListener;

    private DecimalFormat _decFormat = new DecimalFormat("0.0");

    private boolean BlackOutState = false;

    private AlertDialog.Builder dlgAlert;

    @Override
    public Object onRetainNonConfigurationInstance()
    {
        return _yClient;
    }

    private void setViewColor()
    {
        cvCurrentColor.setBackgroundColor(Color.argb(255,sbRed.getProgress(),sbGreen.getProgress(),sbBlue.getProgress()));
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        _yClient = (tcpClient)getLastNonConfigurationInstance();
        if (_yClient == null) {
            _yClient = new tcpClient();
        }
        InitializeApp();
    }

    private void InitializeApp() {
        sbRed = (SeekBar) findViewById(R.id.sbRed);
        sbGreen = (SeekBar) findViewById(R.id.sbGreen);
        sbBlue = (SeekBar) findViewById(R.id.sbBlue);
        sbDimmer = (SeekBar) findViewById(R.id.sbDimmer);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        btnDisconnect = (Button) findViewById(R.id.btnDisconnect);
        btnBlackOut = (Button) findViewById(R.id.btnBlackOut);
        lblRed = (TextView) findViewById(R.id.lblRed);
        lblGreen = (TextView) findViewById(R.id.lblGreen);
        lblBlue = (TextView) findViewById(R.id.lblBlue);
        lblDimmer = (TextView) findViewById(R.id.lblDimmer);
        cvCurrentColor = (View) findViewById(R.id.cvCurrentColor);

        dlgAlert = new AlertDialog.Builder(this);

        setViewColor();

        redbarListener = new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser)
            {
                setViewColor();
               _yClient.sendFaderValue(1,progress);
               lblRed.setText(getString(R.string.sliderRED) + " ("+progress+")");
            }

            public void onStartTrackingTouch(SeekBar seekBar)
            {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar)
            {
                // TODO Auto-generated method stub
            }
        };
        sbRed.setOnSeekBarChangeListener(redbarListener);

        greenbarListener = new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser)
            {
                setViewColor();
                _yClient.sendFaderValue(2, progress);
                lblGreen.setText(getString(R.string.sliderGREEN) + " ("+progress+")");
            }

            public void onStartTrackingTouch(SeekBar seekBar)
            {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar)
            {
                // TODO Auto-generated method stub
            }
        };
        sbGreen.setOnSeekBarChangeListener(greenbarListener);

        bluebarListener = new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser)
            {
                setViewColor();
                _yClient.sendFaderValue(3, progress);
                lblBlue.setText(getString(R.string.sliderBLUE) + " ("+progress+")");
            }

            public void onStartTrackingTouch(SeekBar seekBar)
            {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar)
            {
                // TODO Auto-generated method stub
            }
        };
        sbBlue.setOnSeekBarChangeListener(bluebarListener);

        dimmerbarListener = new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser)
            {
                _yClient.sendFaderValue(4, progress);
                lblDimmer.setText(getString(R.string.sliderDIMMER) + " ("+ _decFormat.format((float)progress/255 *100) +"%)");
            }

            public void onStartTrackingTouch(SeekBar seekBar)
            {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar)
            {
                // TODO Auto-generated method stub
            }
        };
        sbDimmer.setOnSeekBarChangeListener(dimmerbarListener);

        btnBlackOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BlackOutState = !BlackOutState;
                _yClient.sendBlackOut(BlackOutState);
            }
        });

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = "";
                result = _yClient.connect();
                //dlgAlert.setMessage(result);
                //dlgAlert.setTitle("Verbindungsversuch");
                //dlgAlert.setPositiveButton("OK", null);
                //dlgAlert.setCancelable(true);
                //dlgAlert.create().show();
            }
        });

        btnDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _yClient.close();
            }
        });
    }
}
