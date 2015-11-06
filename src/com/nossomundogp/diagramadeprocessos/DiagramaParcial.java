package com.nossomundogp.diagramadeprocessos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DiagramaParcial extends Activity {

	public static final int MENU_FINALIZAR = 2;
	boolean correto = false;
	
	Bundle params;
	Drawable corPadrao;
	
	//String[] iniciacao = {"Selecionar o gerente de projetos", "Determinar a cultura e os sistemas existentes da empresa",
	//		"Reunir processos, procedimentos e informações históricas", "Dividir projetos grandes em fases", "Compreender o caso de negócios",
	//		"Identificar requisitos, premissas, riscos e restrições iniciais, além dos acordos existentes", "Avaliar a viabilidade dos projetos e de produtos com as restrições determinadas",
	//		"Criar objetivos mensuráveis", "Desenvolver o termo de abertura do projeto", "Identificar as partes interessadas e determinar suas expectativas, influência a impacto"};
	
	//String[] planejamento = {"Determinar como você vai planejar para cada área de atuação", "Determinar requisitos detalhados", "Criar a declaração do escopo do projeto", 
	//		"Determinar o que adquirir e preparar documentos de aquisição", "Determinar a equipe de planejamento", "Criar a EAP e o dicionário da EAP", "Criar a lista de atividades",
	//		"Criar o diagrama de rede", "Estimar os requisitos de recursos", "Estimar o tempo e os custos", "Determinar o caminho crítico", "Desenvolver o cronograma", "Desenvolver o orçamento",
	//		"Determinar padrões, processos e métricas de qualidade", "Criar o plano de melhoria de processos", "Determinar todos os papéis e responsabilidades", "Planejar as comunicações e engajamento das partes interessadas",
	//		"Realizar a identificação dos riscos, as análises qualitativa e quantitativa dos riscos e o planejamento de respostas aos riscos", "Voltar - iterações", "Finalizar os documentos de aquisição", 
	//		"Criar o plano de gerenciamento de mudanças", "Concluir a parte de 'como executar e controlar' de todos os planos de gerenciamento", "Desenvolver uma linha de base de desempenho e um plano de GP realista e final",
	//		"Obter a aprovação formal do plano", "Organizar uma reunião de partida"};
	
	//String[] execucao = {"Executar o trabalho de acordo com o plano do GP", "Produzir as entregas do produto", "Reunir dados de desempenho e trabalho", 
	//		"Solicitar mudanças", "Implementar apenas as mudanças aprovadas", "Melhorar continuamente", "Seguir os processos", "Determinar se os processos estão corretos e são eficazes",
	//		"Realizar auditorias de qualidade", "Mobilizar a equipe final", "Gerenciar pessoas", "Avaliar o desempenho individual e de equipe", "Realizar atividades de desenvolvimento de equipe",
	//		"Oferecer reconhecimento e recompensas", "Usar registros de questões", "Facilitar a resolução de conflitos", "Liberar recursos conforme o trabalho for terminado", "Enviar e receber informações e solicitar feedback",
	//		"Reportar o desempenho do projeto", "Gerenciar o engajamento e as expectativas das partes interessadas", "Realizar reuniões", "Selecionar fornecedores"};
	
	//String[] monitoramento = {"Tomar medidas para controlar o projeto", "Medir o desempenho pela linha de base", "Medir o desempenho por outras métricas do plano de GP", "Analisar e avaliar o desempenho",
	//		"Determinar se as variações requerem ações corretivas ou outra solicitação de mudanças", "Influenciar os fatores que causam mudanças", "Solicitar mudanças", "Realizar o controle  integrado de mudanças",
	//		"Aprovar ou rejeitar mudanças", "Atualizar o plano do GP e os documentos do projeto", "Informar as partes interessadas sobre os resultados de solicitações de mudança", "Monitorar o engajamento das partes interessadas",
	//		"Gerenciar a configuração", "Criar previsões", "Obter aceite do cliente de entregas parciais", "Realizar o controle da qualidade", "Realizar as reavaliações e auditorias de riscos",
	//		"Gerenciar as reservas", "Controlar as aquisições"};
	
	//String[] encerramento = {"Confirmar que o trabalho é realizado de acordo com os requisitos", "Terminar o encerramento das aquisições", "Obter a aceitação final do produto", "Terminar o encerramento financeiro",
	//		"Entregar o produto completo", "Solicitar feedback do cliente sobre o produto", "Concluir relatórios finais de desempenho", "Registros de índice e acervo", "Coletar as lições aprendidas finais e atualizar a base de conhecimento"};
	
	List<String> listaStringIniciacao = new ArrayList<String>();
	List<String> listaStringPlanejamento = new ArrayList<String>();
	List<String> listaStringExecucao = new ArrayList<String>();
	List<String> listaStringMonitoramento = new ArrayList<String>();
	List<String> listaStringEncerramento = new ArrayList<String>();
	
	ArrayList<View> listaOpcoes = new ArrayList<View>();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagrama_parcial);
        
        Intent it = getIntent();
    	params = it.getExtras();
        
        iniciaVariaveis();
        carregaDados(params.getString("Tipo"));
        
    }
    
    class MyOnLongClickListener implements OnLongClickListener {

		@Override
		public boolean onLongClick(View v) {
			ClipData data = ClipData.newPlainText("simple_text", "text");
			DragShadowBuilder sb = new View.DragShadowBuilder(v);
			v.startDrag(data, sb, v, 0);
			v.setVisibility(View.INVISIBLE);
			return (true);
		}
    }
    
    class  MyOnDragListener implements OnDragListener {
    	private int num;
    	
    	public MyOnDragListener(int num) {
    		super();
    		this.num = num;
    	}

		@Override
		public boolean onDrag(View v, DragEvent event) {
			int action = event.getAction();
			String tipo = params.getString("Tipo");
			
			switch (action) {
			case DragEvent.ACTION_DRAG_STARTED:
				Log.i("Script", num + " - ACTION_DRAG_STARTED");
				if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
					return (true);
				}
				return (false);
				
			case DragEvent.ACTION_DRAG_ENTERED:
				Log.i("Script", num + " - ACTION_DRAG_ENTERED");
				v.setBackgroundColor(Color.parseColor("#F8FCAB"));//Amarelo claro
				break;
				
			case DragEvent.ACTION_DRAG_LOCATION:
				Log.i("Script", num + " - ACTION_DRAG_LOCATION");
				break;
				
			case DragEvent.ACTION_DRAG_EXITED:
				Log.i("Script", num + " - ACTION_DRAG_EXITED");
				if (tipo.equals("A")) {
					v.setBackgroundColor(getResources().getColor(R.color.iniciacao));
				} else if (tipo.equals("B")) {
					v.setBackgroundColor(getResources().getColor(R.color.planejamento));
				} else if (tipo.equals("C")) {
					v.setBackgroundColor(getResources().getColor(R.color.execucao));
				} else if (tipo.equals("D")) {
					v.setBackgroundColor(getResources().getColor(R.color.monitoramento_controle));
				} else {
					v.setBackgroundColor(getResources().getColor(R.color.encerramento));
				}
				
				break;
				
			case DragEvent.ACTION_DROP:
				Log.i("Script", num + " - ACTION_DROP");
				View view = (View) event.getLocalState();
				ViewGroup owner = (ViewGroup) view.getParent();
				owner.removeView(view);
				LinearLayout container = (LinearLayout) v;
				container.addView(view);
				view.setVisibility(View.VISIBLE);
				
				if (v.getId() == R.id.esquerda) {
					Button b = (Button) view;
					b.setTextColor(Color.BLACK);
				}
				
				break;
				
			case DragEvent.ACTION_DRAG_ENDED:
				Log.i("Script", num + " - ACTION_DRAG_ENDED");
				if (tipo.equals("A")) {
					v.setBackgroundColor(getResources().getColor(R.color.iniciacao));
				} else if (tipo.equals("B")) {
					v.setBackgroundColor(getResources().getColor(R.color.planejamento));
				} else if (tipo.equals("C")) {
					v.setBackgroundColor(getResources().getColor(R.color.execucao));
				} else if (tipo.equals("D")) {
					v.setBackgroundColor(getResources().getColor(R.color.monitoramento_controle));
				} else {
					v.setBackgroundColor(getResources().getColor(R.color.encerramento));
				}
				break;
			}
			return true;
		}
    	
    }
    
    private void iniciaVariaveis() {
    	//Carregandos os textos da área iniciação
    	listaStringIniciacao = new ArrayList<String>();
		listaStringIniciacao.add(getResources().getString(R.string.ini_1));
		listaStringIniciacao.add(getResources().getString(R.string.ini_2));
		listaStringIniciacao.add(getResources().getString(R.string.ini_3));
		listaStringIniciacao.add(getResources().getString(R.string.ini_4));
		listaStringIniciacao.add(getResources().getString(R.string.ini_5));
		listaStringIniciacao.add(getResources().getString(R.string.ini_6));
		listaStringIniciacao.add(getResources().getString(R.string.ini_7));
		listaStringIniciacao.add(getResources().getString(R.string.ini_8));
		listaStringIniciacao.add(getResources().getString(R.string.ini_9));
		listaStringIniciacao.add(getResources().getString(R.string.ini_10));
    	//Carregandos os textos da área planejamento
    	listaStringPlanejamento = new ArrayList<String>();
		listaStringPlanejamento.add(getResources().getString(R.string.pla_1));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_2));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_3));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_4));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_5));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_6));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_7));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_8));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_9));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_10));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_11));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_12));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_13));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_14));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_15));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_16));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_17));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_18));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_19));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_20));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_21));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_22));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_23));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_24));
		listaStringPlanejamento.add(getResources().getString(R.string.pla_25));
		//Carregandos os textos da área execução
    	listaStringExecucao = new ArrayList<String>();
    	listaStringExecucao.add(getResources().getString(R.string.exe_1));
    	listaStringExecucao.add(getResources().getString(R.string.exe_2));
    	listaStringExecucao.add(getResources().getString(R.string.exe_3));
    	listaStringExecucao.add(getResources().getString(R.string.exe_4));
    	listaStringExecucao.add(getResources().getString(R.string.exe_5));
    	listaStringExecucao.add(getResources().getString(R.string.exe_6));
    	listaStringExecucao.add(getResources().getString(R.string.exe_7));
    	listaStringExecucao.add(getResources().getString(R.string.exe_8));
    	listaStringExecucao.add(getResources().getString(R.string.exe_9));
    	listaStringExecucao.add(getResources().getString(R.string.exe_10));
    	listaStringExecucao.add(getResources().getString(R.string.exe_11));
    	listaStringExecucao.add(getResources().getString(R.string.exe_12));
    	listaStringExecucao.add(getResources().getString(R.string.exe_13));
    	listaStringExecucao.add(getResources().getString(R.string.exe_14));
    	listaStringExecucao.add(getResources().getString(R.string.exe_15));
    	listaStringExecucao.add(getResources().getString(R.string.exe_16));
    	listaStringExecucao.add(getResources().getString(R.string.exe_17));
    	listaStringExecucao.add(getResources().getString(R.string.exe_18));
    	listaStringExecucao.add(getResources().getString(R.string.exe_19));
    	listaStringExecucao.add(getResources().getString(R.string.exe_20));
    	listaStringExecucao.add(getResources().getString(R.string.exe_21));
    	listaStringExecucao.add(getResources().getString(R.string.exe_22));
    	//Carregandos os textos da área monitoramento
    	listaStringMonitoramento = new ArrayList<String>();
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_1));
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_2));
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_3));
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_4));
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_5));
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_6));
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_7));
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_8));
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_9));
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_10));
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_11));
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_12));
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_13));
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_14));
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_15));
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_16));
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_17));
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_18));
    	listaStringMonitoramento.add(getResources().getString(R.string.mon_19));
    	//Carregandos os textos da área encerramento
    	listaStringEncerramento = new ArrayList<String>();
    	listaStringEncerramento.add(getResources().getString(R.string.enc_1));
    	listaStringEncerramento.add(getResources().getString(R.string.enc_2));
    	listaStringEncerramento.add(getResources().getString(R.string.enc_3));
    	listaStringEncerramento.add(getResources().getString(R.string.enc_4));
    	listaStringEncerramento.add(getResources().getString(R.string.enc_5));
    	listaStringEncerramento.add(getResources().getString(R.string.enc_6));
    	listaStringEncerramento.add(getResources().getString(R.string.enc_7));
    	listaStringEncerramento.add(getResources().getString(R.string.enc_8));
    	listaStringEncerramento.add(getResources().getString(R.string.enc_9));
    	
    	listaOpcoes = new ArrayList<View>();
    	
    	LinearLayout containerEsquerda = (LinearLayout) findViewById(R.id.esquerda);
    	LinearLayout containerDireita = (LinearLayout) findViewById(R.id.direita);
    	containerEsquerda.removeAllViews();
    	containerDireita.removeAllViews();
    }
    
    private void carregaDados(String tipo) {
    	
    	//listaStringIniciacao = iniciacao;
    	//listaStringPlanejamento = Arrays.asList(planejamento);
        //listaStringExecucao = Arrays.asList(execucao);
        //listaStringMonitoramento = Arrays.asList(monitoramento);
        //listaStringEncerramento = Arrays.asList(encerramento);
        
        LinearLayout containerEsquerda = (LinearLayout) findViewById(R.id.esquerda);
        LinearLayout containerDireita = (LinearLayout) findViewById(R.id.direita);
        
        TextView titulo = (TextView) findViewById(R.id.txtTitulo);
        if (tipo.equals("A")) {
        	carregaListas(listaStringIniciacao, listaStringPlanejamento, listaStringExecucao, listaStringMonitoramento, listaStringEncerramento, tipo, "B", "C", "D", "E");
        	titulo.setText("Iniciação");
        	containerEsquerda.setBackgroundResource(R.color.iniciacao);
        	containerDireita.setBackgroundResource(R.color.iniciacao);
        } else if (tipo.equals("B")) {
        	carregaListas(listaStringPlanejamento, listaStringIniciacao, listaStringExecucao, listaStringMonitoramento, listaStringEncerramento, tipo, "A", "C", "D", "E");
        	titulo.setText("Planejamento");
        	containerEsquerda.setBackgroundResource(R.color.planejamento);
        	containerDireita.setBackgroundResource(R.color.planejamento);
        } else if (tipo.equals("C")) {
        	carregaListas(listaStringExecucao, listaStringIniciacao, listaStringPlanejamento, listaStringMonitoramento, listaStringEncerramento, tipo, "A", "B", "D", "E");
        	titulo.setText("Execução");
        	containerEsquerda.setBackgroundResource(R.color.execucao);
        	containerDireita.setBackgroundResource(R.color.execucao);
        } else if (tipo.equals("D")) {
        	carregaListas(listaStringMonitoramento, listaStringIniciacao, listaStringPlanejamento, listaStringExecucao, listaStringEncerramento, tipo, "A", "B", "C", "E");
        	titulo.setText("Monitoramento e Controle");
        	containerEsquerda.setBackgroundResource(R.color.monitoramento_controle);
        	containerDireita.setBackgroundResource(R.color.monitoramento_controle);
        } else {
        	carregaListas(listaStringEncerramento, listaStringIniciacao, listaStringPlanejamento, listaStringExecucao, listaStringMonitoramento, tipo, "A", "B", "C", "D");
        	titulo.setText("Encerramento");
        	containerEsquerda.setBackgroundResource(R.color.encerramento);
        	containerDireita.setBackgroundResource(R.color.encerramento);
        }
        
        Collections.shuffle(listaOpcoes);
        
        
        for(View vTexto: listaOpcoes) {
        	vTexto.setOnLongClickListener(new MyOnLongClickListener());
        	containerEsquerda.addView(vTexto);
        }
        
        findViewById(R.id.esquerda).setOnDragListener(new MyOnDragListener(1));
        findViewById(R.id.direita).setOnDragListener(new MyOnDragListener(2));
        
    }
    
    private void carregaListas(List<String> listaPrincipal, List<String> listaA, List<String> listaB, List<String> listaC, List<String> listaD,
    							String tagPrincipal, String tagA, String tagB, String tagC, String tagD) {
    	Button texto;

    	for(String tLinha: listaPrincipal) {
        	texto = new Button(this);
        	texto.setText(tLinha);
        	texto.setTag(tagPrincipal);
        	
        	listaOpcoes.add(texto);
        }
    	
    	preenche_opcoes(listaA, tagA);
    	preenche_opcoes(listaB, tagB);
    	preenche_opcoes(listaC, tagC);
    	preenche_opcoes(listaD, tagD);
        
    }
    
    private void preenche_opcoes(List<String> lista, String tag) {
    	Button texto;
    	
    	for (int i = 0; i < lista.size(); i++) {
    		texto = new Button(this);
        	texto.setText(lista.get(i));
        	texto.setTag(tag);
        	listaOpcoes.add(texto);
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.activity_diagrama_parcial, menu);
		
		//Inicia desabilitado e só pode clicar depois de checar
		menu.getItem(MENU_FINALIZAR).setEnabled(correto);
		
        return true;
    }
    
    @Override
    public boolean onMenuItemSelected(int panel, MenuItem item) {
    	switch (item.getItemId()) {
    	
		case R.id.parcial_menu_checar:
			checar();
			break;
		
		case R.id.parcial_menu_reiniciar:
			reiniciar();
			break;

		case R.id.parcial_menu_finalizar:
			finalizar();
			break;
		
		}
    	return true;
    }
    
    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {
        menu.getItem(MENU_FINALIZAR).setEnabled(correto);
        return true;
    }
    
    /**
     * Verifica se o usuário preenchou corretamento de acordo com
     * o grupo de processos escolhido na tela principal.
     */
    private void checar() {
    	int count = 0;
		int total = params.getInt("Total");
		boolean errado = false;
		String tag = "";
		
		LinearLayout l = (LinearLayout) findViewById(R.id.direita);
		ArrayList<View> listViews = l.getTouchables();
		Button botaoC;
		for(View bt: listViews) {
			botaoC = (Button) bt;
			tag = botaoC.getTag().toString().trim();
			
			if (botaoC.getText().equals("Solicitar mudanças") && 
					(params.getString("Tipo").equals("C") || params.getString("Tipo").equals("D"))) {
				count++;
			} else if (tag.equals(params.getString("Tipo"))) {
				count++;
			} else {
				count = 0;
				errado = true;
				corPadrao = botaoC.getBackground();
				botaoC.setTextColor(getResources().getColor(R.color.cor_errado));
			}
		}
		
		if (errado) {
			Toast.makeText(DiagramaParcial.this, "Existe uma ou mais ações não pertencentes a esse grupo de processo!", Toast.LENGTH_SHORT).show();
			correto = false;
			invalidateOptionsMenu();
			return;
		}
		
		if (count == total) {
			Toast.makeText(DiagramaParcial.this, "Parabéns, você concluiu com sucesso o grupo de processo!", Toast.LENGTH_SHORT).show();
			correto = true;
			invalidateOptionsMenu();
		} else {
			Toast.makeText(DiagramaParcial.this, "Falta uma ou mais ações para o grupo de processo!", Toast.LENGTH_SHORT).show();
			correto = false;
			invalidateOptionsMenu();
		}
    }
    
    /**
     * Reinicia os estado da tela.
     * Limpa as opções do ScrollView da direita e ramdomiza as
     * opções do ScrollView da esquerda.
     */
    private void reiniciar() {
    	iniciaVariaveis();
        carregaDados(params.getString("Tipo"));
    }
    
    /**
     * Retorna a lista preenchida pelo usuário para a tela principal.
     */
    private void finalizar() {
    	LinearLayout containerDireita = (LinearLayout) findViewById(R.id.direita);
		ArrayList<View> listaDireita = containerDireita.getTouchables();
		ArrayList<String> listaString = new ArrayList<String>();
		
		Button btnAux;
		for(View botaoR: listaDireita) {
			btnAux = (Button) botaoR;
			listaString.add(btnAux.getText().toString());
		}
		
		Intent itFilho = new Intent();
		itFilho.putStringArrayListExtra("Lista", listaString);
		itFilho.putExtra("Tipo", params.getString("Tipo"));
		setResult(RESULT_OK, itFilho);
		finish();
    } 
    
    /*
    private void teste() {
    	ArrayList<String> t = new ArrayList<String>();
    	for(int i = 1; i <= 10; i++) {
    		t.add(getResources().getString(R.string.ini_1));
    		t.add(getResources().getString(R.string.ini_2));
    		t.add(getResources().getString(R.string.ini_3));
    		t.add(getResources().getString(R.string.ini_4));
    		t.add(getResources().getString(R.string.ini_5));
    		t.add(getResources().getString(R.string.ini_6));
    		t.add(getResources().getString(R.string.ini_7));
    		t.add(getResources().getString(R.string.ini_8));
    		t.add(getResources().getString(R.string.ini_9));
    		t.add(getResources().getString(R.string.ini_10));
    	}
    }*/
}
