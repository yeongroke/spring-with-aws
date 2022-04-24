package com.yrkim.springwithaws.model.dto;

import com.yrkim.springwithaws.auth.model.dto.UserDto;
import com.yrkim.springwithaws.common.model.dto.BaseTimeDto;
import com.yrkim.springwithaws.model.entity.OrderMaster;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMasterDto extends BaseTimeDto {
    @Schema(name = "주문마스터번호", description = "주문마스터번호", example = "1")
    private Long id;
    @NotNull
    @Schema(name = "총 주문 가격", description = "총 주문 가격", example = "1")
    private Long totalPrice;
    @NotNull
    @Schema(name = "총 주문 갯수", description = "총 주문 갯수", example = "1")
    private Long orderCount;
    @Schema(name = "주문 상세 리스트", description = "주문 상세 리스트", example = "1")
    private Collection<OrderDetailDto> orderDetailDtos = new ArrayList<>();
    @Schema(name = "주문자 정보", description = "주문자 정보", example = "1")
    private UserDto userDto;

    public OrderMasterDto(OrderMaster orderMaster) {
        this.id = orderMaster.getId();
        this.totalPrice = orderMaster.getTotalPrice();
        this.orderCount = orderMaster.getOrderCount();
        this.orderDetailDtos = orderMaster.getOrderDetails()
                .stream()
                .map(orderDetail -> {
                    return new OrderDetailDto(orderDetail);
                }).collect(Collectors.toList());
        this.userDto = new UserDto(orderMaster.getUser());
    }

    public OrderMaster toEntity() {
        return OrderMaster.builder()
                .id(this.id)
                .totalPrice(this.totalPrice)
                .orderCount(this.orderCount)
                .orderDetails(this.orderDetailDtos
                        .stream()
                        .map(OrderDetailDto::toEntity)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
