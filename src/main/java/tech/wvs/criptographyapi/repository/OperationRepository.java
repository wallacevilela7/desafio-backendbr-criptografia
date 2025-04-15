package tech.wvs.criptographyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.wvs.criptographyapi.domain.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
