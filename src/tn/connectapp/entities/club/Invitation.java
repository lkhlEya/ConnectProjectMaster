package tn.connectapp.entities.club;

import java.sql.Date;

public class Invitation {

	private Long invitationId;
	private String status;
	private Date creationDate;
	private Long clubId;
	private Long memberId;
	private Long postId;
	private String acceptanceFlag;
        private String memberName;
        private String clubName;

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberName() {
        return memberName;
    }

	public Invitation() {
	}

	public Invitation(Long invitationId, String status, Date creationDate, Long clubId, Long memberId, Long postId,
			String acceptanceFlag,String memberName,String clubName) 
	{
		this.invitationId = invitationId;
		this.status = status;
		this.creationDate = creationDate;
		this.clubId = clubId;
		this.memberId = memberId;
		this.postId = postId;
		this.acceptanceFlag = acceptanceFlag;
                this.memberName = memberName;
                this.clubName = clubName;

	}

	public Long getInvitationId() {
		return invitationId;
	}

	public void setInvitationId(Long invitationId) {
		this.invitationId = invitationId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getClubId() {
		return clubId;
	}

	public void setClubId(Long clubId) {
		this.clubId = clubId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getAcceptanceFlag() {
		return acceptanceFlag;
	}

	public void setAcceptanceFlag(String acceptanceFlag) {
		this.acceptanceFlag = acceptanceFlag;
	}

    @Override
    public String toString() {
        return "Invitation{" + "invitationId=" + invitationId + ", status=" + status + ", creationDate=" + creationDate + ", clubId=" + clubId + ", memberId=" + memberId + ", postId=" + postId + ", acceptanceFlag=" + acceptanceFlag + ", memberName=" + memberName + ", clubName=" + clubName + '}';
    }


        
}
