package intership.dev.contact.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import intership.dev.contact.R;
import intership.dev.contact.model.Contact;
import intership.dev.contact.view.CircleImageView;

/**
 * Created by vietruyn on 22/07/2015.
 */

public class EditContactFragment extends Fragment implements View.OnClickListener {

    private CircleImageView imgAvatar;
    private TextView tvName;
    private EditText edtName, edtDesc;
    private Button btnSave, btnCancel;
    private ImageView imgBack;

    Intent mIntent;
    Contact mContact;
    int mPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_contact, null);

        mIntent = getActivity().getIntent();
        Bundle bundle = mIntent.getExtras();
        mContact = (Contact) bundle.getSerializable("contact");
        mPosition = bundle.getInt("position");

        init(view);
        setValue();

        /**
         * Event click button save
         */
        btnSave.setOnClickListener(this);

        /**
         * Event click button cancel
         */
        btnCancel.setOnClickListener(this);

        /**
         * Event when click back
         */
        imgBack.setOnClickListener(this);

        return view;
    }



    /**
     * @param view
     */
    private void init(View view) {
        imgAvatar = (CircleImageView) view.findViewById(R.id.imgAvatar);
        edtName = (EditText) view.findViewById(R.id.edtName);
        edtDesc = (EditText) view.findViewById(R.id.edtDesc);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        tvName = (TextView) view.findViewById(R.id.tvName);
        imgBack = (ImageView) view.findViewById(R.id.imgBack);
    }

    /**
     * Set value name, desciption and avatar
     */
    private void setValue() {
        edtName.setText(mContact.getmNameContact().toString());
        edtDesc.setText(mContact.getmDescContact().toString());
        imgAvatar.setBackgroundResource(mContact.getmAvatar());
        tvName.setText(mContact.getmNameContact().toString());

    }

    /**
     * Event buttons
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //Save
            case R.id.btnSave:
                final Dialog dialog = new Dialog(getActivity(), R.style.Theme_Dialog);

                // Include dialog.xml file
                dialog.setContentView(R.layout.dialog_list_contact);

                // set values for custom dialog components - text
                TextView tvMessenger = (TextView) dialog.findViewById(R.id.tvMessenger);
                tvMessenger.setText(Html.fromHtml("Are you sure you want to edit this contact?"));
                dialog.show();

                //Set event when click ok in dialog
                Button btnOk = (Button) dialog.findViewById(R.id.btnOk);
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContact.setmNameContact(edtName.getText().toString());
                        mContact.setmDescContact(edtDesc.getText().toString());
                        mIntent.putExtra("position", mPosition);
                        mIntent.putExtra("contact", mContact);
                        getActivity().setResult(Activity.RESULT_OK, mIntent);
                        dialog.hide();
                        getActivity().finish();
                    }
                });
                //Set event when click ok in dialog
                Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hide();
                    }
                });
                break;

            //Cancel
            case R.id.btnCancel:
                getActivity().finish();
                break;

            //Back
            case R.id.imgBack:
                getActivity().finish();
            default:
        }
    }
}
