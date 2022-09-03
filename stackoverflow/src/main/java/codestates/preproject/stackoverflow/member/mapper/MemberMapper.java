package codestates.preproject.stackoverflow.member.mapper;

import codestates.preproject.stackoverflow.member.dto.MemberDto;
import codestates.preproject.stackoverflow.member.entity.Member;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberJoinToMember(MemberDto.Join requestBody);
    Member memberLoginToMember(MemberDto.Login requestBody);
    Member memberPatchToMember(MemberDto.Patch requestBody);
    MemberDto.Response memberToMemberResponseDto(Member requestBody);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void updateMemberFromDto(MemberDto.Response response, @MappingTarget Member member);
}
