package com.demo.customer.repository;

import com.demo.customer.model.Customer;
import com.demo.customer.model.CustomerSearchRequest;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


class CustomCustomerRepositoryImpl implements CustomCustomerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Customer> fetchCustomersBy(CustomerSearchRequest searchRequest) {
        StringBuilder query = new StringBuilder(QueryStrings.FETCH_CUSTOMERS_QUERY);

        StringBuilder whereClause = new StringBuilder();

        if (StringUtils.isNotBlank(searchRequest.firstName())) {
            appendWhereClause(whereClause, " c.first_name = :firstName");
        }
        if (StringUtils.isNotBlank(searchRequest.lastName())) {
            appendWhereClause(whereClause, " c.last_name = :lastName");
        }
        if (StringUtils.isNotBlank(searchRequest.city())) {
            appendWhereClause(whereClause, " ad.city = :city");
        }
        if (StringUtils.isNotBlank(searchRequest.state())) {
            appendWhereClause(whereClause, " ad.state = :state");
        }

        if (!whereClause.isEmpty()) {
            query.append(whereClause);
        }

        query.append(" LIMIT :limit OFFSET :offset");

        Query customerSeachQuery = em.createNativeQuery(query.toString(), Customer.class);

        if (StringUtils.isNotBlank(searchRequest.firstName())) {
            customerSeachQuery.setParameter("firstName", searchRequest.firstName());
        }
        if (StringUtils.isNotBlank(searchRequest.lastName())) {
            customerSeachQuery.setParameter("lastName", searchRequest.lastName());
        }
        if (StringUtils.isNotBlank(searchRequest.city())) {
            customerSeachQuery.setParameter("city", searchRequest.city());
        }
        if (StringUtils.isNotBlank(searchRequest.state())) {
            customerSeachQuery.setParameter("state", searchRequest.state());
        }
        customerSeachQuery.setParameter("limit", searchRequest.limit());
        customerSeachQuery.setParameter("offset", searchRequest.offset());
        return customerSeachQuery.getResultList();
    }

    private void appendWhereClause(StringBuilder whereClause, String condition) {
        if (whereClause.isEmpty()) {
            whereClause.append(" where ");
        } else {
            whereClause.append(" and ");
        }

        whereClause.append(condition);
    }
}
