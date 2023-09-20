package com.uog.foodapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uog.foodapp.ListActivity;
import com.uog.foodapp.R;
import com.uog.foodapp.database.Person;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    public interface ClickListener{
        void onItemClick(int position, View v, long id);
    }

    public void setListener (ClickListener listener){
        this.listener = listener;
    }

    private static ClickListener listener;

    private List<Person> personList;
    public void setPersonList(List<Person> personList){
        this.personList = personList;
    }

    public PersonAdapter(List<Person> personList){
        this.personList=personList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Person person = personList.get(position);
        holder.lbl_Id.setText(person.getId() + "");
        holder.lbl_Name.setText(person.getName());
        holder.lbl_Address.setText(person.getAddress());
        holder.lbl_Phone.setText(person.getPhone());
        holder.lbl_Age.setText(person.getAge() + "");


    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView lbl_Id, lbl_Name, lbl_Address, lbl_Phone, lbl_Age;
        Button btn_Remove, btn_Edit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lbl_Id = itemView.findViewById(R.id.lbl_Id);
            lbl_Name = itemView.findViewById(R.id.lbl_Name);
            lbl_Address = itemView.findViewById(R.id.lbl_Address);
            lbl_Phone = itemView.findViewById(R.id.lbl_Phone);
            lbl_Age = itemView.findViewById(R.id.lbl_Age);
            btn_Remove = itemView.findViewById(R.id.btn_Remove);
            btn_Edit = itemView.findViewById(R.id.btn_Edit);
            btn_Remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onItemClick(getAdapterPosition(),view,R.id.btn_Remove);

                }
            });

            btn_Edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onItemClick(getAdapterPosition(),view,R.id.btn_Edit);

                }
            });

        }
    }

}
