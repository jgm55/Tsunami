package com.tsunami;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NavDrawerAdapter extends ArrayAdapter<String> {
	private Context context;
	private String[] topViews;
	private int[] topViewImages = { R.drawable.debug_icon,
			R.drawable.stats_icon, R.drawable.tsunamis_icon };

	public NavDrawerAdapter(Context context, String[] topViews) {
		super(context, R.layout.drawer_list_item, topViews);
		this.context = context;
		this.topViews = topViews;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		RowData data = null;

		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.drawer_list_item, parent, false);

			data = new RowData();
			data.image = (ImageView) row.findViewById(R.id.drawer_image);
			data.text = (TextView) row.findViewById(R.id.drawer_text);

			row.setTag(data);
		} else {
			data = (RowData) row.getTag();
		}
		
		data.text.setText(topViews[position]);
		data.image.setImageResource(topViewImages[position]);

		return row;
	}

	static class RowData {
		ImageView image;
		TextView text;
	}

}
