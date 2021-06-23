package com.gyg.user.service.impl;

import com.gyg.user.entity.User;
import com.gyg.user.mapper.UserMapper;
import com.gyg.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gyg
 * @since 2021-06-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
