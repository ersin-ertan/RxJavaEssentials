package com.nullcognition.rxjavaessentials.base_examples;
// ersin 23/09/15 Copyright (c) 2015+ All rights reserved.


import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

public class Subjects{

// both observable and object - four different types:
//	publish, behavior, replay, async

	public void publish(){

		PublishSubject<Integer> publishSubject = PublishSubject.create();

		Subscription subscription = publishSubject.subscribe(new Observer<Integer>(){
			@Override public void onCompleted(){ }
			@Override public void onError(final Throwable e){ }
			@Override public void onNext(final Integer integer){/*Print message here*/ }
		});
		publishSubject.onNext(2);
	}

	public void publishPrivateObservable(){

		// can be connected to observables and be observed at the same time

		final PublishSubject<Boolean> publishSubject = PublishSubject.create();

		publishSubject.subscribe(new Observer<Boolean>(){
			@Override public void onCompleted(){ }
			@Override public void onError(final Throwable e){ }
			@Override public void onNext(final Boolean aBoolean){/*print completed*/ }
		});

		Observable.create(subscriber -> {
			for(int i = 0; i < 5; i++){subscriber.onNext(i);}
			subscriber.onCompleted();
		}).doOnCompleted(() -> publishSubject.onNext(true)).subscribe();

		// or

//		doOnCompleted(new Action0(){
//			@Override
//			public void call(){
//				subject.onNext(true);
//			}
//		}).subscribe();
	}

	public void behavior(){

		// emits most recent item observed, and all subsequent items observed to each subscribe item

		BehaviorSubject<Integer> behaviorSubject = BehaviorSubject.create(1); // default value, when an observer subscribes it will see 1(the most recent item)
	}

	public void replay(){

		// buffers all items observed, and replays them to a subscribing observer

		ReplaySubject<Integer> replaySubject = ReplaySubject.create();
	}

	public void async(){

		// publishes the last item observed upon observables completion

		AsyncSubject asyncSubject = AsyncSubject.create();
	}



}
