package com.example.jogodamemoria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private GridLayout tabuleiro;
    private ArrayList<Carta> listaCartas;
    private Carta carta1 = null;
    private Carta carta2 = null;
    private int pares = 0;
    private android.widget.Button btDupla;
    private android.widget.Button btIndiv;


    // DUPLA:
    private boolean dupla = false;
    private int jogador1 = 0;
    private int jogador2 = 0;
    private int jogadorVez = 1;
    private TextView tvJogador1;
    private TextView tvJogador2;
    private TextView tvJogador;
    private ImageView ivLogo;
    private Button btMenu;


    // INDIVIDUAL:
    private boolean individual = false;
    private long tempo = 0;
    private long tempoInicial = 0;
    private TextView tvTempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tabuleiro = findViewById(R.id.tabuleiroGrid);
        tvTempo = findViewById(R.id.tvTempo);
        tvJogador1 = findViewById(R.id.tvJogador1);
        tvJogador2 = findViewById(R.id.tvJogador2);
        tvJogador = findViewById(R.id.tvJogador);
        btDupla = findViewById(R.id.btDupla);
        btIndiv = findViewById(R.id.btIndiv);
        ivLogo = findViewById(R.id.ivLogo);
        btMenu = findViewById(R.id.btMenu);
    }

    public void btIndivClick(View view) {
        individual = true;
        dupla = false;
        tempoInicial = System.currentTimeMillis();

        tvTempo.setVisibility(View.VISIBLE);
        tvJogador1.setVisibility(View.GONE);
        tvJogador2.setVisibility(View.GONE);
        tvJogador.setVisibility(View.GONE);
        btDupla.setVisibility(View.GONE);
        btIndiv.setVisibility(View.GONE);
        ivLogo.setVisibility(View.GONE);
        btMenu.setVisibility(View.VISIBLE);

        tempo = 0;

        NovoJogo();
    }

    public void btDuplaClick(View view) {
        individual = false;
        dupla = true;

        tvTempo.setVisibility(View.GONE);
        tvJogador1.setVisibility(View.VISIBLE);
        tvJogador2.setVisibility(View.VISIBLE);
        tvJogador.setVisibility(View.VISIBLE);
        btDupla.setVisibility(View.GONE);
        btIndiv.setVisibility(View.GONE);
        ivLogo.setVisibility(View.GONE);
        btMenu.setVisibility(View.VISIBLE);

        pares = 0;
        jogador1 = 0;
        jogador2 = 0;
        jogadorVez = 1;

        tvJogador1.setText("Jogador 1: " + jogador1);
        tvJogador2.setText("Jogador 2: " + jogador2);
        tvJogador.setText("Jogador " + jogadorVez + " joga");
        tvJogador.setTextColor(android.graphics.Color.parseColor("#d80000"));

        NovoJogo();
    }

    public void btMenu(View view) {
        individual = false;
        dupla = false;

        tvTempo.setVisibility(View.GONE);
        tvJogador1.setVisibility(View.GONE);
        tvJogador2.setVisibility(View.GONE);
        tvJogador.setVisibility(View.GONE);
        btDupla.setVisibility(View.VISIBLE);
        btIndiv.setVisibility(View.VISIBLE);
        ivLogo.setVisibility(View.VISIBLE);
        btMenu.setVisibility(View.GONE);
        tabuleiro.removeAllViews();
    }

    private void NovoJogo() {
        listaCartas = new ArrayList<>();
        listaCartas.add(new Carta(R.drawable.luffy));
        listaCartas.add(new Carta(R.drawable.luffy));
        listaCartas.add(new Carta(R.drawable.zoro));
        listaCartas.add(new Carta(R.drawable.zoro));
        listaCartas.add(new Carta(R.drawable.nami));
        listaCartas.add(new Carta(R.drawable.nami));
        listaCartas.add(new Carta(R.drawable.usopp));
        listaCartas.add(new Carta(R.drawable.usopp));
        listaCartas.add(new Carta(R.drawable.law));
        listaCartas.add(new Carta(R.drawable.law));
        listaCartas.add(new Carta(R.drawable.robin));
        listaCartas.add(new Carta(R.drawable.robin));
        listaCartas.add(new Carta(R.drawable.sanji));
        listaCartas.add(new Carta(R.drawable.sanji));
        listaCartas.add(new Carta(R.drawable.tony_tony));
        listaCartas.add(new Carta(R.drawable.tony_tony));
        listaCartas.add(new Carta(R.drawable.yamato));
        listaCartas.add(new Carta(R.drawable.yamato));

        Collections.shuffle(listaCartas);
        tabuleiro.removeAllViews();

        for(int i = 0; i < listaCartas.size(); i++) {
            Carta carta = listaCartas.get(i);

            android.widget.ImageButton imageButton = new ImageButton(this);
            imageButton.setImageResource(R.drawable.verso_onepiece);
            imageButton.setBackgroundColor(android.graphics.Color.TRANSPARENT);
            imageButton.setScaleType(ImageButton.ScaleType.CENTER_CROP);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = 0;
            params.setMargins(8, 8, 8, 8);
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            imageButton.setLayoutParams(params);
            tabuleiro.addView(imageButton);

            carta.setImageButton(imageButton);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    virarCarta(carta);
                }
            });

        }
    }


    private void virarCarta(Carta carta) {
        if(carta.estaVirada() || carta1 == carta || carta2 == carta) {
            return;
        }
        carta.getImageButton().setImageResource(carta.getIdImagem());
        carta.setestaVirada(true);

        if(carta1 == null) {
            carta1 = carta;
        }
        else {
            carta2 = carta;
            verificarPar();
        }

    }

    private void verificarPar() {
        if (carta1.getIdImagem() == carta2.getIdImagem()) {
            carta1.setPar(true);
            carta2.setPar(true);
            carta1 = null;
            carta2 = null;
            pares ++;
            if (individual) {
                if (pares == listaCartas.size() / 2) {
                    tempo = (System.currentTimeMillis() - tempoInicial)/1000;
                    tvTempo.setText("Seu tempo foi de: " + tempo + "s!");
                }
            }
            if (dupla) {
                if (jogadorVez == 1){
                    jogador1 ++;
                }
                else{
                    jogador2 ++;
                }
                tvJogador1.setText("Jogador 1: " + jogador1);
                tvJogador2.setText("Jogador 2: " + jogador2);
                if (pares == listaCartas.size() / 2) {
                    if(jogador1>jogador2){
                        tvJogador.setText("Jogador 1 venceu!");
                        tvJogador.setTextColor(android.graphics.Color.parseColor("#d80000")); // Azul
                    }
                    else {
                        tvJogador.setText("Jogador 2 venceu!");
                        tvJogador.setTextColor(android.graphics.Color.parseColor("#4c6c97")); // Vermelho
                    }
                }
            }
        }
        else{
            android.os.Handler handler = new android.os.Handler(android.os.Looper.getMainLooper());
            handler.postDelayed(() -> {
                carta1.getImageButton().setImageResource(R.drawable.verso_onepiece);
                carta2.getImageButton().setImageResource(R.drawable.verso_onepiece);

                carta1.setestaVirada(false);
                carta2.setestaVirada(false);

                carta1 = null;
                carta2 = null;

                if(dupla){
                    if(jogadorVez == 1){
                        jogadorVez = 2;
                        tvJogador.setTextColor(android.graphics.Color.parseColor("#4c6c97")); // Azul
                    }
                    else{
                        jogadorVez = 1;
                        tvJogador.setTextColor(android.graphics.Color.parseColor("#d80000")); // Vermelho
                    }
                    tvJogador.setText("Jogador " + jogadorVez + " joga");
                }

            },1000);
        }

    }
}