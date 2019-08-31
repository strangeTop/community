package com.community.dto;

import java.util.List;

public class TagDto {
    private String name;
    private List<String> tags;

    public TagDto(String name, List<String> tags) {
        this.name=name;
        this.tags=tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
