package eco.com.spring.mcsv.account.mov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "number_account")
    private String numberAccount;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "initial_balance")
    private Float initialBalance;

    private String estate;

    private String client;
}
