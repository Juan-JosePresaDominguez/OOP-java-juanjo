package com.myshoppingcart.service;

import com.myshoppingcart.model.Producto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ShoppingCartTest {

    @Test
    public void test() {
        assertTrue(true);
    }

    @Test
    public void cuando_se_crea_el_carrito_tiene_0_articulos() {
        // given ... void

        // when
        ShoppingCart cart = new ShoppingCart();

        // then
        int num = cart.getItemCount();
        double bal = cart.getBalance();

        assertThat(num, is(0));
        assertThat(bal, is(0d));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10, 1000})
    public void cuando_esta_vacio_el_carrito_tiene_0_articulos(int numProducts) {
        // given
        ShoppingCart cart = new ShoppingCart();

        Random rand = new Random();

        for (int i = 0; i < numProducts; i++) {
            cart.addItem(new Producto(i + 1, "fake " + i, rand.nextDouble() * 100));
        }

        // System.out.println("count: " + cart.getItemCount());

        // when
        cart.empty();

        // then
        int num = cart.getItemCount();
        double bal = cart.getBalance();

        assertThat(num, is(0));
        assertThat(bal, is(0d));
    }

    @Test
    public void cuando_se_agrega_un_nuevo_producto_la_cantidad_de_articulos_debe_ser_incrementado() {

    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10, 1000})
    public void cuando_se_agrega_un_nuevo_producto_el_nuevo_saldo_debe_ser_la_suma_de_anteriores_mas_el_costo_del_producto(int numProducts) {
        // given
        ShoppingCart cart = new ShoppingCart();
        Random rand = new Random();
        double inc = 0;

        // when
        for (int i = 0; i < numProducts; i++) {
            double precio = rand.nextDouble() * 100;
            cart.addItem(new Producto(i + 1, "fake " + i, precio));
            inc += precio;
        }

        System.out.println("count: " + cart.getItemCount());

        // then
        int num = cart.getItemCount();
        double bal = cart.getBalance();

        assertThat(num, is(numProducts));
        assertThat(bal, is(inc));
    }

    @Test
    public void cuando_se_elimina_un_elemento_se_debe_disminuir_el_numero_de_elementos() {

    }

    @Test
    public void cuando_se_retira_un_producto_que_no_esta_en_el_carrito_se_debe_lanzar_ProductNotFoundException() {

    }

}