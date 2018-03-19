package com.ylw.repository.utm;


/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import com.ylw.entity.utm.UtmLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UtmLogDao extends PagingAndSortingRepository<UtmLog,Long>, JpaSpecificationExecutor<UtmLog>{

}
