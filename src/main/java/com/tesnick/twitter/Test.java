package com.tesnick.twitter;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class Test {

    public static void main(String[] args) {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey("")
                .setOAuthConsumerSecret("")
                .setOAuthAccessToken("")
                .setOAuthAccessTokenSecret("");

        TwitterStream twitterStream = new TwitterStreamFactory(cb.build())
                .getInstance();

        StatusListener listener = new StatusListener() {
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }

            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };

        twitterStream.addListener(listener);

        FilterQuery filtre = new FilterQuery();
        String[] keywordsArray = { "obama" };
        filtre.track(keywordsArray);
        twitterStream.filter(filtre);

//        long[] users = new long[]{someid,someotherid,otherid};
//        twitterStream.addListener(listener);
//        FilterQuery filter = new FilterQuery();
//        filtre.follow(users);
//        twitterStream.filter(filtre);

//        Twitter twitter = new TwitterFactory().getInstance();
//        User user = null; //tw is your Twitter variable from twitter4j.Twitter tw=tf.getInstance();
//        try {
//            user = twitter.showUser("barackobama");
//            long id = user.getId();
//        } catch (TwitterException e) {
//            e.printStackTrace();
//        }

    }
}
