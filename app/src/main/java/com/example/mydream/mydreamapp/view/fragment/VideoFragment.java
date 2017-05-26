package com.example.mydream.mydreamapp.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydream.mydreamapp.R;

import butterknife.ButterKnife;


public class VideoFragment extends Fragment{
	private View videoView;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		if (videoView == null) {
			videoView = inflater.inflate(R.layout.video_fragment, container, false);
			ButterKnife.bind(this, videoView);
			initview();
		}
		// 缓存的viewiew需要判断是否已经被加过parent，
		// 如果有parent需要从parent删除，要不然会发生这个view已经有parent的错误。
		ViewGroup parent = (ViewGroup) videoView.getParent();
		if (parent != null) {
			parent.removeView(videoView);
		}



		return videoView;
	}

	private void initview() {




	}
}