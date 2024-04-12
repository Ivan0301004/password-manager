package com.ivan.passwordmanager.controllers;

import com.ivan.passwordmanager.dto.GroupDto;
import com.ivan.passwordmanager.model.Group;
import com.ivan.passwordmanager.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/users/groups")
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        List<GroupDto> groups = this.groupService.getAllGroups();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(groups);
    }

    @PostMapping("users/{userId}/groups")
    public ResponseEntity<GroupDto> createGroupToUser(@RequestBody Group group, @PathVariable long userId) {
        GroupDto groupToAdd = this.groupService.createNewGroupToUser(group, userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(groupToAdd);
    }

    @DeleteMapping("/users/{userId}/groups/{groupId}")
    public ResponseEntity<Map<String, String>> removeGroupByIdFromUser(@PathVariable Long userId, @PathVariable Long groupId) {
        this.groupService.removeGroupById(userId, groupId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Successfully");

        return ResponseEntity.ok().body(response);
    }


    @PutMapping("/users/{userId}/groups/{groupId}")
    public ResponseEntity<Group> updateGroupById(@PathVariable long groupId, @PathVariable long userId,
                                                 @RequestBody GroupDto groupDto) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(this.groupService.updateGroup(groupId, userId, groupDto));
    }

}
