package seedu.mark.model.bookmark;

import static seedu.mark.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.mark.model.tag.Tag;

/**
 * Represents a Bookmark in Mark.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Bookmark {

    // Identity fields
    private final Name name;
    private final Url url;

    // Data fields
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Bookmark(Name name, Url url, Remark remark, Set<Tag> tags) {
        requireAllNonNull(name, url, remark, tags);
        this.name = name;
        this.url = url;
        this.remark = remark;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Url getUrl() {
        return url;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both bookmarks of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two bookmarks.
     */
    public boolean isSameBookmark(Bookmark otherBookmark) {
        if (otherBookmark == this) {
            return true;
        }

        return otherBookmark != null
                && otherBookmark.getName().equals(getName())
                && otherBookmark.getUrl().equals(getUrl());
    }

    /**
     * Returns true if both bookmarks have the same identity and data fields.
     * This defines a stronger notion of equality between two bookmarks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Bookmark)) {
            return false;
        }

        Bookmark otherBookmark = (Bookmark) other;
        return otherBookmark.getName().equals(getName())
                && otherBookmark.getUrl().equals(getUrl())
                && otherBookmark.getRemark().equals(getRemark())
                && otherBookmark.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, url, remark, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" URL: ")
                .append(getUrl())
                .append(" Remark: ")
                .append(getRemark())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
