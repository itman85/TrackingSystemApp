package phannguyen.com.trackingsystem.ui.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import phannguyen.com.trackingsystem.R;

/**
 * Created by ThuNguyen on 5/11/2017.
 */

public class DashboardItemLayout extends CardView {
    @BindView(R.id.ivDashboard)
    ImageView ivDashboard;
    @BindView(R.id.tvDashboard)
    TextView tvDashboard;

    public DashboardItemLayout(Context context) {
        super(context);
    }

    public DashboardItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashboardItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DashboardItemLayout, defStyleAttr, 0);
        int dashboardIconId = a.getResourceId(R.styleable.DashboardItemLayout_dashboard_icon, 0);
        String dashboardTitle = a.getString(R.styleable.DashboardItemLayout_dashboard_title);
        a.recycle();

        // Common
        setClickable(true);
        setRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics()));
        setCardElevation(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()));
        setUseCompatPadding(true);
        //setBackgroundResource(R.drawable.layout_item_border_black);

        // Add to root
        LayoutInflater.from(context).inflate(R.layout.ctrl_dashboard_item, this, true);
        ButterKnife.bind(this, this);
        ivDashboard.setImageResource(dashboardIconId);
        tvDashboard.setText(dashboardTitle);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        setMeasuredDimension(widthSize, 4*widthSize/5);
//    }
}
