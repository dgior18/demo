package com.example.demo.group;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ClassService {

    private final static String GROUP_NOT_FOUND_MSG =
            "Group with ID number %d not found";

    private final ClassRepository groupRepository;

    private void groupExist(ClassRequest request, String errorMessage) {
        boolean groupExist = groupRepository
                .findByGroupId(request.getGroupId())
                .isPresent();

        if (groupExist) {
            log.error(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
    }

    private void groupNotExist(Long idNumber) {
        boolean groupExist = groupRepository
                .findByGroupId(idNumber)
                .isPresent();

        if (!groupExist) {
            log.error(String.format(GROUP_NOT_FOUND_MSG, idNumber));
            throw new IllegalStateException(String.format(GROUP_NOT_FOUND_MSG, idNumber));
        }
    }

    public String addGroup(ClassRequest request) {

        String errorMessage = String.format("Group with ID number %d already added.", request.getGroupId());

        groupExist(request, errorMessage);

        var group = new Class(request.getName(),
                request.getGroupId());

        groupRepository.save(group);

        return String.format("Group with ID number %d added successfully.", request.getGroupId());
    }

    public String deleteGroup(Long groupId) {

        groupNotExist(groupId);

        var group = groupRepository.findByGroupId(groupId).get();

        groupRepository.delete(group);

        /*TODO: saerto cxrilidan sadac iqneba romeli maswavlebeli da moswavle romel
            jgufshia iqidanac unda amoishalo
        */
        return "Group deleted successfully";
    }

    public Class findByGroupId(Long groupId) {

        groupNotExist(groupId);

        var group = groupRepository.findByGroupId(groupId).get();

        return group;
    }

    public List<Class> findByGroupName(String name) {

        var groups = groupRepository.findByName(name);

        if (groups.isEmpty()) {
            log.info("Group not found");
        }

        return groups;
    }

    public String editGroupName(String name, Long groupId) {

        var group = findByGroupId(groupId);
        group.setName(name);

        groupRepository.updateName(name, groupId);

        log.info("Edited group name. New name is " + name);
        return "Edited";
    }

    public String editGroupId(Long newId, Long groupId) {

        var group = findByGroupId(groupId);
        group.setGroupId(newId);

        groupRepository.updateGroupIdNumber(newId, groupId);

        log.info("Edited group ID number. New ID number is " + newId);
        return "Edited";
    }

}
