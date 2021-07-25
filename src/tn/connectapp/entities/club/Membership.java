package tn.connectapp.entities.club;

import java.sql.Date;

public class Membership {

	private Long membershipId;
	private Long postId;
	private Long memberId;
	private Long clubId;
	private String status;
	private Date membershipDate;
	private Long managerId;
        private String memberName;
        private String clubName;
        private String postName;
        private String managerName;

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostName() {
        return postName;
    }




    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getClubName() {
        return clubName;
    }

	public Membership() {
	}

    public Membership(Long membershipId, Long postId, Long memberId, Long clubId, String status, Date membershipDate, Long managerId, String memberName, String clubName,String postName,String managerName) {
        this.membershipId = membershipId;
        this.postId = postId;
        this.memberId = memberId;
        this.clubId = clubId;
        this.status = status;
        this.membershipDate = membershipDate;
        this.managerId = managerId;
        this.memberName = memberName;
        this.clubName = clubName;
        this.postName = postName;
        this.managerName = managerName;

        
    }



	public Long getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(Long membershipId) {
		this.membershipId = membershipId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getClubId() {
		return clubId;
	}

	public void setClubId(Long clubId) {
		this.clubId = clubId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getMembershipDate() {
		return membershipDate;
	}

	public void setMembershipDate(Date membershipDate) {
		this.membershipDate = membershipDate;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

    @Override
    public String toString() {
        return "Membership{" + "membershipId=" + membershipId + ", postId=" + postId + ", memberId=" + memberId + ", clubId=" + clubId + ", status=" + status + ", membershipDate=" + membershipDate + ", managerId=" + managerId + ", memberName=" + memberName + ", clubName=" + clubName + ", postName=" + postName + ", managerName=" + managerName + '}';
    }

}
