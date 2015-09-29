package com.nullcognition.rxjavaessentials;
// ersin 29/09/15 Copyright (c) 2015+ All rights reserved.


public class CombiningObservables{

	// combining functions of multiple observables to create a single one

	// merge - to observables into one, interleaving emissions, unless merged synchronously
	// mergeDelayError - will merge and continue emissions even if one stream onErrors

	// zip - taking two observables streams and a function and combining the streams as a 1:1, waiting in time
	// until values are pair to emit thus stream1 will wait for stream2s output to produce a single emission on stream3

	// join - join() function combines items from two Observables, working with time windows. thus emissions from stream1
	// will wait until stream2 emits, combining the values via a function, or waits until the next time window starts, thus
	// waiting for a stream1 and stream2 emissions

	// combineLatest - stream1 will work with stream2s lastest values and vice versa, thus multiple emissions from a stream
	// will combine with the same value of the other stream if it has not yet changed

	// and, then, when, - part of joins package, to combine based on patterns and plans

	// switch - when a stream produces a new type of observable, subscribe to it and unsubb from the last one

	// startWith - prior to the stream producing results, will start the stream with preset values
}
