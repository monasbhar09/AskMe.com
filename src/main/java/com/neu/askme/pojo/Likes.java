package com.neu.askme.pojo;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Likes {

    public static final short LIKE_VALUE = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", nullable = false)
    protected User user;

    protected short value;

    public Likes(User user, short value) {
        this.user = user;
        this.value = value;
    }

    public Likes() {
	}

	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }
}
