package com.lyloou.flow.model.greeting;

public class GreetingVO {

    private final long id;
    private final String content;

    public GreetingVO(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
