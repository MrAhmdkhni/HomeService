package ir.maktab.service.impl;

import ir.maktab.base.service.impl.BaseServiceImpl;
import ir.maktab.entity.comment.Comment;
import ir.maktab.repository.CommentRepository;
import ir.maktab.service.CommentService;

public class CommentServiceImpl
        extends BaseServiceImpl<Comment, Long, CommentRepository>
        implements CommentService {

    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
    }
}
