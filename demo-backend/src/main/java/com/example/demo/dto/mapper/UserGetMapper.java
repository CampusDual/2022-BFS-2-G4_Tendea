package com.example.demo.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.UserGetDTO;
import com.example.demo.entity.User;


@Mapper
public interface UserGetMapper {
	UserGetMapper INSTANCE = Mappers.getMapper( UserGetMapper.class );
	
	UserGetDTO userToUserGetDTO(User user);
	
	List<UserGetDTO> usertToUserGetDtoList(List<User> users);
	
	User userGetDTOtoUser(UserGetDTO userDTO);

}
