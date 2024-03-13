package com.sparta.newsfeed.repository;

import static com.sparta.newsfeed.entity.QUser.user;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.newsfeed.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryT {

    @Autowired
    private JPAQueryFactory queryFactory;

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return queryFactory.selectFrom(user)
            .where(eqEmail(email))
            .fetch()
            .stream()
            .findFirst();
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return queryFactory.selectFrom(user)
            .where(eqNickname(nickname))
            .fetch()
            .stream()
            .findFirst();
    }

    @Override
    public List<User> search(String nickname, String email) {
        return queryFactory
            .selectFrom(user)
            .where(eqNickname(nickname), containEmail(email))
            .fetch();
    }

    private Predicate containEmail(String email) {
        if (email == null || email.isEmpty()) {
            return null;
        }
        return user.email.containsIgnoreCase(email);
    }

    private BooleanExpression eqNickname(String nickname) {
        if (nickname == null || nickname.isEmpty()) {
            return null;
        }
        return user.nickname.eq(nickname);
    }
    private BooleanExpression eqEmail(String email) {
        if (email == null || email.isEmpty()) {
            return null;
        }
        return user.email.eq(email);
    }
}
