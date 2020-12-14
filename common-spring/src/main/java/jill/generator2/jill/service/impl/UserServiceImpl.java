package jill.generator2.jill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jill.generator2.jill.entity.User;
import jill.generator2.jill.mapper.UserMapper;
import jill.generator2.jill.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jill
 * @since 2020-12-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
