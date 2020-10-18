package com.example.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView gepHP1, gepHP2, gepHP3, playerHP1, playerHP2, playerHP3, img_gepchoose, img_playerchoose;
    private TextView playerEredmeny, gepEredmeny, dontetlenEredmeny;
    private Button btn_Ko, btn_Papir, btn_Ollo;

    private String playerValasztas, gepValasztas;
    private int JatekosPontja, GepPontja, gepszam, playerElet, gepElet, dontetlen_korok_szama;
    private AlertDialog.Builder uzenet;
    private Toast sajatToast;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        //kő gomb
        btn_Ko.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                img_playerchoose.setImageResource(R.drawable.rock);
                playerValasztas = "ko";
                Jatek();
            }
        });

        //papír gomb
        btn_Papir.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                img_playerchoose.setImageResource(R.drawable.paper);
                playerValasztas = "papir";
                Jatek();
            }
        });

        //olló gomb
        btn_Ollo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                img_playerchoose.setImageResource(R.drawable.scissors);
                playerValasztas = "ollo";
                Jatek();
            }
        });
    }

    private void init()
    {
        gepHP1 = findViewById(R.id.gep_HP1);
        gepHP2 = findViewById(R.id.gep_HP2);
        gepHP3 = findViewById(R.id.gep_HP3);

        playerHP1 = findViewById(R.id.player_HP1);
        playerHP2 = findViewById(R.id.player_HP2);
        playerHP3 = findViewById(R.id.player_HP3);

        playerEredmeny = findViewById(R.id.player_eredmeny);
        gepEredmeny = findViewById(R.id.gep_eredmeny);
        dontetlenEredmeny = findViewById(R.id.dontetlen_korok_szama);

        img_gepchoose = findViewById(R.id.image_gepChoose);
        img_playerchoose = findViewById(R.id.image_playerChoose);

        btn_Ko = findViewById(R.id.btn_Ko);
        btn_Papir = findViewById(R.id.btn_Papir);
        btn_Ollo = findViewById(R.id.btn_Ollo);

        JatekosPontja = 0;
        GepPontja = 0;
        dontetlen_korok_szama = 0;

        UjJatek();

        uzenet = new AlertDialog.Builder(MainActivity.this);
        uzenet.setCancelable(false).setMessage("Szeretne új játékot játszani?").setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                UjJatek();
            }
        });
        uzenet.setNegativeButton("Nem", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        });
    }

    private void UjJatek()
    {
        playerElet = 3;
        gepElet = 3;

        playerHP1.setImageResource(R.drawable.heart2);
        playerHP2.setImageResource(R.drawable.heart2);
        playerHP3.setImageResource(R.drawable.heart2);

        gepHP1.setImageResource(R.drawable.heart2);
        gepHP2.setImageResource(R.drawable.heart2);
        gepHP3.setImageResource(R.drawable.heart2);

        img_playerchoose.setImageResource(android.R.color.transparent);
        img_gepchoose.setImageResource(android.R.color.transparent);
    }

    private void Jatek()
    {
        gepszam = (int)(Math.random() * 3 + 1);

        switch (gepszam)
        {
            case 1: gepValasztas = "ko";
                img_gepchoose.setImageResource(R.drawable.rock);
                break;
            case 2: gepValasztas = "papir";
                img_gepchoose.setImageResource(R.drawable.paper);
                break;
            case 3: gepValasztas = "ollo";
                img_gepchoose.setImageResource(R.drawable.scissors);
                break;
        }

        //döntetlen
        if (gepValasztas == playerValasztas)
        {
            Toast.makeText(MainActivity.this, "Ez a kör döntetlen", Toast.LENGTH_SHORT).show();
            dontetlen_korok_szama++;
            dontetlenEredmeny.setText(dontetlen_korok_szama+"");
        }
        //játékos nyert
        else if (gepValasztas == "ko" && playerValasztas == "papir" ||
                gepValasztas == "papir" && playerValasztas == "ollo" ||
                gepValasztas == "ollo" && playerValasztas == "ko")
        {
            Toast.makeText(MainActivity.this, "Ezt a kört a játékos nyerte", Toast.LENGTH_SHORT).show();
            gepElet--;
        }
        //gép nyert
        else
        {
            Toast.makeText(MainActivity.this, "Ezt a kört a gép nyerte", Toast.LENGTH_SHORT).show();
            playerElet--;
        }

        //életek levonása
        switch (gepElet)
        {
            case 2: gepHP3.setImageResource(R.drawable.heart1); break;
            case 1: gepHP2.setImageResource(R.drawable.heart1); break;
            case 0: gepHP1.setImageResource(R.drawable.heart1);
        }
        switch (playerElet)
        {
            case 2: playerHP3.setImageResource(R.drawable.heart1); break;
            case 1: playerHP2.setImageResource(R.drawable.heart1); break;
            case 0: playerHP1.setImageResource(R.drawable.heart1);
        }

        //végső nyertes kihirdetése
        if (gepElet == 0)
        {
            JatekosPontja++;
            playerEredmeny.setText(JatekosPontja+"");

            //TODO: custom toast

            uzenet.setTitle("Győzelem");
            AlertDialog dialog = uzenet.create();
            dialog.show();
        }
        else if (playerElet == 0)
        {
            GepPontja++;
            gepEredmeny.setText(GepPontja+"");

            uzenet.setTitle("Vereség");
            AlertDialog dialog = uzenet.create();
            dialog.show();
        }
    }
}