package tech.wvs.criptographyapi.controller.dto;

public record OperationRequest(String userDocument,
                               String creditCardToken,
                               Long operationValue) {
}
