package dev.marvin.savingsmanagement.customer.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tbl_customers",
        uniqueConstraints = {@UniqueConstraint(name = "customer_unique_email", columnNames = "email")})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_id_sequence", sequenceName = "customer_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String email;
    private String mobile;
    private String memberNumber;
    @CreationTimestamp
    private String createdDate;
    @UpdateTimestamp
    private String updatedDate;
}
