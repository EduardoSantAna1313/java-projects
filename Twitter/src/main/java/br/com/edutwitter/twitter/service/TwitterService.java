/**
 *
 */
package br.com.edutwitter.twitter.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.github.redouane59.twitter.TwitterClient;
import com.github.redouane59.twitter.dto.tweet.Tweet;
import com.github.redouane59.twitter.signature.TwitterCredentials;

/**
 * @author Eduardo
 */
public class TwitterService {

	private final Authentication auth;

	private TwitterClient client;

	public TwitterService(final Authentication auth) {
		this.auth = auth;
	}

	public List<Tweet> listByUser(final String userName) throws Exception {

		final TwitterClient twitterClient = getClient();

		final var user = twitterClient.getUserFromUserName(userName);

		final var id = user.getId();

		return twitterClient.getUserTimeline(id, 10);
	}

	public Tweet get(final String id) throws Exception {

		final TwitterClient twitterClient = getClient();

		return twitterClient.getTweet(id);
	}

	private TwitterClient getClient() throws FileNotFoundException, IOException {

		if (client == null) {
			client = new TwitterClient(TwitterCredentials.builder().accessToken(auth.getAccessToken())
					.accessTokenSecret(auth.getAccessTokenSecret()).apiKey(auth.getApiKey())
					.apiSecretKey(auth.getApiSecretKey()).build());
		}

		return client;
	}

}
