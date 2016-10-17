package com.example.administrator.learntocook.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.renderscript.Type;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/8.
 */
public class FontView extends TextView {
    public FontView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context ) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),
               "fonts/BB.TTF" );
        setTypeface(typeface);
        setText(Html.fromHtml("<font color=\"#BEDOD7\">学</font><font color=\"#8B0000\">做</font><font color=\"#F2BA6B\">菜</font>"));
    }
}
