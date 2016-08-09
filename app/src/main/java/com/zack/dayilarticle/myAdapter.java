package com.zack.dayilarticle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Zackv on 2016/8/8.
 */
public class myAdapter extends BaseAdapter {
    private List<ArticleBean> mData;
    private Context mContext;
    private LayoutInflater inflater;

    public myAdapter(List<ArticleBean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    public int getCount() {
        return mData.size();
    }

    public Object getItem(int position) {
        if (mData == null) {
            return null;
        }
        return mData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item, null);

            viewHolder.title = (TextView) convertView.findViewById(R.id.loveArticleTitle);
            viewHolder.author = (TextView) convertView.findViewById(R.id.loveArticleAuthor);

            convertView.setTag(viewHolder);
        }
        else {

            viewHolder= (ViewHolder) convertView.getTag();
        }ArticleBean item=mData.get(position);
        CardView cd = (CardView) convertView.findViewById(R.id.cdView);
        if(item.isSelected()){
            cd.setSelected(true);
        }
        else {
            cd.setSelected(false);
        }




        viewHolder.title.setText(item.getTitle());
        viewHolder.author.setText(item.getAuthor());
        return convertView;
    }


    public static class ViewHolder{

        TextView title;
        TextView author;

    }
}
