package tech.wvs.criptographyapi.controller;

import org.hibernate.grammars.hql.HqlParser;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.wvs.criptographyapi.controller.dto.OperationRequest;
import tech.wvs.criptographyapi.controller.dto.OperationResponse;
import tech.wvs.criptographyapi.controller.dto.UpdateOperationRequest;
import tech.wvs.criptographyapi.service.OperationService;

@RestController
@RequestMapping(path = "/api/operations")
public class OperationController {

    private final OperationService service;

    public OperationController(OperationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody OperationRequest dto) {
        service.create(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<OperationResponse>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        var content = service.findAll(page, pageSize);
        return ResponseEntity.ok(content);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OperationResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);

        return entity.isPresent() ?
                ResponseEntity.ok(OperationResponse.toResponse(entity.get())) :
                ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateById(@PathVariable Long id,
                                           @RequestBody UpdateOperationRequest dto) {
        var entity = service.update(id, dto);

        return entity != null ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        var deleted = service.delete(id);

        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
