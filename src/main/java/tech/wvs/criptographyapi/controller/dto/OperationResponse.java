package tech.wvs.criptographyapi.controller.dto;

import tech.wvs.criptographyapi.domain.Operation;

public record OperationResponse(Long id,
                                String userDocument,
                                String creditCardToken,
                                Long operationValue) {

    public static OperationResponse toResponse (Operation operation) {
        return new OperationResponse(
                operation.getId(),
                operation.getRawUserDocument(),
                operation.getRawCreditCardToken(),
                operation.getOperationValue());
    }
}
