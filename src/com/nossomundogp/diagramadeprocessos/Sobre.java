package com.nossomundogp.diagramadeprocessos;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class Sobre extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sobre);
		
		Button btnBlog = (Button) findViewById(R.id.btnBlogNmgp);
		btnBlog.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String link = "http://nossomundogp.com/";
				abreLink(link);
			}
		});
		
		
		Button btnDesenvolvedor = (Button) findViewById(R.id.btnDesenvolvedor);
		btnDesenvolvedor.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String link = "https://br.linkedin.com/in/daybsonrabelo";//TODO: Verificar o link a ser colocado.
				abreLink(link);
			}
		});
		
	}
	
	private void abreLink(String link) {
		Uri uri = Uri.parse(link);
		Intent it = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(it);
	}
}