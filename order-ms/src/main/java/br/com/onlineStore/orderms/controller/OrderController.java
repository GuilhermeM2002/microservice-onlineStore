package br.com.onlineStore.orderms.controller;

import br.com.onlineStore.orderms.application.dto.OrderDto;
import br.com.onlineStore.orderms.application.useCasesImpl.CancelOrderUseCaseImpl;
import br.com.onlineStore.orderms.application.useCasesImpl.MakeOrderUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private MakeOrderUseCaseImpl makeOrderUseCase;
    @Autowired
    private CancelOrderUseCaseImpl cancelOrderUseCase;

    @PostMapping
    public ResponseEntity makeOrder(
            @RequestBody OrderDto orderDto,
            @CookieValue String email,
            UriComponentsBuilder uriComponentsBuilder)
    {
        var uri = uriComponentsBuilder.path("order/${id}").buildAndExpand(orderDto.getId()).toUri();
        var order = makeOrderUseCase.makeOrder(email, orderDto.getAddress());

        return ResponseEntity.created(uri).body(order);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity cancelOrder(@PathVariable Long id){
        cancelOrderUseCase.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }
}
