package dev.marvin.savingsmanagement.customer.dao;

import dev.marvin.savingsmanagement.customer.domain.Customer;
import dev.marvin.savingsmanagement.customer.rowMapper.CustomerRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CustomerJdbcDataAccessService implements CustomerDAO{

    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper;
    @Override
    public List<Customer> getAllCustomers() {

        String sql = """
                SELECT id, name, email, mobile, memberNumber
                FROM tbl_customers
                """;

        return jdbcTemplate.query(sql, customerRowMapper);
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        String sql = """
                SELECT id, name, email, mobile, memberNumber
                FROM tbl_customers
                WHERE id = ?
                """;

        return jdbcTemplate.query(sql, customerRowMapper, customerId).stream().findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {

    }

    @Override
    public void updateCustomer(Customer customer) {

    }

    @Override
    public void deleteCustomer(Customer customer) {

    }
}
