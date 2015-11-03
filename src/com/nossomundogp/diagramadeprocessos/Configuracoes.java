package com.nossomundogp.diagramadeprocessos;

import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Configuracoes extends Activity {
	
	private String[] languages = { "Ing", "Por"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configuracoes);
		
		Spinner spinner = (Spinner) findViewById(R.id.spLinguagem);
		spinner.setPrompt(getResources().getString(R.string.selecionar_linguagem));
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_spinner_item, languages);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String linguagem = "en";
				
				switch (arg2) {
				case 0:
					linguagem = "en";
					break;
				case 1:
					linguagem = "pt";
					break;
				}
				Locale locale = new Locale(linguagem);
				Locale.setDefault(locale);
				Configuration config = new Configuration();
				config.locale = locale;
				getResources().updateConfiguration(config, null);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public void onClick(View v) {
		Intent itFilho = new Intent();
		setResult(RESULT_OK, itFilho);
		finish();
	}

}
