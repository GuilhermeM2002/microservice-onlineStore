package br.com.onlineStore.shoppingCartms.controller;

import br.com.onlineStore.shoppingCartms.application.dto.ItemCartDto;
import br.com.onlineStore.shoppingCartms.application.dto.PersistDto;
import br.com.onlineStore.shoppingCartms.application.useCasesImpl.FindAllItemByUserEmailUseCaseImpl;
import br.com.onlineStore.shoppingCartms.application.useCasesImpl.GenerateItemCartUseCaseImpl;
import br.com.onlineStore.shoppingCartms.application.useCasesImpl.UpdateItemCartUseCaseImpl;
import br.com.onlineStore.shoppingCartms.core.useCases.UpdateItemCartUseCase;
import br.com.onlineStore.shoppingCartms.infra.ShoppingCartRepositoryService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartRepositoryService service;
    @Autowired
    private GenerateItemCartUseCaseImpl generateItemCartUseCase;
    @Autowired
    private FindAllItemByUserEmailUseCaseImpl findAllItemByUserEmailUseCase;
    @Autowired
    private UpdateItemCartUseCaseImpl updateItemCartUseCaseImpl;

    @PostMapping
    public ResponseEntity<ItemCartDto> persist(
            UriComponentsBuilder uriBuilder,
            HttpServletResponse response,
            @RequestBody PersistDto dto,
            @CookieValue(name = "cart-token", required = false) String token,
            @CookieValue(name = "email-cookie", required = false) String email){

        var uri = uriBuilder.path("shoppingCart/${id}").buildAndExpand(dto.id()).toUri();
        if(token == null){
            var cookie = service.createCookie();
            response.addCookie(cookie);
            var item = generateItemCartUseCase.generateItemCart(dto, cookie.getValue(), email);
            return ResponseEntity.created(uri).body(item);
        }
        var item = generateItemCartUseCase.generateItemCart(dto, token, email);

        return ResponseEntity.created(uri).body(item);
    }

    @PutMapping("{id}")
    public ResponseEntity<ItemCartDto> update(@RequestBody @Valid ItemCartDto dto, @PathVariable Long id){
        var cart = updateItemCartUseCaseImpl.updateItemCart(dto, id);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.deleteProductCart(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<ItemCartDto> findAll(@PageableDefault(sort = "price") Pageable pageable){
        return service.allShoppingCart(pageable);
    }

    @GetMapping("{token}")
    public ResponseEntity<Set<ItemCartDto>> findAllItemByUserEmail(@PathVariable String email){
        var allItem = findAllItemByUserEmailUseCase.findAllItemByUserEmail(email);
        return ResponseEntity.ok(allItem);
    }
}
