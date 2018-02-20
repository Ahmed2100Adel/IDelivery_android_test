package com.example.yasmeen.nowaitressing1;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class moreDataOfOffer extends AppCompatActivity {
String id,name,url,numsOfOrders,inDays,howMuchMinus,howMuchPercentage,serialNumber;
    TextView nameOfOffer,txnumsOfOrders,txindays,txpercentage,txMinus,txserital,txnotnumsNotOfOrders,indaysNotOffer,percentageNot,minusNot;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_data_of_offer);
        nameOfOffer = (TextView)findViewById(R.id.nameOfOffer);
        txnumsOfOrders = (TextView)findViewById(R.id.numsOfOrders);
        imageView  =(ImageView)findViewById(R.id.ivmoreDataOffers);
        txindays  =(TextView) findViewById(R.id.txindays);
        txpercentage  =(TextView) findViewById(R.id.txpercentage);
        txMinus  =(TextView) findViewById(R.id.txMinus);
        txserital  =(TextView) findViewById(R.id.txserital);
        txnotnumsNotOfOrders  =(TextView) findViewById(R.id.txnotnumsNotOfOrders);
        indaysNotOffer  =(TextView) findViewById(R.id.indaysNotOffer);
        percentageNot  =(TextView) findViewById(R.id.percentageNot);
        minusNot  =(TextView) findViewById(R.id.minusNot);
        Bundle b = getIntent().getExtras();
        id = b.getString("id");
        name = b.getString("name");
        url = b.getString("url");
        numsOfOrders = b.getString("numsOfOrders");
        inDays = b.getString("inDays");
        howMuchMinus = b.getString("howMuchMinus");
        howMuchPercentage = b.getString("howMuchPercentage");
        serialNumber = b.getString("serialNumber");
        puttingElements();
    }
    public void puttingElements(){
        Glide.with(this)
                .load(url)
                .into(imageView);
        nameOfOffer.setText(name);
        txnumsOfOrders.setText(numsOfOrders);
        txindays.setText(inDays);
        txpercentage.setText(howMuchPercentage+"%");
        txMinus.setText(howMuchMinus);
        txserital.setText(serialNumber);
        if(numsOfOrders.equals("0")){
            txnumsOfOrders.setVisibility(View.INVISIBLE);
            txnotnumsNotOfOrders.setVisibility(View.INVISIBLE);

        }
        if(inDays.equals("0")){
            txindays.setVisibility(View.GONE);
            indaysNotOffer.setVisibility(View.GONE);
        }if(howMuchPercentage.equals("0")){
            txpercentage.setVisibility(View.GONE);
            percentageNot.setVisibility(View.GONE);
        }if(howMuchMinus.equals("0")){
            txMinus.setVisibility(View.GONE);
            minusNot.setVisibility(View.GONE);
        }
    }
public void sertialonClick(View view){
    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    ClipData clip = ClipData.newPlainText("Copied to your clipboard", serialNumber);
    clipboard.setPrimaryClip(clip);
    Toast.makeText(this, "Copied to your clipboard", Toast.LENGTH_SHORT).show();
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_developer,menu);

        return super.onCreateOptionsMenu(menu);
    }
    public void back(MenuItem item)
    {
        this.finish();
    }
}
 /*   public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }*/