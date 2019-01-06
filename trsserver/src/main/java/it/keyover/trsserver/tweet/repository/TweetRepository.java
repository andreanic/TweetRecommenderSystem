package it.keyover.trsserver.tweet.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.keyover.trsserver.entity.Tweet;

@Repository
public interface TweetRepository extends PagingAndSortingRepository<Tweet, Long> {
}
