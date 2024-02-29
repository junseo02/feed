package com.study.feed.feed

class CommentParentVo(
    val commentId: String
  , val commentSeq: Int
  , val commentText: String
  , val userId: String
  , val userName: String
  , val createDate: String
  , var childList: ArrayList<CommentChildVo>
)

class CommentChildVo (
    val commentId: String
  , val commentChildId: String
  , val commentChildSeq: Int
  , val commentChildText: String
  , val userId: String
  , val userName: String
  , val createDate: String
)