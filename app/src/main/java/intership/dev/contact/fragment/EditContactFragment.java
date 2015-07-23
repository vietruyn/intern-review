package intership.dev.contact.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import intership.dev.contact.ListContactActivity;
import intership.dev.contact.R;
import intership.dev.contact.model.Contact;
import intership.dev.contact.view.CircleImageView;

/**
 * Created by vietruyn on 22/07/2015.
 */

public class EditContactFragment extends Fragment {

    private CircleImageView imgAvatar;
    private TextView tvName;
    private EditText edtName, edtDesc;
    private Button btnSave, btnCancel;
    private ImageView imgBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_contact, null);
        final Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        final Contact mContact = (Contact) bundle.getSerializable("contact");

        imgAvatar = (CircleImageView) view.findViewById(R.id.imgAvatar);
        edtName = (EditText) view.findViewById(R.id.edtName);
        edtDesc = (EditText) view.findViewById(R.id.edtDesc);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        tvName = (TextView) view.findViewById(R.id.tvName);
        imgBack = (ImageView) view.findViewById(R.id.imgBack);

        edtName.setText(mContact.getmNameContact().toString());
        edtDesc.setText(mContact.getmDescContact().toString());
        imgAvatar.setBackgroundResource(mContact.getmAvatar());
        tvName.setText(mContact.getmNameContact().toString());
        final int position = bundle.getInt("position");

        /**
         * Event click back
         */
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        /**
         * Event click button save
         */
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContact.setmNameContact(edtName.getText().toString());
                mContact.setmDescContact(edtDesc.getText().toString());
                intent.putExtra("position", position);
                intent.putExtra("contact", mContact);
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
            }
        });

        /**
         * Event click button cancel
         */
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }


}
