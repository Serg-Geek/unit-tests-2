package seminars.second.simple_shopping_cart;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    Shop shop;
    Cart cart;

    Product product;

    public static List<Product> getStoreItems() {
        List<Product> products = new ArrayList<>();

// Три массива Названия, Цены, Кол-во
        String[] productNames = {"bacon", "beef", "ham", "salmon", "carrot", "potato", "onion", "apple", "melon", "rice", "eggs", "yogurt"};
        Double[] productPrice = {170.00d, 250.00d, 200.00d, 150.00d, 15.00d, 30.00d, 20.00d, 59.00d, 88.00d, 100.00d, 80.00d, 55.00d};
        Integer[] stock = {10, 10, 10, 10, 10, 10, 10, 70, 13, 30, 40, 60};

// Последовательно наполняем список продуктами
        for (int i = 0; i < productNames.length; i++) {
            products.add(new Product(i + 1, productNames[i], productPrice[i], stock[i]));
        }

// тоже самое
// Product product = new Product(1,"bacon", 170.00d, 10);
// products.add(product);
        return products;
    }
         @BeforeEach
         void setup() {
         shop = new Shop(getStoreItems());
         cart = new Cart(shop);
 }


        @Test
        void getTotalPriceDifferentTotalTest () {
        cart.addProductToCartByID(1);
        cart.addProductToCartByID(2);
        cart.addProductToCartByID(3);
        cart.addProductToCartByID(4);


        assertThat(cart.getTotalPrice()).isEqualTo(770);

        }

        @Test
        void getTotalPriceSameTotalTest () {
        cart.addProductToCartByID(1);
        cart.addProductToCartByID(2);
        cart.addProductToCartByID(3);
        cart.addProductToCartByID(4);
        cart.addProductToCartByID(1);
        cart.addProductToCartByID(2);
        cart.addProductToCartByID(3);
        cart.addProductToCartByID(4);


        assertThat(cart.getTotalPrice()).isEqualTo(1540);

        }
    @Test
    void getTotalPriceRemoveTotalTest () {
        cart.addProductToCartByID(1);
        cart.addProductToCartByID(2);
        cart.addProductToCartByID(3);
        cart.addProductToCartByID(4);
        cart.removeProductByID(3);


        assertThat(cart.getTotalPrice()).isEqualTo(570);
    }

            @Test
            void getTotalQuantityInShopTest () {
            cart.addProductToCartByID(1);
            cart.addProductToCartByID(2);
            cart.addProductToCartByID(3);

            shop.getProductsShop().get(0).getQuantity();



            assertThat( shop.getProductsShop().get(0).getQuantity()).isEqualTo(9);
            assertThat( shop.getProductsShop().get(1).getQuantity()).isEqualTo(9);
            assertThat( shop.getProductsShop().get(2).getQuantity()).isEqualTo(9);

        }


        @Test
            void lastPruductDisappearShopTest () {
            for (int i = 0; i <11; i++){
                cart.addProductToCartByID(1);
            }
            assertThat( shop.getProductsShop().get(0).getQuantity()).isEqualTo(0);
            assertThat(cart.cartItems.get(0).getQuantity()).isEqualTo(10);

        }

        @Test
            void ProductReturnedToShopTest () {
            for (int i = 0; i <3; i++){
                cart.addProductToCartByID(1);
            }
            assertThat( shop.getProductsShop().get(0).getQuantity()).isEqualTo(7);
            assertThat(cart.cartItems.get(0).getQuantity()).isEqualTo(3);

            cart.removeProductByID(1);
            cart.removeProductByID(1);

            assertThat( shop.getProductsShop().get(0).getQuantity()).isEqualTo(9);
            assertThat(cart.cartItems.get(0).getQuantity()).isEqualTo(1);


        }


        @ParameterizedTest
        @ValueSource(ints ={-100,100})

        void deletedProductIsReturnedToShop(int i){

/**
 * 2.7. Разработайте параметризованный модульный тест для проверки,
 * что при вводе неверного идентификатора товара генерируется исключение RuntimeException.
 * <br><b>Ожидаемый результат:</b>
 * Исключение типа RuntimeException и сообщение Не найден продукт с id
 * *Сделать тест параметризованным
 */


                assertThatThrownBy(() -> cart.addProductToCartByID(i)).isInstanceOf(RuntimeException.class)
                        .describedAs("Не найден продукт с id: " + i);


            }


    /**
     * 2.8.      * 2.8. Создайте модульный тест для проверки, что при попытке удалить из корзины больше товаров,
     * чем там есть, генерируется исключение RuntimeException.удаляет продукты до того, как их добавить)
     * <br><b>Ожидаемый результат:</b> Исключение типа NoSuchFieldError и сообщение "В корзине не найден продукт с id"
     */
        @Test
            void incorrectProductSelectionCausesException() {

            assertThatThrownBy(() -> cart.removeProductByID(1)).isInstanceOf(RuntimeException.class)
                    .describedAs("Не найден продукт с id: " + 1);
        }



        /**
         * 2.10. Нужно оптимизировать тестовый метод, согласно следующим условиям:
         * <br> 1. Отображаемое имя - "Advanced test for calculating TotalPrice"
         * <br> 2. Тест повторяется 10 раз
         * <br> 3. Установлен таймаут на выполнение теста 70 Миллисекунд (unit = TimeUnit.MILLISECONDS)
         * <br> 4. После проверки работоспособности теста, его нужно выключить
         */
        @Test
        @DisplayName("Advanced test for calculating TotalPrice")
        @RepeatedTest(10)
        @Timeout(value = 70, unit = TimeUnit.MILLISECONDS)
        void testAnnotations(){
            assertTrue(true);
        }


    }




