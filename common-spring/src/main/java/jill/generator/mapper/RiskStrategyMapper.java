package jill.generator.mapper;

import java.util.List;
import jill.generator.entity.RiskStrategy;
import jill.generator.entity.RiskStrategyExample;
import org.apache.ibatis.annotations.Param;

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