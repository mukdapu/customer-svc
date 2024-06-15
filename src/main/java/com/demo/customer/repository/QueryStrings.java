package com.demo.customer.repository;

class QueryStrings {

    private QueryStrings() {
        //
    }


    static final String FETCH_CUSTOMERS_QUERY = "select c.* " +
            "from customer c join address ad on c.id = ad.customer_id";
}
