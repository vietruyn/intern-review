package intership.dev.contact.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import intership.dev.contact.R;
import intership.dev.contact.model.Contact;

/**
 * Created by vietruyn on 21/07/2015.
 */
public class ContactAdapter extends ArrayAdapter<Contact> {

    Context context;

    public ContactAdapter(Context context, int resourceId,
                          List<Contact> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imgAvatar, imgDelete, imgEdit;
        TextView tvName;
    }


    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder holder = null;
        final Contact contact = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_list_contact, null);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imgDelete);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imgDelete);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.tvName.setText(contact.getmNameContact());
        holder.imgAvatar.setImageResource(contact.getmAvatar());
        String mDesc = contact.getmDescContact();
        return convertView;
    }
}