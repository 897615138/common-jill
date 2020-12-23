package result.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import result.entity.RiskStrategy;
import result.entity.RiskStrategyExample;

public interface RiskStrategyMapper {
    long countByExample(RiskStrategyExample example);

    int deleteByExample(RiskStrategyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RiskStrategy record);

    int insertSelective(RiskStrategy record);

    List<RiskStrategy> selectByExampleWithBLOBs(RiskStrategyExample example);

    List<RiskStrategy> selectByExample(RiskStrategyExample example);

    RiskStrategy selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskStrategy record, @Param("example") RiskStrategyExample example);

    int updateByExampleWithBLOBs(@Param("record") RiskStrategy record, @Param("example") RiskStrategyExample example);

    int updateByExample(@Param("record") RiskStrategy record, @Param("example") RiskStrategyExample example);

    int updateByPrimaryKeySelective(RiskStrategy record);

    int updateByPrimaryKeyWithBLOBs(RiskStrategy record);

    int updateByPrimaryKey(RiskStrategy record);
}