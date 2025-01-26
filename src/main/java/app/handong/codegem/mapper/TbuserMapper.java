package app.handong.codegem.mapper;

import app.handong.codegem.dto.TbuserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbuserMapper {

    // 이메일로 사용자 찾기
    TbuserDto.DetailResDto findByEmail(String email);
}
