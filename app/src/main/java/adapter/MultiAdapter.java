package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zx.sportexam.R;

import java.util.List;

import bean.SimpleGradeBean;

/**
 * Created by zx on 2016/10/12.
 */

public class MultiAdapter extends BaseAdapter {
    List<SimpleGradeBean> list;
    private LayoutInflater inflater;

    public MultiAdapter(Context context,List<SimpleGradeBean> bean){
        this.list = bean;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.multi_item,null);
            viewHolder.tvName = (TextView) view.findViewById(R.id.id_multi_name);
            viewHolder.tvNo = (TextView) view.findViewById(R.id.id_multi_num);
            viewHolder.tvDate = (TextView)view.findViewById(R.id.id_multi_time);
            viewHolder.tvScore = (TextView) view.findViewById(R.id.id_multi_grade);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        SimpleGradeBean bean = list.get(i);
        viewHolder.tvName.setText(bean.getName());
        viewHolder.tvNo.setText(bean.getNo());
        viewHolder.tvDate.setText(bean.getDate());
        viewHolder.tvScore.setText(bean.getScore());
        return view;
    }

    class ViewHolder{
        public TextView tvName,tvNo,tvDate,tvScore;
    }
}
