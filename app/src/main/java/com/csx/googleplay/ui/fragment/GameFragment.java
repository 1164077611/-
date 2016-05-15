package com.csx.googleplay.ui.fragment;

import com.csx.googleplay.ui.view.LoadingPage.ResultState;

import android.view.View;

/**
 * 游戏
 * @author csx
 */
public class GameFragment extends BaseFragment {

	@Override
	public View onCreateSuccessView() {
		return null;
	}

	@Override
	public ResultState onLoad() {
		return ResultState.STATE_EMPTY;
	}

}
