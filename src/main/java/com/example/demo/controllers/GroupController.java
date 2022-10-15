package com.example.demo.controllers;

import com.example.demo.group.Class;
import com.example.demo.group.ClassRequest;
import com.example.demo.group.ClassService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/")
@AllArgsConstructor
public class GroupController {

    private final ClassService groupService;

    @PostMapping("/add/group")
    public String addGroup(@RequestBody ClassRequest request) {
        return groupService.addGroup(request);
    }

    @PatchMapping("/edit/group/name")
    public String editGroupName(@RequestParam("name") String name,
                                @RequestParam("groupId") Long groupId) {
        return groupService.editGroupName(name, groupId);
    }

    @PatchMapping("/edit/group/groupId")
    public String editGroupIdNumber(@RequestParam("newId") Long newId,
                                    @RequestParam("groupId") Long groupId) {
        return groupService.editGroupId(newId, groupId);
    }

    @GetMapping("/find/group/groupId")
    public Class findByGroupId(@RequestParam("groupId") Long groupId) {
        return groupService.findByGroupId(groupId);
    }

    @GetMapping("/find/group/name")
    public List<Class> findByName(@RequestParam("name") String name) {
        return groupService.findByGroupName(name);
    }

    @DeleteMapping("/delete/group")
    public String deleteGroup(@RequestParam("groupId") Long groupId) {
        return groupService.deleteGroup(groupId);
    }

}
