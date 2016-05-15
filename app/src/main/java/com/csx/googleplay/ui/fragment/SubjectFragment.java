package com.csx.googleplay.ui.fragment;

import com.csx.googleplay.domain.SubjectInfo;
import com.csx.googleplay.ui.adapter.MyBaseAdapter;
import com.csx.googleplay.ui.holder.BaseHolder;
import com.csx.googleplay.ui.holder.SubjectHolder;
import com.csx.googleplay.ui.http.protocol.SubjectProtocol;
import com.csx.googleplay.ui.view.LoadingPage.ResultState;
import com.csx.googleplay.ui.view.MyListView;
import com.csx.googleplay.utils.UIUtils;

import android.view.View;

import java.util.ArrayList;

/**
 * 专题
 * @author csx
 */
public class SubjectFragment extends BaseFragment {

	private ArrayList<SubjectInfo> data;

	@Override
	public View onCreateSuccessView() {
		MyListView view = new MyListView(UIUtils.getContext());
		view.setAdapter(new SubjectAdapter(data));
		return view;
	}

	@Override
	public ResultState onLoad() {
		SubjectProtocol protocol = new SubjectProtocol();
		data = protocol.getData(0);
		return check(data);
	}

	class SubjectAdapter extends MyBaseAdapter<SubjectInfo> {

		public SubjectAdapter(ArrayList<SubjectInfo> data) {
			super(data);
		}

		@Override
		public BaseHolder<SubjectInfo> getHolder(int position) {
			return new SubjectHolder();
		}

		@Override
		public ArrayList<SubjectInfo> onLoadMore() {
			SubjectProtocol protocol = new SubjectProtocol();
			ArrayList<SubjectInfo> moreData = protocol.getData(getListSize());
			return moreData;
		}

	}

}
