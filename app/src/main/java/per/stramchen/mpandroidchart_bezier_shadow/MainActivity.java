package per.stramchen.mpandroidchart_bezier_shadow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chart = findViewById(R.id.chart);
        initChart();
        List<StatisticsBean> statisticsBeans = new ArrayList<>();
        StatisticsBean statisticsBean = new StatisticsBean();
        statisticsBean.setmValue(2);
        StatisticsBean statisticsBean2 = new StatisticsBean();
        statisticsBean2.setmValue(3);
        StatisticsBean statisticsBean3 = new StatisticsBean();
        statisticsBean3.setmValue(2);
        StatisticsBean statisticsBean4 = new StatisticsBean();
        statisticsBean4.setmValue(3);
        statisticsBeans.add(statisticsBean);
        statisticsBeans.add(statisticsBean2);
        statisticsBeans.add(statisticsBean3);
        statisticsBeans.add(statisticsBean4);
        setChartData(statisticsBeans);
    }

    private void initChart() {
        chart.setDrawGridBackground(false);
        chart.getDescription().setEnabled(false);
        chart.setDrawBorders(false);
        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);
        //禁止通过双击缩放图表
        chart.setDoubleTapToZoomEnabled(false);
        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                return false;
            }
        });
        chart.setScaleEnabled(true);
        chart.setNoDataText("无数据");
        //禁止X轴缩放
        chart.setScaleXEnabled(false);
        //禁止Y轴缩放
        chart.setScaleYEnabled(false);
        chart.setTouchEnabled(true);
        //X轴
        chart.getXAxis().setGranularityEnabled(true);//使用默认最小刻度1
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setLabelCount(5, true);
        chart.getXAxis().setDrawAxisLine(false);
        chart.getXAxis().setTextColor(Color.parseColor("#AAAAAA"));
        chart.getXAxis().setDrawGridLines(false);

        //Y轴左边
        chart.getAxisLeft().setDrawAxisLine(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisLeft().setSpaceTop(1f);
        chart.getAxisLeft().setGranularity(0.1f);
        chart.getAxisLeft().setLabelCount(5, true);
        chart.getAxisLeft().setTextColor(Color.parseColor("#AAAAAA"));

        //Y轴右边
        chart.getAxisRight().setEnabled(false);
        chart.getLegend().setEnabled(false);
    }

    private void setChartData(List<StatisticsBean> amountModels) {
        chart.resetTracking();
        chart.getXAxis().setLabelCount(5, true);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < amountModels.size(); i++) {
            StatisticsBean amountModel = amountModels.get(i);
            int value = (int) amountModel.getmValue();
            BarEntry barEntry = new BarEntry(i, value);
            values.add(barEntry);
        }
        chart.getAxisLeft().setAxisMaximum(10);
        chart.getAxisLeft().setAxisMinimum(0);
        LineDataSet dataSet1 = new LineDataSet(values, "数据");
        dataSet1.setColor(Color.parseColor("#00B2DE"));
        dataSet1.setDrawFilled(true);
        //设置包括的范围区域填充颜色
        if (Utils.getSDKInt() >= 18) {
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.gradient_chart_humidity_00dfde);
            dataSet1.setFillDrawable(drawable);
        } else {
            dataSet1.setFillColor(Color.parseColor("#00B2DE"));
        }
        //设置线的宽度
        dataSet1.setLineWidth(0.8f);
        //图中Y轴数据圆点
        dataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet1.setDrawCircles(true);
        dataSet1.setCircleHoleRadius(2);
        dataSet1.setCircleRadius(4);
        dataSet1.setDrawShadow(true);
        dataSet1.setShadowColor(Color.parseColor("#8000B2DE"));
        dataSet1.setCircleColor(Color.parseColor("#00B2DE"));
        dataSet1.setDrawHighlightIndicators(false);
        dataSets.add(dataSet1);
        LineData lineData = new LineData(dataSets);
        //设置单个图顶部不显示y轴的数值
        lineData.setDrawValues(false);
        lineData.setHighlightEnabled(true);
        chart.setData(lineData);

    }

    public class StatisticsBean {

        private double mValue;


        public double getmValue() {
            return mValue;
        }

        public void setmValue(double mValue) {
            this.mValue = mValue;
        }
    }
}

