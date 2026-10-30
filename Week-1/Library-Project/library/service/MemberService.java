package com.training.library.service;





import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.training.library.exceptions.LibraryDataException;
import com.training.library.exceptions.MemberNotFoundException;
import com.training.library.model.Member;
import com.training.library.repository.LibraryDataStore;


public class MemberService {

    private final List<Member> members = new ArrayList<>();
    private final LibraryDataStore dataStore;

    public MemberService(LibraryDataStore dataStore) {
        if (dataStore == null) {
            throw new IllegalArgumentException(
                    "Data store cannot be null");
        }

        this.dataStore = dataStore;

        try {
            members.addAll(dataStore.loadMembers());
        } catch (IOException e) {
            throw new LibraryDataException(
                    "Failed to load members", e);
        }
    }

    public void registerMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException(
                    "Member cannot be null");
        }

        if (findMemberById(member.getId()).isPresent()) {
            throw new IllegalArgumentException(
                    "Member ID already exists: " + member.getId());
        }

        members.add(member);
        saveMembers();
    }

    public Optional<Member> findMemberById(int id) {
        return members.stream()
                .filter(member -> member.getId() == id)
                .findFirst();
    }

    public void updateMember(int id, String name) {
        Member member = findMemberById(id)
                .orElseThrow(() -> new MemberNotFoundException(
                        "Member not found with ID: " + id));

        member.updateName(name);
        saveMembers();
    }

    public void deleteMember(int id) {
        Member member = findMemberById(id)
                .orElseThrow(() -> new MemberNotFoundException(
                        "Member not found with ID: " + id));

        member.setActive(false);
        saveMembers();
    }

    public boolean isValidMember(int id) {
        return findMemberById(id)
                .map(Member::isActive)
                .orElse(false);
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(members);
    }

    public List<Member> getActiveMembers() {
        return members.stream()
                .filter(Member::isActive)
                .toList();
    }

    private void saveMembers() {
        try {
            dataStore.saveMembers(members);
        } catch (IOException e) {
            throw new LibraryDataException(
                    "Failed to save members", e);
        }
    }
}
