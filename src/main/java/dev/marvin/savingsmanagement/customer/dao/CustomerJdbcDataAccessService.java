package dev.marvin.savingsmanagement.customer.dao;

import dev.marvin.savingsmanagement.customer.domain.Customer;
import dev.marvin.savingsmanagement.customer.rowmapper.CustomerRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository(value = "jdbc")
public class CustomerJdbcDataAccessService implements CustomerDAO {

    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper;

    @Override
    public List<Customer> getAllCustomers() {

        String sql = """
                SELECT * FROM tbl_customers
                """;
        return jdbcTemplate.query(sql, customerRowMapper);
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        String sql = """
                SELECT id, name, email, mobile, member_number, government_ID, address
                FROM tbl_customers
                WHERE id = ?
                """;
        return jdbcTemplate.query(sql, customerRowMapper, customerId).stream().findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        String sql = """
                INSERT INTO tbl_customers(name, email, mobile, member_number, government_ID, address)
                VALUES(?,?,?,?,?,?)
                """;
        int rowsAffected = jdbcTemplate.update(sql, customer.getName(), customer.getEmail(), customer.getMobile(), customer.getMemberNumber(), customer.getGovernmentId(), customer.getAddress());
        System.out.println("JDBC INSERT result = " + rowsAffected);
    }

    @Override
    public void updateCustomer(Customer customer) {
        if (customer.getName() != null && !customer.getName().isEmpty()) {
            String sql = """
                    UPDATE tbl_customers
                    SET name = ?
                    WHERE id = ?
                    """;
            int rowsAffected = jdbcTemplate.update(sql, customer.getName(), customer.getId());
            System.out.println("JDBC UPDATE customer name result = " + rowsAffected);
        }
        if (customer.getEmail() != null && !customer.getEmail().isEmpty()) {
            String sql = """
                    UPDATE tbl_customers
                    SET email = ?
                    WHERE id = ?
                    """;
            int rowsAffected = jdbcTemplate.update(sql, customer.getEmail(), customer.getId());
            System.out.println("JDBC UPDATE customer email result = " + rowsAffected);
        }

        if (customer.getMobile() != null && !customer.getMobile().isEmpty()) {
            String sql = """
                    UPDATE tbl_customers
                    SET mobile = ?
                    WHERE id = ?
                    """;
            int rowsAffected = jdbcTemplate.update(sql, customer.getMobile(), customer.getId());
            System.out.println("JDBC UPDATE customer mobile result = " + rowsAffected);
        }

        if (customer.getAddress() != null && !customer.getAddress().isEmpty()) {
            String sql = """
                    UPDATE tbl_customers
                    SET address = ?
                    WHERE id = ?
                    """;
            int rowsAffected = jdbcTemplate.update(sql, customer.getAddress(), customer.getId());
            System.out.println("JDBC UPDATE customer address result = " + rowsAffected);
        }

    }

    @Override
    public void deleteCustomerById(Long customerId) {
        String sql = """
                DELETE FROM tbl_customers
                WHERE id = ?
                """;
        int rowsAffected = jdbcTemplate.update(sql, customerId);
        System.out.println("JDBC DELETE result = " + rowsAffected);

    }

    @Override
    public boolean existsCustomerWithEmail(String email) {
        String sql = """
                  SELECT COUNT(*)
                  FROM tbl_customers
                  WHERE email = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }
}
