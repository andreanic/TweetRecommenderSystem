package it.keyover.trsserver.tweet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.keyover.trsserver.entity.TwitterUser;

@Repository
public interface TwitterUserRepository extends PagingAndSortingRepository<TwitterUser, Long> {
	public List<TwitterUser> findByVerified(Boolean verified);
	public Optional<TwitterUser> findOneByScreenName(String screenName);
}
