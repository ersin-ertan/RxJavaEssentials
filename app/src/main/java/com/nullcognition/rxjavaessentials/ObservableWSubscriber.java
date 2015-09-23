package com.nullcognition.rxjavaessentials;
// ersin 23/09/15 Copyright (c) 2015+ All rights reserved.


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;

public class ObservableWSubscriber{


	public void create(){

		// OnSubscribe extends Action1
		Observable.create(new Observable.OnSubscribe<Integer>(){
			@Override public void call(final Subscriber<? super Integer> subscriber){ int i; }
		});

		// or

		Observable<Integer> integerObservable =
				Observable.create( // this is the important lines
						subscriber -> {

							for(int i = 0; i < 5; i++){
								subscriber.onNext(i);
								try{ Thread.sleep(500);}
								catch(InterruptedException e){ e.printStackTrace(); }
							}

							subscriber.onCompleted();
						}
				);

		Subscription subscription = integerObservable.subscribe(new Observer<Integer>(){
			@Override public void onCompleted(){ }
			@Override public void onError(final Throwable e){ }
			@Override public void onNext(final Integer integer){ }
		});
	}

	public void from(){

		List<Integer> items = new ArrayList<Integer>();
		items.add(1);
		items.add(10);
		items.add(100);
		items.add(200);

		Observable<Integer> integerObservable = Observable.from(items); // this is the important line
		// emits from a list/array or future via the .get(), future parameters may also use a timeout to onError

		Subscription subscription = integerObservable.subscribe(new Observer<Integer>(){
			@Override public void onCompleted(){ }
			@Override public void onError(final Throwable e){}
			@Override public void onNext(final Integer integer){ }
		});
	}

	public void just(){

		Observable<String> stringObservable = Observable.just(returnsAString()); // may take up to 9 parameters
// may also take a list/array displayed sequentially, like hard coding input

		Subscription subscription = stringObservable.subscribe(new Observer<String>(){
			@Override public void onCompleted(){ }
			@Override public void onError(final Throwable e){ }
			@Override public void onNext(final String s){ }
		});
	}

	private String returnsAString(){
		return "string";
	}

	public void emptyNeverThrow(){
		Observable.empty(); // emit nothing, terminate normally
		Observable.never(); // emit nothing, never terminate
//		Observable.throw; // emit nothing, terminate onError

		// see error handling https://github.com/ReactiveX/RxJava/wiki/Error-Handling-Operators
	}
}
