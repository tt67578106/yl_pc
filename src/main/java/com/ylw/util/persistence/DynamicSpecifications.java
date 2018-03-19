package com.ylw.util.persistence;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springside.modules.utils.Collections3;

import com.google.common.collect.Lists;
import com.ylw.util.DateConvertUtils;

public class DynamicSpecifications<T> {
	/**
	 * 重写条件拼接功能
	 * @param filters
	 * @param entityClazz
	 * @param or !注意，不要轻易使用or,这将会带来巨大的性能损耗
	 * @return
	 */
	public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters, final Class<T> entityClazz,final boolean or) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if (Collections3.isNotEmpty(filters)) {

					List<Predicate> predicates = Lists.newArrayList();
					for (SearchFilter filter : filters) {
						// nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
						String[] names = StringUtils.split(filter.fieldName, ".");
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}
						// logic operator
						switch (filter.operator) {
						case BOOLEANEQ:
							if(filter.value!=null && !filter.value.equals("")){
								boolean status = Boolean.parseBoolean((String) filter.value);
								predicates.add(builder.equal(expression, status));
							}
							break;
						case EQ:
							if(filter.value != null && StringUtils.isNotBlank(filter.value+"")){
								predicates.add(builder.equal(expression, filter.value));
							}
							break;
						case LIKE:
							predicates.add(builder.like(expression, "%" + filter.value + "%"));
							break;
						case GT:
							predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
							break;
						case LT:
							predicates.add(builder.lessThan(expression, (Comparable) filter.value));
							break;
						case GTE:
							predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
							break;
						case LTE:
							predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
							break;
						case DATEGTE:
							predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) DateConvertUtils.firstDate(filter.value+"")));
							break;
						case DATELTE:
							predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) DateConvertUtils.endDate(filter.value+"")));
							break;
						case IN:
							predicates.add(builder.in(expression).value((List<Integer>)filter.value));
							break;
						}
					}

					// 将所有条件用 and 联合起来
					if (!predicates.isEmpty()) {
						if(or == true){
							return builder.or(predicates.toArray(new Predicate[predicates.size()]));
						}else{
							return builder.and(predicates.toArray(new Predicate[predicates.size()]));
						}
					}
				}

				return builder.conjunction();
			}
		};
	}
	
	
	/**
	 * 
	 * @param filters
	 * @param entityClazz
	 * @param orParams
	 * @return
	 */
	public static <T> Specification<T> bySearchFilterOr(final Collection<SearchFilter> filters, final Class<T> entityClazz,final SearchFilter... orParams) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = Lists.newArrayList();
				List<Predicate> predicatesOr = Lists.newArrayList();
				if (Collections3.isNotEmpty(filters)) {
					for (SearchFilter filter : filters) {
						// nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
						String[] names = StringUtils.split(filter.fieldName, ".");
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}

						// logic operator
						switch (filter.operator) {
						case BOOLEANEQ:
							if(filter.value!=null && !filter.value.equals("")){
								boolean status = Boolean.parseBoolean((String) filter.value);
								predicates.add(builder.equal(expression, status));
							}
							break;
						case EQ:
							if(filter.value != null && StringUtils.isNotBlank(filter.value + "")){
								predicates.add(builder.equal(expression, filter.value));
							}
							break;
						case LIKE:
							predicates.add(builder.like(expression, "%" + filter.value + "%"));
							break;
						case GT:
							predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
							break;
						case LT:
							predicates.add(builder.lessThan(expression, (Comparable) filter.value));
							break;
						case GTE:
							predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
							break;
						case LTE:
							predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
							break;
						case DATEGTE:
							predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) DateConvertUtils.firstDate(filter.value+"")));
							break;
						case DATELTE:
							predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) DateConvertUtils.endDate(filter.value+"")));
							break;
						}
					}
				}
				

				if (orParams != null && orParams.length > 0) {
					for (SearchFilter filter : orParams) {
						String[] names = StringUtils.split(filter.fieldName, ".");
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}
						switch (filter.operator) {
						case BOOLEANEQ:
							if(filter.value!=null && !filter.value.equals("")){
								boolean status = Boolean.parseBoolean((String) filter.value);
								predicatesOr.add(builder.equal(expression, status));
							}
							break;
						case EQ:
							predicatesOr.add(builder.equal(expression, filter.value));
							break;
						case LIKE:
							predicatesOr.add(builder.like(expression, "%" + filter.value + "%"));
							break;
						case GT:
							predicatesOr.add(builder.greaterThan(expression, (Comparable) filter.value));
							break;
						case LT:
							predicatesOr.add(builder.lessThan(expression, (Comparable) filter.value));
							break;
						case GTE:
							predicatesOr.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
							break;
						case LTE:
							predicatesOr.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
							break;
						case DATEGTE:
							predicatesOr.add(builder.greaterThanOrEqualTo(expression, (Comparable) DateConvertUtils.firstDate(filter.value+"")));
							break;
						case DATELTE:
							predicatesOr.add(builder.lessThanOrEqualTo(expression, (Comparable) DateConvertUtils.endDate(filter.value+"")));
							break;
						}
					}
				}

				if (predicatesOr != null && predicatesOr.size() > 0) {
					return	builder.and(builder.and(predicates.toArray(new Predicate[predicates.size()])),
							builder.or(predicatesOr.toArray(new Predicate[predicatesOr.size()])));
				} else if (!predicates.isEmpty() && predicatesOr.size() == 0) {
					return builder.and(predicates.toArray(new Predicate[predicates.size()]));
				}
				return builder.conjunction();
			}
		};
	}
}