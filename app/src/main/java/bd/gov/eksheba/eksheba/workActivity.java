package bd.gov.eksheba.eksheba;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class workActivity extends AppCompatActivity {

    private ViewStub stubGrid;
    private ViewStub stubList;
    private ListView listView;
    private GridView gridView;
    private ListViewAdapter listViewAdapter;
    private GridViewAdapter gridViewAdapter;
    private List<Product> productList;
    private int currentViewMode = 0;

    static final int VIEW_MODE_LISTVIEW = 0;
    static final int VIEW_MODE_GRIDVIEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jewel = new Intent(workActivity.this, secondActivity.class);
                startActivity(jewel);
            }
        });

        stubList = (ViewStub) findViewById(R.id.stub_list);
        stubGrid = (ViewStub) findViewById(R.id.stub_grid);

        //Inflate ViewStub before get view

        stubList.inflate();
        stubGrid.inflate();

        listView = (ListView) findViewById(R.id.mylistview);
        gridView = (GridView) findViewById(R.id.mygridview);

        //get list of product
        getProductList();

        //Get current view mode in share reference
        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
        currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_LISTVIEW);//Default is view listview
        //Register item lick
        listView.setOnItemClickListener(onItemClick);
        gridView.setOnItemClickListener(onItemClick);

        switchView();

    }

    private void switchView() {

        if(VIEW_MODE_LISTVIEW == currentViewMode) {
            //Display listview
            stubList.setVisibility(View.VISIBLE);
            //Hide gridview
            stubGrid.setVisibility(View.GONE);
        } else {
            //Hide listview
            stubList.setVisibility(View.GONE);
            //Display gridview
            stubGrid.setVisibility(View.VISIBLE);
        }
        setAdapters();
    }

    private void setAdapters() {
        if(VIEW_MODE_LISTVIEW == currentViewMode) {
            listViewAdapter = new ListViewAdapter(this, R.layout.list_item, productList);
            listView.setAdapter(listViewAdapter);
        } else {
            gridViewAdapter = new GridViewAdapter(this, R.layout.grid_item, productList);
            gridView.setAdapter(gridViewAdapter);
        }
    }

    public List<Product> getProductList() {
        //pseudo code to get product, replace your code to get real product here
        productList = new ArrayList<>();
        productList.add(new Product(R.drawable.ekshop_logo, "একশপ", "This is description 1"));
        productList.add(new Product(R.drawable.bd_logo, "স্ট\u200D্যাম্প ভেন্ডর লাইসেন্স প্রাপ্তির আবেদন", "This is description 2"));
        productList.add(new Product(R.drawable.bd_logo, "বৈবাহিক/অবৈবাহিক সনদ প্রাপ্তির আবেদন", "This is description 3"));
        productList.add(new Product(R.drawable.bd_logo, "বালাইনাশক বিক্রয়ের লাইসেন্স প্রাপ্তির আবেদন", "This is description 4"));
        productList.add(new Product(R.drawable.bd_logo, "অসচ্ছল প্রতিবন্ধী ভাতা মঞ্জুরীর জন্য আবেদন", "This is description 5"));
        productList.add(new Product(R.drawable.bd_logo, "যুব উন্নয়ন অফিসে প্রশিক্ষণ কোর্সের ভর্তির আবেদন", "This is description 6"));
        productList.add(new Product(R.drawable.bd_logo, "বাংলাদেশ গেজেটে প্রকাশিত নাম / তথ্য সংশোধনের জন্য আবেদনপত্র", "This is description 7"));
        productList.add(new Product(R.drawable.bd_logo, "বয়স্ক ভাতা মঞ্জুরির আবেদনপত্র", "This is description 8"));
        productList.add(new Product(R.drawable.bd_logo, "বিধবা/ স্বামী পরিত্যক্তা দুঃস্থ মহিলাদের ভাতা মঞ্জুরীর জন্য আবেদন", "This is description 9"));
        productList.add(new Product(R.drawable.bd_logo, "কৃষি উপকরণ সহায়তা কার্ড প্রাপ্তির আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "দারিদ্র মা’র জন্য মাতৃত্বকাল ভাতা মঞ্জুরীর আবেদনপত্র", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "মৃত্যু সনদ প্রাপ্তির জন্য আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "আগ্নেয়াস্ত্রের লাইসেন্স প্রাপ্তির আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "মহিলা ও শিশু সাহায্য তহবিল” হতে সাহায্যের আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "তথ্য কমিশনে অভিযোগ দায়ের ফরম", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "স্ট\u200D্যাম্প ভেন্ডর লাইসেন্স নবায়নের আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "ওয়াজ/ জিকির মাহফিল/ মহাযজ্ঞ ও লীলা কীর্তন/ ধর্মীয় অনুষ্ঠানে মাইক ব্যবহারের জন্য অনুমতি প্রাপ্তির আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "ইট ভাটার লাইসেন্স নবায়নের আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "আগ্নেয়াস্ত্রের লাইসেন্স নবায়নের আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "পেট্রোলিয়াম জাতীয় ব্যবসা পরিচালনার অনাপত্তিমূলক পত্র (এনওসি) প্রাপ্তির আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "মাননীয় প্রধানমন্ত্রীর ত্রাণ ও কল্যাণ তহবিল হতে প্রাপ্ত আর্থিক অনুদানের চেক উত্তোলনের আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "আবাসিক হোটেলের লাইসেন্স প্রাপ্তির আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "এসিড ও এসিড জাতীয় দ্রব্য বিক্রয়ের লাইসেন্স প্রাপ্তির আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "এসিড ও এসিড জাতীয় দ্রব্য বিক্রয়ের লাইসেন্স নবায়নের আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "এসিড ও এসিড জাতীয় দ্রব্য ব্যবহারের অনুমতি প্রাপ্তির আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "করাতকল/ইটভাটা/ফিলিং স্টেশন নির্মাণের নিমিত্ত জমির মালিকানা সনদ প্রাপ্তির আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "জমির শ্রেণি পরিবর্তনের আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "আদিবাসী (ক্ষুদ্র নৃগোষ্ঠী) প্রত্যয়নপত্র পাবার আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "অধিগ্রহণকৃত জমির ক্ষতিপূরণের টাকা উত্তোলনের জন্য আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "জমির সীমানা পিলার নির্ধারণের জন্য আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "মেলা / বিনোদন অনুষ্ঠান / বৈশাখী মেলা / সার্কাস আয়োজনের জন্য অনুমতি প্রাপ্তির আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "সাংস্কৃতিক অনুষ্ঠান/ জারি/ বাউল অনুষ্ঠান/ র\u200D্যাফেল ড্র / পুতুল নাচ প্রদর্শনীর জন্য অনুমতি প্রাপ্তির আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "বালুমহাল ইজারাপত্রে অংশগ্রহণের জন্য তালিকাভুক্তির/ লাইসেন্স প্রাপ্তির আবেদন ", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "বিটিআরসি-র সাধারন আবেদনপত্র", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "মুক্তিযোদ্ধা গেজেটভূক্তি আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "বালাইনাশক বিক্রয়ের লাইসেন্স নবায়নের জন্য আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "বিটিআরসি-র সাধারন আবেদনপত্র", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "সমাজকল্যাণ মন্ত্রণালয়ে সাধারণ আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "জাতীয় ভোক্তা অধিকার অভিযোগ ফরম", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "তথ্য অধিদফতরে প্রেস অ্যাক্রিডিটেশন কার্ডের জন্য আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "বাংলাদেশ পেট্রোলিয়াম ইন্সটিটিউট নিবন্ধন ফরম", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "ওয়াক্\u200Cফ অধ্যাদেশ, ১৯৬২ এর ৪৭ ধারামতে ওয়াক্\u200Cফ সম্পত্তি তালিকাভুক্তির দরখাস্ত", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "বাংলাদেশী নাগরিকত্বের জন্য আবেদনপত্র", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "তথ্য কমিশনে অভিযোগ দায়ের ফরম", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "সাধারণ ভবিষ্য তহবিল হতে অগ্রিম উত্তোলনের আবেদন ফরম", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "খাদ্যশস্য ও খাদ্য সামগ্রী ব্যবসার লাইসেন্স প্রাপ্তির জন্য আবেদন", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "ডিজিটাল রেকর্ড রুম", "This is description 10"));
        productList.add(new Product(R.drawable.bd_logo, "লগ আউট", "This is description 10"));
        return productList;
    }

    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Do any thing when user click to item
            Toast.makeText(getApplicationContext(), productList.get(position).getTitle(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(workActivity.this,MainActivity.class);
            if (position==0) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/57");
            } else if (position==1) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/60");
            } else if (position==2) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/62");
            } else if (position==3) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/65");
            } else if (position==4) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/69");
            } else if (position==5) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/71");
            } else if (position==6) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/73");
            } else if (position==7) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/75");
            } else if (position==8) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/77");
            } else if (position==9) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/79");
            } else if (position==10) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/80");
            } else if (position==11) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/81");
            } else if (position==12) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/82");
            } else if (position==13) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/84");
            } else if (position==14) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/85");
            } else if (position==15) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/86");
            } else if (position==16) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/88");
            } else if (position==17) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/89");
            } else if (position==18) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/91");
            } else if (position==19) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/92");
            } else if (position==20) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/94");
            } else if (position==21) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/95");
            } else if (position==22) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/96");
            } else if (position==23) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/97");
            } else if (position==24) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/98");
            } else if (position==25) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/100");
            } else if (position==26) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/102");
            } else if (position==27) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/104");
            } else if (position==28) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/106");
            } else if (position==29) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/108");
            } else if (position==30) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/109");
            } else if (position==31) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/110");
            } else if (position==32) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/112");
            } else if (position==33) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/113");
            } else if (position==34) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/63");
            } else if (position==35) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/74");
            } else if (position==36) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/122");
            } else if (position==37) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/123");
            } else if (position==38) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/125");
            } else if (position==39) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/127");
            } else if (position==40) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/129");
            } else if (position==41) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/131");
            } else if (position==42) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/132");
            } else if (position==43) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/133");
            } else if (position==44) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/134");
            } else if (position==45) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/136");
            } else if (position==46) {
                intent.putExtra("url", "http://eksheba.gov.bd/entrepreneur/OnlineServices/checkService/137");
            } else if (position==47) {
                intent.putExtra("url", "http://eksheba.gov.bd/logout");
            }
            startActivity(intent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_menu_1:
                if(VIEW_MODE_GRIDVIEW == currentViewMode) {
                    currentViewMode = VIEW_MODE_LISTVIEW;
                } else {
                    currentViewMode = VIEW_MODE_GRIDVIEW;
                }
                //Switch view
                switchView();
                //Save view mode in share reference
                SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("currentViewMode", currentViewMode);
                editor.commit();

                break;
        }
        return true;
    }
}
