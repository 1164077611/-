package com.csx.googleplay.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.csx.googleplay.R;
import com.csx.googleplay.domain.SubjectInfo;
import com.csx.googleplay.ui.http.HttpHelper;
import com.csx.googleplay.utils.BitmapHelper;
import com.csx.googleplay.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

/**
 * 专题holder
 * 
 * @author csx
 */
public class SubjectHolder extends BaseHolder<SubjectInfo> {

	private ImageView ivPic;
	private TextView tvTitle;

	private BitmapUtils mBitmapUtils;

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.list_item_subject);
		ivPic = (ImageView) view.findViewById(R.id.iv_pic);
		tvTitle = (TextView) view.findViewById(R.id.tv_title);

		mBitmapUtils = BitmapHelper.getBitmapUtils();

		return view;
	}

	@Override
	public void refreshView(SubjectInfo data) {
		tvTitle.setText(data.des);
		mBitmapUtils.display(ivPic, HttpHelper.URL + "image?name=" + data.url);
	}

}
