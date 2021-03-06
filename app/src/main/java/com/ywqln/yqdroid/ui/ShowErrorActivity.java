package com.ywqln.yqdroid.ui;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ywqln.yqdroid.R;
import com.ywqln.yqdroid.base.BaseActivity;
import com.ywqln.yqdroid.util.MultiTapUtil;
import com.ywqln.yqdroid.widgets.view.StatusBarNotification;

import butterknife.BindView;

/**
 * 描述:待描述.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2018/6/26
 */
public class ShowErrorActivity extends BaseActivity {

    @BindView(R.id.clickButton)
    public Button clickButton;

    @BindView(R.id.imageView)
    public ImageView imageView;

    @BindView(R.id.editText)
    public EditText editText;

    @BindView(R.id.starButton)
    public Button starButton;

    private StatusBarNotification mNotification;

    @Override
    protected int layoutResId() {
        return R.layout.activity_showerror;
    }

    @Override
    protected void initViews() {
        // 方式1
        mNotification = new StatusBarNotification.Builder(this)
                .setMessage("这是提示消息")
                .setTextColor(Color.WHITE)
                .setBgColor(Color.argb(180, 255, 0, 0))
                .setDisplayDelayed(2000).build();

        mNotification.show();
    }

    @Override
    protected void initComplete() {
        clickButton.setOnClickListener(view -> btn_click());
        starButton.setOnClickListener(view -> star_click());

        imageView.setOnClickListener(new MultiTapUtil()
                .setInterval(300)
                .setTriggerCount(5)
                .setMultiTapListener(new MultiTapUtil.MultiTapListener() {
                    @Override
                    public void multiClick(View view) {
                        new StatusBarNotification.Builder(ShowErrorActivity.this)
                                .setMessage("欢迎管理员")
                                .show();
                    }
                }));

    }

    private void btn_click() {

        int position = 0;
        try {
            position = Integer.parseInt(editText.getText().toString().trim());
        } catch (Exception e) {
            mNotification.setMessage("不要调皮，请输入数字哦😝");
            mNotification.show();
            return;
        }


        String number = fibonacci(position);

        mNotification.setMessage("结果是：" + number);
        mNotification.show();

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.create();


        // 方式2
//        new StatusBarNotification.Builder(this)
//                .setMessage("当前网络状态不佳，可下拉刷新尝试")
//                .setTextColor(Color.WHITE)
//                .setBgColor(Color.argb(180, 0, 255, 0))
//                .setDisplayDelayed(2000).show();

    }

    private String fibonacci(int position) {
        if (position == 0) {
            return "0";
        }
        if (position == 1) {
            return "1";
        }
        if (position < 0) {
            return "不要调皮，请输入正数哦😝";
        }
        int[] fibonacciArr = new int[position];
        fibonacciArr[0] = 0;
        fibonacciArr[1] = 1;

        for (int i = 2; i < position; i++) {
            fibonacciArr[i] = fibonacciArr[i - 2] + fibonacciArr[i - 1];
        }
        return String.valueOf(fibonacciArr[position - 1]);
    }

    private void star_click() {
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        container.setBackgroundColor(Color.parseColor("#88222288"));

//        for (int i = 0; i < 10; i++) {
//            // 添加每一行的容器
//            LinearLayout lineContainer = new LinearLayout(this);
//            LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            container.addView(lineContainer, lineParams);
//
//            // 给每一行容器添加星星✨
//            for (int j = 0; j < i; j++) {
//                TextView startView = new TextView(this);
//                startView.setText("★");
//                lineContainer.addView(startView);
//            }
//        }

        for (int i = 10; i > 0; i--) {
            // 添加每一行的容器
            LinearLayout lineContainer = new LinearLayout(this);
            LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            container.addView(lineContainer, lineParams);

            // 给每一行容器添加星星✨
            for (int j = 0; j < i; j++) {
                TextView startView = new TextView(this);
                startView.setText("★");
                lineContainer.addView(startView);
            }
        }

        this.addContentView(container, containerParams);
    }
}
