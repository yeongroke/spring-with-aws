package com.yrkim.springwithaws.model.dto;

import com.yrkim.springwithaws.auth.model.dto.UserDto;
import com.yrkim.springwithaws.common.model.dto.BaseTimeDto;
import com.yrkim.springwithaws.model.entity.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto extends BaseTimeDto {
    @Schema(name = "id", description = "회원번호", example = "1")
    private Long id;
    @NotNull
    @Schema(name = "itemName", description = "상품명", example = "yeongroke")
    private String itemName;
    @NotNull
    @Schema(name = "sellPrice", description = "상품가격", example = "1")
    private Long sellPrice;
    @NotNull
    @Schema(name = "sellCount", description = "판매상품갯수", example = "1")
    private Long sellCount;
    @Schema(name = "sellStartTs", description = "상품 판매 시작 시간", example = "2022-01-01 01:01:01")
    private LocalDateTime sellStartTs;
    @Schema(name = "sellEndTs", description = "상품 판매 종료 시간", example = "2022-01-01 01:01:01")
    private LocalDateTime sellEndTs;
    @NotNull
    @Schema(name = "userDto", description = "회원번호", example = "1")
    private UserDto userDto;

    public OrderItemDto(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.itemName = orderItem.getItemName();
        this.sellPrice = orderItem.getSellPrice();
        this.sellCount = orderItem.getSellCount();
        this.sellStartTs = orderItem.getSellStartTs();
        this.sellEndTs = orderItem.getSellEndTs();
        this.userDto = new UserDto(orderItem.getUser());
    }

    public OrderItem toEntity() {
        return OrderItem.builder()
                .id(this.id)
                .itemName(this.itemName)
                .sellPrice(this.sellPrice)
                .sellCount(this.sellCount)
                .sellStartTs(this.sellStartTs)
                .sellEndTs(this.sellEndTs)
                .user(this.userDto.toEntity())
                .build();
    }
}
