package jill.generator2.jill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jill.generator2.jill.entity.RiskStrategy;
import jill.generator2.jill.mapper.RiskStrategyMapper;
import jill.generator2.jill.service.IRiskStrategyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 风险识别策略表 服务实现类
 * </p>
 *
 * @author jill
 * @since 2020-12-14
 */
@Service
public class RiskStrategyServiceImpl extends ServiceImpl<RiskStrategyMapper, RiskStrategy> implements IRiskStrategyService {

}
