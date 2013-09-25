package com.vnu.androtweet.adapters;

import java.util.Date;
import java.util.List;

import android.content.Context;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.vnu.androtweet.R;
import com.vnu.androtweet.models.Tweet;
import com.vnu.androtweet.models.User;

public class TweetsAdapter  extends ArrayAdapter<Tweet>{


	public TweetsAdapter(Context context, List<Tweet> tweets) {
		super(context, 0, tweets);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    View view = convertView;
	    if (view == null) {
	    	LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    	view = inflater.inflate(R.layout.tweet_item, null);
	    }
	     
        Tweet tweet = getItem(position);
        User user = tweet.getUser();
        
        ImageView imageView = (ImageView) view.findViewById(R.id.ivProfilePic);
        ImageLoader.getInstance().displayImage(user.getProfileImgUrl(), imageView);
        
        imageView.setTag(user);
        
        TextView nameView = (TextView) view.findViewById(R.id.tvName);
        String formattedName = "<b>" + user.getName() + "</b>" + " <small><font color='#777777'>@" +
        		user.getScreenName() + "</font></small>";
        nameView.setText(Html.fromHtml(formattedName));

        TextView bodyView = (TextView) view.findViewById(R.id.tvBody);
        bodyView.setText(Html.fromHtml(tweet.getBody()));
        
        TextView tvCreatedAt = (TextView) view.findViewById(R.id.tvCreatedAt);
        long tweetCreatedAt =  tweet.getCreatedAt().getTime();
        long currentTime = new Date().getTime(); 
   
        String diff = (String) DateUtils.getRelativeTimeSpanString(tweetCreatedAt, currentTime, 0, DateUtils.FORMAT_ABBREV_RELATIVE);
        
        tvCreatedAt.setText(diff);
        
        return view;
	}

}
