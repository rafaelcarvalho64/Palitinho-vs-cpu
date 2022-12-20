package com.example.pedra_papel_tesoura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    TextView textView, textView2, textView3, textView4, textView5, textView6;
    Button btnSt, btC0, btC1, btC2, btC3, btC4, btC5, btC6, btReset, btZero, btPedra, btPapel, btTesoura;
    ImageView imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imagemMinha, ImagemCPU;
    String result;
    Integer chuteCPU, chutePlayer, minhaEscolha, cpuEscolha, mS, cpuS, finalValue;
    Random r, rr, rrr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        btnSt= findViewById(R.id.btnSt);
        btC0 = findViewById(R.id.btC0);
        btC1 = findViewById(R.id.btC1);
        btC2 = findViewById(R.id.btC2);
        btC3 = findViewById(R.id.btC3);
        btC4 = findViewById(R.id.btC4);
        btC5 = findViewById(R.id.btC5);
        btC6 = findViewById(R.id.btC6);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        btReset = findViewById(R.id.btReset);
        btZero = findViewById(R.id.btZero);
        btPedra = findViewById(R.id.btPedra);
        btPapel = findViewById(R.id.btPapel);
        btTesoura = findViewById(R.id.btTesoura);
        imagemMinha = findViewById(R.id.imagemMinha);
        ImagemCPU = findViewById(R.id.ImagemCPU);

        r = new Random();
        rr = new Random();
        rrr = new Random();
        mS = 3;
        cpuS = 3;

        nos();

        btZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minhaEscolha = 0;
                resetHandsImage();
            }
        });

        btPedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minhaEscolha = 1;
                resetHandsImage();
            }
        });

        btPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minhaEscolha = 2;
                resetHandsImage();
            }
        });

        btTesoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minhaEscolha = 3;
                resetHandsImage();
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mS = 3;
                cpuS = 3;
                nos();
                btnSOFF();
                resetHandsImage();
                textView3.setText("");
                textView4.setText("");
            }
        });

        btC0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chutePlayer = 0;
                resetHandsImage();
            }
        });

        btC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chutePlayer = 1;
                resetHandsImage();
            }
        });

        btC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chutePlayer = 2;
                resetHandsImage();
            }
        });

        btC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chutePlayer = 3;
                resetHandsImage();
            }
        });

        btC4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chutePlayer = 4;
                resetHandsImage();
            }
        });

        btC5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chutePlayer = 5;
                resetHandsImage();
            }
        });

        btC6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chutePlayer = 6;
                resetHandsImage();
            }
        });

        btnSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg;
                if (minhaEscolha == null){
                     msg="Escolha quantos palitos colocar na mão.";
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
                else if (chutePlayer == null){
                    msg="Escolha um valor para chutar.";
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
                else{
                    calcular();
                }
            }
        });
    }

    public void resetHandsImage(){
        ImagemCPU.setImageResource(R.drawable.fistup);
        imagemMinha.setImageResource(R.drawable.fistdown);
    }

    public void nos(){
        imageView.setVisibility(View.VISIBLE);
        imageView2.setVisibility(View.VISIBLE);
        imageView3.setVisibility(View.VISIBLE);
        imageView4.setVisibility(View.VISIBLE);
        imageView5.setVisibility(View.VISIBLE);
        imageView6.setVisibility(View.VISIBLE);

        textView.setText("Restam:");
        textView2.setText("Restam:");

        if (mS == 2){
            imageView3.setVisibility(View.INVISIBLE);
        }
        else if (mS == 1){
            imageView2.setVisibility(View.INVISIBLE);
            imageView3.setVisibility(View.INVISIBLE);
            textView2.setText("Resta:");
        }
        else if (mS == 0){
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            imageView3.setVisibility(View.INVISIBLE);
            textView2.setText("Você venceu!");
            imagemMinha.setImageResource(R.drawable.pngegg);
            result = "voce ganhou o jogo!";
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }

        if (cpuS == 2){
            imageView6.setVisibility(View.INVISIBLE);
        }
        else if(cpuS == 1){
            imageView5.setVisibility(View.INVISIBLE);
            imageView6.setVisibility(View.INVISIBLE);
            textView.setText("Resta:");
        }
        else if(cpuS == 0){
            imageView4.setVisibility(View.INVISIBLE);
            imageView5.setVisibility(View.INVISIBLE);
            imageView6.setVisibility(View.INVISIBLE);
            textView.setText("A CPU venceu!");
            ImagemCPU.setImageResource(R.drawable.pngegg);
            result = "voce perdeu o jogo!";
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    }

    public void endGameHideBtn(){
        btZero.setVisibility(View.INVISIBLE);
        btPedra.setVisibility(View.INVISIBLE);
        btPapel.setVisibility(View.INVISIBLE);
        btTesoura.setVisibility(View.INVISIBLE);
        btC0.setVisibility(View.INVISIBLE);
        btC1.setVisibility(View.INVISIBLE);
        btC2.setVisibility(View.INVISIBLE);
        btC3.setVisibility(View.INVISIBLE);
        btC4.setVisibility(View.INVISIBLE);
        btC5.setVisibility(View.INVISIBLE);
        btC6.setVisibility(View.INVISIBLE);
        btnSt.setVisibility(View.INVISIBLE);
        textView5.setVisibility(View.INVISIBLE);
        textView6.setVisibility(View.INVISIBLE);
    }

    public void startRoundShowBtn(){
        btZero.setVisibility(View.VISIBLE);
        btPedra.setVisibility(View.VISIBLE);
        btPapel.setVisibility(View.VISIBLE);
        btTesoura.setVisibility(View.VISIBLE);
        btC0.setVisibility(View.VISIBLE);
        btC1.setVisibility(View.VISIBLE);
        btC2.setVisibility(View.VISIBLE);
        btC3.setVisibility(View.VISIBLE);
        btC4.setVisibility(View.VISIBLE);
        btC5.setVisibility(View.VISIBLE);
        btC6.setVisibility(View.VISIBLE);
        btnSt.setVisibility(View.VISIBLE);
        textView5.setVisibility(View.VISIBLE);
        textView6.setVisibility(View.VISIBLE);
    }

    public void btnSOFF(){
        startRoundShowBtn();
        if (mS == 2){
            btTesoura.setVisibility(View.INVISIBLE);
        }
        else if (mS == 1){
            btPapel.setVisibility(View.INVISIBLE);
            btTesoura.setVisibility(View.INVISIBLE);
        }
        else if (mS == 0){
            endGameHideBtn();
        }

        if (cpuS == 0){
            endGameHideBtn();
        }

        if (mS + cpuS == 5){
            btC6.setVisibility(View.INVISIBLE);
        }
        else if(mS + cpuS == 4){
            btC5.setVisibility(View.INVISIBLE);
            btC6.setVisibility(View.INVISIBLE);
        }
        else if(mS + cpuS == 3){
            btC4.setVisibility(View.INVISIBLE);
            btC5.setVisibility(View.INVISIBLE);
            btC6.setVisibility(View.INVISIBLE);
        }
        else if(mS + cpuS == 2){
            btC3.setVisibility(View.INVISIBLE);
            btC4.setVisibility(View.INVISIBLE);
            btC5.setVisibility(View.INVISIBLE);
            btC6.setVisibility(View.INVISIBLE);
        }
    }

    public void resetChoices(){
        minhaEscolha = null;
        chutePlayer = null;
    }

    public void calcular(){
        int cpu = r.nextInt(cpuS + 1);
        int guessPlayer = rr.nextInt(mS + 1);
        chuteCPU = cpu + guessPlayer;
        textView3.setText("Chute:" + chuteCPU);
        textView4.setText("Chute:" + chutePlayer);
        if (chuteCPU == chutePlayer){
            result = "Chutes iguais, chute novamente.";
            resetChoices();
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }
        else{
            if (cpu == 0){
                cpuEscolha = 0;
                ImagemCPU.setImageResource(R.drawable.handopenup);
            }
            else if (cpu == 1){
                cpuEscolha = 1;
                ImagemCPU.setImageResource(R.drawable.onestickup);
            }
            else if (cpu == 2){
                cpuEscolha = 2;
                ImagemCPU.setImageResource(R.drawable.twostickup);
            }
            else if (cpu == 3){
                cpuEscolha = 3;
                ImagemCPU.setImageResource(R.drawable.threestickup);
            }

            finalValue = cpuEscolha + minhaEscolha;

            if (minhaEscolha == 0){
            imagemMinha.setImageResource(R.drawable.handopendown);
            }
            else if (minhaEscolha == 1){
                imagemMinha.setImageResource(R.drawable.onestickdown);
            }
            else if (minhaEscolha == 2){
                imagemMinha.setImageResource(R.drawable.twostickdown);
            }
            else if (minhaEscolha == 3){
                imagemMinha.setImageResource(R.drawable.threestickdown);
            }

            if (finalValue == chutePlayer){
                result = "Você acertou!";
                mS --;
            }
            else if (finalValue == chuteCPU){
                result = "CPU acertou!";
                cpuS --;
            }
            else{
                result = "Ninguém acertou!";
            }

            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();

            btnSOFF();
            nos();
            resetChoices();
        }
    }
}