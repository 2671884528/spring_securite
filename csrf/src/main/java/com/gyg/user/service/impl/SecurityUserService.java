package com.gyg.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gyg.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 郭永钢
 */
@Service("userDetailsService")
public class SecurityUserService implements UserDetailsService {
	@Autowired
	UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		QueryWrapper<com.gyg.user.entity.User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("number",s);
		com.gyg.user.entity.User user = userMapper.selectOne(queryWrapper);

		if (user==null) {
			throw new UsernameNotFoundException("账户不存在");
		}
		List<GrantedAuthority> list = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
		return new User(user.getNumber(), new BCryptPasswordEncoder().encode(user.getPassword()), list);
	}
}
