package org.yanzi.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AbsListView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

/**
 *  这个类是专门用来给使用imageLoader的listview用来记录页面
 *  以便页面能够快速的恢复
 */
public class AbsListViewBaseActivity extends Activity {

    protected static final String STATE_PAUSE_ON_SCROLL = "STATE_PAUSE_ON_SCROLL";
    protected static final String STATE_PAUSE_ON_FLING = "STATE_PAUSE_ON_FLING";

    protected AbsListView listView;

    protected boolean pauseOnScroll = false;
    protected boolean pauseOnFling = true;
    private ImageLoader imageLoader;

    public AbsListViewBaseActivity(ImageLoader imageLoader){
        this.imageLoader =imageLoader;

    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {//这个是处理重启的最好的方法
        pauseOnScroll = savedInstanceState.getBoolean(STATE_PAUSE_ON_SCROLL, false);
        pauseOnFling = savedInstanceState.getBoolean(STATE_PAUSE_ON_FLING, true);
    }

    @Override
    public void onResume() {
        super.onResume();
        applyScrollListener();
    }

    private void applyScrollListener() {
        listView.setOnScrollListener(new PauseOnScrollListener(imageLoader, pauseOnScroll, pauseOnFling));
    }


     @Override
    public void onSaveInstanceState(Bundle outState) {//这个方法不保证一定会被调用，所以使用pause是最好的
        //一个好的，用来测试应用程序恢复状态的能力的方法是简单地旋转设备的屏幕方向
        outState.putBoolean(STATE_PAUSE_ON_SCROLL, pauseOnScroll);
        outState.putBoolean(STATE_PAUSE_ON_FLING, pauseOnFling);
    }
/*
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem pauseOnScrollItem = menu.findItem(R.id.item_pause_on_scroll);
        pauseOnScrollItem.setVisible(true);
        pauseOnScrollItem.setChecked(pauseOnScroll);

        MenuItem pauseOnFlingItem = menu.findItem(R.id.item_pause_on_fling);
        pauseOnFlingItem.setVisible(true);
        pauseOnFlingItem.setChecked(pauseOnFling);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_pause_on_scroll:
                pauseOnScroll = !pauseOnScroll;
                item.setChecked(pauseOnScroll);
                applyScrollListener();
                return true;
            case R.id.item_pause_on_fling:
                pauseOnFling = !pauseOnFling;
                item.setChecked(pauseOnFling);
                applyScrollListener();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
