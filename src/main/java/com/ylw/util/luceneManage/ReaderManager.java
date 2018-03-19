package com.ylw.util.luceneManage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map.Entry;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;
/**
  * IndexReader 生命周期，以及改变后 reopen 管理类
  * @author admin
  *
  */
public class ReaderManager {
    /**
      * reader 回收 Map
      */
    private static final Map<DirectoryReader, Long> recyleReaderMap = new HashMap<DirectoryReader, Long>();
   
    /**
      * old reader 回收最大生命周期
      */
    private static final int oldReaderMaxLifeTime = 60 * 1000;
   
    private static final Timer readerRefereshTimer = new Timer();
   
    private static final Map<Directory, DirectoryReader> readerMap = new HashMap<Directory, DirectoryReader>();
   
    private static final ReaderManager manager = new ReaderManager();
   
    public static final synchronized ReaderManager getInstance()
    {
       return manager ;
    }
   
    /**
      * 创建 indexReader 并放缓存
      * @param reader
      */
    public synchronized void createIndexReader(Directory dir)
    {
       try {
           readerMap .put(dir, DirectoryReader. open (dir));
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
   
    /**
      * 获取 IndexReader
      * @param dir
      * @return
      */
    public IndexReader getIndexReader (Directory dir)
    {
    	refreshIndexReader(dir);
    	return readerMap .get(dir);
    }
    
    public static synchronized void refreshIndexReader(Directory dir) 
    {
    	try
    	{
    		DirectoryReader oldReader = readerMap.get(dir);
    		if (oldReader==null)
    			return;
    		DirectoryReader newReader = DirectoryReader.openIfChanged (oldReader);
    		if (newReader != null )
    		{
    			// 替换旧 reader 对象
    			readerMap .put(dir, newReader);
    			// 放入回收 MAP 中
    			recyleReaderMap .put(oldReader, System. currentTimeMillis ());
    		}
    	}catch (IOException e) {
            e.printStackTrace();
         }
    }
   
    static
    {
       readerRefereshTimer .schedule( new TimerTask(){
          
           public void run() {
             
              // 判断处理 reader 是否改变
              for (Entry<Directory, DirectoryReader> entry : new HashMap<Directory, DirectoryReader>( readerMap ).entrySet()) {
            	  /*	
            	  try {
            		  DirectoryReader oldReader = entry.getValue();
            		  DirectoryReader newReader = DirectoryReader. openIfChanged (oldReader);
                      if (newReader != null )
                      {
                         // 替换旧 reader 对象
                         readerMap .put(entry.getKey(), newReader);
                         // 放入回收 MAP 中
                         recyleReaderMap .put(oldReader, System. currentTimeMillis ());
                      }
                  } catch (IOException e) {
                     e.printStackTrace();
                  }
 * 
 */
            	  ReaderManager.refreshIndexReader(entry.getKey());
              }
            	  
              // 处理 old reader 回收
              for (Entry<DirectoryReader, Long> entry : new HashMap<DirectoryReader, Long>( recyleReaderMap ).entrySet()) {
                  if (System. currentTimeMillis () - entry.getValue()> oldReaderMaxLifeTime )
                  {
                     try {
                         entry.getKey().close();
                     } catch (IOException e) {
                         e.printStackTrace();
                     } finally {
                         recyleReaderMap .remove(entry.getKey());
                     }
                  }
              }
           }
          
       }, 5 * 1000, 5 * 1000);
    }
}