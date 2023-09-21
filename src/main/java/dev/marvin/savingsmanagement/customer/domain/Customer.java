package dev.marvin.savingsmanagement.customer.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_customers", uniqueConstraints = {@UniqueConstraint(name = "customer_unique_email", columnNames = "email")})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_id_sequence", sequenceName = "customer_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_sequence")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String mobile;

    @Column(nullable = false)
    private String memberNumber;

    @Column(name = "government_ID", nullable = false)
    private String governmentId;

    @Column(nullable = false)
    private String address;
}
