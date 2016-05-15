package com.csx.googleplay.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.csx.googleplay.R;
import com.csx.googleplay.domain.CategoryInfo;
import com.csx.googleplay.utils.LogUtils;
import com.csx.googleplay.utils.UIUtils;


/**
 * 分类模块标题holder
 * 
 * @author csx
 */
public class TitleHolder extends BaseHolder<CategoryInfo> {

	public TextView tvTitle;

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.list_item_title);
		tvTitle = (TextView) view.findViewById(R.id.tv_title);
		return view;
	}

	@Override
	public void refreshView(CategoryInfo data) {
		LogUtils.v("设置标题");
		tvTitle.setText(data.title);
	}

}
