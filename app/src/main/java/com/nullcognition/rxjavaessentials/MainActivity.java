package com.nullcognition.rxjavaessentials;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

// four main classes:
// Observable, Subjects, Observer, Subscriber

// observable for async, chainability, ease of use, single use or sequences
// onNext(), onError(), onComplete()

// hot and cold observables: hot emits on creation(observer sees portion of sequence), cold emits on a subscription(observer sees whole sequence)
//


public class MainActivity extends AppCompatActivity{

	@OnClick(R.id.btn_recyclerActivity) void recyclerActivity(final View view){
//		startActivity(new Intent(this, RecyclerActivity.class));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
	}
}


