package com.ohgiraffers.transactional.section01.annotation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;


@SpringBootTest

/* 설명. @Transactional
    DML(insert, update, delete) 작업 테스트 시 실제 DB에 적용을 하지 않으려면 @Transactional 테스트 패키지에 어노테이션 추가
 */
//@Transactional
public class OrderServiceTest {

    @Autowired
    private OrderService registOrderService;

    private static Stream<Arguments> getOrderInfo() {
        OrderDTO orderInfo = new OrderDTO();
        orderInfo.setOrderDate(LocalDate.now());
        orderInfo.setOrderTime(LocalTime.now());

        orderInfo.setOrderMenus(
                List.of(
                        new OrderMenuDTO(1, 10),
                        new OrderMenuDTO(2, 10),
                        new OrderMenuDTO(3, 10),
                        new OrderMenuDTO(4, 10),
                        new OrderMenuDTO(5, 10),
                        new OrderMenuDTO(6, 10)
                )
        );

        return Stream.of(
                Arguments.of(orderInfo)
        );
    }

    @DisplayName("주문 등록 테스트")
    @ParameterizedTest
    @MethodSource("getOrderInfo")
    void testRegistNewOrder(OrderDTO orderInfo) {
        Assertions.assertDoesNotThrow(
                () -> registOrderService.registNewOrder(orderInfo)
        );
    }
}
