package it.keyover.trsserver.tweet.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.keyover.trsserver.entity.Hashtag;

@Repository
public interface HashtagRepository extends PagingAndSortingRepository<Hashtag, String> {
	public Optional<Hashtag> findByValue(String value);
}
