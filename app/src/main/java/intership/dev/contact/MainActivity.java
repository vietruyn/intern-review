package intership.dev.contact;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import intership.dev.contact.fragment.ListContactFragment;

/**
 * Created by vietruyn on 24/07/2015.
 */
public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        addListContactFragment();
    }

    private void addListContactFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ListContactFragment fragment = new ListContactFragment();
        transaction.add(R.id.lnFragment, fragment);
        transaction.commit();
    }
}