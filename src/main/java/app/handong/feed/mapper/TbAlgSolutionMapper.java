package app.handong.feed.mapper;

import app.handong.feed.dto.AlgorithmDto;
import app.handong.feed.dto.TbuserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface TbAlgSolutionMapper {

    // 이메일로 사용자 찾기
    List<AlgorithmDto.AlgSolutionDashboardDto> aggregateAllSolutions();

    @Select("SELECT githubFilePath FROM mydb_TbAlgSolution WHERE userId = #{userId}")
    List<String> findPath(@Param("userId") Long userId);
}
