package com.yrkim.springwithaws.model.dto;

import com.yrkim.springwithaws.model.entity.OrderDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/*
* @NoArgsConstructor
* 기본 생성자 자동 추가
* */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto extends BaseTimeDto {
    @Schema(name = "주문 상세 번호", description = "주문 상세 번호", example = "1")
    private Long id;
    @NotNull
    @Schema(name = "구매 가격", description = "구매 가격", example = "1")
    private Long purchasePrice;
    @NotNull
    @Schema(name = "주문 갯수", description = "주문 갯수", example = "1")
    private Long purchaseCount;
    @NotNull
    @Schema(name = "주문 마스터 번호", description = "주문 마스터 번호", example = "1")
    private OrderMasterDto orderMasterDto;
    @NotNull
    @Schema(name = "주문 마스터 번호", description = "주문 마스터 번호", example = "1")
    private OrderItemDto orderItemDto;

    public OrderDetailDto(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
        this.purchasePrice = orderDetail.getPurchasePrice();
        this.purchaseCount = orderDetail.getPurchaseCount();
        this.orderMasterDto = new OrderMasterDto(orderDetail.getOrderMaster());
        this.orderItemDto = new OrderItemDto(orderDetail.getOrderItem());
    }

    public OrderDetail toEntity() {
        return OrderDetail.builder()
                .id(this.id)
                .purchasePrice(this.purchasePrice)
                .purchaseCount(this.purchaseCount)
                .orderMaster(this.orderMasterDto.toEntity())
                .orderItem(this.orderItemDto.toEntity())
                .build();
    }
}