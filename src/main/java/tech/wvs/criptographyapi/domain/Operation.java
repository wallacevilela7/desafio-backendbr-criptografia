package tech.wvs.criptographyapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.wvs.criptographyapi.service.CryptoService;

@Entity
@Table(name = "operation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_document")
    private String userDocument;

    @Column(name = "credit_card_token")
    private String creditCardToken;

    @Column(name = "operation_value", nullable = false)
    private Long operationValue;

    @Transient
    private String rawUserDocument;

    @Transient
    private String rawCreditCardToken;


    @PrePersist
    public void prePersist() {
        this.userDocument = (CryptoService.encrypt(rawUserDocument));
        this.creditCardToken = (CryptoService.encrypt(rawCreditCardToken));
    }

    @PostLoad
    public void postLoad() {
        this.rawUserDocument = CryptoService.decrypt(userDocument);
        this.rawCreditCardToken = CryptoService.decrypt(creditCardToken);
    }
}
