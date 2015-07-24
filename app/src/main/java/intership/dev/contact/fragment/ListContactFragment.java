package intership.dev.contact.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import intership.dev.contact.R;
import intership.dev.contact.adapter.ContactAdapter;
import intership.dev.contact.model.Contact;
import intership.dev.contact.view.LoadMoreListview;


public class ListContactFragment extends Fragment implements View.OnClickListener {

    private static final String[] NAME = new String[]{"Strawberry",
            "Banana", "Orange", "Mixed", "Abbott", "Abraham", "Alvin", "Dalton", "Gale", "Halsey", "Isaac", "Philbert", "Abbott"};

    private static final String[] DESC = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "k", "l", "m", "n"};

    private static final Integer[] AVATAR = {R.drawable.ic_avt1,
            R.drawable.ic_avt2, R.drawable.ic_avt3, R.drawable.ic_avt4, R.drawable.ic_avt1,
            R.drawable.ic_avt2, R.drawable.ic_avt3, R.drawable.ic_avt4, R.drawable.ic_avt1,
            R.drawable.ic_avt2, R.drawable.ic_avt3, R.drawable.ic_avt4, R.drawable.ic_avt1,};

    private ContactAdapter mContactAdapter;
    private LoadMoreListview lvContact;
    private ArrayList<Contact> mContacts;

    private ImageView imgBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_contacts, null);
        lvContact = (LoadMoreListview) view.findViewById(R.id.lvContact);
        imgBack =(ImageView) view.findViewById(R.id.imgBack);

        imgBack.setOnClickListener(this);
        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setValue();
    }

    @Override
    public void onResume() {
        //Set data for listview
        mContactAdapter = new ContactAdapter(getActivity(), R.layout.item_list_contact, mContacts);
        lvContact.setAdapter(mContactAdapter);
        lvContact.setOnLoadMoreListener(new LoadMoreListview.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new LoadDataTask().execute();
            }
        });
        super.onResume();
    }

    /**
     * Set value Contact
     */
    private void setValue() {
        mContacts = new ArrayList<Contact>();
        for (int i = 0; i < NAME.length; i++) {
            Contact item = new Contact(AVATAR[i], NAME[i], DESC[i]);
            mContacts.add(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBack:
                getActivity().finish();
        }
    }

    /**
     * Asyntact load more list
     */
    private class LoadDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            if (isCancelled()) {
                return null;
            }
            // Simulates a background task
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < NAME.length; i++) {
                Contact item = new Contact(AVATAR[i], NAME[i], DESC[i]);
                mContacts.add(item);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            mContactAdapter.notifyDataSetChanged();
            ((LoadMoreListview) lvContact).onLoadMoreComplete();

            super.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {

            // Notify the loading more operation has finished
            ((LoadMoreListview) lvContact).onLoadMoreComplete();
        }
    }

}



