package ru.job4j.html;

import java.time.LocalDateTime;
import java.util.Objects;

public class Post {
    public int id;
    public String title;
    public String link;
    public String description;
    public LocalDateTime created;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(title, post.title) && Objects.equals(link, post.link) && Objects.equals(description, post.description) && Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, link, description, created);
    }

    @Override
    public String toString() {
        return "Post{"
                + "title='" + title + '\''
                + ", link='" + link + '\''
                + ", description='" + description
                + '\'' + ", created=" + created + '}';
    }
}
