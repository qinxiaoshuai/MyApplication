package com.example.mydream.mydreamapp.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydream.mydreamapp.R;

public class MusicFragment extends Fragment{

	private View musicView;



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		if (musicView == null) {
			musicView = inflater.inflate(R.layout.music_fragment, container, false);
			initview();

		}
// 缓存的viewiew需要判断是否已经被加过parent，
// 如果有parent需要从parent删除，要不然会发生这个view已经有parent的错误。
		ViewGroup parent = (ViewGroup) musicView.getParent();
		if (parent != null) {
			parent.removeView(musicView);
		}

		return musicView;

	}




	private void initview() {

	}



}