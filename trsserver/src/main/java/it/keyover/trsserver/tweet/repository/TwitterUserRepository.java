package it.keyover.trsserver.tweet.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.keyover.trsserver.entity.TwitterUser;

@Repository
public interface TwitterUserRepository extends PagingAndSortingRepository<TwitterUser, Long> {

}
