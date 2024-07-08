package edu.tust.neusoft.backend.model.dto;

import edu.tust.neusoft.backend.model.Carts;
import edu.tust.neusoft.backend.model.Goods;
import edu.tust.neusoft.backend.model.Store;

public class CartWithGoodsDTO {
    private Carts cart;
    private Goods goods;
    private Store store;

    public CartWithGoodsDTO(Carts cart, Goods goods, Store store) {
        this.cart = cart;
        this.goods = goods;
        this.store = store;
    }

    public Carts getCart() {
        return cart;
    }

    public void setCart(Carts cart) {
        this.cart = cart;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
