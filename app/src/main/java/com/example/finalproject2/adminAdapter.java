package com.example.finalproject2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class adminAdapter extends FirebaseRecyclerAdapter<MainModel,adminAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public adminAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModel model) {
        holder.nama.setText(model.getNama());
        holder.stok.setText(model.getStok());
        Glide.with(holder.img.getContext())
                .load(model.getUrl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.ic_launcher_background)
                .into(holder.img);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.updatepop))
                        .setExpanded(true,1200)
                        .create();

                View view = dialogPlus.getHolderView();

                EditText name = (EditText) view.findViewById(R.id.updateNAMA);
                EditText stok = (EditText) view.findViewById(R.id.updateSTOK);
                EditText url = (EditText) view.findViewById(R.id.updateURL);
                Button btnEdit = view.findViewById(R.id.tombolUpdate);
                Button update = view.findViewById(R.id.tombolUpdate);
                name.setText(model.getNama());
                stok.setText(model.getStok());
                url.setText(model.getUrl());
                dialogPlus.show();

                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map =  new HashMap<>();
                        map.put("nama", name.getText().toString());
                        map.put("stok",stok.getText().toString());
                        map.put("url", url.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Barang")
                                .child(adminProduct.getData()).child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {

                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.nama.getContext(), "Update Barang", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {

                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.nama.getContext(), "Erorr", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.nama.getContext());
                builder.setTitle("Apakah Anda Yakin Untuk Menghapus");
                builder.setMessage("Jika Sudah Di Hapus Gabisa Di-Undo");
                builder.setPositiveButton("Delete",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Barang").child(adminProduct.getData())
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.nama.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminitem,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView nama,stok;
        Button btnEdit,btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.gambar);
            nama = (TextView) itemView.findViewById(R.id.namaBrg);
            stok = (TextView) itemView.findViewById(R.id.stok);
            btnEdit = (Button) itemView.findViewById(R.id.updateBRG);
            btnDelete = (Button) itemView.findViewById(R.id.deleteBRG);
        }
    }
}
