package com.wanger.secondhand.service.impl;

import com.wanger.secondhand.entity.User;
import com.wanger.secondhand.mapper.UserMapper;
import com.wanger.secondhand.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanger
 * @since 2020-12-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
