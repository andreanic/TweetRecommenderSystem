package it.keyover.trsserver.lucene.repository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.lucene.controller.LuceneController;
import it.keyover.trsserver.lucene.exception.DirectoryNullException;
import it.keyover.trsserver.lucene.exception.IndexReaderOpenException;
import it.keyover.trsserver.lucene.exception.IndexWriterCreationException;
import it.keyover.trsserver.lucene.exception.OpenDirectoryException;


@Service
public class LuceneRepository implements ILuceneRepository {
	private static final Logger logger = LoggerFactory.getLogger(LuceneController.class);
	private static final String INDEX_PATH = "./index";
	
	@Override
	public Directory getIndexDirectory() throws BaseException {
		Path idxpath = Paths.get(INDEX_PATH);
		Directory directory = null;
		try {
			directory = FSDirectory.open(idxpath);
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new OpenDirectoryException();
		}
		
		return directory;
	}

}
