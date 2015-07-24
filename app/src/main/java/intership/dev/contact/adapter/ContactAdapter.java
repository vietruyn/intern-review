package intership.dev.contact.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


import intership.dev.contact.R;
import intership.dev.contact.fragment.EditContactFragment;
import intership.dev.contact.model.Contact;

/**
 * Created by vietruyn on 21/07/2015.
 */
public class ContactAdapter extends ArrayAdapter<Contact> implements EditContactFragment.OnChangeItemListener {


    private FragmentActivity mActivity;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private EditContactFragment mEditContactFragment;

    public ContactAdapter(FragmentActivity mActivity, int resourceId,
                          List<Contact> items) {
        super(mActivity, resourceId, items);
        this.mActivity = mActivity;
    }


    @Override
    public void onChange(Contact contact) {

    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imgAvatar, imgDelete, imgEdit;
        TextView tvName;
    }


    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder holder = null;
        final Contact contact = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) mActivity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_list_contact, null);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imgDelete);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imgEdit);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.tvName.setText(contact.getmNameContact());
        holder.imgAvatar.setImageResource(contact.getmAvatar());
        String mDesc = contact.getmDescContact();

        /**
         * Show dialog when click icon delete
         */
        final ViewHolder finalHolder = holder;
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getContext(), R.style.Theme_Dialog);
                // Include dialog.xml file
                dialog.setContentView(R.layout.dialog_list_contact);

                // Set values for custom dialog components - text
                TextView tvMessenger = (TextView) dialog.findViewById(R.id.tvMessenger);
                tvMessenger.setText(Html.fromHtml("Are you sure you want to delete " + "<b>" + contact.getmNameContact().toString() + "</b>" + "?"));
                dialog.show();

                //Set backround icon delete;
                finalHolder.imgDelete.setBackgroundResource(R.drawable.ic_delete_check);

                //Set event when click ok in dialog
                Button btnOk = (Button) dialog.findViewById(R.id.btnOk);
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        remove(contact);
                        dialog.hide();
                        finalHolder.imgDelete.setBackgroundResource(R.drawable.ic_delete);
                    }
                });

                //Set event when click ok in dialog
                Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hide();
                        finalHolder.imgDelete.setBackgroundResource(R.drawable.ic_delete);
                    }
                });
            }
        });
        /**
         * Show dialog when click icon edit
         */
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callEditContactFragment(contact);
            }
        });

        return convertView;
    }

    private void callEditContactFragment(Contact contact) {
        mFragmentManager = mActivity.getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        if (mEditContactFragment == null) {
            mEditContactFragment = new EditContactFragment();
            mEditContactFragment.setOnChangeItemListener(this);
        }
        Bundle dataBundle = new Bundle();
        dataBundle.putSerializable("dataBundle", contact);


        mEditContactFragment.setArguments(dataBundle);
        mFragmentTransaction.replace(R.id.lnFragment, mEditContactFragment);
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }
}