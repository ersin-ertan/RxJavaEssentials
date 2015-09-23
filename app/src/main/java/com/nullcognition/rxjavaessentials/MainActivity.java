package com.nullcognition.rxjavaessentials;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import lombok.Getter;
import lombok.Setter;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;

// four main classes:
// Observable, Subjects, Observer, Subscriber

// observable for async, chainability, ease of use, single use or sequences
// onNext(), onError(), onComplete()

// hot and cold observables: hot emits on creation(observer sees portion of sequence), cold emits on a subscription(observer sees whole sequence)
//


public class MainActivity extends AppCompatActivity{

	@Bind(R.id.txtV_hello) TextView txtVHello;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


	}
}


