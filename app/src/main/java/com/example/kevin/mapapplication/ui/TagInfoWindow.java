package com.example.kevin.mapapplication.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.nfc.Tag;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kevin.mapapplication.R;
import com.example.kevin.mapapplication.model.MarkerManager;
import com.google.android.gms.maps.model.Marker;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kevin on 3/12/16.
 */
public class TagInfoWindow {

    private Context context;

    public TagInfoWindow (Context con) {
        context = con;
    }

    public View BuildTagContent(Marker marker) {
        LinearLayout hor_layout = new LinearLayout(context), ver_layout = new LinearLayout(context);
        ImageView infoimage = new ImageView(context);
        TextView shorttitle = new TextView(context);
        TextView type = new TextView(context);
        TextView cost = new TextView(context);

        try {
            JSONObject mdata = MarkerManager.getInstance().Get(marker.getId());
            if((int) mdata.get("Id") == 0) {
                return null;
            }
            else {
                if(mdata.get("Color").equals("Green")) {
                    infoimage.setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ic_greentag));
                }
                else if(mdata.get("Color").equals("Blue")) {
                    infoimage.setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ic_bluetag));
                }
                else {
                    infoimage.setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ic_redtag));
                }
                shorttitle.setText((String)mdata.get("ShortTitle"));
                type.setText((String)mdata.get("Type"));
                cost.setText("Reward: " + Integer.toString((int)mdata.get("Reward")) + " Points");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        infoimage.setPadding(0, 15, 0, 0);

        shorttitle.setTextSize(16);
        shorttitle.setTypeface(Typeface.DEFAULT_BOLD);

        hor_layout.setOrientation(LinearLayout.HORIZONTAL);
        hor_layout.setPadding(0, 10, 10, 10);
        ver_layout.setOrientation(LinearLayout.VERTICAL);
        hor_layout.addView(infoimage);
        ver_layout.addView(shorttitle);
        ver_layout.addView(type);
        ver_layout.addView(cost);
        hor_layout.addView(ver_layout);

        return hor_layout;

    }

}
