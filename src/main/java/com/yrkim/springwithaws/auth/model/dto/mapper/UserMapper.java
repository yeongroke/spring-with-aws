package com.yrkim.springwithaws.auth.model.dto.mapper;

import com.yrkim.springwithaws.auth.model.dto.UserDto;
import com.yrkim.springwithaws.auth.model.entity.User;
import com.yrkim.springwithaws.common.tools.mapstruct.EntityMapper;
import org.mapstruct.*;

import java.util.List;

/**
* componentModel
* : "spring" 속성을 통해 spring 프로젝트에서 bean으로 등록
* uses
* : 다른 MapSturct Import 역할
* unmappedTargetPolicy
* ERROR : 매핑되지 않은 대상 속성은 빋르에 실패. 이는 실수로 매핑하지 않은 필드를 방지하는데 도움
* WARN : (기본값) 빌드 중 경고 메시지
* IGNORE : 출력 또는 오류 없음
* InjectionStrategy
* : InjectionStrategy의 FILED 또는 CONSTRUCTOR으로 객체 주입
* */
@Mapper(
    componentModel = "spring",
    uses = {},
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UserMapper extends EntityMapper<UserDto, User> {

    @Mappings(value = {
            @Mapping(source = "id", target = "id", ignore = true),
            @Mapping(target = "orderMaster", ignore = true),
            @Mapping(target = "orderItem", ignore = true)
        }
    )
    User toEntity(UserDto dto);

    @Mapping(source = "id", target = "id", ignore = true)
    UserDto toDto(User entity);

    @Mappings(value = {
            @Mapping(source = "id", target = "id", ignore = true),
            @Mapping(target = "orderMaster", ignore = true),
            @Mapping(target = "orderItem", ignore = true)
        }
    )
    List<User> toEntity(List<UserDto> dto);

    @Mapping(source = "id", target = "id", ignore = true)
    List<UserDto> toDto(List<User> entity);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings(value = {
            @Mapping(source = "id", target = "id", ignore = true),
            @Mapping(target = "orderMaster", ignore = true),
            @Mapping(target = "orderItem", ignore = true)
        }
    )
    void partialUpdate(@MappingTarget User entity, UserDto dto);
}
