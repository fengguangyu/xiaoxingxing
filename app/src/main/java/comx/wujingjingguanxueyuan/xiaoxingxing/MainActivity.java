package comx.wujingjingguanxueyuan.xiaoxingxing;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import comx.wujingjingguanxueyuan.xiaoxingxing.activity.BooksFragment;
import comx.wujingjingguanxueyuan.xiaoxingxing.activity.BottomNavigationBean;
import comx.wujingjingguanxueyuan.xiaoxingxing.activity.MoviesFragment;
import comx.wujingjingguanxueyuan.xiaoxingxing.activity.MusicFragment;
import comx.wujingjingguanxueyuan.xiaoxingxing.activity.NewsstandFragment;
import etong.bottomnavigation.lib.BottomNavigationBar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationBar bottomLayout;

    private ArrayList<BottomNavigationBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // Translucent navigation bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Instant run");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, MoviesFragment.newInstance()).commitAllowingStateLoss();

        setUpBottomNavigationBar();


        //TODO 动态设置tab数量
//        findViewById(R.id.addTab).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomLayout.addTab(R.drawable.selector_movie, "Movies & Tv", 0xff4a5965);
//            }
//        });


    }

    public void setUpBottomNavigationBar() {

        bottomLayout = (BottomNavigationBar) findViewById(R.id.bottomLayout);
//        bottomLayout.setTextColorResId(R.color.color_tab_text);
        bottomLayout.addTab(R.drawable.selector_movie, "Movies & Tv", 0xff4a5965);
        bottomLayout.addTab(R.drawable.selector_music, "Music", 0xff096c54);
        bottomLayout.addTab(R.drawable.selector_books, "Books", 0xff8a6a64);
        bottomLayout.addTab(R.drawable.selector_news, "Newsstand", 0xff553b36);
        bottomLayout.setOnTabListener(new BottomNavigationBar.TabListener() {
            @Override
            public void onSelected(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = MoviesFragment.newInstance();
                        break;
                    case 1:
                        fragment = MusicFragment.newInstance();
                        break;
                    case 2:
                        fragment = BooksFragment.newInstance();
                        break;
                    case 3:
                        fragment = NewsstandFragment.newInstance();
                        break;
                    default:
                        fragment = MoviesFragment.newInstance();
                        break;
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment)
//                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .commitAllowingStateLoss();
            }
        });
        Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bottomLayout.requestLayout();
            }
        },1000);
            }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
