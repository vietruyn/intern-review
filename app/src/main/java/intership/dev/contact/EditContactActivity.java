package intership.dev.contact;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import intership.dev.contact.fragment.EditContactFragment;

/**
 * Created by vietruyn on 22/07/2015.
 */
public class EditContactActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        EditContactFragment fragment = new EditContactFragment();
        transaction.add(R.id.lnEditFragment, fragment);
        transaction.commit();
    }
}