package com.example.ratha.firebasedemo.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.ratha.firebasedemo.AuthenticationActivity;
import com.example.ratha.firebasedemo.R;

/**
 * Created by ratha on 12/13/2017.
 */

public class CreateAccountDialog extends DialogFragment{
    Context context;
    DialogCallBack dialogCallBack ;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
        this.dialogCallBack= (DialogCallBack) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);

        final View view= LayoutInflater.from(context).inflate(R.layout.create_accout_dialog,null);
        final ViewHolder viewHolder=new ViewHolder(view);
        builder.setView(view)
                .setTitle("Create new Account")
                .setPositiveButton("SignUp", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogCallBack.saveUserAccount(viewHolder.userName.getText().toString(),
                                viewHolder.password.getText().toString());
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }
    class ViewHolder{
        private EditText userName,password;
        public ViewHolder(View itemView){
            userName=itemView.findViewById(R.id.edUserName);
            password=itemView.findViewById(R.id.edPassword);
        }
    }

    public interface DialogCallBack{
        void saveUserAccount(String name,String email);
    }
}
