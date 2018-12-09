package com.example.akshaya.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons= new Button[3][3];
    private  Boolean player1turn= true;
    private int player1points,roundcount,player2points;
    private TextView player1,player2;
    Button reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1= findViewById(R.id.player1);
        player2=findViewById(R.id.player2);
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
            {
                String ButtonId= "button_"+i+j;
                int resId = getResources().getIdentifier(ButtonId,"id",getPackageName());
                buttons[i][j]=findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
             reset=findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roundcount=0;
                player1points=0;
                player2points=0;
                player1.setText("Player 1:"+player1points);
                player2.setText("Player 2:"+player2points);
                for(int i=0;i<3;i++)
                    for(int j=0;j<3;j++)
                {
                    buttons[i][j].setText("");
                }
                player1turn=true;
            }
        });

    }

    @Override
    public void onClick(View view) {
        if(!((Button) view).getText().toString().equals(""))
        {
            return;
        }
        if(player1turn == true)
        {
            ((Button) view).setText("X");

        }
        else
        {
            ((Button) view).setText("O");

        }
        roundcount++;
        if(winner())
        {
            if(player1turn)
                player1wins();
            else
                player2wins();
        }
        else if(roundcount==9)
            draw();
        else
            player1turn = !player1turn;




    }
    private Boolean winner()
    {
        String[][] field =new String[3][3];
        for(int i=0 ;i<3;i++)
        {
            for ( int j =0;j<3;j++)
            {
                field[i][j]= buttons[i][j].getText().toString();
            }

        }
        for(int i=0;i<3;i++)
        {
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !(field[i][0].equals("")))
            {
                return true;
            }

        }
        for(int j=0;j<3;j++)
        {
            if(field[0][j].equals(field[1][j]) && field[0][j].equals(field[2][j]) && !(field[0][j].equals("")))
            {
                return true;
            }

        }
        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !(field[0][0].equals("")))
        {
            return true;
        }
        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !(field[2][0].equals("")))
        {
            return true;
        }
        return false;
    }
    private void player1wins()
    {
        player1points++;
        Toast.makeText(this, "player 1 is the winner", Toast.LENGTH_SHORT).show();
        player1.setText("Player 1:"+player1points);
        player2.setText("Player 2:"+player2points);
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                buttons[i][j].setText("");
        roundcount=0;
        player1turn=true;

    }
    private  void player2wins()
    {
        player2points++;
        Toast.makeText(this, "player 2 is the winner", Toast.LENGTH_SHORT).show();
        player1.setText("Player 1:"+player1points);
        player2.setText("Player 2:"+player2points);
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                buttons[i][j].setText("");
        roundcount=0;
        player1turn=true;

    }
    private void draw()
    {
        Toast.makeText(this, "DRAW MATCH", Toast.LENGTH_SHORT).show();
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                buttons[i][j].setText("");
        roundcount=0;
        player1turn = true;
    }
}
