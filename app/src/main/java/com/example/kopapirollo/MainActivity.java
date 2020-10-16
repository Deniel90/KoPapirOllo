package com.example.kopapirollo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView gepHP1, gepHP2, gepHP3, playerHP1, playerHP2, playerHP3, img_gepchoose, img_playerchoose;
    private TextView playerEredmeny, gepEredmeny, dontetlenek;
    private Button btn_Ko, btn_Papir, btn_Ollo;
    private String playerValasztas;
    private String gepValasztas;

    private int PlayerPont, GepPont, Dontetlen, JatekosPontja, GepPontja, gepszam, playerElet, gepElet;

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
        btn_Ollo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_playerchoose.setImageResource(R.drawable.scissors);
                playerValasztas = "ollo";
                Jatek();
            }
        });
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
            Dontetlen++;
            dontetlenek.setText(Dontetlen);
            Toast.makeText(MainActivity.this, "Döntetlen", Toast.LENGTH_SHORT).show();
        }
        //játékos nyert
        else if (gepValasztas == "ko" && playerValasztas == "papir" ||
                gepValasztas == "papir" && playerValasztas == "ollo" ||
                gepValasztas == "ollo" && playerValasztas == "ko")
        {
            JatekosPontja++;
            playerEredmeny.setText(JatekosPontja);
            Toast.makeText(MainActivity.this, "A játékos nyert", Toast.LENGTH_SHORT).show();
            gepElet--;
        }
        //gép nyert
        else
        {
            GepPontja++;
            gepEredmeny.setText(GepPontja);
            Toast.makeText(MainActivity.this, "A gép nyert", Toast.LENGTH_SHORT).show();
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
            //AlertDialog: "Játékos nyerte a játszmát"
        } else if (playerElet == 0)
        {
            //AlertDialog: "A gép nyerte a játszmát"
        }

        //AlertDialog: játszma végén új játék? Igen -> init() , nem -> bezár
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
        dontetlenek = findViewById(R.id.text_dontetlenek);

        img_gepchoose = findViewById(R.id.image_gepChoose);
        img_playerchoose = findViewById(R.id.image_playerChoose);

        btn_Ko = findViewById(R.id.btn_Ko);
        btn_Papir = findViewById(R.id.btn_Papir);
        btn_Ollo = findViewById(R.id.btn_Ollo);

        PlayerPont = 0;
        GepPont = 0;
        Dontetlen = 0;
        JatekosPontja = 0;
        GepPontja = 0;
        playerElet = 3;
        gepElet = 3;
    }
}