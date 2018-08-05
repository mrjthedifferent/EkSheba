package bd.gov.eksheba.eksheba;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

public class secondActivity extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        //Set Event
        setSingleEvent(mainGrid);
        //setToggleEvent(mainGrid);
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (finalI == 0) {
                        Intent intent = new Intent(secondActivity.this, MainActivity.class);
                        intent.putExtra("url", "http://hardiup.com/eksheba/login");
                        startActivity(intent);
                    } else if (finalI == 1) {
                        Intent intent = new Intent(secondActivity.this, MainActivity.class);
                        intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/profile/view");
                        startActivity(intent);
                    } else if (finalI == 2) {
                        Intent intent = new Intent(secondActivity.this, workActivity.class);
                        startActivity(intent);
                    } else if (finalI == 3) {
                        Intent intent = new Intent(secondActivity.this, MainActivity.class);
                        intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/report/today");
                        startActivity(intent);
                    } else if (finalI == 4) {
                        Intent intent = new Intent(secondActivity.this, MainActivity.class);
                        intent.putExtra("url", "http://hardiup.com/eksheba/offline_report");
                        startActivity(intent);

                        /*android.support.v7.app.AlertDialog.Builder builder1 = new android.support.v7.app.AlertDialog.Builder(secondActivity.this);
                        builder1.setMessage("দুঃখিত.. এই পাতাটি বর্তমান বন্ধ আছে.");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "ঠিক আছে",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        android.support.v7.app.AlertDialog alert11 = builder1.create();
                        alert11.show();*/
                    } else if (finalI == 5) {
                        Intent intent = new Intent(secondActivity.this, MainActivity.class);
                        intent.putExtra("url", "http://eksheba.gov.bd/logout");
                        startActivity(intent);
                    } else if (finalI == 6) {
                        Intent intent = new Intent(secondActivity.this, appActivity.class);
                        startActivity(intent);
                    } else if (finalI == 7) {
                        Intent intent = new Intent(secondActivity.this, DeveloperActivity.class);
                        startActivity(intent);
                    } else {

                    }

                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("আপনি কি অ্যাপ বন্ধ করতে চান?\nঅ্যাপ বন্ধ করার আগে আপনার একাউন্ট থেকে লগআউট করুন কেননা একসেবা একের অধিক ব্রাউজারে একসাথে লগইন থাকা সমর্থন করে না।");
        builder.setPositiveButton("হ্যাঁ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton("না", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
