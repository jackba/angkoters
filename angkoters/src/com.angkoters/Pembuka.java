package com.angkoters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Pembuka extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        View gambar =     findViewById(R.id.logo);
        gambar.setOnClickListener(this);
        
       
    }
    public void onClick(View v){
    	switch (v.getId()){
    	case R.id.logo:
    		startActivity(new Intent (this, MenuUtama.class));
    		break;
    		}
    	}
    }
