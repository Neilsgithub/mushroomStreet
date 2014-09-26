package com.tony.mushrommstreet.activity;

/**
 * @author TonyWang
 * @time 2014 8.31
 * */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.mushroomstreet.R;
import com.tony.mushrommstreet.fragment.CollocationFragment;
import com.tony.mushrommstreet.fragment.FavoriteFragment;
import com.tony.mushrommstreet.fragment.MessageFragment;
import com.tony.mushrommstreet.fragment.MineFragment;
import com.tony.mushrommstreet.fragment.SearchFragment;
import com.tony.mushroomstreet.AppConfig;

/**
 * 
 * ��Ӧ������ҳ�涼û���õ���������
 * �������Ҫ������ֱ��ʹ��volley jar�� ������� ���ݣ�����adapter���resetData����������ͼ����
 * ����������̾�û��д�������������
 * 
 * */

/** ������ʾ��ҳ�� */
public class MainActivity extends BaseActivity implements
		OnCheckedChangeListener {

	private RadioGroup radioGroup;
	/**
	 * 1��ʾFavoriteFragment 2��ʾSearchFragment 3��ʾCollocationFragment
	 * 4��ʾMessageFragment 5��ʾMineFragment
	 * */
	private int whichFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		AppConfig.initConfig(this);
		initActionBar();
		initView();
		initListener();
	}

	public void initView() {
		leftIcon.setVisibility(View.VISIBLE);
		rightIcon.setVisibility(View.VISIBLE);
		radioGroup = (RadioGroup) findViewById(R.id.bottom_menu);
		radioGroup.check(R.id.favorite);
		redirectTo(FavoriteFragment.getInstance());
		whichFragment = 1;
	}

	public void initListener() {
		radioGroup.setOnCheckedChangeListener(this);
	}
	
	public void setChildActionBar(int what) {
		switch (what) {
		case 0:
			leftIcon.setVisibility(View.GONE);
			title.setVisibility(View.GONE);
			rightIcon.setVisibility(View.VISIBLE);
			break;
		case 1:
			leftIcon.setVisibility(View.GONE);
			rightIcon.setVisibility(View.VISIBLE);
			title.setVisibility(View.VISIBLE);
			title.setText("����");
			break;
		case 2:
			leftIcon.setVisibility(View.GONE);
			title.setText("����");
			title.setVisibility(View.VISIBLE);
			rightIcon.setVisibility(View.VISIBLE);
			break;
		case 3:
			leftIcon.setVisibility(View.GONE);
			title.setText("�����ϵ��");
			title.setVisibility(View.VISIBLE);
			rightIcon.setVisibility(View.VISIBLE);
			break;
		case 4:
			leftIcon.setImageResource(R.drawable.title_icon_set);
			title.setVisibility(View.GONE);
			rightIcon.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
	
	public void redirectTo(Fragment fragment) {
		FragmentTransaction beginTransaction = getSupportFragmentManager()
				.beginTransaction();
		beginTransaction.replace(R.id.fragment_container, fragment)
				.commitAllowingStateLoss();
	}

	public void switchContent(Fragment from, Fragment to) {
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		if (!to.isAdded()) { // ���ж��Ƿ�add��
			transaction.hide(from).add(R.id.fragment_container, to).commit(); // ���ص�ǰ��fragment��add��һ����Activity��
		} else {
			transaction.hide(from).show(to).commit(); // ���ص�ǰ��fragment����ʾ��һ��
		}
	}
	
	
	/**����ʹ��redirectTo�л�fragment�ᵼ��fragment�л�ʱ�����ݶ�ʧ
	 * ��Ϊfragment��replace���ˣ����Ը���switchContent�л�fragment
	 * 9.14
	 * switchContent�����actionBar title�쳣����ʱ����redirecTo
	 * 9.19
	 * actionBarTitle ������
	 * 9.26
	 * */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.favorite:
			setChildActionBar(0);
			switch (whichFragment) {
			case 2:
				switchContent(SearchFragment.getInstance(),
						FavoriteFragment.getInstance());
				break;
			case 3:
				switchContent(MessageFragment.getInstance(),
						FavoriteFragment.getInstance());
				break;
			case 4:
				switchContent(MessageFragment.getInstance(),
						FavoriteFragment.getInstance());
				break;
			case 5:
				switchContent(MineFragment.getInstance(),
						FavoriteFragment.getInstance());
				break;
			}
			whichFragment = 1;
			break;
		case R.id.category:
			setChildActionBar(1);
			switch (whichFragment) {
			case 1:
				switchContent(FavoriteFragment.getInstance(),
						SearchFragment.getInstance());
				break;
			case 3:
				switchContent(CollocationFragment.getInstance(),
						SearchFragment.getInstance());
				break;
			case 4:
				switchContent(MessageFragment.getInstance(),
						SearchFragment.getInstance());
				break;
			case 5:
				switchContent(MineFragment.getInstance(),
						SearchFragment.getInstance());
				break;
			}
			whichFragment = 2;
			break;
		case R.id.collocation:
			setChildActionBar(2);
			switch (whichFragment) {
			case 1:
				switchContent(FavoriteFragment.getInstance(),
						CollocationFragment.getInstance());
				break;
			case 2:
				switchContent(SearchFragment.getInstance(),
						CollocationFragment.getInstance());
				break;
			case 4:
				switchContent(MessageFragment.getInstance(),
						CollocationFragment.getInstance());
				break;
			case 5:
				switchContent(MineFragment.getInstance(),
						CollocationFragment.getInstance());
				break;
			}
			whichFragment = 3;
			break;
		case R.id.message:
			setChildActionBar(3);
			switch (whichFragment) {
			case 1:
				switchContent(FavoriteFragment.getInstance(),
						MessageFragment.getInstance());
				break;
			case 2:
				switchContent(SearchFragment.getInstance(),
						MessageFragment.getInstance());
				break;
			case 3:
				switchContent(CollocationFragment.getInstance(),
						MessageFragment.getInstance());
				break;
			case 5:
				switchContent(MineFragment.getInstance(),
						MessageFragment.getInstance());
				break;
			}
			whichFragment = 4;
			break;
		case R.id.mine:
			setChildActionBar(4);
			switch (whichFragment) {
			case 1:
				switchContent(FavoriteFragment.getInstance(),
						MineFragment.getInstance());
				break;
			case 2:
				switchContent(SearchFragment.getInstance(),
						MineFragment.getInstance());
				break;
			case 3:
				switchContent(CollocationFragment.getInstance(),
						MineFragment.getInstance());
				break;
			case 4:
				switchContent(MessageFragment.getInstance(),
						MineFragment.getInstance());
				break;
			}
			whichFragment = 5;
			break;
		default:
			break;
		}
		
		
	/*
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.favorite:
			redirectTo(FavoriteFragment.getInstance());
			break;
		case R.id.category:
			redirectTo(SearchFragment.getInstance());
			break;
		case R.id.collocation:
			redirectTo(CollocationFragment.getInstance());
			break;
		case R.id.message:
			redirectTo(MessageFragment.getInstance());
			break;
		case R.id.mine:
			redirectTo(MineFragment.getInstance());
			break;
		default:
			break;
		}*/

	}

}
