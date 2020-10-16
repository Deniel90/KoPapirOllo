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

    private int PlayerPont, GepPont, Dontetlen, JatekosPontja, GepPontja, gepszam;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

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
            case 1: gepValasztas = "ko"; break;
            case 2: gepValasztas = "papir"; break;
            case 3: gepValasztas = "ollo"; break;
        }

        //Toast.makeText(MainActivity.this, gepValasztas, Toast.LENGTH_SHORT).show();
        if (gepValasztas == playerValasztas)
        {
            Dontetlen++;
            dontetlenek.setText(Dontetlen);
        }
        else if (gepValasztas == "ko" && playerValasztas == "papir" ||
                gepValasztas == "papir" && playerValasztas == "ollo" ||
                gepValasztas == "ollo" && playerValasztas == "ko")
        {
            JatekosPontja++;
            playerEredmeny.setText(JatekosPontja);
        }
        else
        {
            GepPontja++;
            gepEredmeny.setText(GepPontja);
        }
    }

    private void init() {
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
    }
}