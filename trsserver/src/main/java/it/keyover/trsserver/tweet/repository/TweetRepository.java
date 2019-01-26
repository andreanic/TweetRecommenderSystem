package it.keyover.trsserver.tweet.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.keyover.trsserver.entity.Tweet;

@Repository
public interface TweetRepository extends PagingAndSortingRepository<Tweet, Long> {
	public List<Tweet> findByTwitteridIn(Collection twitterids);
	public Tweet findFirstByCategory(String category);
}
