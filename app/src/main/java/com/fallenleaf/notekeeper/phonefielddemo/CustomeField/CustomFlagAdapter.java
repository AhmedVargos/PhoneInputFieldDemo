package com.fallenleaf.notekeeper.phonefielddemo.CustomeField;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fallenleaf.notekeeper.phonefielddemo.R;

/**
 * Created by Vargo on 26/09/2017.
 */

public class CustomFlagAdapter extends BaseAdapter {
    Context context;
    TypedArray flagImages;
    String[] countryCodes;

    public CustomFlagAdapter(Context context, TypedArray flagImages, String[] countryCodes) {
        this.context = context;
        this.flagImages = flagImages;
        this.countryCodes = countryCodes;
    }

    @Override
    public int getCount() {
        return countryCodes.length;
    }

    @Override
    public Object getItem(int i) {
        return countryCodes[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_flag_adapter, null);
        ImageView icon =  view.findViewById(R.id.flag_image);
        TextView iconText =  view.findViewById(R.id.flag_code);
        icon.setImageResource(flagImages.getResourceId(i,0));
        iconText.setText(countryCodes[i]);
        return view;
    }

}