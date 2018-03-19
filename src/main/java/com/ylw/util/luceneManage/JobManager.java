package com.ylw.util.luceneManage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
//import com.chenlb.mmseg4j.analysis.MaxWordAnalyzer;
public class JobManager {
    /**

      * 所有 writer 公共配置

      */
	
    private static final Analyzer analyzer = new IKAnalyzer5x(); 
    //private static final Analyzer analyzer =new IKAnalyzer(true);//new StandardAnalyzer(Version.LUCENE_4_9); 
	private static final IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
    public static boolean reCreateIndex=false;
    static {
       iwc .setOpenMode(OpenMode. CREATE_OR_APPEND);
       iwc .setRAMBufferSizeMB(20.0);
       iwc .setMaxBufferedDocs(10000);
    }
    private Directory dir ;
    private IndexReader reader ;
    private IndexSearcher searcher ;
    private IndexWriter writer ;

    private static JobManager indexManager;  
	public static JobManager getInstance(String file, boolean force) {
		if(force){
			indexManager = new JobManager(file);
		}else{
		    if (indexManager == null) {  
		    	indexManager = new JobManager(file);  
		    }
	    }
	    return indexManager;  
	}  
    
    /**
      * 构造函数
      * @param indexPath
      */
    private JobManager(String indexPath) {
       init(new File(indexPath).toPath());
    }

    private void init(Path dirFile) {
      try {
           dir = FSDirectory.open(dirFile);
           // 根据 Directory 对象，初始化 indexReader 对象
           ReaderManager. getInstance ().createIndexReader( dir );
           // 初始化 writer 对象
        	 writer = new IndexWriter( dir , iwc );
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    public IndexSearcher getSearcher() {
       DirectoryReader ireader;
	try {
		ireader = DirectoryReader.open(dir);
		searcher = new IndexSearcher(ireader);
       if ( reader == null || reader != ireader)
       {
           reader = ireader;
           searcher = new IndexSearcher( reader );
       }
       return searcher ;
	} catch (IOException e) {
		e.printStackTrace();
	}
	return null;
    }
    
    public IndexWriter getWriter() {
       return writer ;
    }
   
    public void commitWriter()
    {
       try {
           writer .commit();
       } catch (IOException e) {
    	   e.printStackTrace();
    	   rollbackWriter();
       }
    }
    public void rollbackWriter() {
       try {
           writer .rollback();
       } catch (IOException e1) {
           e1.printStackTrace();
       }
    }
    
    public Analyzer getAnalyzer()
    {
    	return analyzer;
    }
    public static FieldType getFieldType(boolean indexed,boolean stored,
    		boolean tokenized){
    	return getFieldType(indexed,stored, tokenized,false);
    }
    public static FieldType getFieldType(boolean indexed,boolean stored,
    		boolean tokenized,boolean isINTFile) {
    	FieldType  ft= new FieldType();
    	ft.setStored(stored);
    	ft.setTokenized(tokenized);
    	if (isINTFile)
    		ft.setNumericType(FieldType.NumericType.INT);
		return ft;
	}

}