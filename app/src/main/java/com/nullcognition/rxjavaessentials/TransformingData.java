package com.nullcognition.rxjavaessentials;
// ersin 29/09/15 Copyright (c) 2015+ All rights reserved.


public class TransformingData{

	// map one value to another by applying a function to it

	// flatmap is to take an emitted observable, which was the value of a source observable, and to
	// turn it into a value itself instead of an observable

	// concatmap, flatmap is interleaved, concat appends streams sequentially

	// FlatMapIterable - like flatmap, but pairing is with source and iterables instead of source and observables

	// switchMap - like flatmap, but dynamically unsubbs and subs to the stream which has the newest data

	// scan - accumulator, applying a function to every item emitted and injecting the computed value into the next
	// emission to be an argument in the function

	// groupBy - given a criteria, will group like objects together

	// buffer - can buffer results of the stream into lists, basesd on counts, skips, time intervals

	// window - similar to buffer but emitting Observables instead of lists
}
