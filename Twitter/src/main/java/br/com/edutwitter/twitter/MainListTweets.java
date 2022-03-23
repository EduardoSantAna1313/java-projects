package br.com.edutwitter.twitter;

import br.com.edutwitter.twitter.service.Authentication;
import br.com.edutwitter.twitter.service.TwitterService;

/**
 * Class para ler tweets com twittered.
 *
 * @author Eduardo
 */
public class MainListTweets {

	public static void main(final String[] args) throws Exception {

		final TwitterService service = new TwitterService(Authentication.getDefault());

		final var listTweets = service.listByUser("jack");
		listTweets.forEach(t -> System.out.println(t.getText()));
	}

}
