package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entity.comment.Comment;
import ir.maktab.repository.CommentRepository;
import jakarta.persistence.EntityManager;

public class CommentRepositoryImpl extends BaseRepositoryImpl<Comment, Long> implements CommentRepository {

    public CommentRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<Comment> getEntityClass() {
        return Comment.class;
    }
}
