package tech.wvs.criptographyapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tech.wvs.criptographyapi.controller.dto.OperationRequest;
import tech.wvs.criptographyapi.controller.dto.OperationResponse;
import tech.wvs.criptographyapi.controller.dto.UpdateOperationRequest;
import tech.wvs.criptographyapi.domain.Operation;
import tech.wvs.criptographyapi.repository.OperationRepository;

import java.util.Optional;

@Service
public class OperationService {

    private final OperationRepository repository;

    public OperationService(OperationRepository operationRepository) {
        this.repository = operationRepository;
    }

    public void create(OperationRequest dto) {
        var entity = new Operation();
        entity.setRawUserDocument(dto.userDocument());
        entity.setRawCreditCardToken(dto.creditCardToken());
        entity.setOperationValue(dto.operationValue());

        repository.save(entity);
    }

    public Page<OperationResponse> findAll(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);

        var content = repository.findAll(pageRequest);

        return content.map(entity -> OperationResponse.toResponse(entity));
    }

    public Optional<Operation> findById(Long id) {
        return repository.findById(id);
    }

    public Operation update(Long id, UpdateOperationRequest dto) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operation not found"));

        entity.setOperationValue(dto.operationValue());

        return repository.save(entity);
    }

    public boolean delete(Long id) {
        var exists = repository.existsById(id);

        if (exists) {
            repository.deleteById(id);
        }

        return exists;
    }
}
