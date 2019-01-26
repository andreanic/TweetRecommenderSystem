package it.keyover.trsserver.lucene.repository;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;

import it.keyover.trsserver.exception.BaseException;

public interface ILuceneRepository {
	public Directory getIndexDirectory() throws BaseException;
}
