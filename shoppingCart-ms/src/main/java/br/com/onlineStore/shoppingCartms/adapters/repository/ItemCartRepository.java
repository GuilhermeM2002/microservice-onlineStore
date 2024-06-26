package br.com.onlineStore.shoppingCartms.adapters.repository;

import br.com.onlineStore.shoppingCartms.core.domain.ItemCart;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCartRepository extends JpaRepository<ItemCart, Long> {
    Set<ItemCart> findByShoppingCart_UserEmail(String email);
}
