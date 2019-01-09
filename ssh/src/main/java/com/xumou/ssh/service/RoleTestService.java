package com.xumou.ssh.service;

import com.xumou.ssh.entity.Role;
import com.xumou.ssh.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;

/**
 *
 */
@Transactional
@Service
public class RoleTestService {

    @Autowired
    private RoleRepository roleRepository;


    public Object selectSingleTable() {
        return roleRepository.findAll(new Specification<Role>() {
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate singleTableSelect =
                        query.where(criteriaBuilder.gt(root.get("id"), 33),
                                criteriaBuilder.le(root.get("id"), 35))
                        .groupBy(root.get("id"))
                        .orderBy(criteriaBuilder.asc(root.get("id")))
                        .getGroupRestriction();
                return singleTableSelect;
            }
        });
    }

    public Object insertRole(Role role) {
        return roleRepository.save(role);
    }

    public Object selectJoinMoreTable() {
        return roleRepository.findAll(new Specification<Role>() {
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                root.join("users", JoinType.LEFT).getOn();
                return query
                        .where(criteriaBuilder.gt(root.get("id"), 33),
                                criteriaBuilder.le(root.get("id"), 35))
                        .groupBy(Arrays.asList(root.get("id"),
                                root.get("name")))
                        .groupBy(root.get("id"))
                        .orderBy(Arrays.asList(criteriaBuilder.desc(root.get("id")),
                                criteriaBuilder.desc(root.get("name"))))
                        .orderBy(criteriaBuilder.desc(root.get("id")))
                        .getGroupRestriction();
            }
        });
    }
}