package com.csx.googleplay.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

import com.csx.googleplay.R;
import com.csx.googleplay.domain.AppInfo;
import com.csx.googleplay.ui.holder.DetailAppInfoHolder;
import com.csx.googleplay.ui.holder.DetailDesHolder;
import com.csx.googleplay.ui.holder.DetailDownloadHolder;
import com.csx.googleplay.ui.holder.DetailPicsHolder;
import com.csx.googleplay.ui.holder.DetailSafeHolder;
import com.csx.googleplay.ui.http.protocol.HomeDetailProtocol;
import com.csx.googleplay.ui.view.LoadingPage;
import com.csx.googleplay.utils.UIUtils;


/**
 * 首页应用详情页
 * 
 * @author csx
 */
public class HomeDetailActivity extends BaseActivity {

	private LoadingPage mLoadingPage;
	private String packageName;
	private AppInfo data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mLoadingPage = new LoadingPage(this) {

			@Override
			public ResultState onLoad() {
				return HomeDetailActivity.this.onLoad();
			}

			@Override
			public View onCreateSuccessView() {
				return HomeDetailActivity.this.onCreateSuccessView();
			}
		};

		// setContentView(R.layout.activity_main);
		setContentView(mLoadingPage);// 直接将一个view对象设置给activity

		// 获取从HomeFragment传递过来的包名
		packageName = getIntent().getStringExtra("packageName");

		// 开始加载网络数据
		mLoadingPage.loadData();

		initActionbar();
	}

	// 初始化actionbar
	private void initActionbar() {
		ActionBar actionbar = getSupportActionBar();
		// actionbar.setHomeButtonEnabled(true);// home处可以点击
		actionbar.setDisplayHomeAsUpEnabled(true);// 显示左上角返回键,当和侧边栏结合时展示三个杠图片
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				break;

			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	public View onCreateSuccessView() {
		// 初始化成功的布局
		View view = UIUtils.inflate(R.layout.page_home_detail);

		// 初始化应用信息模块
		FrameLayout flDetailAppInfo = (FrameLayout) view
				.findViewById(R.id.fl_detail_appinfo);
		// 动态给帧布局填充页面
		DetailAppInfoHolder appInfoHolder = new DetailAppInfoHolder();
		flDetailAppInfo.addView(appInfoHolder.getRootView());
		appInfoHolder.setData(data);

		// 初始化安全描述模块
		FrameLayout flDetailSafe = (FrameLayout) view
				.findViewById(R.id.fl_detail_safe);
		DetailSafeHolder safeHolder = new DetailSafeHolder();
		flDetailSafe.addView(safeHolder.getRootView());
		safeHolder.setData(data);

		// 初始化截图模块
		HorizontalScrollView hsvPic = (HorizontalScrollView) view
				.findViewById(R.id.hsv_detail_pics);
		DetailPicsHolder picsHolder = new DetailPicsHolder();
		hsvPic.addView(picsHolder.getRootView());
		picsHolder.setData(data);

		// 初始化描述模块
		FrameLayout flDetailDes = (FrameLayout) view
				.findViewById(R.id.fl_detail_des);
		DetailDesHolder desHolder = new DetailDesHolder();
		flDetailDes.addView(desHolder.getRootView());
		desHolder.setData(data);

		// getIntent().getSerializableExtra("list");

		// 初始化下载模块
		FrameLayout flDetailDownload = (FrameLayout) view
				.findViewById(R.id.fl_detail_download);
		DetailDownloadHolder downloadHolder = new DetailDownloadHolder();
		flDetailDownload.addView(downloadHolder.getRootView());
		downloadHolder.setData(data);

		return view;
	}

	public LoadingPage.ResultState onLoad() {
		// 请求网络,加载数据
		HomeDetailProtocol protocol = new HomeDetailProtocol(packageName);
		data = protocol.getData(0);

		if (data != null) {
			return LoadingPage.ResultState.STATE_SUCCESS;
		} else {
			return LoadingPage.ResultState.STATE_ERROR;
		}
	}


}
