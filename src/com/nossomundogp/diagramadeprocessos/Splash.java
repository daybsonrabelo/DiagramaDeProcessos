package com.nossomundogp.diagramadeprocessos;

import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.app.Activity;
import android.content.Intent;

public class Splash extends Activity implements Runnable {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		Handler handler = new Handler();
		handler.postDelayed(this, 5000);//5 segundos
	}

	@Override
	public void run() {
		startActivity(new Intent(this, Principal.class));
		finish();
	}

}
