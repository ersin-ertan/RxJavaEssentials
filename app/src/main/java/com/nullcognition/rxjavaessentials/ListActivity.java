package com.nullcognition.rxjavaessentials;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;

public class ListActivity extends AppCompatActivity{

	public static final String TAG = ListActivity.class.getSimpleName();

	List<String> list = Arrays.asList("a", "o", "e", "u", "i", "d", "h", "t", "n", "s"
			, "o", "e", "u", "i", "d", "h", "t", "n", "s"
			, "o", "e", "u", "i", "d", "h", "t", "n", "s"
			, "o", "e", "u", "i", "d", "h", "t", "n", "s"
			, "o", "e", "u", "i", "d", "h", "t", "n", "s");

	List<String> listSubset = Arrays.asList("a", "o", "e", "u", "i", "d", "h", "t", "n", "s");


	StringAdapter adapter;

	@Bind(R.id.listView)     RecyclerView       recyclerView;
	@Bind(R.id.swipe_layout) SwipeRefreshLayout swipeRefreshLayout;

	@Override protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		ButterKnife.bind(this);
		swipeRefreshLayout.setOnRefreshListener(this::refreshList);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		adapter = new StringAdapter(list, android.R.layout.simple_list_item_1);
		recyclerView.setAdapter(adapter);
		swipeRefreshLayout.setRefreshing(true);

		//					refreshTheList();

//			getFileDir()
//				.subscribeOn(Schedulers.io())
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribe(file -> {
//					mFilesDir = file;
//					refreshTheList();
//				});
	}

//	private Observable<File> getFileDir() {
//		return Observable.create(subscriber -> {
//			subscriber.onNext(App.instance.getFilesDir());
//			subscriber.onCompleted();
//		});
//	}

	private void refreshList(){


		getStrings()
				.toSortedList()
//				.repeat(4) // four sets of the the same list
//				.just(listSubset) // would return just the subset
				.subscribe(new Observer<List<String>>(){
					@Override public void onCompleted(){ Log.d(TAG, "refresh onCompleted");}
					@Override public void onError(final Throwable e){ Log.d(TAG, "refresh onError");}
					@Override public void onNext(final List<String> strings){
						Log.d(TAG, "refresh onNext");
						adapter.addStrings(strings);
						swipeRefreshLayout.setRefreshing(false);
//				            storeList(appInfos);
					}
				});
	}

//	private void storeList(List<AppInfo> appInfos){
//		ApplicationsList.getInstance().setList(appInfos);
//
//		Schedulers.io().createWorker().schedule(() -> {
//			SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//			Type appInfoType = new TypeToken<List<AppInfo>>(){
//			}.getType();
//			sharedPref.edit().putString("APPS", new Gson().toJson(appInfos, appInfoType)).apply();
//		});
//	}

	// instead of creating the list of stings, use .from() to get the list from an external source, then
	// is transformed into an Observable
	private Observable<String> getStrings(){

		// Observable.defer(this::getStrings); // creates list when upon first subscription
		// range() emit N integers from a specific starting number X
		// Observable.interval(3,  TimeUnit.SECONDS) good for polling
		// Observable.timer(3, 3, TimeUnit.SECONDS) emit after a time
		// Sample emit the most recent items emitted by an Observable within periodic time intervals
		// debounce will emit only one per interval regardless of the number of inputs received, thus is
		// good in limiting button clicks for people who spam

		return Observable.create(subscriber -> {
			List<String> strings = new ArrayList<String>(10);

			for(String s : list){ strings.add(s);} // get the data from the source

			for(String s : strings){
				if(subscriber.isUnsubscribed()){return;} // return if unsubbed
				subscriber.onNext(s); // onNext each item
			}

			if(!subscriber.isUnsubscribed()){ subscriber.onCompleted(); }
			// when there are no more items send onComplete
		});
	}
}


class StringAdapter extends RecyclerView.Adapter<StringAdapter.ViewHolder>{

	private List<String> mApplications;

	private int rowLayout;

	public StringAdapter(List<String> strings, int rowLayout){
		mApplications = strings;
		this.rowLayout = rowLayout;
	}

	public void addStrings(List<String> applications){
		mApplications.clear();
		mApplications.addAll(applications);
		notifyDataSetChanged();
	}

	public void addStrings(int position, String strings){
		if(position < 0){position = 0;}
		mApplications.add(position, strings);
		notifyItemInserted(position);
	}

	@Override
	public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i){
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, int i){
		final String string = mApplications.get(i);
		viewHolder.name.setText(string);
//		getBitmap(string.getIcon())
//				.subscribeOn(Schedulers.io())
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribe(viewHolder.image::setImageBitmap);
	}

	@Override
	public int getItemCount(){
		return mApplications == null ? 0 : mApplications.size();
	}

//	private Observable<Bitmap> getBitmap(String icon){
//		return Observable.create(subscriber -> {
//			subscriber.onNext(BitmapFactory.decodeFile(icon));
//			subscriber.onCompleted();
//		});
//	}


	public static class ViewHolder extends RecyclerView.ViewHolder{

		public TextView name;

		public ViewHolder(View itemView){
			super(itemView);
			name = (TextView) itemView.findViewById(android.R.id.text1);
		}
	}
}
