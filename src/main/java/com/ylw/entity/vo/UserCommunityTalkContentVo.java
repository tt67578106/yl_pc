package com.ylw.entity.vo;


/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

public class UserCommunityTalkContentVo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	private java.lang.Integer replyId;
	private String createTimeString;
	private java.lang.String content;
	private String replyHeadImage;
	private Integer replyUserId;
	private String replyUserName;
	
	public UserCommunityTalkContentVo(Integer replyId,
			String createTimeString,
			String content,
			String replyHeadImage,
			Integer replyUserId,
			String replyUserName) {
		super();
		this.replyId = replyId;
		this.createTimeString = createTimeString;
		this.content = content;
		this.replyHeadImage = replyHeadImage;
		this.replyUserId = replyUserId;
		this.replyUserName = replyUserName;
	}

	public java.lang.Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(java.lang.Integer replyId) {
		this.replyId = replyId;
	}

	public String getCreateTimeString() {
		return createTimeString;
	}

	public void setCreateTimeString(String createTimeString) {
		this.createTimeString = createTimeString;
	}

	public java.lang.String getContent() {
		return content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	public String getReplyHeadImage() {
		return replyHeadImage;
	}

	public void setReplyHeadImage(String replyHeadImage) {
		this.replyHeadImage = replyHeadImage;
	}

	public String getReplyUserName() {
		return replyUserName;
	}

	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}

	public Integer getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(Integer replyUserId) {
		this.replyUserId = replyUserId;
	}
	
}

