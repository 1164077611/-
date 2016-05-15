package com.csx.googleplay.ui.fragment;

import com.csx.googleplay.domain.AppInfo;
import com.csx.googleplay.ui.adapter.MyBaseAdapter;
import com.csx.googleplay.ui.holder.AppHolder;
import com.csx.googleplay.ui.holder.BaseHolder;
import com.csx.googleplay.ui.http.protocol.AppProtocol;
import com.csx.googleplay.ui.view.LoadingPage.ResultState;
import com.csx.googleplay.ui.view.MyListView;
import com.csx.googleplay.utils.UIUtils;

import android.view.View;

import java.util.ArrayList;

/**
 * 应用
 * @author csx
 */
public class AppFragment extends BaseFragment {

	private ArrayList<AppInfo> data;

	// 只有成功才走此方法
	@Override
	public View onCreateSuccessView() {
		MyListView view = new MyListView(UIUtils.getContext());
		view.setAdapter(new AppAdapter(data));
		return view;
	}

	@Override
	public ResultState onLoad() {
		AppProtocol protocol = new AppProtocol();
		data = protocol.getData(0);
		return check(data);
	}

	class AppAdapter extends MyBaseAdapter<AppInfo> {

		public AppAdapter(ArrayList<AppInfo> data) {
			super(data);
		}



		@Override
		public BaseHolder<AppInfo> getHolder(int position) {
			return new AppHolder();
		}

		@Override
		public ArrayList<AppInfo> onLoadMore() {
			AppProtocol protocol = new AppProtocol();
			ArrayList<AppInfo> moreData = protocol.getData(getListSize());
			return moreData;
		}

	}

}
