package com.web.springmvc.newsweb.service;

import com.web.springmvc.newsweb.dto.RoleDTO;
import com.web.springmvc.newsweb.exception.RoleNotFoundException;
import com.web.springmvc.newsweb.model.Role;
import com.web.springmvc.newsweb.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;


    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = roleRepository.save(mapToEntity(roleDTO));
        return roleDTO;
    }

    public void deleteRole(RoleDTO roleDTO) {
        roleRepository.delete(mapToEntity(roleDTO));
    }

    public RoleDTO getRoleById(Integer id) {
        return mapToDTO(roleRepository.findById(id).orElseThrow(()-> new RoleNotFoundException("Not found role")));
    }

    private Role mapToEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setCode(roleDTO.getCode());
        role.setName(roleDTO.getName());
        return role;
    }

    private RoleDTO mapToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setCode(role.getCode());
        roleDTO.setName(role.getName());
        roleDTO.setId(role.getId());
        return roleDTO;
    }
}
