package com.example.ux_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;
import java.util.zip.Inflater;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    Context ctx;
    Intent intent;
    Vector<Game> GameVector;

    public GameAdapter(Context ctx, Intent intent) {
        this.ctx = ctx;
        this.intent = intent;
    }

    // setter vector
    public void setGameVector(Vector<Game> gameVector ) {
        this.GameVector = gameVector;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.game_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.ViewHolder holder, int position) {
        // set data
        Game game = GameVector.get(position);
        holder.gametitletxt.setText(game.getGameTitle());
        holder.gamedesctxt.setText(game.getGameDesc());
        holder.gameimage.setImageDrawable(game.getImg());
        holder.detailsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uname = intent.getStringExtra("usernameglobal");
                Intent intent = new Intent(ctx, DetailsActivity.class );
                intent.putExtra("titlevariable", GameVector.get(position).getGameTitle());
                intent.putExtra("descvariable", GameVector.get(position).getGameDesc());
                intent.putExtra("imagelistglobal", GameVector.get(position).getIdx());

                GameAdapter gameAdapter = new GameAdapter(ctx,intent);

                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return GameVector.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView gametitletxt, gamedesctxt;
        CardView gamecv;
        ImageView gameimage;
        Button detailsbtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            detailsbtn = itemView.findViewById(R.id.detailsbtn);
            gametitletxt = itemView.findViewById(R.id.gametitle);
            gamedesctxt = itemView.findViewById(R.id.gamedesc);
            gameimage = itemView.findViewById(R.id.gameimage);
            gamecv = itemView.findViewById(R.id.game_cv);
        }
    }
}
