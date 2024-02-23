package com.ohgiraffers.mybatiesspring.section01.factorybean;

import java.util.List;

public interface MenuMapper {

    List<MenuDTO> selectAllMenuByOrderableStatus(String orderableStatus);
}
