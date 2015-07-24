package intership.dev.contact.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import intership.dev.contact.R;
import intership.dev.contact.adapter.ContactAdapter;
import intership.dev.contact.model.Contact;
import intership.dev.contact.view.LoadMoreListview;


public class ListContactFragment extends Fragment {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_contacts, null);

        mContacts = new ArrayList<Contact>();
        for (int i = 0; i < NAME.length; i++) {
            Contact item = new Contact(AVATAR[i], NAME[i], DESC[i]);
            mContacts.add(item);
        }

        //Set data for listview
        lvContact = (LoadMoreListview) view.findViewById(R.id.lvContact);
        mContactAdapter = new ContactAdapter(getActivity(), R.layout.item_list_contact, mContacts);
        lvContact.setAdapter(mContactAdapter);
        lvContact.setOnLoadMoreListener(new LoadMoreListview.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new LoadDataTask().execute();
            }
        });

        return view;
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

            // We need notify the adapter that the data have been changed
            mContactAdapter.notifyDataSetChanged();

            // Call onLoadMoreComplete when the LoadMore task, has finished
            ((LoadMoreListview) lvContact).onLoadMoreComplete();

            super.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {
            // Notify the loading more operation has finished
            ((LoadMoreListview) lvContact).onLoadMoreComplete();
        }
    }

    /**
     *Get item contact from Edit contact
     * @param requestCode
     * @param resultCode
     * @param data
     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1 && resultCode == RESULT_OK) {
//            Contact contact = (Contact) data.getSerializableExtra("contact");
//            int position = data.getIntExtra("position", -1);
//            mContacts.set(position, contact);
//            mContactAdapter.notifyDataSetChanged();
//        }
}



