package com.nossomundogp.diagramadeprocessos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory2;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class Principal extends Activity implements OnClickListener {
	
	public final int Principal = 1;
	//public final int Configuracoes = 2;
	
	/**
	 * Menus da aplicação.
	 */
	public static final int AJUDA = 0;
	public static final int SOBRE = 1;
	//public static final int CONFIGURACOES = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        
        tratamentoArquivos();
		        
        Button btnIniciacao = (Button) findViewById(R.id.btnIniciacao);
		Button btnPlanejamento = (Button) findViewById(R.id.btnPlanejamento);
		Button btnExecucao = (Button) findViewById(R.id.btnExecucao);
		Button btnMonitoramento = (Button) findViewById(R.id.btnMonitoramento);
		Button btnEncerramento = (Button) findViewById(R.id.btnEncerramento);
		
		btnIniciacao.setOnClickListener(this);
		btnPlanejamento.setOnClickListener(this);
		btnExecucao.setOnClickListener(this);
		btnMonitoramento.setOnClickListener(this);
		btnEncerramento.setOnClickListener(this);
		
    }

	@Override
	public void onClick(View v) {
		Intent it = new Intent(this, DiagramaParcial.class);
		Bundle params = new Bundle();
		
		switch (v.getId()) {
		case R.id.btnIniciacao:
			params.putString("Tipo", "A");
			params.putInt("Total", 10);
			break;
		case R.id.btnPlanejamento:
			params.putString("Tipo", "B");
			params.putInt("Total", 25);
			break;
		case R.id.btnExecucao:
			params.putString("Tipo", "C");
			params.putInt("Total", 22);
			break;
		case R.id.btnMonitoramento:
			params.putString("Tipo", "D");
			params.putInt("Total", 19);
			break;
		case R.id.btnEncerramento:
			params.putString("Tipo", "E");
			params.putInt("Total", 9);
			break;
		}
		it.putExtras(params);
		startActivityForResult(it, Principal);
	}
	
	public void onActivityResult(int codigo, int resultado, Intent it) {
		if (codigo ==  Principal) {
			if (resultado == RESULT_OK) {
				if (it != null) {
					Bundle params = it.getExtras();
					
					String tipo = params.getString("Tipo"); 
					ArrayList<String> lista = params.getStringArrayList("Lista");
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
					
					ListView lv = null;
					
					if (tipo.equals("A")) {
						lv = (ListView) findViewById(R.id.lvIniciacao);
					} else if (tipo.equals("B")) {
						lv = (ListView) findViewById(R.id.lvPlanejamento);
					} else if (tipo.equals("C")) {
						lv = (ListView) findViewById(R.id.lvExecucao);
					} else if (tipo.equals("D")) {
						lv = (ListView) findViewById(R.id.lvMonitoramento);
					} else  {
						lv = (ListView) findViewById(R.id.lvEncerramento);
					}
					
					lv.setAdapter(adapter);
				}
			}
		} /*else if (codigo == Configuracoes) {
			if (resultado == RESULT_OK) {
				Intent i = getIntent();
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				finish();
				startActivity(i);
			}
		}*/
    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.activity_principal, menu);
		setMenuBackground();
		return true;
    }
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		Intent it;
		switch (item.getItemId()) {
		case R.id.principal_menu_ajuda:
			//it = new Intent(this, Ajuda.class);
			it = new Intent(this, ScreenSlideActivity.class);
			startActivity(it);
			break;
		case R.id.principal_menu_sobre:
			it = new Intent(this, Sobre.class);
			startActivity(it);
			break;
		/*case CONFIGURACOES:
			it = new Intent(this, Configuracoes.class);
			startActivityForResult(it, Configuracoes);
			break;
		*/
		}
		return false;
	}
	
	protected void setMenuBackground() {
		getLayoutInflater().setFactory2(new Factory2() {
			
			@Override
			public View onCreateView(String name, Context context, AttributeSet attrs) {
				if ( name.equalsIgnoreCase( "com.android.internal.view.menu.IconMenuItemView" ) ) {
	                try { // Ask our inflater to create the view  
	                    LayoutInflater f = getLayoutInflater();  
	                    final View view = f.createView( name, null, attrs );  
	                    /* The background gets refreshed each time a new item is added the options menu.  
	                    * So each time Android applies the default background we need to set our own  
	                    * background. This is done using a thread giving the background change as runnable 
	                    * object */
	                    new Handler().post( new Runnable() {  
	                        public void run () {  
	                            // sets the background color   
	                            view.setBackgroundResource(R.color.cor_errado);
	                            // sets the text color              
	                            ((TextView) view).setTextColor(Color.RED);
	                            // sets the text size              
	                            ((TextView) view).setTextSize(18);
	            }
	                    } );  
	                return view;
	            }
	        catch ( InflateException e ) {}
	        catch ( ClassNotFoundException e ) {}  
	    } 
	            return null;
			}
			
			@Override
			public View onCreateView(View parent, String name, Context context,
					AttributeSet attrs) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
	
	public void tratamentoArquivos() {
		String arq = "arq.txt";
        String cat = "arquivo";
        
        FileOutputStream out;
        File f;
        
		try {
			f = getFileStreamPath(arq);
	        if (!f.exists()) {
	        	out = openFileOutput(arq, MODE_APPEND);
				out.write("\n".getBytes());
				out.close();
				Log.i(cat, "Arquivo gravado com sucesso!");
				
				startActivity(new Intent(this, ScreenSlideActivity.class));
	        }
		} catch (FileNotFoundException e) {
			Log.e(cat, "Arquivo não encontrado: " + e.getMessage(), e);
		} catch (IOException e) {
			Log.e(cat, e.getMessage(), e);
		}
	}

}
